/*
 * 
 */
package jpp.digraph.exceptions;

import jpp.digraph.graph.IEdge;
import jpp.digraph.graph.INode;

// TODO: Auto-generated Javadoc
/**
 * The Class EdgeNotExistsException.
 */
public class EdgeNotExistsException extends DiGraphException {
	
	/** The edge. */
	private IEdge<? extends INode> edge=null;
	
	
	/**
	 * Instantiates a new edge not exists exception.
	 *
	 * @param edge the edge
	 */
	public EdgeNotExistsException(IEdge<? extends INode> edge){
		super();
		System.out.println("Edge doesn't exist: " + edge.getDescription());
		this.edge = edge;
		
	}
	
	/**
	 * Instantiates a new edge not exists exception.
	 *
	 * @param msg the msg
	 * @param edge the edge
	 */
	public EdgeNotExistsException(String msg, IEdge<? extends INode> edge){
		super(msg);
		System.out.println("Edge doesn't exist: " + edge.getDescription() + "\n"+ msg);
		this.edge = edge;
		
	}
	
	/**
	 * Gets the edge.
	 *
	 * @return the edge
	 */
	public IEdge<? extends INode> getEdge(){
		return this.edge;
		
	}
	
	


}
