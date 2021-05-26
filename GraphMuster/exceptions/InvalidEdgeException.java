package jpp.digraph.exceptions;

import jpp.digraph.graph.IEdge;
import jpp.digraph.graph.INode;

public class InvalidEdgeException extends DiGraphException {
	IEdge edge;

	public InvalidEdgeException(IEdge<? extends INode> edge) {
		this.edge = edge;
	}

	public InvalidEdgeException(String msg, IEdge<? extends INode> edge) {
		super(msg);
		this.edge = edge;
	}

	public IEdge<? extends INode> getEdge() {
		return edge;

	}

}
