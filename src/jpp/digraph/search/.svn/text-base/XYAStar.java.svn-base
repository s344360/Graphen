/*
 * 
 */
package jpp.digraph.search;

import jpp.digraph.graph.ICostEdge;
import jpp.digraph.graph.IDiGraph;
import jpp.digraph.graph.IXYNode;

// TODO: Auto-generated Javadoc
/**
 * The Class XYAStar.
 *
 * @param <G> the generic type
 * @param <N> the number type
 * @param <E> the element type
 */
public class XYAStar <G extends IDiGraph<N, E>, N extends IXYNode, E extends ICostEdge<N>> extends AbstractAStar<G, N, E> {
	

	/* (non-Javadoc)
	 * @see jpp.digraph.search.AbstractAStar#h(jpp.digraph.graph.INode, jpp.digraph.graph.INode)
	 */
	@Override
	public double h(N node, N target) {
		return (double)Math.abs(Math.sqrt((node.getX()-target.getX())*(node.getX()-target.getX()) + (node.getY()-target.getY())*(node.getY()-target.getY())));
	}

}
