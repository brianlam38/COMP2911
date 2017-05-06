/**
 * COMP2911 FreightSystem Assignment 2
 * Date: April 2017
 * Author: Brian Lam
 */

import java.util.*;

public class AStar {
	
	private PriorityQueue<Score> pq;
	private HashMap<State, Integer> gCost;
	private HashMap<State, Integer> fCost;
	private HashMap<State, Boolean> visited;
	private HashMap<State, State> cameFrom;
	private HashMap<HashSet<Job>, Integer> heuristics;
	
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
		this.gCost = new HashMap<State, Integer>(); 	// real costs
		this.fCost = new HashMap<State, Integer>(); 	// total costs
		this.visited = new HashMap<State, Boolean>();
		this.cameFrom = new HashMap<State, State>();	// tracks path
		this.heuristics = new HashMap<HashSet<Job>, Integer>();
		this.cities = cities;
		this.edges = edges;
		this.jobs = jobs;
	}
	
	/**
	 * Calculate job list cost.
	 * 
	 * Get total cost of remaining jobs in the current state's job list.
	 */
	private int getJobListCost(HashSet<Job> jobs) {
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
		fCost.put(init, getJobListCost(jobs));
		pq.add(new Score(init, gCost.get(init)));

		/*
			While PQ !empty
				// GRABBING DATA TO USE
				grab score of top object
				increase count of expanded nodes (# nodes taken off queue)
				grab currState
				grab currCity
				
				// DETERMINING IF WE HAVE FINISHED
				if currState has no jobs (our work is completed)
					print count of expanded nodes (# nodes taken off queue)
					print total cost
					print path using reconstruct()
					break A*
				
				// MARKING VISITED / NOT VISITED
				if currState !visited
					set currState to visited
				else
					continue

				// ADD IN CURRSTATE'S HEURISTICS
				grab currState jobList
				if no heuristics exist for currJobList 
					add heuristic cost for currJobList
				
				grab currState's cost
				loop through neighbours : check edges
					grab neighbourCity name
					grab currState's heuristic

					declare neighbourJobList
					init neighbourJobList = currStateJobList
					if (SOME STATEMENT)
						DO SOME SHIT

					declare new A*State(neighbourCity, neighbourCityJobList)
					if state has been visited
						skip

					grab actual cost = currState's existing path cost + new edge cost
					if nextJobList size != currJobList size
						// DONT UNDERSTAND THIS LINE

					store where we came from using hashmap<nextState, fromState>
					store actual score so far in nextState
					grab estimated cost = actualCost + heuristicCost
					store estimated cost in nextState
					
					create new comparison object = new Score(nextState, estimatedCost)
					add score object in PQ

				// NOTE: Estimated cost is using ACTUAL_COST + HEURISTIC_COST
				//		 As a basis, you can ignore HEURISTIC_COST at the moment.
				//		 Just take into account only ACTUAL_COST at the moment, then add heuristics later.
		*/

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
			if (!heuristics.containsKey(currentJobList)) {
				heuristics.put(currentJobList, getJobListCost(currentJobList));
			}
			
			// Loop through neighbours
			int heuristic = heuristics.get(currentJobList);
			for (Map.Entry<String, Integer> next : edges.get(currCity).entrySet()) {
				String nextCity = next.getKey();
				HashSet<Job> nextJobList = new HashSet<Job>();
				nextJobList = copyJobsList(currentJobList);
				if (nextJobList.remove(new Job(currCity, nextCity, edges.get(currCity).get(nextCity)))) {
					// heuristic -= edges.get(currCity).get(nextCity);
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
				// Update the new state with cost and path information then add into PQ
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

		// while curr state != from state
		// 		if prevState jobSize != currState jobSize (state change)
		//			replace "Empty" with "Job"
		// 		else
		//			use "Empty"
		// 
		// 		store result = "store the line (Job Sydney to Wagga)" + newline + prev results
		//		^ This concatenates the output, to make sure it prints from FIRST_JOB -> LAST_JOB
		//
		// print out the entire stored result
		
		System.out.println(count + " nodes expanded");
		System.out.printf("cost = " + "%d" + "\n", realScore.get(currState));

		while (!currState.equals(from)) {
			String loaded = "Empty";
			State prevState = path.get(currState);
			if (prevState.getJobs().size() != currState.getJobs().size()) {
				loaded = "Job";
			}
			result = 	loaded
						+ " " + 
						prevState.getCity() +  
						" to " + 
						currState.getCity() + "\n" + result;
			currState = prevState;
		}
		// Print entire concatenated string
		System.out.println(result);
	}
}
