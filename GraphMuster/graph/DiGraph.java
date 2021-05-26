package jpp.digraph.graph;
import java.util.Collection;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import jpp.digraph.exceptions.EdgeNotExistsException;
import jpp.digraph.exceptions.InvalidEdgeException;
import jpp.digraph.exceptions.NodeNotExistsException;

public class DiGraph<N extends INode, E extends IEdge<N>> implements IDiGraph<N, E> {

	HashSet<N> nodes = new HashSet<N>();
	HashSet<E> edges = new HashSet<E>();

	public DiGraph() {
		nodes = new HashSet<N>();
		edges = new HashSet<E>();
	}

	public DiGraph(Collection<N> nodes) {
		this.nodes = new HashSet<N>(nodes);
		this.edges = new HashSet<E>();

	}

	public DiGraph(Collection<N> nodes, Collection<E> edges) throws InvalidEdgeException {
		this.edges = new HashSet<E>(edges);
		for (E edg : this.edges)
			if (!nodes.contains(edg.getSource()) || !nodes.contains(edg.getTarget())) {
				throw new InvalidEdgeException(edg);
			}
		this.nodes = new HashSet<N>(nodes);

	}

	public int getNodeCount() {
		return nodes.size();

	}

	public int getEdgeCount() {
		return edges.size();

	}

	public Collection<N> getNodes() {
		return nodes;

	}

	public Collection<E> getEdges() {
		return edges;

	}

	public void setNodes(Collection<N> nodes) {
		this.nodes.clear();
		this.edges.clear();
		this.nodes = new HashSet<N>(nodes);

	}

	public void setEdges(Collection<E> edges) throws InvalidEdgeException {
		this.edges.clear();
		this.edges = new HashSet<E>(edges);
		for (E edg : this.edges)
			if (!nodes.contains(edg.getSource()) || !nodes.contains(edg.getTarget())) {
				throw new InvalidEdgeException(edg);
			}
		this.nodes = new HashSet<N>(nodes);
	}

	public void setGraph(Collection<N> nodes, Collection<E> edges) throws InvalidEdgeException {
		this.nodes.clear();
		this.edges.clear();
		this.edges = new HashSet<E>(edges);
		for (E edg : this.edges)
			if (!nodes.contains(edg.getSource()) || !nodes.contains(edg.getTarget())) {
				throw new InvalidEdgeException(edg);
			}
		this.nodes = new HashSet<N>(nodes);

	}

	public void removeNodes() {
		nodes.clear();
		edges.clear();

	}

	public void removeEdges() {
		edges.clear();

	}

	public boolean containsNode(N node) {
		if (nodes.contains(node)) {
			return true;
		}
		return false;
	}

	public boolean containsEdge(E edge) {
		if (edges.contains(edge)) {
			return true;
		}
		return false;

	}

	public boolean containsEdge(N source, N target) {
		for (E edg : this.edges) {
			if (edg.getSource() == source && edg.getTarget() == target) {
				return true;
			}
		}

		return false;
	}

	public void addNode(N node) {
		nodes.add(node);

	}

	public void addEdge(E edge) throws InvalidEdgeException {
		if (!nodes.contains(edge.getSource()) || !nodes.contains(edge.getTarget())) {
			throw new InvalidEdgeException(edge);
		}
		edges.add(edge);

	}

	public void removeNode(N node) throws NodeNotExistsException {
		HashSet<E> edges2 = new HashSet<E>(edges);

		if (!nodes.contains(node)) {
			throw new NodeNotExistsException(node);
		}
		for (E edg : edges2) {
			if (edg.getSource() == node || edg.getTarget() == node) {
				edges.remove(edg);
			}
		}

		nodes.remove(node);

	}

	public void removeEdge(E edge) throws InvalidEdgeException, EdgeNotExistsException {
		if (!edges.contains(edge)) {
			throw new EdgeNotExistsException(edge);
		}
		if (!nodes.contains(edge.getSource()) || !nodes.contains(edge.getTarget())) {
			throw new InvalidEdgeException(edge);
		}
		edges.remove(edge);
	}

	public Collection<N> getPredecessors(N node) throws NodeNotExistsException {
		HashSet<N> nodes3 = new HashSet<N>();
		if (!nodes.contains(node)) {
			throw new NodeNotExistsException(node);
		}

		for (E edg : this.edges) {
			if (edg.getTarget() == node) {
				nodes3.add(edg.getSource());
			}

		}

		return nodes3;

	}

	public Collection<E> getPredecessorEdges(N node) throws NodeNotExistsException {
		HashSet<E> edges2 = new HashSet<E>();
		if (!nodes.contains(node)) {
			throw new NodeNotExistsException(node);
		}
		for (E edg : this.edges) {
			if (edg.getTarget() == node) {
				edges2.add(edg);
			}

		}
		return edges2;

	}

	public Collection<N> getSuccessors(N node) throws NodeNotExistsException {
		HashSet<N> nodes4 = new HashSet<N>();
		if (!nodes.contains(node)) {
			throw new NodeNotExistsException(node);
		}
		for (E edg : this.edges) {
			if (edg.getSource() == node) {
				nodes4.add(edg.getTarget());
			}

		}
		return nodes4;

	}

	public Collection<E> getSuccessorEdges(N node) throws NodeNotExistsException {
		HashSet<E> edges3 = new HashSet<E>();
		if (!nodes.contains(node)) {
			throw new NodeNotExistsException(node);
		}
		for (E edg : this.edges) {
			if (edg.getSource() == node) {
				edges3.add(edg);
			}

		}
		return edges3;

	}

	public Collection<E> getEdgesBetween(N source, N target) throws NodeNotExistsException {
		HashSet<E> edges4 = new HashSet<E>();
		if (!nodes.contains(source)) {
			throw new NodeNotExistsException(source);
		}
		if (!nodes.contains(target)) {
			throw new NodeNotExistsException(target);
		}
		for (E edg : this.edges) {
			if (edg.getSource() == source && edg.getTarget() == target) {
				edges4.add(edg);
			}

		}

		return edges4;

	}

}
