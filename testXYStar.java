package jpp.digraph.search;

import java.util.ArrayList;
import java.util.List;

import jpp.digraph.exceptions.InvalidEdgeException;
import jpp.digraph.exceptions.NodeNotExistsException;
import jpp.digraph.graph.CostEdge;
import jpp.digraph.graph.DiGraph;
import jpp.digraph.graph.INode;
import jpp.digraph.graph.XYNode;

public class testXYStar {

	public static void main(String[] args) throws InvalidEdgeException, NodeNotExistsException {
		XYNode node1 = new XYNode("n1", 1, 2);
		XYNode node2 = new XYNode("n2", 3, 4);
		XYNode node3 = new XYNode("n3", 3, 1);
		XYNode node4 = new XYNode("n4", 6, 1);
		XYNode node5 = new XYNode("n5", 6, 4);
		XYNode node6 = new XYNode("n6", 5, 3);
		XYNode node7 = new XYNode("n7", 8, 4);
		XYNode node8 = new XYNode("n8", 8, 2);

		CostEdge<INode> edge1 = new CostEdge<INode>("e1", node1, node2, 1.5);
		CostEdge<INode> edge2 = new CostEdge<INode>("e2", node1, node3, 9.7);
		CostEdge<INode> edge3 = new CostEdge<INode>("e3", node3, node4, 2);
		CostEdge<INode> edge4 = new CostEdge<INode>("e4", node2, node5, 3);
		CostEdge<INode> edge5 = new CostEdge<INode>("e5", node5, node7, 2.1);
		CostEdge<INode> edge6 = new CostEdge<INode>("e6", node3, node6, 17);
		CostEdge<INode> edge7 = new CostEdge<INode>("e7", node6, node7, 1.1);
		CostEdge<INode> edge8 = new CostEdge<INode>("e8", node7, node8, 1);
		CostEdge<INode> edge9 = new CostEdge<INode>("e9", node7, node6, 2);
		CostEdge<INode> edge10 = new CostEdge<INode>("e10", node6, node3, 1);

		ArrayList<XYNode> nodes = new ArrayList<>();
		nodes.add(node1);
		nodes.add(node2);
		nodes.add(node3);
		nodes.add(node4);
		nodes.add(node5);
		nodes.add(node6);
		nodes.add(node7);
		nodes.add(node8);

		ArrayList<CostEdge<INode>> edges = new ArrayList<>();
		edges.add(edge1);
		edges.add(edge2);
		edges.add(edge3);
		edges.add(edge4);
		edges.add(edge5);
		edges.add(edge6);
		edges.add(edge7);
		edges.add(edge8);
		edges.add(edge9);
		edges.add(edge10);

		DiGraph graph = new DiGraph();
		graph.setGraph(nodes, edges);

		XYAStar alg = new XYAStar();
		List<CostEdge<INode>> way = alg.search(graph, node1, node4);
		System.out.println(way);
		// if (way != null)
		{
			for (int i = 0; i < way.size(); i++) {
				// System.out.println(way.get(i).getDescription());
				System.out.println(way.get(i).getId());
			}
		}
		// else System.out.println("null");

	}
}