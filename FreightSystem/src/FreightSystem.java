/**
 * COMP2911 FreightSystem Assignment 2
 * Date: April 2017
 * Author: Brian Lam
 */

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.*;

// SUMMARY
// Main idea is to implement A* with a variety of different "strategy patterns" / different heuristic functions.
// 1. Build basic initial Heuristic (Euclidean distance Heuristic)
// 2. Test the base A* algorithm
// 3. Build a second improved Heuristic function (do some research on this after building base version)
// 4. Test the improved A* algorithm

// OUTPUT
// n nodes expanded (number of nodes taken OFF the queue)
// if soln exists
//		output total cost of the solution as an integer (should be same regardless of Heuristic / path)
// else
//		output "No solution"
// Sequence of trips that make up the optimal solution
//		Shown as Job Sydney to Bathurst
//		Shown as Empty Bathurst to Wagga

/**
 * DESIGN
 */
// 1. FreightSystem		- MAIN SYSTEM
//						-> Parse and processes input
//						-> Generates graph obj + inserts data into it
//						-> Stores other input to be used by other classes
//						- DATA
//						-> HashMap<String, Integer> unloadCost
//						-> HashMap<String, Integer> cities	(index number = cities, used throughout system)
//						-> GraphMatrix graph object (main graph to store edges/weights)
//						-> int numVertices
//						
//						- METHODS
//						-> Public: getCity(String cityName): return city number
//						-> Public: getUnloadCost(String cityName): return unload cost

//						-> Delegates input / requests to the STRATEGY object
//						-> Delegates _unloading_ input to the OPTIMISER
//						-> Delegates _travelCost_ input to the OPTPIMISER

// 2. GraphMatrix		- MATRIX REPRESENTATION OF THE GRAPH
//						-> Contains information about vertices, edges and their weights
//						- DATA
//						-> 2D Matrix
//						- METHODS
//						-> void addEdge
//						-> void removeEdge
//						-> boolean isConnected: is v connected with w?

// 3. Strategy			- STRATEGY SELECTOR SYSTEM: AN INTERFACE THAT DEFINES BEHAVIOUR OF PATH SELECTION
//						-> Selects the CONCRETE STRATEGY object to be used (heuristic function / strategy)
//						-> Calls methods from the selected CONCRETE STRATEGY

// 4. Concrete Strategy - CONCRETE STRATEGY / HEURISTIC
//						-> Contains methods to calculate the Heuristic cost
//						-> Returns the Heuristic cost to be used in calculating the total cost

// 5. Optimiser			- PRIMARY ALGORITHM
//						-> Performs the job unloading / travel operations
//						- DATA
//						-> PQ
//						-> prev[] array / collection >> the previous vertex
//						-> dist[] array / collection >> the cost so far
//						- METHODS
//						-> Private Method to initialise prev[] = -1			>> using #vertices from main sys
//						-> Private Method to initialise dist[] = infinite	>> using #vertices from main sys
//						
//						-> Set dist[sourceNode] = 0
//						->


public class FreightSystem {
	
	public static HashMap<String, Integer> unloadCost = new HashMap<String, Integer>();
	public static HashMap<String, Integer> city = new HashMap<String, Integer>();
	public static HashMap<Integer, String> cityStr = new HashMap<Integer, String>();
	public static GraphMatrix graph;
	public static AStar algorithm;

	private static int numCity = 0;
	
	/**
	 * Take city string as key and return city/vertex number.
	 */
	public static int getCityID(String cityName) {
		return city.get(cityName);
	}
	
	/**
	 * Take cityID as key and return city string name.
	 */
	public static String getCityStr(int cityID) {
		return cityStr.get(cityID);
	}
	
	
	/**
	 * Take city string as key and return unload cost for city.
	 */
	public static int getUnloadCost(String cityName) {
		return unloadCost.get(cityName);
	}
	
	/**
	 * Return weight of edge between two vertices.
	 */
	public static int getWeight(int v, int w) {
		return graph.matrix[v][w];
	}
	
	/**
	 * Parses input line-by-line and issues commands.
	 */
	public static void main(String[] args) {
		boolean graphExists = false;
		boolean AStarExists = false;
		Scanner sc = null;
		try {
			sc = new Scanner(new FileReader(args[0])); 
			while (sc.hasNextLine()) { 
				String line = sc.nextLine();
				String[] input = line.split("\\s+");			
				// Parses unloading cost and city strings
				if (input[0].equals("Unloading")) {
					System.out.println("----------------------- PARSE UNLOAD COST AND CITY STRINGS -----------------------");
					int unload = Integer.parseInt(input[1]);
					unloadCost.put(input[2], unload);
					city.put(input[2], numCity);
					cityStr.put(numCity, input[2]);
					numCity++;
					
					// --- PRINT TESTS ------------------------------------------------------------------------------------
					System.out.println("### CURRENT CITIES/UNLOADS KNOWN ###");
					System.out.println("NUMBER OF CITIES:" + numCity);
					// CITY H.M: Print key / value pairs
					Set<String> keySet = city.keySet();
					System.out.println("CITY KEYSET = " + keySet);
					Collection<Integer> vSet = city.values();
					System.out.println("CITY VALUESET = " + vSet);
					// CITYSTR H.M: Print key / value pairs
					Set<Integer> keyStr = cityStr.keySet();
					System.out.println("CITYSTR KEYSET = " + keyStr);
					Collection<String> citySet = cityStr.values();
					System.out.println("CITYSTR VALUESET = " + citySet);
					// UNLOAD H.M: Print key / value pairs
					Collection<Integer> valueSet = unloadCost.values();
					System.out.println(valueSet);
					System.out.println("### ############################ ###");
					// --- END TESTS ------------------------------------------------------------------------------------
				}
				// Create GraphMatrix object w/ matrix and Parses travel cost input into Matrix
				if (input[0].equals("Cost")) {
					System.out.println("----------------------- PARSE GRAPH EDGES AND WEIGHTS -----------------------");
					Set<String> keySet = city.keySet();
					System.out.println("CITY KEYSET = " + keySet);
					
					if (graphExists == false) {
						graph = new GraphMatrix(numCity);
						graphExists = true;
					}
					int travelCost = Integer.parseInt(input[1]);
					String src = input[2];
					String dest = input[3];
					graph.addEdge(travelCost, city.get(src), city.get(dest));
					
					// --- PRINT TESTS ---------------------------------------------------------------------------------
					int row = 0;
					for (int i = 0; i < numCity; i++) {
						for (int j = 0; j < numCity; j++) {
							System.out.print("[" + graph.matrix[i][j] + "]");
						}
						row++;
						System.out.println("  ---  ROW = " + row);
					}
					System.out.println("______________________");
					// --- END TESTS ------------------------------------------------------------------------------------
				}
				// Parses specific job strings
				if (input[0].equals("Job")) {
					System.out.println("----------------------- PARSE JOB REQUESTS -----------------------");
					// Create A* object and initialise prev/dist arrays
					if (!AStarExists) {
						algorithm = new AStar();
						algorithm.setDone(numCity);
						algorithm.setDist(numCity);
						AStarExists = true;
					}
					// Add jobs
				}
			}
			// Run A* algorithm
			algorithm.aStar(city.get("Sydney"), numCity);
		}
		catch (FileNotFoundException e) {
		}
		finally {
			if (sc != null) sc.close();
		}
	}	
}