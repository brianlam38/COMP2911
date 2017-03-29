import java.io.*;
import java.util.*;

public class VanRentalSystem {
	// Begin taking in input
	Scanner sc = null;
    try {
		sc = new Scanner(new FileReader(args[0]));    // # args[0] is the first command line argument
        // The above simply opens the file to make it readable
        // You must add code below to do stuff with the opened file :)
    }
	// Doing error testing. Handling exceptions.
    catch (FileNotFoundException e) {
    	
    }
	
	// Everything is done, closing the file
    finally {
        if (sc != null) sc.close();
    }	
}
