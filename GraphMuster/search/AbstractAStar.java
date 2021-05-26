package jpp.digraph.search;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.TreeSet;

import jpp.digraph.exceptions.NodeNotExistsException;
import jpp.digraph.graph.ICostEdge;
import jpp.digraph.graph.IDiGraph;
import jpp.digraph.graph.INode;

public abstract class AbstractAStar<G extends IDiGraph<N, E>, N extends INode, E extends ICostEdge<N>>
		implements IDiGraphSearch<G, N, E> {

	private List<IDiGraphSearchListener<N, E>> listeners = new ArrayList<IDiGraphSearchListener<N, E>>();
	private HashMap<N, Double> edgCost;
	private HashMap<N, List<E>> edgList;
	private G graph;

	public AbstractAStar() {

	}

	public abstract double h(N node, N target);

	@Override
	public List<E> search(G graph, N source, N target) throws NodeNotExistsException {

		if (!graph.getNodes().contains(source)) {
			throw new NodeNotExistsException(source);
		}

		if (!graph.getNodes().contains(target)) {
			throw new NodeNotExistsException(target);
		}

		this.graph = graph;
		HashSet<N> open = new HashSet<N>();
		HashSet<N> close = new HashSet<N>();
		HashMap<N, Double> edgCost = new HashMap<N, Double>();
		HashMap<N, List<E>> edgList = new HashMap<N, List<E>>();
		N min = source;
		open.add(min);
		edgCost.put(min, 0.0);
		edgList.put(min, new LinkedList<E>());
		while (!open.isEmpty()) {
			min = open.iterator().next();
			for (N node : open) {
				if (edgCost.get(node) + h(node, target) < edgCost.get(min) + h(min, target))
					min = node;
			}
			open.remove(min);

			for (IDiGraphSearchListener<N, E> liste : this.listeners)
				liste.onExpandNode(min, edgList.get(min));

			if (min.equals(target)) {

				return edgList.get(min);
			}
			close.add(min);
			for (N suc : graph.getSuccessors(min)) {
				List<E> way = new LinkedList<E>();
				List<E> edgBetween = new LinkedList<E>(graph.getEdgesBetween(min, suc));
				E bestEdge;
				if (edgBetween.isEmpty()) {
					bestEdge = null;
				} else {
					bestEdge = edgBetween.get(0);
				}

				if (!open.contains(suc) && !close.contains(suc)) {
					open.add(suc);
					double suc_cost = bestEdge.getCost() + edgCost.get(min);
					edgCost.put(suc, suc_cost);
					way.addAll(edgList.get(min));
					way.add(bestEdge);
					edgList.put(suc, way);

				} else if (edgCost.get(min) + bestEdge.getCost() < edgCost.get(suc)) {
					double suc_cost = bestEdge.getCost() + edgCost.get(min);
					edgCost.put(suc, suc_cost);
					way.addAll(edgList.get(min));
					way.add(bestEdge);
					edgList.put(suc, way);
					if (close.contains(suc)) {
						open.add(suc);
						close.remove(suc);
					}
				}
			}
		}
		return null;
	}

	@Override
	public void addListener(IDiGraphSearchListener<N, E> listener) {
		listeners.add(listener);
	}

	@Override
	public void removeListener(IDiGraphSearchListener<N, E> listener) {
		listeners.remove(listener);
	}

	@Override
	public Collection<IDiGraphSearchListener<N, E>> getListeners() {
		return listeners;
	}

}
