/*
 * 
 */
package jpp.digraph.search;


import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;

import jpp.digraph.exceptions.NodeNotExistsException;
import jpp.digraph.graph.ICostEdge;
import jpp.digraph.graph.IDiGraph;
import jpp.digraph.graph.INode;


// TODO: Auto-generated Javadoc
/**
 * The Class AbstractAStar.
 *
 * @param <G> the generic type
 * @param <N> the number type
 * @param <E> the element type
 */
public abstract class AbstractAStar <G extends IDiGraph<N, E>, N extends INode, E extends ICostEdge<N>>  implements IDiGraphSearch<G, N, E>{
	
	/** The listener. */
	private List<IDiGraphSearchListener<N, E>> listener = new LinkedList<IDiGraphSearchListener<N, E>>();
	 
 	/**
 	 * H.
 	 *
 	 * @param node the node
 	 * @param target the target
 	 * @return the double
 	 */
 	public abstract double h(N node, N target);
	 
 	/** The graph. */
 	protected G graph;

		/* (non-Javadoc)
		 * @see jpp.digraph.search.IDiGraphSearch#search(jpp.digraph.graph.IDiGraph, jpp.digraph.graph.INode, jpp.digraph.graph.INode)
		 */
		@Override
		public List<E> search(G graph, N source, N target)	throws NodeNotExistsException {
			this.graph = graph;
			ArrayList<N> open = new ArrayList<N>();
			ArrayList<N> close = new ArrayList<N>();
			HashMap<N, Double> nodes_cost = new HashMap<N, Double>();
			HashMap<N, List<E>> node_wayTo = new HashMap<N, List<E>>();
			N min = source;
			
			if(!graph.containsNode(source) || !graph.containsNode(target))
				throw new NodeNotExistsException();

			open.add(min);
			updateCostMap(nodes_cost, min, 0.0);
			updateWayMap(node_wayTo, min,null);
			
		while(!open.isEmpty()){
			min = removeMin(nodes_cost, target , open);
			open.remove(min);
			

			
			for(IDiGraphSearchListener<N, E> liste :this.listener)
				liste.onExpandNode(min, node_wayTo.get(min));
			
			
			if(min==target){
				double cost = 0.0;
				for(E edge:node_wayTo.get(min))
					cost+=edge.getCost();
				System.out.println(source + "->" + target + " "+cost);
				return node_wayTo.get(min);
			}
			close.add(min);
			for(N suc: graph.getSuccessors(min)){
				if(!open.contains(suc)&&!close.contains(suc)){
					open.add(suc);
					double suc_cost = getBestEdge(min, suc).getCost() + nodes_cost.get(min);
					updateCostMap(nodes_cost, suc, suc_cost);
					updateWayMap(node_wayTo, suc,min);
					
				}else if(nodes_cost.get(min) + getBestEdge(min, suc).getCost() < nodes_cost.get(suc)){
					double suc_cost = getBestEdge(min, suc).getCost() + nodes_cost.get(min);
					updateCostMap(nodes_cost, suc, suc_cost);
					updateWayMap(node_wayTo, suc,min);
					if(close.contains(suc)){
						open.add(suc);
						close.remove(suc);
					}
				}
			}	
		}

			return null;
		}

		/* (non-Javadoc)
		 * @see jpp.digraph.search.IDiGraphSearch#addListener(jpp.digraph.search.IDiGraphSearchListener)
		 */
		@Override
		public void addListener(IDiGraphSearchListener<N, E> listener) {

				this.listener.add(listener);

		}

		/* (non-Javadoc)
		 * @see jpp.digraph.search.IDiGraphSearch#removeListener(jpp.digraph.search.IDiGraphSearchListener)
		 */
		@Override
		public void removeListener(IDiGraphSearchListener<N, E> listener) {
				this.listener.remove(listener);

		}

		/* (non-Javadoc)
		 * @see jpp.digraph.search.IDiGraphSearch#getListeners()
		 */
		@Override
		public Collection<IDiGraphSearchListener<N, E>> getListeners() {
			return this.listener;
		}
		
		/**
		 * Removes the min.
		 *
		 * @param node_cost the node_cost
		 * @param target the target
		 * @param open the open
		 * @return the n
		 */
		private N removeMin(HashMap<N, Double> node_cost, N target, ArrayList<N> open){

			
			N min = open.get(0);
			
			for(N node:open){
				if(node_cost.get(node) + h(node,target) < node_cost.get(min) + h(min,target))
					min = node;
			}
			
			return min;
			
		}
		
		/**
		 * Gets the best edge.
		 *
		 * @param source the source
		 * @param target the target
		 * @return the best edge
		 * @throws NodeNotExistsException the node not exists exception
		 */
		private E getBestEdge(N source, N target) throws NodeNotExistsException{
			List<E> temp_edges = new LinkedList<E>(graph.getEdgesBetween(source, target));
			if(temp_edges.isEmpty())
				return null;
			
			E bestEdge = temp_edges.get(0);
			for(E edge: temp_edges){
				if(edge.getCost() < bestEdge.getCost())
					bestEdge = edge;
			}
			
			return bestEdge;
		}
		
		/**
		 * Update cost map.
		 *
		 * @param node_cost the node_cost
		 * @param node the node
		 * @param cost the cost
		 */
		private void updateCostMap(HashMap<N, Double> node_cost, N node, double cost){
			if(node_cost.containsKey(node))
				return;
			node_cost.put(node,cost);
		}
		
		/**
		 * Update way map.
		 *
		 * @param node_wayTo the node_way to
		 * @param node the node
		 * @param precessor the precessor
		 * @throws NodeNotExistsException the node not exists exception
		 */
		
		
		private void updateWayMap(HashMap<N, List<E>> node_wayTo, N node, N precessor) throws NodeNotExistsException{
			if(node_wayTo.containsKey(node))
				return;
			
			List<E> way = new LinkedList<E>();
			if(node==null || precessor==null){
				node_wayTo.put(node, way);
				return;
			}
			E bestEdge = getBestEdge(precessor, node);	
			
			
			way.addAll(node_wayTo.get(precessor)); //Add way from source to precessor of this node
			way.add(bestEdge);						//Add way from precessor to this node
			
			node_wayTo.put(node, way);				//Link way-to-this-node -> this node   
			
		}


}
