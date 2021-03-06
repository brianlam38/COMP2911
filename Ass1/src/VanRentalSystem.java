import java.io.*;
import java.util.*;
import java.util.Map.Entry;
import java.time.*;
//import java.time.format.DateTimeFormatter;

public class VanRentalSystem {

	/**
	 * Converts month string to integer form.
	 * @return
	 */
	private static int convertMonthToInt(String month) {
		String[] months = {"Jan", "Feb", "Mar", "Apr", "May", "Jun", "Jul", "Aug", "Sep", "Oct", "Nov", "Dec"};
		for (int i = 0; i < 12; i++) {
			if (month.equals(months[i])) {
				return i+1;
			}
		}
		return 0;
	}
	
	/**
	 * Creates a start date and time object
	 * @return
	 */
	public static LocalDateTime setStart(String hourStr, String monthStr, String dateStr) {
		int hour = Integer.parseInt(hourStr);
		int month = convertMonthToInt(monthStr);											// Convert str format to int
		int date = Integer.parseInt(dateStr);
		LocalDateTime startDateTime = LocalDateTime.of(2017, month, date, hour, 0, 0, 0);	// Create date time obj
		// DateTimeFormatter formatter = DateTimeFormatter.ofPattern("HH:mm MMM dd");
		// String dateTimeStr = startDateTime.format(formatter);							// Convert date time to str w/ custom format
		return startDateTime;
	}

	/**
	 * Creates an end date and time object
	 * @return
	 */
	public static LocalDateTime setEnd(String hourStr, String monthStr, String dateStr) {
		int hour = Integer.parseInt(hourStr);
		int month = convertMonthToInt(monthStr);										// Convert str format to int
		int date = Integer.parseInt(dateStr);
		LocalDateTime endDateTime = LocalDateTime.of(2017, month, date, hour, 0, 0, 0);	// Create date time obj
		// formatter = DateTimeFormatter.ofPattern("HH:mm MMM dd");
		// String dateTimeStr = endDateTime.format(formatter);							// Convert date time to str w/ custom format
		return endDateTime;
	}
	
	public static void main(String[] args) {
		Scanner sc = null;
		try {
			String currDepot = "empty";
			int depotOrder = 0;
			HashMap<Integer, Booking> bookingMap = new HashMap<Integer, Booking>();	// BOOKING HASHMAP <booking ID, booking obj>
			HashMap<Integer, VanDepot> depotMap = new HashMap<Integer, VanDepot>();	// DEPOT HASHMAP <order declared, VanDepot obj>
			
			sc = new Scanner(new FileReader(args[0])); 
			while (sc.hasNextLine()) { 
				String line = sc.nextLine();
				String[] input = line.split("\\s+"); 	// separates string by whitespace and stores in array
				
				// Parsing input into Depot and Van HashMaps
				if (input[0].equals("Location")) {
					System.out.println("------------------------------------------------ LOCATION-");
					String depotName = input[1];
					String vanName = input[2];
					String vanType = input[3];
					
					// first depot
					if (currDepot.equals("empty")) {
						currDepot = depotName;
						VanDepot depot = new VanDepot(depotName);
						depotMap.put(depotOrder, depot);
						depot.addVan(depotName, vanName, vanType);
					// existing depot
					} else if (currDepot.equals(depotName)) {
						depotMap.get(depotOrder).addVan(depotName, vanName, vanType);
					// new depot
					} else if (!(currDepot.equals(depotName))){
						depotOrder++;
						currDepot = depotName;
						VanDepot depot = new VanDepot(depotName);
						depotMap.put(depotOrder, depot);
						depot.addVan(depotName, vanName, vanType);
					}

					/* ############### PRINT TESTS ############### */
					System.out.println("THIS IS THE DEPOT NAME:" + depotMap.get(depotOrder).name);
					System.out.println("THIS IS THE DEPOT ORDER DECLARED:" + depotOrder);
					System.out.println("THIS IS THE VAN NAME:" + vanName);
					System.out.println("THIS IS THE VAN TYPE:" + vanType);

				}
				// Skip comments
				if (input[0].equals("#") || input[0].isEmpty()) {
					System.out.println("------------------------------------------------ COMMENT/EMPTY - REMOVED");
					continue;
				}
				// Do stuff with booking requests
				if (input[0].equals("Request")) {
					System.out.println("------------------------------------------------ BOOKING REQUEST");
					//Request <id> <hour1> <month1> <date1> <hour2> <month2> <date2> <num1> <type1> [<num2> <type2>]
					int bookingID = Integer.parseInt(input[1]);
					LocalDateTime startBooking = setStart(input[2], input[3], input[4]);	// determine booking start
					LocalDateTime endBooking = setEnd(input[5], input[6], input[7]);		// determine end date
					
					/**
					 * CHECK IF THERE IS ONE VANTYPE OR MIXTYPE BOOKING
					 */
					int numAuto, numManual = 0;
					if (input[9].equals("Automatic")) {				// store # auto/man bookings
						numAuto = Integer.parseInt(input[8]);
						numManual = 0;
					} else {
						numManual = Integer.parseInt(input[8]);
						numAuto = 0;
					}
					
					if (line.length() > 41) {						// store #auto/man bookings
						if (input[11].equals("Automatic")) {
							numAuto = Integer.parseInt(input[10]);
						} else {
							numManual = Integer.parseInt(input[10]);
						}					
					}
					
					// Check available vans
					// Chuck them into list
					// Assign them each to booking + request new booking				
					/**
					 * ASSIGN AUTO + MANUAL VANS TO A VANLIST, THEN ADD VANS IN VANLIST TO BOOKING CLASS
					 */
					Booking request = new Booking(bookingID, startBooking, endBooking, numAuto, numManual);	// create new booking with list of vans objects booked
					bookingMap.put(bookingID, request);
					
					while (numAuto != 0) {
						for (int order = 0; order < depotOrder; order++) {
							depotMap.get(order).bookAutoVan(numAuto, request);
						}
						numAuto--;
					}

					while (numManual != 0) {
						for (int order = 0; order < depotOrder; order++) {
							depotMap.get(order).bookAutoVan(numManual, request);
						}
						numManual--;
					}
					
					Iterator<CamperVan> itr = request.vanList.iterator();
					while (itr.hasNext()) {
						System.out.println("VANS INSIDE BOOKING " + bookingID + " " + "ARE: " + itr.next());
					}
					
					System.out.println("################## ASSIGNMENT OUTPUT ##################");
					//System.out.println("Booking " + request.ID + " " + depot + " " listOfVans + " " );
					
					// OUTPUT FORMAT
					// Booking [id] [depot1] [van, van, van]; [depot2] [van]
					// Change [id] [depot1] [van]; [depot2] [van]
					// Cancel [id]
					// Booking rejected
					// [PrintDepot] [van] [startDateTime] [endDateTime]
					// [PrintDepot] [van] [startDateTime] [endDateTime]
					
					
					// If booking date/time valid:
						// In order declared, get(orderDeclared from 0-n), search through each Depot's vanMap
					// If van exists:
						// Assign vans to Booking Hashmap (in order of declaration)
					// Else go to next Depot
					
					/* ############### PRINT TESTS ############### */
					System.out.println("THE BOOKING ID IS: " + request.ID);
					System.out.println("BOOKING START: " + request.start);
					System.out.println("BOOKING END: " + request.end);
					System.out.println("BOOKING #AUTO: " + request.auto);
					System.out.println("BOOKING #MANUAL: " + request.manual);
				}
				// Do stuff with change requests
				if (input[0].equals("Change")) {
					System.out.println("------------------------------------------------ BOOKING CHANGE");
				}
				// Do stuff with cancellation requests
				if (input[0].equals("Cancel")) {
					System.out.println("------------------------------------------------ BOOKING CANCELLATION");
				}
				// Do stuff with
				if (input[0].equals("Print")) {
					System.out.println("------------------------------------------------ PRINT THINGS");

					// Print bookings of all vehicles at specified depot in order of vehicle declarations
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