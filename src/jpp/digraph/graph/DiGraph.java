/*
 * 
 */
package jpp.digraph.graph;

import java.util.ArrayList;
import java.util.Collection;
import jpp.digraph.exceptions.EdgeNotExistsException;
import jpp.digraph.exceptions.InvalidEdgeException;
import jpp.digraph.exceptions.NodeNotExistsException;

// TODO: Auto-generated Javadoc
/**
 * The Class DiGraph.
 *
 * @param <N> the number type
 * @param <E> the element type
 */
public class DiGraph<N extends INode, E extends IEdge<N>> implements
		IDiGraph<N, E> {

	/** The nodes. */
	private Collection<N> nodes;
	
	/** The edges. */
	private Collection<E> edges;

	/** The node count. */
	private int nodeCount = 0;
	
	/** The edge count. */
	private int edgeCount = 0;

	/**
	 * Instantiates a new di graph.
	 */
	public DiGraph() {
		this.nodes = new ArrayList<N>();
		this.edges = new ArrayList<E>();

	}

	// Erzeugt einen leeren Graphen.
	/**
	 * Instantiates a new di graph.
	 *
	 * @param nodes the nodes
	 */
	public DiGraph(Collection<N> nodes) {

		this.nodes = new ArrayList<N>(nodes);
		this.edges = new ArrayList<E>();

		this.nodeCount = nodes.size();

	}

	// Erzeugt einen Graphen mit den übergebenen Knoten.
	/**
	 * Instantiates a new di graph.
	 *
	 * @param nodes the nodes
	 * @param edges the edges
	 * @throws InvalidEdgeException the invalid edge exception
	 */
	public DiGraph(Collection<N> nodes, Collection<E> edges)
			throws InvalidEdgeException {

		this.nodes = new ArrayList<N>(nodes);

		for (E edge : edges)
			if (!testEdge(edge))
				throw new InvalidEdgeException("BigConstGraph ", edge);

		this.edges = new ArrayList<E>(edges);

		this.nodeCount = nodes.size();
		this.edgeCount = edges.size();

	}

	// Erzeugt einen Graphen mit den übergebenen Knoten und Pfeilen.
	// Existieren die von einem Pfeil benötigten Knoten nicht, so wird eine
	// InvalidEdgeException geworfen.

	/* (non-Javadoc)
	 * @see jpp.digraph.graph.IDiGraph#getNodeCount()
	 */
	@Override
	public int getNodeCount() {
		return this.nodeCount;
	}

	/* (non-Javadoc)
	 * @see jpp.digraph.graph.IDiGraph#getEdgeCount()
	 */
	@Override
	public int getEdgeCount() {
		return this.edgeCount;
	}

	/* (non-Javadoc)
	 * @see jpp.digraph.graph.IDiGraph#getNodes()
	 */
	@Override
	public Collection<N> getNodes() {
		return this.nodes;
	}

	/* (non-Javadoc)
	 * @see jpp.digraph.graph.IDiGraph#getEdges()
	 */
	@Override
	public Collection<E> getEdges() {
		return this.edges;
	}

	/* (non-Javadoc)
	 * @see jpp.digraph.graph.IDiGraph#setNodes(java.util.Collection)
	 */
	@Override
	public void setNodes(Collection<N> nodes) {
		this.nodes.clear();
		this.edges.clear();

		this.nodes.addAll(nodes);

		this.nodeCount = nodes.size();
		this.edgeCount = edges.size();

	}

	/* (non-Javadoc)
	 * @see jpp.digraph.graph.IDiGraph#setEdges(java.util.Collection)
	 */
	@Override
	public void setEdges(Collection<E> edges) throws InvalidEdgeException {
		for (E edge : edges)
			if (!testEdge(edge))
				throw new InvalidEdgeException("SetEd ", edge);

		this.edges.clear();
		this.edgeCount = 0;

		this.edges.addAll(edges);

		this.edgeCount = edges.size();

	}

	/* (non-Javadoc)
	 * @see jpp.digraph.graph.IDiGraph#setGraph(java.util.Collection, java.util.Collection)
	 */
	@Override
	public void setGraph(Collection<N> nodes, Collection<E> edges)
			throws InvalidEdgeException {
		this.nodes.clear();
		this.edges.clear();

		this.nodes.addAll(nodes);

		for (E edge : edges)
			if (!testEdge(edge))
				throw new InvalidEdgeException("SetGraph ", edge);

		this.edges.addAll(edges);

		this.nodeCount = nodes.size();
		this.edgeCount = edges.size();

	}

	/* (non-Javadoc)
	 * @see jpp.digraph.graph.IDiGraph#removeNodes()
	 */
	@Override
	public void removeNodes() {
		this.nodes.clear();
		this.edges.clear();

		this.nodeCount = nodes.size();
		this.edgeCount = edges.size();

	}

	/* (non-Javadoc)
	 * @see jpp.digraph.graph.IDiGraph#removeEdges()
	 */
	@Override
	public void removeEdges() {
		this.edges.clear();

		this.edgeCount = edges.size();

	}

	/* (non-Javadoc)
	 * @see jpp.digraph.graph.IDiGraph#removeGraph()
	 */
	@Override
	public void removeGraph() {
		this.nodes.clear();
		this.edges.clear();

		this.nodeCount = nodes.size();
		this.edgeCount = edges.size();
	}

	/* (non-Javadoc)
	 * @see jpp.digraph.graph.IDiGraph#containsNode(jpp.digraph.graph.INode)
	 */
	@Override
	public boolean containsNode(N node) {
		for (N tmp : this.nodes)
			if (tmp == node)
				return true;
		return false;
	}

	/* (non-Javadoc)
	 * @see jpp.digraph.graph.IDiGraph#containsEdge(jpp.digraph.graph.IEdge)
	 */
	@Override
	public boolean containsEdge(E edge) {
		return this.edges.contains(edge);
	}

	/* (non-Javadoc)
	 * @see jpp.digraph.graph.IDiGraph#containsEdge(jpp.digraph.graph.INode, jpp.digraph.graph.INode)
	 */
	@Override
	public boolean containsEdge(N source, N target) {
		for (E edge : this.edges)
			if (edge.getSource().equals(source)
					&& edge.getTarget().equals(target))
				return true;
		return false;
	}

	/* (non-Javadoc)
	 * @see jpp.digraph.graph.IDiGraph#addNode(jpp.digraph.graph.INode)
	 */
	@Override
	public void addNode(N node) {
		if (!containsNode(node))
			this.nodes.add(node);

		this.nodeCount = this.nodes.size();

	}

	/* (non-Javadoc)
	 * @see jpp.digraph.graph.IDiGraph#addEdge(jpp.digraph.graph.IEdge)
	 */
	@Override
	public void addEdge(E edge) throws InvalidEdgeException {
		if (!testEdge(edge))
			throw new InvalidEdgeException("AddEd", edge);
		if (!containsEdge(edge))
			this.edges.add(edge);

		this.edgeCount = this.edges.size();

	}

	/* (non-Javadoc)
	 * @see jpp.digraph.graph.IDiGraph#removeNode(jpp.digraph.graph.INode)
	 */
	@Override
	public void removeNode(N node) throws NodeNotExistsException {

		if (!containsNode(node))
			throw new NodeNotExistsException(node);
		ArrayList<E> tmp = new ArrayList<E>();
		for (E edge : this.edges) {
			if (edge.getSource().equals(node) || edge.getTarget().equals(node)) {
				tmp.add(edge);
			}
		}
		this.edges.removeAll(tmp);
		if (!this.nodes.remove(node))
			throw new NodeNotExistsException(node);

		this.nodeCount = this.nodes.size();
		this.edgeCount = this.edges.size();
	}

	/* (non-Javadoc)
	 * @see jpp.digraph.graph.IDiGraph#removeEdge(jpp.digraph.graph.IEdge)
	 */
	@Override
	public void removeEdge(E edge) throws InvalidEdgeException,
			EdgeNotExistsException {

		if (!testEdge(edge))
			throw new InvalidEdgeException("RemEd: ", edge);

		if (!this.edges.remove(edge))
			throw new EdgeNotExistsException(edge);

		this.edgeCount = this.edges.size();

	}

	/* (non-Javadoc)
	 * @see jpp.digraph.graph.IDiGraph#getPredecessors(jpp.digraph.graph.INode)
	 */
	@Override
	public Collection<N> getPredecessors(N node) throws NodeNotExistsException {

		if (!this.containsNode(node))
			throw new NodeNotExistsException("GetPreNo ", node);

		ArrayList<N> preNo = new ArrayList<N>();

		for (E edge : this.edges)
			if (edge.getTarget().equals(node))
				preNo.add(edge.getSource());

		return preNo;
	}

	/* (non-Javadoc)
	 * @see jpp.digraph.graph.IDiGraph#getPredecessorEdges(jpp.digraph.graph.INode)
	 */
	@Override
	public Collection<E> getPredecessorEdges(N node)
			throws NodeNotExistsException {

		if (!this.containsNode(node))
			throw new NodeNotExistsException("GetPreEd", node);

		ArrayList<E> preEd = new ArrayList<E>();

		for (E edge : this.edges)
			if (edge.getTarget().equals(node))
				preEd.add(edge);

		return preEd;
	}

	/* (non-Javadoc)
	 * @see jpp.digraph.graph.IDiGraph#getSuccessors(jpp.digraph.graph.INode)
	 */
	@Override
	public Collection<N> getSuccessors(N node) throws NodeNotExistsException {
		if (!this.containsNode(node))
			throw new NodeNotExistsException("GetSucNo ", node);

		ArrayList<N> sucNo = new ArrayList<N>();

		for (E edge : this.edges)
			if (edge.getSource().equals(node)
					&& !sucNo.contains(edge.getTarget()))
				sucNo.add(edge.getTarget());

		return sucNo;
	}

	/* (non-Javadoc)
	 * @see jpp.digraph.graph.IDiGraph#getSuccessorEdges(jpp.digraph.graph.INode)
	 */
	@Override
	public Collection<E> getSuccessorEdges(N node)
			throws NodeNotExistsException {

		if (!this.containsNode(node))
			throw new NodeNotExistsException("GetSucEd", node);

		ArrayList<E> sucEd = new ArrayList<E>();

		for (E edge : this.edges)
			if (edge.getSource().equals(node))
				sucEd.add(edge);

		return sucEd;
	}

	/* (non-Javadoc)
	 * @see jpp.digraph.graph.IDiGraph#getEdgesBetween(jpp.digraph.graph.INode, jpp.digraph.graph.INode)
	 */
	@Override
	public Collection<E> getEdgesBetween(N source, N target)
			throws NodeNotExistsException {
		if (!this.containsNode(source))
			throw new NodeNotExistsException("GetEdBetwSou", source);
		if (!this.containsNode(target))
			throw new NodeNotExistsException("GetEdBetwTar", target);

		ArrayList<E> edBet = new ArrayList<E>();

		for (E edge : this.edges)
			if (edge.getSource()==(source)
					&& edge.getTarget()==(target))
				edBet.add(edge);

		return edBet;
	}

	// MY OWN
	// FUNCTIONS------------------------------------------------------------------------------------
	// ----------------------------------------------------------------------------------------------------

	/**
	 * Test edge.
	 *
	 * @param edge the edge
	 * @return true, if successful
	 */
	private boolean testEdge(E edge) {
		return (containsNode(edge.getSource()) && containsNode(edge.getTarget()));

	}

	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		String my_string = "";
		for (N node : this.nodes)
			my_string += node + "\n";
		for (E edge : this.edges)
			my_string += edge + "\n";
		return my_string;
	}

}
