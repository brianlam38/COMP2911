/**
 * COMP2911 FreightSystem Assignment 2
 * Date: April 2017
 * Author: Brian Lam
 */

import java.util.*;


/**
 * AStar finds the optimal path for completing the jobs provided by the main system.
 * 
 * It takes in graph and joblist information provided by the main system.
 * It tracks path costs, heuristic costs and total estimated costs to determine optimal path.
 * Uses a Priority Queue to analyse states and assist with determining the optimal path.
 * Tracks the path taken so it can be relayed as output.
 */
public class AStar {
	private PriorityQueue<Score> pq;
	private HashMap<State, Boolean> visited;
	private HashMap<State, State> cameFrom;
	
	private HashMap<State, Integer> fCost;	 			// estimated total cost
	private HashMap<State, Integer> gCost; 				// path cost
	private HashMap<HashSet<Job>, Integer> hCost;		// heuristic cost
	
	private HashMap<String, Integer> cities;
	private HashMap<String, HashMap<String, Integer>> edges;
	private HashSet<Job> jobs;

	/**
		Constructor for AStarSearch
		
		Takes in data from FreightSystem for use in the algorithm.
		Stores data structures for Priority Queue, Heuristic costs, Visited states and path taken.
	*/
	public AStar(HashMap<String, Integer> cities, HashMap<String, HashMap<String, Integer>> edges, HashSet<Job> jobs) {
		this.pq = new PriorityQueue<Score>();
		this.visited = new HashMap<State, Boolean>();
		this.cameFrom = new HashMap<State, State>();	
		this.fCost = new HashMap<State, Integer>();
		this.gCost = new HashMap<State, Integer>();
		this.hCost = new HashMap<HashSet<Job>, Integer>();
		this.cities = cities;
		this.edges = edges;
		this.jobs = jobs;
	}
	
	/**
	 * Calculate job list cost.
	 * 
	 * Get total cost of remaining jobs in the current state's job list.
	 */
	private int getTotalPathCost(HashSet<Job> jobs) {
		int result = 0;
		for (Job job : jobs) {
			result += job.getCost();
		}
		return result;
	}
	
	/**
	 * A Star algorithm.
	 * 
	 * Using a priority queue, push in states, compare costs, and track jobs 
	 * being completed until arriving at a state with an empty job list.
	 */
	public void runAStar() {
		Integer count = 0;
		State init = new State("Sydney", jobs);
		gCost.put(init, 0);
		fCost.put(init, getTotalPathCost(jobs));
		pq.add(new Score(init, gCost.get(init)));
		
		// Begin main PQ operations
		while (!pq.isEmpty()) {
			Score score = pq.poll();
			count++;
			State currState = score.getState();
			String currCity = currState.getCity();
			// Goal state has been reached, no jobs remaining! Woohoo
			if (currState.getJobs().isEmpty()) {
				printOptimalPath(count, gCost, init, currState, cameFrom);
				break;
			}
			// Skip visited
			if (visited.get(currState) == null) {
				visited.put(currState, true);
			} else {
				continue;
			}
			// Grab current state's job list, add total cost to HM
			HashSet<Job> currentJobList = currState.getJobs();
			if (!hCost.containsKey(currentJobList)) {
				hCost.put(currentJobList, getTotalPathCost(currentJobList));
			}
			// Loop through neighbours
			int heuristic = hCost.get(currentJobList);
			for (Map.Entry<String, Integer> next : edges.get(currCity).entrySet()) {
				String nextCity = next.getKey();
				HashSet<Job> nextJobList = new HashSet<Job>();
				nextJobList = copyJobsList(currentJobList);
				// Remove job from joblist.
				if (nextJobList.remove(new Job(currCity, nextCity, edges.get(currCity).get(nextCity)))) {
					heuristic -= edges.get(currCity).get(nextCity);
				}
				// Check if neighbour has been visited
				State nextState = new State(nextCity, nextJobList);
				if (visited.containsKey(nextState)) {
					continue;
				}
				// Add unload cost to actual cost
				int actualCost = gCost.get(currState) + edges.get(currCity).get(nextCity);
				if (nextJobList.size() != currentJobList.size()) {
					actualCost += cities.get(nextCity);
				}
				// Update the new state with cost and path information then add to PQ
				cameFrom.put(nextState, currState);
				gCost.put(nextState, actualCost);
				int estimatedCost = actualCost + heuristic;
				fCost.put(nextState, estimatedCost);
				Score sc = new Score(nextState, estimatedCost);
				pq.add(sc);
			}
		}	
	}
	
	/**
	 * Clone a job list.
	 * 
	 * Used to store continuation of remaining jobs in each future state.
	 */
	@SuppressWarnings("unchecked")
	private HashSet<Job> copyJobsList(HashSet<Job> currentJobList) {
		return (HashSet<Job>) currentJobList.clone();
	}
	
	/**
	 * Prints all the output.
	 * 
	 * Prints output for nodes expanded, total cost and sequence of empty trips or job trips.
	 */
	private static void printOptimalPath(int count, HashMap<State, Integer> realScore, State from, State to,
		HashMap<State, State> path) {
		State currState = to;
		String result = "";
		
		System.out.println(count + " nodes expanded");
		System.out.printf("cost = " + "%d" + "\n", realScore.get(currState));

		while (!currState.equals(from)) {
			String loaded = "Empty";
			State prevState = path.get(currState);
			if (prevState.getJobs().size() != currState.getJobs().size()) {
				loaded = "Job";
			}
			result = loaded + " " + prevState.getCity() + " to " + currState.getCity() + "\n" + result;
			currState = prevState;
		}
		// Print entire concatenated string
		System.out.println(result);
	}
}
