/*
 * 
 */
package jpp.digraph.search;

import java.util.List;

import jpp.digraph.graph.CostEdge;
import jpp.digraph.graph.IEdge;
import jpp.digraph.graph.INode;
import jpp.digraph.graph.XYNode;

// TODO: Auto-generated Javadoc
/**
 * The listener interface for receiving my events.
 * The class that is interested in processing a my
 * event implements this interface, and the object created
 * with that class is registered with a component using the
 * component's <code>addMyListener<code> method. When
 * the my event occurs, that object's appropriate
 * method is invoked.
 *
 * @param <N> the number type
 * @param <E> the element type
 * @see MyEvent
 */
public class MyListener<N extends INode, E extends IEdge<N>> implements IDiGraphSearchListener<N, E> {
	
	/** The node. */
	private XYNode node;
	
	/** The edge. */
	private CostEdge<XYNode> edge;
	
	/**
	 * Instantiates a new my listener.
	 *
	 * @param node the node
	 */
	public MyListener(N node){
		this.node=(XYNode)node;
		
		
	}

	/* (non-Javadoc)
	 * @see jpp.digraph.search.IDiGraphSearchListener#onExpandNode(jpp.digraph.graph.INode, java.util.List)
	 */
	@Override
	public void onExpandNode(N node, List<E> way) {
		// TODO Auto-generated method stub
		
	}

	 


}
