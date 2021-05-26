/*
 * 
 */
package jpp.digraph.gui;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.util.Vector;

import javax.swing.JPanel;

import jpp.digraph.graph.CostEdge;
import jpp.digraph.graph.ICostEdge;
import jpp.digraph.graph.IEdge;

// TODO: Auto-generated Javadoc
/**
 * The Class PaintEdge.
 */
public class PaintEdge extends JPanel {

	/** The source. */
	private PaintNode source;

	/** The target. */
	private PaintNode target;

	/** The color. */
	private Color color;

	/** The x1. */
	int x1 = 0;
	
	/** The y1. */
	int y1 = 0;
	
	/** The x2. */
	int x2 = 0;
	
	/** The y2. */
	int y2 = 0;

	/**
	 * Instantiates a new paint edge.
	 * 
	 * @param source
	 *            the source
	 * @param target
	 *            the target
	 * @param color
	 *            the color
	 */
	public PaintEdge(PaintNode source, PaintNode target, Color color) {
		this.source = source;
		this.target = target;
		this.color = color;

	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see javax.swing.JComponent#paintComponent(java.awt.Graphics)
	 */
	@Override
	protected void paintComponent(Graphics g) {
		super.paintComponent(g);
		g.setColor(color);

		x1 = source.getX() + source.getRadius();
		y1 = source.getY() + source.getRadius();
		x2 = target.getX() + target.getRadius();
		y2 = target.getY() + target.getRadius();

		Vector<Double> vec = new Vector<Double>();

		vec.add((double) x2 - x1);
		vec.add((double) y2 - y1);

		double length = Math.sqrt((vec.get(0) * vec.get(0))
				+ (vec.get(1) * vec.get(1)));

		vec.set(0, vec.get(0) / (length));
		vec.set(1, vec.get(1) / (length));

		x2 = (int) (x1 + vec.get(0) * (length - source.getRadius() * 1.5));
		y2 = (int) (y1 + vec.get(1) * (length - source.getRadius() * 1.5));

		Graphics2D g2 = (Graphics2D) g;
		if (color == Color.red) {
			g2.setStroke(new BasicStroke(3));
		} else
			g2.setStroke(new BasicStroke(1));

		g.drawLine(x1, y1, x2, y2);

		x1 = x2;
		y1 = y2;
		x2 = target.getX() + target.getRadius();
		y2 = target.getY() + target.getRadius();

		g.setColor(Color.BLACK);
		g.drawLine(x1, y1, x2, y2);

	}


	/**
	 * Gets the color.
	 * 
	 * @return the color
	 */
	public Color getColor() {
		return this.color;
	}
}
