/*
 * 
 */
package jpp.digraph.gui;


import java.awt.GridLayout;


import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JSlider;

// TODO: Auto-generated Javadoc
/**
 * The Class ControllerPanel.
 */
public class ControllerPanel extends JPanel {
	//Componets
	/** The lbl algo. */
	private JLabel lblAlgo = new JLabel("SuchAlgo");
	
	/** The lbl source. */
	private JLabel lblSource = new JLabel("Start");
	
	/** The lbl target. */
	private JLabel lblTarget = new JLabel("Ziel");
	
	/** The box_algo. */
	private JComboBox<String> box_algo;
	
	/** The box_source. */
	private JComboBox<String> box_source;
	
	/** The box_target. */
	private JComboBox<String> box_target;
	
	/** The sli_time. */
	private JSlider sli_time;
	
	/** The load. */
	private JButton load;
	
	/** The start. */
	private JButton start;
	
	/** The stop. */
	private JButton stop;
	

	
	
	
	
	/**
	 * Instantiates a new controller panel.
	 */
	public ControllerPanel(){
		this.setLayout( new GridLayout(15,0));
		
		initComponents();
		addComponents();

	}


	/**
	 * Inits the components.
	 */
	private void initComponents(){
		box_algo = new JComboBox<String>();
		box_algo.addItem("BFS");
		box_algo.addItem("DFS");
		box_algo.addItem("Dijkstra");
		box_algo.addItem("A-Stern");
		
		box_source = new JComboBox<String>();
		box_target = new JComboBox<String>();
		
		
		sli_time = new JSlider(1, 2000);
		
		load = new JButton("Graph Laden");
		start = new JButton("Starte Suche");
		stop = new JButton("STOP");
		
		  //DisableButtons
		  start.setEnabled(false);
		  stop.setEnabled(false);
	}
	
	
	/**
	 * Adds the components.
	 */
	private void addComponents() {
		this.add(lblAlgo);
		this.add(box_algo);
		
		this.add(lblSource);
		this.add(box_source);
		
		this.add(lblTarget);
		this.add(box_target);
		
		this.add(sli_time);
		
		this.add(load);
		this.add(start);
		this.add(stop);

	}
	



	/**
	 * Gets the lbl algo.
	 *
	 * @return the lbl algo
	 */
	public JLabel getLblAlgo() {
		return lblAlgo;
	}


	/**
	 * Gets the lbl source.
	 *
	 * @return the lbl source
	 */
	public JLabel getLblSource() {
		return lblSource;
	}


	/**
	 * Gets the lbl target.
	 *
	 * @return the lbl target
	 */
	public JLabel getLblTarget() {
		return lblTarget;
	}


	/**
	 * Gets the box_algo.
	 *
	 * @return the box_algo
	 */
	public JComboBox<String> getBox_algo() {
		return box_algo;
	}


	/**
	 * Gets the box_source.
	 *
	 * @return the box_source
	 */
	public JComboBox<String> getBox_source() {
		return box_source;
	}


	/**
	 * Gets the box_target.
	 *
	 * @return the box_target
	 */
	public JComboBox<String> getBox_target() {
		return box_target;
	}


	/**
	 * Gets the sli_time.
	 *
	 * @return the sli_time
	 */
	public JSlider getSli_time() {
		return sli_time;
	}


	/**
	 * Gets the load.
	 *
	 * @return the load
	 */
	public JButton getLoad() {
		return load;
	}


	/**
	 * Gets the start.
	 *
	 * @return the start
	 */
	public JButton getStart() {
		return start;
	}


	/**
	 * Gets the stop.
	 *
	 * @return the stop
	 */
	public JButton getStop() {
		return stop;
	}
	

}
