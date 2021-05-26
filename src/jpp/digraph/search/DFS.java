/*
 * 
 */
package jpp.digraph.search;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.Stack;
import jpp.digraph.exceptions.NodeNotExistsException;
import jpp.digraph.graph.ICostEdge;
import jpp.digraph.graph.IDiGraph;
import jpp.digraph.graph.INode;
import jpp.digraph.graph.XYNode;

// TODO: Auto-generated Javadoc
/**
 * The Class DFS.
 *
 * @param <G> the generic type
 * @param <N> the number type
 * @param <E> the element type
 */
public class DFS<G extends IDiGraph<N, E>, N extends INode, E extends ICostEdge<N>>
		implements IDiGraphSearch<G, N, E> {
	
	/** The listener. */
	private List<IDiGraphSearchListener<N, E>> listener = new LinkedList<IDiGraphSearchListener<N, E>>();
	
	/** The graph. */
	private G graph;
	
	/** The node_way to. */
	private HashMap<N, List<E>> node_wayTo;

	/* (non-Javadoc)
	 * @see jpp.digraph.search.IDiGraphSearch#search(jpp.digraph.graph.IDiGraph, jpp.digraph.graph.INode, jpp.digraph.graph.INode)
	 */
	@Override
	public List<E> search(G graph, N source, N target)
			throws NodeNotExistsException {
		
		
		if(!graph.containsNode(source)||!graph.containsNode(target)){
			throw new NodeNotExistsException();	
		}
		
		this.graph = graph;
		this.node_wayTo = new HashMap<N, List<E>>(); 
		
		ArrayList<N> visited_nodes = new ArrayList<N>();		
		Stack<N> stack = new Stack<N>();

		N node = source;

		stack.push(source);
		updateWayMap(node_wayTo, node,null);
		while(!stack.isEmpty()){
			node = stack.pop();

			//Update Listener
			for(IDiGraphSearchListener<N, E> liste :this.listener)
				liste.onExpandNode(node, node_wayTo.get(node));
			
			visited_nodes.add(node);

			if(node.equals(target)){
				return node_wayTo.get(node);
			}
			
			for(N suc : graph.getSuccessors(node)){
				if(!visited_nodes.contains(suc) && !stack.contains(suc)){
					stack.push(suc);
					updateWayMap(node_wayTo, suc,node);
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
