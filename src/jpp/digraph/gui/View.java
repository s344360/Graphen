/*
 * 
 */
package jpp.digraph.gui;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.EventQueue;

import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JSlider;
import javax.swing.JSplitPane;
import javax.swing.border.EmptyBorder;
import javax.xml.parsers.ParserConfigurationException;

import org.xml.sax.SAXException;

import java.awt.GridLayout;q
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;

import jpp.digraph.exceptions.InvalidEdgeException;
import jpp.digraph.exceptions.NodeNotExistsException;
import jpp.digraph.graph.CostEdge;
import jpp.digraph.graph.DiGraph;
import jpp.digraph.graph.IDiGraph;
import jpp.digraph.graph.IEdge;
import jpp.digraph.graph.INode;
import jpp.digraph.graph.XYNode;
import jpp.digraph.io.XYGXLSupport;
import jpp.digraph.search.Dijkstra;
import jpp.digraph.search.IDiGraphSearchListener;

import javax.swing.JButton;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

// TODO: Auto-generated Javadoc
/**
 * The Class View.
 *
 * @param <G> the generic type
 * @param <N> the number type
 * @param <E> the element type
 */
public class View<G extends IDiGraph<N, E>, N extends INode, E extends IEdge<N>>
		extends JFrame{

	/** The content pane. */
	private JPanel contentPane;
	
	/** The ctr p. */
	private ControllerPanel ctrP;
	
	/** The splitpane. */
	private JSplitPane splitpane;
	
	/** The p_graph. */
	private PaintGraph p_graph;
	
	/** The di. */
	private Dijkstra di;
	
	/** The graph. */
	private DiGraph<XYNode, CostEdge<XYNode>> graph;
	
	/** The source. */
	private N source;
	
	/** The target. */
	private N target;

	/** The nodes_ways. */
	private HashMap<N, List<E>> nodes_ways = new HashMap<N, List<E>>(); 
	
	/** The visit_nodes. */
	private List<N> visit_nodes = new LinkedList<N>();
	
	/** The counter. */
	private int counter=0;
	
	/** The t. */
	private javax.swing.Timer t;



	/**
	 * Create the frame.
	 *
	 */
	public View(){
		setTitle("W A Y F I N D E R");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(new GridLayout());


	
	
		p_graph = new PaintGraph();
		p_graph.setBackground(Color.white);
		ctrP = new ControllerPanel();

		
		splitpane = new JSplitPane(JSplitPane.HORIZONTAL_SPLIT);
		splitpane.setContinuousLayout(true);
		splitpane.setLeftComponent(ctrP);
		splitpane.setRightComponent(p_graph);
		splitpane.setDividerLocation(getWidth()/4);
		
		contentPane.add(splitpane);
		

		

	}



	/**
	 * Paint new graph.
	 *
	 * @param graph the graph
	 */
	public void paintNewGraph(DiGraph<XYNode, CostEdge<XYNode>> graph){
		if(graph==null){
			JPanel empty_panel = new JPanel();
			empty_panel.setBackground(Color.white);
			splitpane.setRightComponent(empty_panel);
			splitpane.setDividerLocation(getWidth()/4);
			empty_panel.repaint();
			return;
		}
		
		this.p_graph = new PaintGraph(graph);
		this.p_graph.setBackground(Color.white);
		
		ctrP.getBox_source().removeAllItems();
		ctrP.getBox_target().removeAllItems();
		
		List<XYNode> tmp_nodes = new LinkedList<XYNode>(graph.getNodes());
		
		for(XYNode node: tmp_nodes ){
			ctrP.getBox_source().addItem(node.getDescription());
			ctrP.getBox_target().addItem(node.getDescription());
		}
		splitpane.setRightComponent(p_graph);
		splitpane.setDividerLocation(getWidth()/4);
		this.p_graph.repaint();
	}
	
	/**
	 * Update graph.
	 *
	 * @param way the way
	 * @param source the source
	 * @param target the target
	 * @param actual_node the actual_node
	 */
	public void updateGraph(List<E> way, N source, N target , N actual_node){
		
		
		if(way != null && source!=null && target!=null){
		this.p_graph.setWay(way);
		this.p_graph.setSource(source);
		this.p_graph.setTarget(target);
		this.p_graph.setActual_node(actual_node);
		}

		this.p_graph.repaint();
		
	}

	/**
	 * Gets the lbl algo.
	 *
	 * @return the lbl algo
	 */
	public JLabel getLblAlgo() {
		return this.ctrP.getLblAlgo();
	}


	/**
	 * Gets the lbl source.
	 *
	 * @return the lbl source
	 */
	public JLabel getLblSource() {
		return this.ctrP.getLblSource();
	}


	/**
	 * Gets the lbl target.
	 *
	 * @return the lbl target
	 */
	public JLabel getLblTarget() {
		return this.ctrP.getLblTarget();
	}


	/**
	 * Gets the box_algo.
	 *
	 * @return the box_algo
	 */
	public JComboBox<String> getBox_algo() {
		return this.ctrP.getBox_algo();
	}


	/**
	 * Gets the box_source.
	 *
	 * @return the box_source
	 */
	public JComboBox<String> getBox_source() {
		return this.ctrP.getBox_source();
	}


	/**
	 * Gets the box_target.
	 *
	 * @return the box_target
	 */
	public JComboBox<String> getBox_target() {
		return this.ctrP.getBox_target();
	}


	/**
	 * Gets the sli_time.
	 *
	 * @return the sli_time
	 */
	public JSlider getSli_time() {
		return this.ctrP.getSli_time();
	}


	/**
	 * Gets the load.
	 *
	 * @return the load
	 */
	public JButton getLoad() {
		return this.ctrP.getLoad();
	}


	/**
	 * Gets the start.
	 *
	 * @return the start
	 */
	public JButton getStart() {
		return this.ctrP.getStart();
	}


	/**
	 * Gets the stop.
	 *
	 * @return the stop
	 */
	public JButton getStop() {
		return this.ctrP.getStop();
	}
	
	/**
	 * Sets the listener.
	 *
	 * @param loadListener the load listener
	 * @param startListener the start listener
	 * @param stopListener the stop listener
	 */
	public void setListener(ActionListener loadListener, ActionListener startListener,ActionListener stopListener){
		ctrP.getLoad().addActionListener(loadListener);
		ctrP.getStart().addActionListener(startListener);
		ctrP.getStop().addActionListener(stopListener);	
	}



	/**
	 * Gets the splitpane.
	 *
	 * @return the splitpane
	 */
	public JSplitPane getSplitpane() {
		return splitpane;
	}
}
