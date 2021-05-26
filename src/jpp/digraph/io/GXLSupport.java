/*
 * 
 */
package jpp.digraph.io;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.Writer;
import java.util.ArrayList;

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

import jpp.digraph.exceptions.InvalidEdgeException;
import jpp.digraph.graph.CostEdge;
import jpp.digraph.graph.DiGraph;
import jpp.digraph.graph.IDiGraph;
import jpp.digraph.graph.IEdge;
import jpp.digraph.graph.INode;
import jpp.digraph.graph.XYNode;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

// TODO: Auto-generated Javadoc
/**
 * The Class GXLSupport.
 *
 * @param <G> the generic type
 * @param <N> the number type
 * @param <E> the element type
 */
public abstract class GXLSupport<G extends IDiGraph<N,E>, N extends INode, E extends IEdge<N>> implements IGXLSupport<G, N, E>
{

	/** The doc. */
	protected Document doc=null;
	
	/** The index. */
	protected int index = 0;
	

	
	/* (non-Javadoc)
	 * @see jpp.digraph.io.IGXLSupport#read(java.io.InputStream)
	 */
	@Override
	public G read(InputStream is) throws ParserConfigurationException, IOException, SAXException, InvalidEdgeException {

		DocumentBuilder builder = null;
		

		builder = DocumentBuilderFactory.newInstance().newDocumentBuilder();
		this.doc = builder.parse(is);			//HIER INPUTSTREAM EIGENTLICH


		NodeList nodes = doc.getElementsByTagName("node");
		NodeList edges = doc.getElementsByTagName("edge");
		System.out.println("There are " +nodes.getLength() +" nodes in the graph");
		System.out.println("There are " +nodes.getLength() +" edges in the graph");
		for (int i = 0; i < edges.getLength(); i++) {

		}
		
	
		
		return createGraph();
	}

	/* (non-Javadoc)
	 * @see jpp.digraph.io.IGXLSupport#write(jpp.digraph.graph.IDiGraph, java.io.OutputStream)
	 */
	@Override
	public void write(G graph, OutputStream os) throws ParserConfigurationException, IOException,TransformerConfigurationException, TransformerException {
		
		
		DocumentBuilder docBuild =  DocumentBuilderFactory.newInstance().newDocumentBuilder();
		Document doc = docBuild.newDocument();
		
		//Wurzel
		this.index++;
		Element root = doc.createElement("gxl");
		doc.appendChild(root);
		
		
			//Graph
			Element graph_element = doc.createElement("graph");
			graph_element.setAttribute("id", "id"+this.index);
			root.appendChild(graph_element);
			
			ArrayList<N> nodes = new ArrayList<N>(graph.getNodes());
			ArrayList<E> edges = new ArrayList<E>(graph.getEdges());
			
			for(N tmp: nodes)
				graph_element.appendChild(createElement(doc, tmp));
			
			for(E tmp: edges)
				graph_element.appendChild(createElement(doc, tmp));
		

			
			
			
			
			// Dokument auf den OutputStream schreiben
			DOMSource domSource = new DOMSource(doc);
			TransformerFactory tf = TransformerFactory.newInstance();
			Transformer transformer = tf.newTransformer();
			transformer.setOutputProperty(OutputKeys.METHOD, "xml");
			transformer.setOutputProperty(OutputKeys.ENCODING, "ISO-8859-1");
			transformer.transform(domSource, new StreamResult(os));
	}

	/* (non-Javadoc)
	 * @see jpp.digraph.io.IGXLSupport#createGraph()
	 */
	@Override
	public abstract G createGraph();
	

	/* (non-Javadoc)
	 * @see jpp.digraph.io.IGXLSupport#createNode(org.w3c.dom.Node)
	 */
	@Override
	public abstract N createNode(Node element);

	/* (non-Javadoc)
	 * @see jpp.digraph.io.IGXLSupport#createEdge(org.w3c.dom.Node)
	 */
	@Override
	public abstract E createEdge(Node element);

	/* (non-Javadoc)
	 * @see jpp.digraph.io.IGXLSupport#createElement(org.w3c.dom.Document, jpp.digraph.graph.INode)
	 */
	@Override
	public abstract Element createElement(Document doc, N node);

	/* (non-Javadoc)
	 * @see jpp.digraph.io.IGXLSupport#createElement(org.w3c.dom.Document, jpp.digraph.graph.IEdge)
	 */
	@Override
	public abstract Element createElement(Document doc, E edge);
}
