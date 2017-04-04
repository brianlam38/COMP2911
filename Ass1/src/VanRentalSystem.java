import java.io.*;
import java.util.*;

// System will store a hashmap of string depos
// Depos will store a hashmap of string vans
// The string values will be the hashmap keys

// How do we store other attributes such as auto/manual in the HashMap?
// Store the van object in the HashMap.
// The name is just the key
// HashMap(String, Van)

public class VanRentalSystem {
	
	public static void main(String[] args) {
		Scanner sc = null;
		HashMap<String, String> hmap = new HashMap<String, String>();
		String locationStr = "Location";
		String commentStr1 = "        #";
		String commentStr2 = "#";
		String requestStr = "Request";
		String changeStr = "Change";
		String cancelStr = "Cancel";
		String printStr = "Print";

		try {
			sc = new Scanner(new FileReader(args[0]));  
			while (sc.hasNextLine()) { 			// while there is something else in the file	
				String line = sc.nextLine(); 	// reads in a line and stores in String object
				
				/**
				 *  THIS SECTION DETERMINES THE MAIN ACTIONS TO BE TAKEN DEPENDING ON INPUT
				 */
				// Parse Depot into System HashMap
				if (line.startsWith(locationStr) == true) {
					System.out.println("--- LOCATION LINE");
					//hmap.put(key, value);
				}
				// 
				if ((line.startsWith(commentStr1) == true) || (line.startsWith(commentStr2) == true)) {
					System.out.println("--- COMMENT LINE");
				}
				
				if (line.startsWith(requestStr) == true) {
					System.out.println("--- REQUEST LINE");
				}
				
				if (line.startsWith(changeStr) == true) {
					System.out.println("--- CHANGE LINE");
				}
				
				if (line.startsWith(cancelStr) == true) {
					System.out.println("--- CANCEL LINE");
				}
				
				if (line.startsWith(printStr) == true) {
					System.out.println("--- PRINT LINE");
				}
				
				// (2) View hashmap
					// Get a set of the hashmap entries
				Set set = hmap.entrySet();
					// Get an iterator OR
					// Don't use an iterator, simply use the get() put() functions
					// (If you don't need to perform operations of key-value pairs)
				
				// (3) Do request, change, cancellation operations
				
				
				
				
				// Helps me check that input is still working
				//System.out.println("LINE:" + lineNum++);	// prints out line number
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
