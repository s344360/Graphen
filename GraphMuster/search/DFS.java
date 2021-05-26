package jpp.digraph.search;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.Stack;

import jpp.digraph.exceptions.NodeNotExistsException;
import jpp.digraph.graph.ICostEdge;
import jpp.digraph.graph.IDiGraph;
import jpp.digraph.graph.INode;

public class DFS<G extends IDiGraph<N, E>, N extends INode, E extends ICostEdge<N>> implements IDiGraphSearch<G, N, E> {
	private HashMap<N, List<E>> edgList;
	private G graph;
	private List<IDiGraphSearchListener<N, E>> listeners = new ArrayList<IDiGraphSearchListener<N, E>>();

	public DFS() {

	}

	@Override
	public List<E> search(G graph, N source, N target) throws NodeNotExistsException {
		if (!graph.getNodes().contains(source)) {
			throw new NodeNotExistsException(source);
		}

		if (!graph.getNodes().contains(target)) {
			throw new NodeNotExistsException(target);
		}
		Stack<N> stack = new Stack<N>();
		this.graph = graph;
		edgList = new HashMap<N, List<E>>();
		HashSet<N> nodList = new HashSet<N>();
		N node = source;
		stack.add(source);
		edgList.put(node, new LinkedList<E>());
		while (!stack.isEmpty()) {
			node = stack.pop();
			nodList.add(node);
			for (IDiGraphSearchListener lis : listeners)
				lis.onExpandNode(node, listeners);
			if (node.equals(target)) {
				return edgList.get(node);
			}
			HashSet<N> sucList = (HashSet<N>) graph.getSuccessors(node);
			for (N no : sucList) {
				if (!nodList.contains(no) && !stack.contains(no)) {
					stack.add(no);
					List<E> edgBetween = new LinkedList<E>(graph.getEdgesBetween(node, no));
					List<E> res = new LinkedList<E>();
					E bestEdge;

					if (edgBetween.isEmpty()) {
						bestEdge = null;
					} else {
						bestEdge = edgBetween.get(0);
					}
					res.addAll(edgList.get(node));

					res.add(bestEdge);
					edgList.put(no, res);
				}
			}
			sucList.clear();
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
