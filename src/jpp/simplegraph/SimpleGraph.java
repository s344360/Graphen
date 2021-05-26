/*
 * 
 */
package jpp.simplegraph;

import java.util.Vector;

// TODO: Auto-generated Javadoc
/**
 * The Class SimpleGraph.
 */
public class SimpleGraph implements ISimpleGraph {

	/** The matrix. */
	private double[][] matrix = null;
	
	/** The edge_count. */
	private int edge_count = 0;

	/**
	 * Instantiates a new simple graph.
	 *
	 * @param n the n
	 */
	public SimpleGraph(int n) {
		if (n > 0) {
			this.matrix = new double[n][n];
			for (int i = 0; i < matrix.length; i++) {
				for (int j = 0; j < matrix.length; j++) {
					if (i != j)
						this.matrix[i][j] = -1;
					else
						this.matrix[i][j] = 0;
				}
			}
		}else{

		this.matrix = new double[0][0];
		}
	}

	/**
	 * Instantiates a new simple graph.
	 *
	 * @param matrix the matrix
	 */
	public SimpleGraph(double[][] matrix) {
		setMatrix(matrix);

	}

	/* (non-Javadoc)
	 * @see jpp.simplegraph.ISimpleGraph#getMatrix()
	 */
	@Override
	public double[][] getMatrix() {

		double[][] tmp = new double[this.matrix.length][this.matrix.length];
		for (int i = 0; i < this.matrix.length; i++) {
			for (int j = 0; j < this.matrix.length; j++) {
				tmp[i][j] = this.matrix[i][j];
			}
		}

		return tmp;
	}

	/* (non-Javadoc)
	 * @see jpp.simplegraph.ISimpleGraph#setMatrix(double[][])
	 */
	@Override
	public void setMatrix(double[][] matrix) {
		

		if (!checkMatrix(matrix)) {
			this.matrix = new double[0][0];
			return;
		}

		this.matrix = new double[matrix.length][matrix.length];
		for (int i = 0; i < matrix.length; i++) {
			for (int j = 0; j < matrix.length; j++) {
				this.matrix[i][j] = matrix[i][j];
				if (matrix[i][j] > 0)
					edge_count++;
			}
		}

	}

	/* (non-Javadoc)
	 * @see jpp.simplegraph.ISimpleGraph#getNodeCount()
	 */
	@Override
	public int getNodeCount() {
		if(checkMatrix(this.matrix))
		return this.matrix.length;
		
		
		
		return 0;
	}

	/* (non-Javadoc)
	 * @see jpp.simplegraph.ISimpleGraph#getEdgeCount()
	 */
	@Override
	public int getEdgeCount() {
		int counter = 0;
		for (int i = 0; i < this.matrix.length; i++)
			for (int j = 0; j < this.matrix.length; j++)
				if (this.matrix[i][j] > 0)
					counter++;

		return counter;
	}

	/* (non-Javadoc)
	 * @see jpp.simplegraph.ISimpleGraph#getPredecessors(int)
	 */
	@Override
	public int[] getPredecessors(int node) {
		int pre[] = null;
		Vector<Integer> vec = new Vector<Integer>();

		if (this.matrix != null) {
			if (!containsNode(node)) {
				int[] empty_array = {};
				return empty_array;
			}
		}

		for (int j = 0; j < this.matrix.length; j++) {
			double tmp = this.matrix[j][node];
			if (tmp > 0) {
				vec.add(j);
			}

			pre = new int[vec.size()];
			for (int i : vec)
				pre[vec.indexOf(i)] = i;
		}
		return pre;
	}

	/* (non-Javadoc)
	 * @see jpp.simplegraph.ISimpleGraph#getSuccessors(int)
	 */
	@Override
	public int[] getSuccessors(int node) {

		int suc[] = null;
		Vector<Integer> vec = new Vector<Integer>();

		if (this.matrix != null) {
			if (!containsNode(node)) {
				int[] empty_array = {};
				return empty_array;
			}
		}

		for (int j = 0; j < this.matrix.length; j++) {
			double tmp = this.matrix[node][j];
			if (tmp > 0) {
				vec.add(j);
			}

			suc = new int[vec.size()];
			for (int i : vec)
				suc[vec.indexOf(i)] = i;
		}
		return suc;
	}

	/* (non-Javadoc)
	 * @see jpp.simplegraph.ISimpleGraph#addEdge(int, int, double)
	 */
	@Override
	public boolean addEdge(int source, int target, double cost) {
		if (!containsNode(target) || !containsNode(source) || source == target)
			return false;

		this.matrix[source][target] = cost;
		this.edge_count++;

		return true;
	}

	/* (non-Javadoc)
	 * @see jpp.simplegraph.ISimpleGraph#removeEdge(int, int)
	 */
	@Override
	public boolean removeEdge(int source, int target) {
		if (!containsEdge(source, target))
			return false;

		this.matrix[source][target] = -1;
		this.edge_count--;
		return true;
	}

	/* (non-Javadoc)
	 * @see jpp.simplegraph.ISimpleGraph#containsNode(int)
	 */
	@Override
	public boolean containsNode(int node) {

		if (this.matrix.length > node && node >= 0
				&& this.matrix[node][node] == 0)
			return true;

		return false;
	}

	/* (non-Javadoc)
	 * @see jpp.simplegraph.ISimpleGraph#containsEdge(int, int)
	 */
	@Override
	public boolean containsEdge(int source, int target) {
		if (!containsNode(source) || !containsNode(target) || target == source)
			return false;

		return this.matrix[source][target] > 0;
	}

	/* (non-Javadoc)
	 * @see jpp.simplegraph.ISimpleGraph#getEdgeCost(int, int)
	 */
	@Override
	public double getEdgeCost(int source, int target) {
		if (!containsEdge(source, target))
			return -1;

		return this.matrix[source][target];
	}

	// MY OWN
	// FUNCTIONS--------------------------------------------------------------------------------
	// ------------------------------------------------------------------------------------------------

	/**
	 * Check matrix.
	 *
	 * @param matrix the matrix
	 * @return true, if successful
	 */
	private boolean checkMatrix(double[][] matrix) {

		if (matrix == null)
			return false;

		if (matrix.length < 1)
			return false;
		for (int i = 0; i < matrix.length; i++)
			if (matrix.length != matrix[i].length)
				return false;
		
		for (int i = 0; i < matrix.length; i++) {
			if (matrix[i][i] != 0)
				return false;
		}
	
		for (int i = 0; i < matrix.length; i++) {
			for (int j = 0; j < matrix.length; j++) {
				if (matrix[i][j] < 0) {
					if (matrix[i][j] != -1.0 || i == j)
						return false;
				} else if (matrix[i][j] != 0.0 && i == j)
					return false;
				if(matrix[i][j]==0 && j!=i)
					return false;
			}
		}

		return true;
	}

	/**
	 * Prints the matrix.
	 *
	 * @param matrix the matrix
	 */
	private void printMatrix(double[][] matrix) {
		String my_string = "";
		for (int i = 0; i < matrix.length; i++) {
			for (int j = 0; j < matrix.length; j++) {
				my_string += matrix[i][j] + "\t";
			}
			my_string += "\n";
		}
		System.out.println(my_string);
		System.out.println("Matrix-Length: " + matrix.length);
	}

}
