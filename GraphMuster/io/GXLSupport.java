package jpp.digraph.io;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashSet;
import java.util.List;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.OutputKeys;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerConfigurationException;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;
import jpp.digraph.exceptions.InvalidEdgeException;
import jpp.digraph.graph.CostEdge;
import jpp.digraph.graph.DiGraph;
import jpp.digraph.graph.IDiGraph;
import jpp.digraph.graph.IEdge;
import jpp.digraph.graph.INode;
import jpp.digraph.graph.XYNode;

public abstract class GXLSupport<G extends IDiGraph<N, E>, N extends INode, E extends IEdge<N>>
		implements IGXLSupport<G, N, E> {
	// ArrayList<N> nodesR = new ArrayList<N>();
	// ArrayList<E> edgesR = new ArrayList<E>();
	// G gr = createGraph();
	HashSet<N> nodR = new HashSet<N>();
	HashSet<E> edgR = new HashSet<E>();
	DiGraph<XYNode, CostEdge<XYNode>> diGraph = new DiGraph<>();

	public G read(InputStream is) throws ParserConfigurationException, IOException, SAXException, InvalidEdgeException {
		DocumentBuilder builder;
		builder = DocumentBuilderFactory.newInstance().newDocumentBuilder();
		Document doc = builder.parse(is);
		NodeList nodeList = doc.getElementsByTagName("node");
		NodeList edgeList = doc.getElementsByTagName("edge");
		G gr = createGraph();

		for (int i = 0; i < nodeList.getLength(); i++) {
			// this.nodes.add(createNode(nodeList.item(i)));
			gr.addNode(createNode(nodeList.item(i)));

		}
		for (int i = 0; i < edgeList.getLength(); i++) {
			// this.edges.add(createEdge(edgeList.item(i)));
			gr.addEdge(createEdge(edgeList.item(i)));

		}
		// diGraph = new DiGraph(nodes, edges);
		// try {
		// diGraph = new DiGraph(nodes, edges);
		//
		// } catch (InvalidEdgeException e) {
		//
		// }

		return gr; // gr

	}

	public void write(G graph, OutputStream os)
			throws ParserConfigurationException, IOException, TransformerConfigurationException, TransformerException {
		DocumentBuilder docBuild = DocumentBuilderFactory.newInstance().newDocumentBuilder();
		Document doc = docBuild.newDocument();
		Element root = doc.createElement("gxl");
		doc.appendChild(root);
		Element tree = doc.createElement("graph");
		tree.setAttribute("id", "id0");
		root.appendChild(tree);
		nodR = new HashSet<N>(graph.getNodes());
		edgR = new HashSet<E>(graph.getEdges());
		for (N no : nodR) {
			tree.appendChild(createElement(doc, no));
		}
		for (E ed : edgR) {
			tree.appendChild(createElement(doc, ed));
		}

		DOMSource domSource = new DOMSource(doc);
		TransformerFactory tf = TransformerFactory.newInstance();
		Transformer transformer = tf.newTransformer();
		transformer.setOutputProperty(OutputKeys.METHOD, "xml");
		transformer.setOutputProperty(OutputKeys.ENCODING, "ISO-8859-1");
		transformer.transform(domSource, new StreamResult(os));
	}

	public abstract G createGraph();

	public abstract N createNode(org.w3c.dom.Node element);

	public abstract E createEdge(org.w3c.dom.Node element);

	public abstract org.w3c.dom.Element createElement(org.w3c.dom.Document doc, N node);

	public abstract org.w3c.dom.Element createElement(org.w3c.dom.Document doc, E edge);

}
