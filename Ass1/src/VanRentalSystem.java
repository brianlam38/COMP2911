import java.io.*;
import java.util.*;

// System will store a hashmap of string depos
// Depos will store a hashmap of string vans
// The string values will be the hashmap keys

// How do we store other attributes such as auto/manual in the hashmap?
// Store the van in the hashmap.
// The name is just the key
// HashMap(String, Van)

public class VanRentalSystem {
	
	public static void main(String[] args) {
		Scanner sc = null;
		HashMap<String, String> hmapDepot = new HashMap<String, String>();
		
		int lineNum = 0;
		try {
			sc = new Scanner(new FileReader(args[0]));  
			while (sc.hasNextLine()) { 			// while there is something else in the file	
				String line = sc.nextLine(); 	// reads in a line and stores in String object
				
				/**
				 *  THINGS TO WORK ON
				 */
				
				// Separate the good from the bad.
				
				
				// (1) Parse input into hashmap
						// Read line by line
						// Separate using java scanner methods / regex expressions
						// Use put(key, value) to insert
				
				// (2) View hashmap
					// Get a set of the hashmap entries
				Set set = hmapDepot.entrySet();
					// Get an iterator OR
					// Don't use an iterator, simply use the get() put() functions
					// (If you don't need to perform operations of key-value pairs)
				
				// (3) Do request, change, cancellation operations
				
				
				
				
				// Helps me check that input is still working
				System.out.println("LINE:" + lineNum++);	// prints out line number
				System.out.println(line);					// prints the line
			}
		}

		// Doing error testing. Handling exceptions.
		catch (FileNotFoundException e) {

		}
	
		// Everything is done, closing the file
		finally {
			if (sc != null) sc.close();
		}
	}	
}

// DESIGN STRATEGY
// 1. Main System / Possibly also Booking System (combine 2-in-1)
	// Scanner for entire file
		// + Scanner for each line
		// Knowing each line's format, parse in the info.
		// Based off info parsed, do a request (booking, changes, cancellations)
	// Returns output
// 2. Booking System
	// Makes booking, changes, cancellations
	// Grabs depot stuff, which grabs van stuff
// 3. Depot Class
	// DATA STRUCTURES: Use a Hash Map to store list of depots, which contain a list of vans in each depo
	// Stores depot stuff
	// Grabs van stuff
// 4. Van Class
	// DATA STRUCTURES: Use a Hash Set to store list of vans
	// Stores van stuff
