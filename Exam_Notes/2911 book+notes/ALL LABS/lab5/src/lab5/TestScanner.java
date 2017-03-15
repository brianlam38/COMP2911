package lab5;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.Scanner;


public class TestScanner {
	
    public static void main(String[] args) {

    Scanner sc = null;
    try
    {
    	Set<Integer> mySet = new ArrayListSet<Integer>();
        sc = new Scanner(new FileReader(args[0]));    
        while (sc.hasNext()) { // while there is something else in the file
        	sc.nextInt(); // reads in an int from standard input // keep doing this until end of input
        }
        
    }
    catch (FileNotFoundException e) {}
    finally
    {
        if (sc != null) sc.close();
    }

}

}