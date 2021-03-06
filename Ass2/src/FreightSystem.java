/**
 * COMP2911 FreightSystem Assignment 2
 * Date: April 2017
 * Author: Brian Lam
 */

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.*;

/**
 * FreightSystem reads in input, parses it and then executes commands to the rest of the system.
 */
public class FreightSystem {

	public HashMap<String, Integer> cities;
	public HashMap<String, HashMap<String, Integer>> edges;
	public HashSet<Job> jobList;
	
	/**
	 * FreightSystem constructor
	 */
	public FreightSystem() {
		this.cities = new HashMap<String, Integer>();
		this.edges = new HashMap<String, HashMap< String, Integer>>();
		this.jobList = new HashSet<Job>();
	}
	
	/**
	 * Parses input line-by-line and issues commands.
	 */
	public static void main(String[] args) {
		FreightSystem sys = new FreightSystem();
		Scanner sc = null;
		try {
			sc = new Scanner(new FileReader(args[0])); 
			while (sc.hasNextLine()) { 
				String line = sc.nextLine();
				String[] input = line.split("\\s+");			
				// Parses unload cost and creates edge
				if (input[0].equals("Unloading")) {
					sys.cities.put(input[2], Integer.parseInt(input[1]));		// insert HM<city, unloadCost>
					sys.edges.put(input[2], new HashMap<String, Integer>());	// insert HM<city, HM<city, weightCost>>
				}
				// Parse edge weight input
				if (input[0].equals("Cost")) {
					// Grab two key cities and add in edge weight
					sys.edges.get(input[2]).put(input[3], Integer.parseInt(input[1]));
					sys.edges.get(input[3]).put(input[2], Integer.parseInt(input[1]));
				}
				// Parses job input
				if (input[0].equals("Job")) {
					Job job = new Job(input[1], input[2], sys.edges.get(input[1]).get(input[2]));
					sys.jobList.add(job);
				}
			}
			// Run A* algorithm
			AStar A = new AStar(sys.cities, sys.edges, sys.jobList);
			A.runAStar();
		}
		catch (FileNotFoundException e) {
		}
		finally {
			if (sc != null) sc.close();
		}
	}	
}