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
	
	private static void addVanToDepot(HashMap<String, VanDepot> depotMap, String depotName, CamperVan van) {
		System.out.println("--- LOCATION LINE");
		depotMap.get(depotName).addVan(van);			// Place Key/Value into HashMap
	}
	
	public static void main(String[] args) {
		Scanner sc = null;
		HashMap<String, VanDepot> depotMap = new HashMap<String, VanDepot>();
		
		try {
			sc = new Scanner(new FileReader(args[0]));  
			while (sc.hasNextLine()) { 			// while there is something else in the file	
				String line = sc.nextLine(); 	// reads in a line and stores in String object
				
				/**
				 *  THIS SECTION DETERMINES THE MAIN ACTIONS TO BE TAKEN DEPENDING ON INPUT
				 */
				
				// THINGS TO DO:
					// Work out how to add van objects into depot hashmap, through using the depot
				
				// Grab line input separated by whitespace
				String[] input = line.split("\\s+");
				
				// Parse Depot into System HashMap
				if (input[0].equals("Location")) {
					String depotName = input[1];
					String vanName = input[2];
					String vanType = input[3];
					
					VanDepot depot = new VanDepot(depotName);			// Create a depo object
					CamperVan van = new CamperVan(vanName, vanType);	// Create van object
					addVanToDepot(depotMap, depotName, van);			// Insert van object into HashMap
					
					String name = depotMap.get(depotName).name;
					System.out.println("THIS IS THE VAN NAME:" + name);
				}
				// Do stuff with comments
				if (input[0].equals("#")) {
					System.out.println("--- COMMENT LINE");
				}
				// Do stuff with booking requests
				if (input[0].equals("Request")) {
					System.out.println("--- REQUEST LINE");
				}
				// Do stuff with change requests
				if (input[0].equals("Change")) {
					System.out.println("--- CHANGE LINE");
				}
				// Do stuff with cancellation requests
				if (input[0].equals("Cancel")) {
					System.out.println("--- CANCEL LINE");
				}
				// Do stuff with
				if (input[0].equals("Print")) {
					System.out.println("--- PRINT LINE");
				}
				
				// (2) View hashmap
					// Get a set of the hashmap entries
				Set set = depotMap.entrySet();
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
