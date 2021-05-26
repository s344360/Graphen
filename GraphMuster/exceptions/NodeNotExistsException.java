package jpp.digraph.exceptions;

import jpp.digraph.graph.INode;

public class NodeNotExistsException extends DiGraphException {
	INode node;

	public NodeNotExistsException(INode node) {
		this.node = node;
	}

	public NodeNotExistsException(String msg, INode node) {
		super(msg);
		this.node = node;
	}

	public INode getNode() {
		return node;

	}

}
