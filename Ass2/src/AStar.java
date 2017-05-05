/**
 * COMP2911 FreightSystem Assignment 2
 * Date: April 2017
 * Author: Brian Lam
 */

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.PriorityQueue;

public class AStar {
	
	private HashMap<String, Integer> cities;
	private HashMap<String, HashMap<String, Integer>> edges;
	private HashSet<Job> jobs;

	/**
		Constructor for AStarSearch
		
		Takes in data from FreightSystem for use in the algorithm.
	*/
	public AStar(	HashMap<String, Integer> cities,
		   				HashMap<String, HashMap<String, Integer>> edges,
		   				HashSet<Job> jobs) {
		this.cities = cities;
		this.edges = edges;
		this.jobs = jobs;
	}

	private Integer calculateMinUnloadingCost(HashSet<Job> jobs) {
		Integer result = 0;
		for (Job job : jobs) {
			result += job.getCost();
		}
		return result;
	}
	
	public void runAStar() {
		HashMap<State, Boolean> visited = new HashMap<State, Boolean>();
		PriorityQueue<Score> pq = new PriorityQueue<Score>();	// PQ with comparator Score
		HashMap<State, Integer> gScore = new HashMap<State, Integer>(); //actualCost
		HashMap<State, Integer> fScore = new HashMap<State, Integer>(); //estimatedCost
		HashMap<State, State> cameFrom = new HashMap<State, State>();
		HashMap<HashSet<Job>, Integer> heuristics = new HashMap<HashSet<Job>, Integer>();
		
		Integer count = 0;
		State init = new State("Sydney", jobs);		// init state = startCity + joblist
		gScore.put(init, 0);									// init 0 realCost to starting state
		fScore.put(init, calculateMinUnloadingCost(jobs));		// init 0 estCost to starting state
		pq.add(new Score(init, gScore.get(init)));				// add this starting state to PQ

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
			Score scr = pq.poll();
			count++;
			State currState = scr.getState();
			String currCity = currState.getCity();

			if (currState.getJobs().isEmpty()) {
				System.out.println(count + " nodes expanded");
				System.out.printf("cost = " + "%d" + "\n", gScore.get(currState));
				reconstruct(init, currState, cameFrom);
				break;
			}

			if (visited.get(currState) == null) {
				visited.put(currState, true);
			} else {
				continue;
			}

			HashSet<Job> currentJobList = currState.getJobs();
			if (!heuristics.containsKey(currentJobList)) {
				heuristics.put(currentJobList, calculateMinUnloadingCost(currentJobList));
			}
			Integer mHeuristic = heuristics.get(currentJobList);
			for (Map.Entry<String, Integer> next : edges.get(currCity).entrySet()) {
				String nextCity = next.getKey();
				
				Integer heuristic = mHeuristic;
				HashSet<Job> nextJobList = new HashSet<Job>();
				nextJobList = clonejobs(currentJobList);
				if (nextJobList.remove(new Job(currCity, nextCity, edges.get(currCity).get(nextCity)))) {
					// heuristic -= edges.get(currCity).get(nextCity);
				}
				
				State nextState = new State(nextCity, nextJobList);
				if (visited.containsKey(nextState)) {
					continue;
				}
				
				Integer actualCost = gScore.get(currState) + edges.get(currCity).get(nextCity);
				if (nextJobList.size() != currentJobList.size()) {
					actualCost += cities.get(nextCity);
				}
				cameFrom.put(nextState, currState);
				gScore.put(nextState, actualCost);
				Integer estimatedCost = actualCost + heuristic;
				fScore.put(nextState, estimatedCost);
				Score sc = new Score(nextState, estimatedCost);
				pq.add(sc);	
			}
		}	
	}

	@SuppressWarnings("unchecked")
	private HashSet<Job> clonejobs(HashSet<Job> currentJobList) {
		return (HashSet<Job>) currentJobList.clone();
	}
	
	private static void reconstruct(State from, State to,
		HashMap<State, State> path) {
		State currState = to;			// Takes in a "TO" state
		String result = "";					// Declare + init empty result

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

		while (!currState.equals(from)) {
			String loaded = "Empty";
			State prevState = path.get(currState);
			if (prevState.getJobs().size() != currState.getJobs().size()) {
				loaded = "Job";
			}
			result = 	loaded
						+ " from " + 
						prevState.getCity() +  
						" to " + 
						currState.getCity() + "\n" + result;
			currState = prevState;
		}
		System.out.println(result);
	}

}
