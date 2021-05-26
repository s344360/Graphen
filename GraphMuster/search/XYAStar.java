package jpp.digraph.search;

import java.util.Collection;
import java.util.List;

import jpp.digraph.exceptions.NodeNotExistsException;
import jpp.digraph.graph.ICostEdge;

import jpp.digraph.graph.IDiGraph;
import jpp.digraph.graph.IXYNode;

public class XYAStar<G extends IDiGraph<N, E>, N extends IXYNode, E extends ICostEdge<N>>
		extends AbstractAStar<G, N, E> {

	@Override
	public double h(N node, N target) {
		double diffx = (target.getX() - node.getX());
		double diffy = (target.getY() - node.getY());
		double diffxSquare = Math.pow(diffx, 2);
		double diffySquare = Math.pow(diffy, 2);
		return (Math.abs(Math.sqrt(diffxSquare + diffySquare)));

	}

}
