/*
 * 
 */
package jpp.digraph.graph;

import java.text.DecimalFormat;

// TODO: Auto-generated Javadoc
/**
 * The Class XYNode.
 */
public class XYNode implements IXYNode {


	/** The description. */
	private String description="";
	
	/** The x. */
	private int x;
	
	/** The y. */
	private int y;
	
	/** The weight. */
	private double weight=0.0;

	
	/**
	 * Instantiates a new xY node.
	 *
	 * @param x the x
	 * @param y the y
	 */
	public XYNode(int x, int y){
		this.x = x;
		this.y = y;
		
	}
//    Erzeugt einen Knoten mit den Koordinaten x und y. Als Beschreibung des Knoten wird ein leerer String verwendet.
	/**
 * Instantiates a new xY node.
 *
 * @param description the description
 * @param x the x
 * @param y the y
 */
public XYNode(String description, int x, int y){
		this.description = description;
		this.x = x;
		this.y = y;
		
	}
//    Erzeugt einen Knoten mit den Koordinaten x und y sowie der Beschreibung description.

	

	/* (non-Javadoc)
 * @see jpp.digraph.graph.INode#getDescription()
 */
@Override
	public String getDescription() {
		return this.description;
	}

	/* (non-Javadoc)
	 * @see jpp.digraph.graph.IXYNode#getX()
	 */
	@Override
	public int getX() {
		return this.x;
	}

	/* (non-Javadoc)
	 * @see jpp.digraph.graph.IXYNode#getY()
	 */
	@Override
	public int getY() {
		return this.y;
	}
	
	
	
	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "["+ description + "(" + x + "/" + y +")"
				+ "]";
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#equals(java.lang.Object)
	 */
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		XYNode other = (XYNode) obj;
		if (x != other.x)
			return false;
		if (y != other.y)
			return false;
		return true;
	}
	
	/**
	 * Gets the weight.
	 *
	 * @return the weight
	 */
	public double getWeight() {
		return weight;
	}
	
	/**
	 * Sets the weight.
	 *
	 * @param weight the new weight
	 */
	public void setWeight(double weight) {
		this.weight = weight;
	}

}
