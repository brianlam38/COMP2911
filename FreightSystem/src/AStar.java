/**
 * COMP2911 FreightSystem Assignment 2
 * Date: April 2017
 * Author: Brian Lam
 */

import java.util.*;

// DESIGN

// Implement Djikstra's
// Do a run-through of base case f(n) = g(n)
// Implement Heuristic functions in Strategy Design
// Do a run-through of uniform-cost + heuristic cost f(n) = g(n) + h(n)

public class AStar {
	
	//private PriorityQueue<Integer> pq;
	private ArrayList<Boolean> done;	// stores vertices that have left queue
	private ArrayList<Integer> dist;	// scan dist[] everytime to find closest w

	public AStar() {
		//this.pq = new PriorityQueue<Integer>();
		this.done = new ArrayList<Boolean>();
		this.dist = new ArrayList<Integer>();
	}
	
	//public void addToPQ(int numCity) {
	//	int prevStart = -1;
	//	for (int i = 0; i < numCity; i++) {
	//		prev.add(prevStart);
	//		pq.add(i);
	//	}
	//}
	
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
				}
			}
			done.set(v, true);
			System.out.println("===   UPDATED DIST   === " + dist);
			System.out.println("=== CHECKED VERTICES === " + done);
			System.out.println("--------------------");
		}
		System.out.println("FINAL DONE = " + done);
		System.out.println("FINAL DIST = " + dist);
	}
	
	/*
	public void aStar(int start, int numCity) {
		System.out.println(pq.size());						// TEST -- REMOVE
		while(pq.size() != 0) {
			int v = pq.poll();
			System.out.println("############");				// TEST -- REMOVE
			System.out.println("VERTEX = " + FreightSystem.getCityStr(v));			// TEST -- REMOVE
			for (int w = 0; w < numCity; w++) {
				System.out.println("PATH TO = " + FreightSystem.getCityStr(w));			// TEST -- REMOVE
				int len = FreightSystem.graph.matrix[v][w];
				System.out.println("LENGTH = " + len);		// TEST -- REMOVE
				if (len < 0) {
					System.out.println("NO EDGE -> SKIP");
					continue;
				}
				System.out.println("EDGE EXISTS -> CHECKING IF IT IS SHORTER THAN CURRENT SHORTEST");
				if (dist.get(v) + len < dist.get(w)) {
					System.out.println("NEW PATH FOUND -> EDGE RELAXATION");
					prev.set(w, v);
					dist.set(w, (dist.get(v) + len));
				}
			}
			System.out.println("----------------------");
			System.out.println("PQ SIZE = " + pq.size());
		}
		System.out.println("FINAL PREV SIZE = " + prev.size());
		System.out.println("FINAL PREV = " + prev);
		System.out.println("FINAL DIST SIZE = " + dist.size());
		System.out.println("FINAL DIST = " + dist);
	}
	*/
}