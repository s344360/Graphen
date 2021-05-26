/*
 * 
 */
import java.io.FileInputStream;
import java.util.ArrayList;
import java.util.List;

import jpp.digraph.graph.CostEdge;
import jpp.digraph.graph.DiGraph;
import jpp.digraph.graph.XYNode;
import jpp.digraph.io.XYGXLSupport;
import jpp.digraph.search.BFS;
import jpp.digraph.search.DFS;
import jpp.digraph.search.Dijkstra;
import jpp.digraph.search.XYAStar;


// TODO: Auto-generated Javadoc
/**
 * The Class Test.
 */
public class Test {

	/**
	 * The main method.
	 *
	 * @param args the arguments
	 * @throws Exception the exception
	 */
	public static void main(String[] args) throws Exception {
		XYGXLSupport supporter = new XYGXLSupport();
		DiGraph<XYNode, CostEdge<XYNode>> graph = new DiGraph<XYNode, CostEdge<XYNode>>();
//		graph = supporter.read(new FileInputStream("c://Users/Matthias/Documents/graph.xml"));
		
		BFS bfs = new BFS();
		DFS dfs = new DFS();
		Dijkstra di = new Dijkstra();
		XYAStar star = new XYAStar();
		ArrayList<XYNode> nodes = new ArrayList<XYNode>(graph.getNodes());
		ArrayList<CostEdge<XYNode>> edges = new ArrayList<CostEdge<XYNode>>(graph.getEdges());
		

		XYNode node1 = new XYNode(0, -3);
		XYNode node2 = new XYNode(0, -2);
		XYNode node3 = new XYNode(0, -1);
		XYNode node4 = new XYNode(0,  0);
		XYNode node5 = new XYNode(0,  1);
		XYNode node6 = new XYNode(0,  2);
		XYNode node7 = new XYNode(0,  3);
		XYNode node8 = new XYNode(0,  4); //TEST NODE
		
		CostEdge<XYNode> edge1= new CostEdge<XYNode>(node2, node1, 1.0);
		CostEdge<XYNode> edge2= new CostEdge<XYNode>(node3, node2, 1.0);
		CostEdge<XYNode> edge3= new CostEdge<XYNode>(node4, node3, 2.0);
		CostEdge<XYNode> edge4= new CostEdge<XYNode>(node4, node5, 1.0);
		CostEdge<XYNode> edge5= new CostEdge<XYNode>(node5, node6, 2.0);
		CostEdge<XYNode> edge6= new CostEdge<XYNode>(node6, node7, 1.0);
		
		graph.addNode(node1);
		graph.addNode(node2);
		graph.addNode(node3);
		graph.addNode(node4);
		graph.addNode(node5);
		graph.addNode(node6);
		graph.addNode(node7);
		
		graph.addEdge(edge1);
		graph.addEdge(edge2);
		graph.addEdge(edge3);
		graph.addEdge(edge4);
		graph.addEdge(edge5);
		graph.addEdge(edge6);
		
		
		
		
		List<CostEdge> list = new ArrayList<CostEdge>(star.search(graph, node4, node5));

		for(CostEdge edge:list)
		System.out.println(edge);

		

	}

}
