import java.io.*;
import java.util.*;

// need to add import for ArrayListSet class

public class VanRentalSystem {
	public static void main(String[] args) {
		Scanner sc = null;
		try {
			Set<Integer> mySet = new ArrayListSet<Integer>();
			sc = new Scanner(new FileReader(args[0]));  
	    
			while (sc.hasNext()) { // while there is something else in the file
	    	sc.nextInt(); // reads in an int from standard input // keep doing this until end of input
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
