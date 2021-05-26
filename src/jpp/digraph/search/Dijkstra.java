/*
 * 
 */
package jpp.digraph.search;

import jpp.digraph.graph.ICostEdge;
import jpp.digraph.graph.IDiGraph;
import jpp.digraph.graph.INode;

// TODO: Auto-generated Javadoc
/**
 * The Class Dijkstra.
 *
 * @param <G> the generic type
 * @param <N> the number type
 * @param <E> the element type
 */
public class Dijkstra <G extends IDiGraph<N, E>, N extends INode, E extends ICostEdge<N>> extends AbstractAStar<G, N, E> {

	/* (non-Javadoc)
	 * @see jpp.digraph.search.AbstractAStar#h(jpp.digraph.graph.INode, jpp.digraph.graph.INode)
	 */
	@Override
	public double h(N node, N target) {
		return 0;
	}

}
