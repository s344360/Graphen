package jpp.digraph.io;

import java.text.DecimalFormat;
import java.util.HashMap;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NamedNodeMap;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import jpp.digraph.graph.CostEdge;
import jpp.digraph.graph.DiGraph;
import jpp.digraph.graph.IXYNode;
import jpp.digraph.graph.XYNode;

public class XYGXLSupport extends GXLSupport<DiGraph<XYNode, CostEdge<XYNode>>, XYNode, CostEdge<XYNode>> {
	HashMap<String, XYNode> map = new HashMap<String, XYNode>();
	DiGraph<XYNode, CostEdge<XYNode>> graph = new DiGraph<XYNode, CostEdge<XYNode>>();

	public XYGXLSupport() {

	}

	@Override
	public DiGraph<XYNode, CostEdge<XYNode>> createGraph() {
		graph = new DiGraph<XYNode, CostEdge<XYNode>>();
		return graph;
	}

	@Override
	public XYNode createNode(Node element) {
		String xString;
		String yString;
		String id = "";
		int x = 0;
		int y = 0;
		String description = "";
		NamedNodeMap nodeMap = element.getAttributes();
		id = nodeMap.item(0).getNodeValue();
		id = element.getAttributes().item(0).getNodeValue();
		description = element.getChildNodes().item(0).getFirstChild().getTextContent();
		// getFirstChild().getFirstChild().getTextContent();
		xString = element.getChildNodes().item(1).getFirstChild().getTextContent();
		x = Integer.parseInt(xString);
		yString = element.getChildNodes().item(2).getFirstChild().getTextContent();
		y = Integer.parseInt(yString);
		XYNode xynode = new XYNode(id, x, y, description);
		this.map.put(id, xynode);
		return xynode;
	}

	@SuppressWarnings("unchecked")
	@Override
	public CostEdge<XYNode> createEdge(Node element) {
		double cost;
		String id = "";
		String costString;
		String description;

		String from = element.getAttributes().item(0).getNodeValue();
		String to = element.getAttributes().item(2).getNodeValue();
		id = element.getAttributes().item(1).getNodeValue();
		description = element.getChildNodes().item(0).getFirstChild().getTextContent();
		costString = element.getChildNodes().item(1).getFirstChild().getTextContent();
		cost = Double.parseDouble(costString);
		XYNode source = this.map.get(from);
		XYNode target = this.map.get(to);
		CostEdge costEdge = new CostEdge<XYNode>(id, source, target, cost, description);

		return costEdge;
	}

	@Override
	public Element createElement(Document doc, XYNode node) {
		Element nodE = doc.createElement("node");
		nodE.setAttribute("id", node.getId());
		Element attr = doc.createElement("attr");
		attr.setAttribute("name", "description");
		// nodE.appendChild(attr);
		Element n1 = doc.createElement("string");
		n1.setTextContent(node.getDescription());
		attr.appendChild(n1);
		nodE.appendChild(attr);

		Element attr1 = doc.createElement("attr");
		attr1.setAttribute("name", "x");
		// nodE.appendChild(attr1);
		Element x = doc.createElement("int");
		// x.setTextContent(node.getX() + "");
		x.setTextContent(String.valueOf(((IXYNode) node).getX()));
		attr1.appendChild(x);
		nodE.appendChild(attr1);

		Element attr2 = doc.createElement("attr");
		attr2.setAttribute("name", "y");
		// nodE.appendChild(attr2);
		Element y = doc.createElement("int");
		// y.setTextContent(node.getY() + "");
		y.setTextContent(String.valueOf(((IXYNode) node).getY()));
		attr2.appendChild(y);
		nodE.appendChild(attr2);

		return nodE;
	}

	@Override
	public Element createElement(Document doc, CostEdge<XYNode> edge) {
		DecimalFormat f1 = new DecimalFormat("#0.00");
		Element edgE = doc.createElement("edge");
		edgE.setAttribute("from", edge.getSource().getId() + "");
		edgE.setAttribute("id", edge.getId());
		edgE.setAttribute("to", edge.getTarget().getId() + "");

		Element attr = doc.createElement("attr");
		attr.setAttribute("name", "description");
		// edgE.appendChild(attr);
		Element e = doc.createElement("string");
		e.setTextContent(edge.getDescription());
		attr.appendChild(e);
		edgE.appendChild(attr);

		Element attr1 = doc.createElement("attr");
		attr1.setAttribute("name", "cost");
		// edgE.appendChild(attr1);
		Element cost = doc.createElement("float");
		// cost.setTextContent(edge.getCost() + "");
		cost.setTextContent(f1.format(((CostEdge<XYNode>) edge).getCost()).replace(",", "."));
		attr1.appendChild(cost);
		edgE.appendChild(attr1);
		return edgE;
	}

}