/*
 * 
 */
package jpp.digraph.exceptions;


import jpp.digraph.graph.INode;

// TODO: Auto-generated Javadoc
/**
 * The Class NodeNotExistsException.
 */
public class NodeNotExistsException extends DiGraphException {
	
	/** The node. */
	private INode node = null;
	
	/**
	 * Instantiates a new node not exists exception.
	 */
	public NodeNotExistsException(){
		
	}
	
	
	/**
	 * Instantiates a new node not exists exception.
	 *
	 * @param node the node
	 */
	public NodeNotExistsException(INode node){
	
		System.out.println("Node doesn't exist: " + node.getDescription());
		this.node = node;
	}
	
	/**
	 * Instantiates a new node not exists exception.
	 *
	 * @param msg the msg
	 * @param node the node
	 */
	public NodeNotExistsException(String msg, INode node){
		super(msg);
		System.out.println("Node doesn't exist: " + node.getDescription() + "\n"+ msg);
		this.node = node;
		
	}
	
	/**
	 * Gets the node.
	 *
	 * @return the node
	 */
	public INode getNode(){
		return this.node;
		
	}

}
