import java.io.*;
import java.util.*;
import java.time.*;
import java.time.format.DateTimeFormatter;
import java.text.*;

// Use LocalDateTime

public class VanRentalSystem {
	
	private static int convertMonthToInt(String month) {
		String[] months = {"Jan", "Feb", "Mar", "Apr", "May", "Jun", "Jul", "Aug", "Sep", "Oct", "Nov", "Dec"};
		for (int i = 0; i < 12; i++) {
			if (month.equals(months[i])) {
				return i;
			}
		}
		return 0;
	}
	
	public static void main(String[] args) {
		Scanner sc = null;
		try {
			HashMap<String, VanDepot> depotMap = new HashMap<String, VanDepot>();
			sc = new Scanner(new FileReader(args[0])); 
			while (sc.hasNextLine()) { 					// while file !empty
				String line = sc.nextLine(); 			// reads in a line and stores in String object
				String[] input = line.split("\\s+"); 	// separates string by whitespace and stores in array
				
				// Parsing input into Depot and Van HashMaps
				if (input[0].equals("Location")) {
					System.out.println("--- LOCATION LINE ------------------------"); // test line (REMOVE)
					String depotName = input[1];
					String vanName = input[2];
					String vanType = input[3];
					
					VanDepot depot = new VanDepot(depotName);		// create depot obj
					depotMap.put(depotName, depot);					// hash in depot obj w/ depotName key
					depot.addVan(vanName, vanType);					// hash in van obj w/ vanName key
					
					/* ############### PRINT TESTS ############### */
					System.out.println("THIS IS THE DEPOT NAME:" + depotMap.get(depotName).name);
					System.out.println("THIS IS THE VAN NAME:" + vanName);
					System.out.println("THIS IS THE VAN TYPE:" + vanType);
				}
				// Skip comments
				if (input[0].equals("#")) {
					System.out.println("--- COMMENT LINE ------------------------");
					continue;
				}
				// Do stuff with booking requests
				if (input[0].equals("Request")) {
					System.out.println("--- REQUEST LINE ------------------------");
					//Request <id> <hour1> <month1> <date1> <hour2> <month2> <date2> <num1> <type1> [<num2> <type2>]
					int bookingID = Integer.parseInt(input[1]);
					int hour = Integer.parseInt(input[2]); 
					String monthStr = input[3];
					int month = convertMonthToInt(monthStr);	// convert str format to int
					int date = Integer.parseInt(input[4]);
					int year = 2017;
					
					
					LocalDateTime dateTime = LocalDateTime.of(year, month, date, hour, 0, 0, 0);		// Create date time obj
					DateTimeFormatter formatter = DateTimeFormatter.ofPattern("HH:mm MMM dd");			// Custom format
					String dateTimeStr = dateTime.format(formatter);									// Convert date time to str w/ custom format
					
					
					/* ############### PRINT TESTS ############### */
					System.out.println(formatDateTime);
					System.out.println("THE BOOKING ID IS: " + bookingID);
				}
				// Do stuff with change requests
				if (input[0].equals("Change")) {
					System.out.println("--- CHANGE LINE ------------------------");
				}
				// Do stuff with cancellation requests
				if (input[0].equals("Cancel")) {
					System.out.println("--- CANCEL LINE ------------------------");
				}
				// Do stuff with
				if (input[0].equals("Print")) {
					System.out.println("--- PRINT LINE ------------------------");
				}
				
				// DATE TIME PRINT FORMAT
				// hour     HH:MM   (24:00 system)
				// month    MMM     (JAN, FEB, MAR)
				// day/date DD      (1-31)

				System.out.println(line);	// prints the line
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