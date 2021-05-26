/*
 * 
 */
package jpp.digraph.gui;

import java.awt.Color;
import java.awt.Graphics;

import javax.swing.JPanel;

// TODO: Auto-generated Javadoc
/**
 * The Class PaintNode.
 */
public class PaintNode extends JPanel {

	/** The x. */
	private int x = 0;
	
	/** The y. */
	private int y = 0;
	
	/** The id. */
	private String id = "";
	
	/** The color. */
	private Color color = null;
	
	/** The radius. */
	private int radius = 15;

	/**
	 * Instantiates a new paint node.
	 *
	 * @param x the x
	 * @param y the y
	 * @param id the id
	 * @param color the color
	 */
	public PaintNode(int x, int y, String id, Color color) {
		this.x = x;
		this.y = y;
		this.id = id;
		this.color = color;
	}

	/* (non-Javadoc)
	 * @see javax.swing.JComponent#paintComponent(java.awt.Graphics)
	 */
	@Override
	protected void paintComponent(Graphics g) {
		super.paintComponent(g);
		g.setColor(color);
		g.fillOval(x, y, 2 * radius, 2 * radius);
		g.setColor(Color.black);
		g.drawString(id, x + radius / 2, y + (radius + radius / 2));
	

	}

	/* (non-Javadoc)
	 * @see javax.swing.JComponent#getX()
	 */
	public int getX() {
		return x;
	}
	
	/* (non-Javadoc)
	 * @see javax.swing.JComponent#getY()
	 */
	public int getY() {
		return y;
	}
	
	/**
	 * Gets the radius.
	 *
	 * @return the radius
	 */
	public int getRadius() {
		return radius;
	}


}
