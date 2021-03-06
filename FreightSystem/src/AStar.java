/**
 * COMP2911 FreightSystem Assignment 2
 * Date: April 2017
 * Author: Brian Lam
 */

import java.util.*;

// Implement Djikstra's
// Do a run-through of base case f(n) = g(n)
// Implement Heuristic functions in Strategy Design
// Do a run-through of uniform-cost + heuristic cost f(n) = g(n) + h(n)

public class AStar {
	private ArrayList<Boolean> done;
	private ArrayList<Integer> dist;

	public AStar() {
		this.done = new ArrayList<Boolean>();
		this.dist = new ArrayList<Integer>();
	}
	
	public void setDone(int numCity) {
		for (int i = 0; i < numCity; i++) {
			done.add(false);
		}
		System.out.println("DONE = " + done);
	}
	
	public void setDist(int numCity) {
		int distVal = 10000;
		for (int i = 0; i < numCity; i++) {
			dist.add(distVal);
		}
		// set start distance = 0
		dist.set(FreightSystem.getCityID("Sydney"), 0);
		System.out.println("DIST = " + dist);
	}
	// Build path
	//private void addToPath(int visited) {
	//	FreightSystem.pathList.add(visited);
	//}
	
	/**
	 * Calculates the shortest path from Source to every Vertex in the graph
	 * ArrayList done will contain weights for each SRC->DEST
	 */
	public void aStar(int start, int numCity) {
		while(true) {
			int v = -1;
			int bestDist = 10000;
			// find vertex with smallest dist[]
			for (int i = 0; i < numCity; i++) {
				// check if vertex visited and if currDist < bestDist
				if (done.get(i) == false && (dist.get(i) < bestDist)) {
					v = i;
					bestDist = dist.get(i);
				}
			}
			// no more vertices to check
			if (bestDist == 10000) break;
			// relax neighbour edges
			for (int w = 0; w < numCity; w++) {
				System.out.println("CHECKING SRC CITY " + FreightSystem.getCityStr(v));
				System.out.println("CHECKING DEST CITY = " + FreightSystem.getCityStr(w));
				int weight = FreightSystem.getWeight(v, w);
				// check if visited + valid edge
				if (done.get(w) == false && (weight != -1)) {
					// find shortest distance
					if (dist.get(w) > (dist.get(v) + weight)) {
						dist.set(w, (dist.get(v) + weight));
						System.out.println("#################################");
						System.out.println("NEW PATH FOUND: " + FreightSystem.getCityStr(v) + " TO " + FreightSystem.getCityStr(w) + " = " + dist.get(w));
						System.out.println("#################################");
					}
					// For every new shortest path found, store it
					// Loop through the shortest path
				}
			}
			// Loop through each 
			// check
			done.set(v, true);
			System.out.println("===   UPDATED DIST   === " + dist);
			System.out.println("=== CHECKED VERTICES === " + done);
			System.out.println("--------------------");
			
		}
		System.out.println("FINAL DONE = " + done);
		System.out.println("FINAL DIST = " + dist);
		//dist.clear();
		//done.clear();
	}
}
