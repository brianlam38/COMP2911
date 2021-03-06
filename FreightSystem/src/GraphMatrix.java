/**
 * COMP2911 FreightSystem Assignment 2
 * Date: April 2017
 * Author: Brian Lam
 */

import java.util.*;

/**
 * A matrix representation of the Freight System Graph.
 * 
 * Contains methods to create a n*n matrix
 */
public class GraphMatrix {
	public int[][] matrix;
	
	/**
	 * Constructor for the matrix
	 * @param number of total vertices / cities
	 * @precondition number of vertices n must not be -ve
	 */
	public GraphMatrix(int n) {
		this.matrix = new int[n][n];
		for (int i = 0; i < n; i++) {
			for (int j = 0; j < n; j++) {
				matrix[i][j] = -1;
			}
		}
	}
	
	/**
	 * Add implied edge and weight to matrix
	 * @precondition edge [v][w] doesn't exist
	 */
	public void addEdge(int travelCost, int v, int w) {
		matrix[v][w] = travelCost;
		matrix[w][v] = travelCost;
	}
	
	/**
	 * Grab specified edge weight
	 * @precondition edge [v][w] exists
	 */
	public int getWeight(int v, int w) {
		return matrix[v][w];
	}
}
