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

