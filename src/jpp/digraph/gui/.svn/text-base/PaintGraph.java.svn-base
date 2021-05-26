/*
 * 
 */
package jpp.digraph.gui;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;

import javax.swing.JFrame;
import javax.swing.JPanel;

import jpp.digraph.graph.IDiGraph;
import jpp.digraph.graph.IEdge;
import jpp.digraph.graph.INode;
import jpp.digraph.graph.XYNode;
import jpp.digraph.search.IDiGraphSearchListener;

// TODO: Auto-generated Javadoc
/**
 * The Class PaintGraph.
 *
 * @param <G> the generic type
 * @param <N> the number type
 * @param <E> the element type
 */
public class PaintGraph<G extends IDiGraph<N, E>, N extends INode, E extends IEdge<N>>
		extends JPanel {
	
	/** The graph. */
	private G graph;
	
	/** The nodes. */
	private List<N> nodes = new LinkedList<N>();
	
	/** The edges. */
	private List<E> edges = new LinkedList<E>();
	
	
	
	/** The p_ red edges. */
	private List<PaintEdge> p_RedEdges = new LinkedList<PaintEdge>();
	
	

	/** The source. */
	private N source;
	
	/** The target. */
	private N target;
	
	/** The actual_node. */
	private N actual_node;

	/** The way. */
	private List<E> way = new LinkedList<E>();
	
	/** The counter. */
	private int counter = 0;
	
	/** The node_to_p node. */
	private HashMap<N, PaintNode> node_to_pNode = new HashMap<N, PaintNode>();

	/**
	 * Instantiates a new paint graph.
	 */
	public PaintGraph() {

	}

	/**
	 * Instantiates a new paint graph.
	 *
	 * @param graph the graph
	 */
	public PaintGraph(G graph) {
		this.graph = graph;
		nodes.addAll(graph.getNodes());
		edges.addAll(graph.getEdges());

		System.out.println(graph);

	}

	/* (non-Javadoc)
	 * @see javax.swing.JComponent#paintComponent(java.awt.Graphics)
	 */
	@Override
	protected void paintComponent(Graphics g) {
		g.clearRect(0, 0, getWidth(), getHeight());
		super.paintComponent(g);
		
		if (this.graph == null)
			return;

		int maxX = 0;
		int maxY = 0;
		int minX = 0;
		int minY = 0;
		for (N node : nodes) {
			if (((XYNode) node).getX() > maxX)
				maxX = ((XYNode) node).getX();
			if (((XYNode) node).getY() > maxY)
				maxY = ((XYNode) node).getY();
			if (((XYNode) node).getX() < minX)
				minX = ((XYNode) node).getX();
			if (((XYNode) node).getY() < minY)
				minY = ((XYNode) node).getY();

		}

		int stepsX = (getWidth() - 35) / Math.abs(maxX - minX);
		int stepsY = (getHeight() - 35) / Math.abs(maxY - minY);

		for (N node : nodes) {
			int x = ((XYNode) node).getX() * stepsX + Math.abs(minX) * stepsX;
			int y = ((XYNode) node).getY() * stepsY + Math.abs(minY) * stepsY;

			if (node == source || node == target) {
				PaintNode p_node = new PaintNode(x, y, node.getDescription(),
						Color.red);
				node_to_pNode.put(node, p_node);

			} else if(node == actual_node){
				PaintNode p_node = new PaintNode(x, y, node.getDescription(),
						Color.green);
				node_to_pNode.put(node, p_node);
			
			}else{
				PaintNode p_node = new PaintNode(x, y, node.getDescription(),
						Color.blue);
				node_to_pNode.put(node, p_node);
				
			}
		}
		p_RedEdges.clear();
		for (E edge : edges) {
			PaintEdge p_edge=null;
			if (!way.contains(edge)) {
				p_edge = new PaintEdge(node_to_pNode.get(edge
						.getSource()), node_to_pNode.get(edge.getTarget()),
						Color.LIGHT_GRAY);
				
			} else {

				
				p_edge= new PaintEdge(node_to_pNode.get(edge
						.getSource()), node_to_pNode.get(edge.getTarget()),
						Color.red);
				p_RedEdges.add(p_edge);
				
			}
			
			p_edge.paintComponent(g);
			
			
		}
		for(PaintEdge p : p_RedEdges)
			p.paintComponent(g);
		
		for(N node : nodes)
			node_to_pNode.get(node).paintComponent(g);

	}

	/**
	 * Sets the way.
	 *
	 * @param way the new way
	 */
	public void setWay(List<E> way) {
		this.way.clear();
		this.way.addAll(way);
	
	}

	/**
	 * Sets the source.
	 *
	 * @param source the new source
	 */
	public void setSource(N source) {
		this.source = source;
	}

	/**
	 * Sets the target.
	 *
	 * @param target the new target
	 */
	public void setTarget(N target) {
		this.target = target;
	}


	/**
	 * Sets the actual_node.
	 *
	 * @param actual_node the new actual_node
	 */
	public void setActual_node(N actual_node) {
		this.actual_node = actual_node;
	}
}
