/**
 * COMP2911 FreightSystem Assignment 2
 * Date: April 2017
 * Author: Brian Lam
 */

import java.util.*;

public class Job {

	// IDEAS
	
	// check if all jobs are completed.
	// if not
	//		continue A* search
	// else
	//		stop search.
	//		return success
	
	// at node N, check if a neighbour is a goal
	// if neighbour is a goal,
	//		go to neighbour
	// else
	//		next shortest path to take?
	//		OR
	//		go towards the next closest goal?
	//		(maybe store info about all neighbour nodes of every goal node, so if neighbour
	//		non-goal node is next + it is also a "nearby" goal node, then go to that node)
	
	int source;
	int goal;
	
	public Job(int src, int g) {
		this.source = src;
		this.goal = g;
	}
	
	public int getStart() {
		return source;
	}
	
	public int getEnd() {
		return goal;
	}
}
