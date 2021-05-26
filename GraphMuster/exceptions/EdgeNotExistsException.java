package jpp.digraph.exceptions;

import jpp.digraph.graph.IEdge;
import jpp.digraph.graph.INode;

public class EdgeNotExistsException extends DiGraphException {

	IEdge<? extends INode> edge;
	
	public EdgeNotExistsException(IEdge<? extends INode> edge) {
		this.edge = edge;

	}

	public EdgeNotExistsException(String msg, IEdge<? extends INode> edge) {
		super(msg);
		this.edge = edge;

	}

	public IEdge<? extends INode> getEdge() {
		return edge;

	}

}
