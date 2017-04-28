/**
 * COMP2911 FreightSystem Assignment 2
 * Date: April 2017
 * Author: Brian Lam
 */

import java.util.*;

/**
 * A matrix representation of the Freight System Graph.
 * 
 * Contains methods to create a n*n matrix, add and remove edges
 * and check if two vertices are connected.
 *
 */
public class GraphMatrix {

	public int[][] matrix;
	
	/**
	 * Constructor for the matrix
	 * @precondition number of vertices n must not be -ve
	 */
	// Create graph with n numVertices and initialise with -ve weights
	public GraphMatrix(int n) {
		this.matrix = new int[n][n];
		for (int i = 0; i < n; i++) {
			for (int j = 0; j < n; j++) {
				matrix[i][j] = -1;
			}
		}
	}
	
	/**
	 * 
	 * @precondition edge [v][w] doesn't exist
	 */
	// Add path from v->w and w->v
	public void addEdge(int travelCost, int v, int w) {
		matrix[v][w] = travelCost;
		matrix[w][v] = travelCost;
	}
	
	// return edge weight between v->w
	public int getWeight(int v, int w) {
		return matrix[v][w];
	}
}