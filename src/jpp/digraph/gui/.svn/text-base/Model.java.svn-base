/*
 * 
 */
package jpp.digraph.gui;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;

import javax.xml.parsers.ParserConfigurationException;

import org.xml.sax.SAXException;

import jpp.digraph.exceptions.InvalidEdgeException;
import jpp.digraph.graph.CostEdge;
import jpp.digraph.graph.DiGraph;
import jpp.digraph.graph.XYNode;
import jpp.digraph.io.XYGXLSupport;

// TODO: Auto-generated Javadoc
/**
 * The Class Model.
 */
public class Model {
	
	/**
	 * Gets the graph.
	 *
	 * @param path the path
	 * @return the graph
	 */
	public DiGraph<XYNode, CostEdge<XYNode>> getGraph(String path){
		
		XYGXLSupport supporter = new XYGXLSupport();
		
		
		try {
			return supporter.read(new FileInputStream(path));
		} catch (InvalidEdgeException | ParserConfigurationException | IOException | SAXException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		};
		
		return null;
		
	}

}
