/*
 * 
 */
package jpp.digraph.gui;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.io.File;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;

import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JPanel;

import jpp.digraph.exceptions.NodeNotExistsException;
import jpp.digraph.graph.CostEdge;
import jpp.digraph.graph.DiGraph;
import jpp.digraph.graph.IDiGraph;
import jpp.digraph.graph.IEdge;
import jpp.digraph.graph.INode;
import jpp.digraph.graph.XYNode;
import jpp.digraph.search.BFS;
import jpp.digraph.search.DFS;
import jpp.digraph.search.Dijkstra;
import jpp.digraph.search.IDiGraphSearchListener;
import jpp.digraph.search.XYAStar;

// TODO: Auto-generated Javadoc
/**
 * The Class Controller.
 * 
 * @param <G>
 *            the generic type
 * @param <N>
 *            the number type
 * @param <E>
 *            the element type
 */
public class Controller<G extends IDiGraph<N, E>, N extends INode, E extends IEdge<N>>
		extends JFrame implements IDiGraphSearchListener<N, E>, ActionListener {

	// Variabeln-----------------------------------------------------------------------------
	/** The model. */
	private Model model;

	/** The view. */
	private View<G, N, E> view;

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

	/** The t. */
	private javax.swing.Timer t;

	/** The period_ms. */
	private int period_ms = 0;

	// -------------------------------------------------------------------------------------

	/**
	 * Instantiates a new controller.
	 */
	public Controller() {
		this.model = new Model();
		this.view = new View<G, N, E>();
		this.view.setVisible(true);

		view.setListener(this, this, this);
	}

	/**
	 * Gets the path.
	 * 
	 * @return the path
	 */
	private String getPath() {

		JFileChooser chooser = new JFileChooser();
		chooser.setCurrentDirectory(new File("."));
		chooser.setFileSelectionMode(JFileChooser.FILES_ONLY);
		chooser.showOpenDialog(null);
		File selectedPfile = chooser.getSelectedFile();

		return selectedPfile.getPath();
	}

	/**
	 * Gets the node for string.
	 * 
	 * @param node
	 *            the node
	 * @return the node for string
	 */
	private N getNodeForString(String node) {
		List<XYNode> tmp_nodes = new LinkedList<XYNode>(graph.getNodes());

		for (XYNode tmp_node : tmp_nodes) {
			if (tmp_node.getDescription().equals(node))
				return (N) tmp_node;
		}

		return null;
	}

	/**
	 * Start search.
	 * 
	 * @param algo
	 *            the algo
	 * @throws NodeNotExistsException
	 *             the node not exists exception
	 */
	private void startSearch(String algo) throws NodeNotExistsException {
		System.out.println("Time: " + period_ms);

		t = new javax.swing.Timer(period_ms, new ActionListener()

		{
			public void actionPerformed(ActionEvent e) {
				if (visit_nodes.isEmpty()) {
					System.out.println("Finshed!");
					t.stop();
					// EnableButtons
					view.getLoad().setEnabled(true);
					view.getStart().setEnabled(true);
					// DisableButtons
					view.getStop().setEnabled(false);
					return;
				}
				// DisableButtons
				view.getLoad().setEnabled(false);
				view.getStart().setEnabled(false);

				N actual_node = visit_nodes.get(0);
				view.updateGraph(nodes_ways.get(actual_node), source, target,
						actual_node);
				visit_nodes.remove(0);

			}
		});
		List<E> emptyWay = new LinkedList<E>();
		view.updateGraph(emptyWay, source, target, null);
		if (algo.equals("BFS")) {
			BFS bfs = new BFS();
			bfs.addListener(this);
			bfs.search(graph, source, target);
		}

		if (algo.equals("DFS")) {
			DFS dfs = new DFS();
			dfs.addListener(this);
			dfs.search(graph, source, target);
		}

		if (algo.equals("Dijkstra")) {
			Dijkstra di = new Dijkstra();
			di.addListener(this);
			di.search(graph, source, target);
		}

		if (algo.equals("A-Stern")) {
			XYAStar star = new XYAStar();
			star.addListener(this);
			star.search(graph, source, target);
		}

	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * jpp.digraph.search.IDiGraphSearchListener#onExpandNode(jpp.digraph.graph
	 * .INode, java.util.List)
	 */
	@Override
	public void onExpandNode(N node, List<E> way) {
		visit_nodes.add(node);
		nodes_ways.put(node, way);
		if (node == target) {
			view.updateGraph(nodes_ways.get(visit_nodes.get(0)), source,
					target, visit_nodes.get(0));
			visit_nodes.remove(0);
			t.start();
			view.getStop().setEnabled(true);
		}

	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * java.awt.event.ActionListener#actionPerformed(java.awt.event.ActionEvent)
	 */
	@Override
	public void actionPerformed(ActionEvent e) {
		// LOAD BUTTON
		if (e.getActionCommand() == view.getLoad().getActionCommand()) {
			DiGraph<XYNode, CostEdge<XYNode>> tmp_graph = model.getGraph(getPath());
			if (tmp_graph.getNodeCount() != 0) {
				
				this.graph = tmp_graph;
				this.view.paintNewGraph(this.graph);
				// EnableButtons
				view.getStart().setEnabled(true);
				return;
				
			}
			System.out.println("Graph ungültig");
		}

		// START BUTTON
		if (e.getActionCommand() == view.getStart().getActionCommand()) {
			String algo = (String) view.getBox_algo().getSelectedItem();
			period_ms = view.getSli_time().getValue();
			source = getNodeForString((String) view.getBox_source()
					.getSelectedItem());
			target = getNodeForString((String) view.getBox_target()
					.getSelectedItem());
			try {
				startSearch(algo);
			} catch (NodeNotExistsException e1) {
				e1.printStackTrace();
			}

		}

		// STOP BUTTON
		if (e.getActionCommand() == view.getStop().getActionCommand()) {
			visit_nodes.clear();
			// EnableButtons
			view.getLoad().setEnabled(true);
			view.getStart().setEnabled(true);
			// DisableButtons
			view.getStop().setEnabled(false);
			t.stop();
		}

	}
}
