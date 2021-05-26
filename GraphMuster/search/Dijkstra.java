package jpp.digraph.search;

import java.util.Collection;
import java.util.List;

import jpp.digraph.exceptions.NodeNotExistsException;
import jpp.digraph.graph.ICostEdge;
import jpp.digraph.graph.IDiGraph;
import jpp.digraph.graph.INode;

public class Dijkstra<G extends IDiGraph<N, E>, N extends INode, E extends ICostEdge<N>>
		extends AbstractAStar<G, N, E> {

	@Override
	public double h(N node, N target) {
		return 0;
	}


}

//if (!graph.getNodes().contains(source)) {
		// throw new NodeNotExistsException(source);
		// }
		//
		// if (!graph.getNodes().contains(target)) {
		// throw new NodeNotExistsException(target);
		// }
		// this.graph = graph;
		// edgList = new HashMap<N, List<E>>();
		// edgCost = new HashMap<N, Double>();
		// TreeSet<N> open = new TreeSet<N>();
		// TreeSet<N> close = new TreeSet<N>();
		// open.add(source);
		// edgCost.put(source, 0.0);
		// N min = source;
		// edgList.put(min, new LinkedList<E>());
		// while (!open.isEmpty()) {
		// min = open.first();
		// open.remove(min);
		//
		// for (IDiGraphSearchListener lis : listeners)
		// lis.onExpandNode(min, listeners);
		//
		// if (min.equals(target)) {
		// return edgList.get(min);
		// }
		// HashSet<N> sucList = (HashSet<N>) graph.getSuccessors(node);
		// for (N no : sucList) {
		// if (!nodList.contains(no) && !queue.contains(no)) {
		// queue.add(no);
		// List<E> edgBetween = new LinkedList<E>(graph.getEdgesBetween(node,
		// no));
		// List<E> res = new LinkedList<E>();
		// E bestEdge;
		//
		// if (edgBetween.isEmpty()) {
		// bestEdge = null;
		// } else {
		// bestEdge = edgBetween.get(0);
		// }
		// res.addAll(edgList.get(node));
		//
		// res.add(bestEdge);
		// edgList.put(no, res);
		// }
		// }
		// sucList.clear();
		// }
