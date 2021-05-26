/*
 * 
 */
package jpp.digraph.exceptions;

import jpp.digraph.graph.IEdge;
import jpp.digraph.graph.INode;

// TODO: Auto-generated Javadoc
/**
 * The Class InvalidEdgeException.
 */
public class InvalidEdgeException extends DiGraphException {
	
	/** The edge. */
	private IEdge<? extends INode> edge=null;
	
	/**
	 * Instantiates a new invalid edge exception.
	 *
	 * @param edge the edge
	 */
	public InvalidEdgeException(IEdge<? extends INode> edge){
		super();
		System.out.println("Edge is invaild: " + edge.getDescription());
		this.edge = edge;
		
	}
	
	/**
	 * Instantiates a new invalid edge exception.
	 *
	 * @param msg the msg
	 * @param edge the edge
	 */
	public InvalidEdgeException(String msg, IEdge<? extends INode> edge){
		super(msg);
		System.out.println("Edge is invaild: " + edge.getDescription() + "\n" + msg);
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
