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
//						-> Parse and processes input / receives requests from client
//						-> Delegates input / requests to the STRATEGY object
//						-> Delegates _unloading_ input to the OPTIMISER
//						-> Delegates

// 2. Strategy			- STRATEGY SELECTOR SYSTEM: AN INTERFACE THAT DEFINES BEHAVIOUR OF PATH SELECTION
//						-> Selects the CONCRETE STRATEGY object to be used (heuristic function / strategy)
//						-> Calls methods from the selected CONCRETE STRATEGY

// 3. Concrete Strategy - CONCRETE STRATEGY / HEURISTIC
//						-> Contains methods to calculate the Heuristic cost
//						-> Returns the Heuristic cost to be used in calculating the total cost

// 4. Optimiser			- PRIMARY ALGORITHM
//						->

public class FreightSystem {
	/**
	 * Parses input line-by-line and issues commands.
	 */
	public static void main(String[] args) {
		Scanner sc = null;
		try {
			sc = new Scanner(new FileReader(args[0])); 
			while (sc.hasNextLine()) { 
				String line = sc.nextLine();
				String[] input = line.split("\\s+");			
				// Parses unloading cost strings
				if (input[0].equals("Unloading")) {
					// do stuff
				}
				// Parses travel cost strings
				if (input[0].equals("Cost")) {
					// do stuff
				}
				// Parses specific job strings
				if (input[0].equals("Job")) {
					// do stuff
				}
			}
		}
		catch (FileNotFoundException e) {
		}
		finally {
			if (sc != null) sc.close();
		}
	}	
}