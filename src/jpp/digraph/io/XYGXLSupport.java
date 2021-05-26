/**
 * 
 */
package jpp.digraph.io;

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.HashMap;

import jpp.digraph.exceptions.InvalidEdgeException;
import jpp.digraph.graph.CostEdge;
import jpp.digraph.graph.DiGraph;
import jpp.digraph.graph.IDiGraph;
import jpp.digraph.graph.IEdge;
import jpp.digraph.graph.INode;
import jpp.digraph.graph.IXYNode;
import jpp.digraph.graph.XYNode;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

// TODO: Auto-generated Javadoc
/**
 * The Class XYGXLSupport.
 *
 * @author Matthias
 */
public class XYGXLSupport extends GXLSupport<DiGraph<XYNode, CostEdge<XYNode>>, XYNode, CostEdge<XYNode>> {

	/** The nodes. */
	private ArrayList<XYNode> nodes;
	
	/** The edges. */
	private ArrayList<CostEdge<XYNode>> edges;
	
	/** The graph. */
	private DiGraph<XYNode,CostEdge<XYNode>> graph;
	
	/** The id_map. */
	private HashMap<String,XYNode> id_map;
	
	/** The node_map. */
	private HashMap<XYNode,String> node_map;
	

	
	/**
	 * Instantiates a new xYGXL support.
	 */
	public XYGXLSupport() {
		id_map = new HashMap<String, XYNode >();
		node_map = new HashMap<XYNode,String>();
	}

	/* (non-Javadoc)
	 * @see jpp.digraph.io.GXLSupport#createGraph()
	 */
	@Override
	public DiGraph<XYNode,CostEdge<XYNode>> createGraph() {
		this.nodes = new ArrayList<XYNode>();
		this.edges = new ArrayList<CostEdge<XYNode>>();
		this.graph = new DiGraph<XYNode,CostEdge<XYNode>>();
		
		
		
		
		NodeList nodes_elements = doc.getElementsByTagName("node");
		NodeList edges_elements = doc.getElementsByTagName("edge");
		System.out.println("There are " +nodes_elements.getLength() +" nodes in the graph");
		System.out.println("There are " +nodes_elements.getLength() +" edges in the graph");
		
		//Create all Node-Objects from XML-source and add them to nodes list
		for(int i=0; i<nodes_elements.getLength();i++){
			this.nodes.add(createNode(nodes_elements.item(i)));

		}
		//Create all Edge-Objects from XML-source and add them to edges list
		for(int i=0; i<edges_elements.getLength();i++){
			CostEdge edge = createEdge(edges_elements.item(i));
			this.edges.add(edge);
			
		}
		
		
		try {
			this.graph.setGraph(this.nodes, this.edges);
		} catch (InvalidEdgeException e) {			
			e.printStackTrace();
		}
		
		
		
		
		return graph;
		
	}

	/* (non-Javadoc)
	 * @see jpp.digraph.io.GXLSupport#createNode(org.w3c.dom.Node)
	 */
	@Override
	public XYNode createNode(Node element) {

		String id = ((Element) element).getAttribute( "id");
	    String description = "";
	    int x = 0;
	    int y = 0;
	    NodeList attrs = ((Element)(element)).getElementsByTagName("attr");
	
	    for (int j = 0; j < attrs.getLength(); j++) {
	        Element attr = (Element)(attrs.item( j));
	        String name = attr.getAttribute( "name");
	        if ( "description".equals( name)) {
	           description = ((Element)(attr.getElementsByTagName( "string").item(0))).getTextContent();
	        } else if ( "x".equals( name)) {
	           x = Integer.parseInt( ((Element)(attr.getElementsByTagName( "int").item(0))).getTextContent());
	        } else if ( "y".equals( name)) {
	             y = Integer.parseInt( ((Element)(attr.getElementsByTagName( "int").item(0))).getTextContent());
	        } else {
	            throw new IllegalArgumentException( "attr.name=" + name);
	        }
	    }

	    XYNode node = new XYNode(description, x, y);
	    this.id_map.put(id, node);
	    
	    
	    return node;
	}

	/* (non-Javadoc)
	 * @see jpp.digraph.io.GXLSupport#createEdge(org.w3c.dom.Node)
	 */
	@Override

	public CostEdge createEdge(Node element) {
		String description 	= "";
		double cost	= 0.0;
		
		String from = ((Element) element).getAttribute("from");
	    String id 	= ((Element) element).getAttribute("id");
	    String to 	= ((Element) element).getAttribute("to");

	    

	    NodeList attrs = ((Element)(element)).getElementsByTagName("attr");
	    for (int j = 0; j < attrs.getLength(); j++) {
	        Element attr = (Element)(attrs.item( j));
	        String name = attr.getAttribute( "name");
	        
	        if ( "description".equals( name)) {
	           description = ((Element)(attr.getElementsByTagName( "string").item(0))).getTextContent();
	           
	        } else if ( "cost".equals( name)) {
	        	cost = Double.parseDouble( ((Element)(attr.getElementsByTagName( "float").item(0))).getTextContent());
	           
	        } else {
	            throw new IllegalArgumentException( "attr.name=" + name);
	        }
	    }
	    
	    XYNode source = this.id_map.get(from);
	    XYNode target = this.id_map.get(to);

	    CostEdge edge 	 = new CostEdge<XYNode>(description,source,target,cost);
	    
		return edge;
	}




	/* (non-Javadoc)
	 * @see jpp.digraph.io.GXLSupport#createElement(org.w3c.dom.Document, jpp.digraph.graph.INode)
	 */
	@Override
	public Element createElement(Document doc, XYNode node) {
		this.index++;
		//Knoten
		Element node_element = doc.createElement("node");
		
		Element description = doc.createElement("attr");
		Element string = doc.createElement("string");
		
		Element x = doc.createElement("attr");
		Element x_value = doc.createElement("int");
		
		Element y = doc.createElement("attr");
		Element y_value = doc.createElement("int");
		
		
		//Knoten
		node_element.setAttribute("id", ("id"+ this.index));
		

		//Description
		description.setAttribute("name", "description");
		
		//String
		
		string.setTextContent(node.getDescription());
			description.appendChild(string);
			node_element.appendChild(description);
			
		
		
		x.setAttribute("name", "x");
		
		//X
		
		x_value.setTextContent(String.valueOf(((IXYNode) node).getX()));
			x.appendChild(x_value);
			node_element.appendChild(x);
			
		
		y.setAttribute("name", "y");
		
		//Y
		
		y_value.setTextContent(String.valueOf(((IXYNode) node).getY()));
		y.appendChild(y_value);
		node_element.appendChild(y);
		//Add to Hashmap node_map
		
		String index = "id" + this.index;
		this.node_map.put(node, index);
		
		return node_element;
	}

	/* (non-Javadoc)
	 * @see jpp.digraph.io.GXLSupport#createElement(org.w3c.dom.Document, jpp.digraph.graph.IEdge)
	 */
	@Override
	public Element createElement(Document doc, CostEdge edge) {
		this.index++;
		//Knoten
		Element edge_element = doc.createElement("edge");
		
		Element description = doc.createElement("attr");
		Element string = doc.createElement("string");
		
		Element cost = doc.createElement("attr");
		Element cost_value = doc.createElement("float");
		DecimalFormat f1 = new DecimalFormat("#0.00");

		
		
		//Knoten
		edge_element.setAttribute("from", (this.node_map.get(edge.getSource())));
		edge_element.setAttribute("id", ("id"+String.valueOf(this.index)));
		edge_element.setAttribute("to", (this.node_map.get(edge.getTarget())));
		

		//Description
		description.setAttribute("name", "description");
		
		//String
		
		string.setTextContent(edge.getDescription());
			description.appendChild(string);
			edge_element.appendChild(description);
			
		
		
		cost.setAttribute("name", "cost");
		
		//Cost

		
		cost_value.setTextContent(f1.format(((CostEdge<XYNode>) edge).getCost()).replace(",","."));
			cost.appendChild(cost_value);
			edge_element.appendChild(cost);

		//Add to Hashmap node_map

		
		return edge_element;
		
	}

	
	/**
	 * Gets the nodes.
	 *
	 * @return the nodes
	 */
	public ArrayList<XYNode> getNodes() {
		return nodes;
	}

	/**
	 * Gets the edges.
	 *
	 * @return the edges
	 */
	public ArrayList<CostEdge<XYNode>> getEdges() {
		return edges;
	}


}
