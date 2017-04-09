/**
 * COMP2911 VanRentalSystem Assignment 1
 * 
 * Date: April 2017
 * Author: Brian Lam
 */

import java.io.*;
import java.util.*;
import java.time.*;
//import java.time.format.DateTimeFormatter;


/**
 * Reads from the input stream and parses commands as per format defined in the assignment spec.
 * The formatted commands will be issued to the booking system for use.
 */
public class VanRentalSystem {

	/**
	 * Converts month string to integer form.
	 */
	private static int monthToInt(String month) {
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
		int month = monthToInt(monthStr);											// Convert str format to int
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
		int month = monthToInt(monthStr);										// Convert str format to int
		int date = Integer.parseInt(dateStr);
		LocalDateTime endDateTime = LocalDateTime.of(2017, month, date, hour, 0, 0, 0);	// Create date time obj
		// formatter = DateTimeFormatter.ofPattern("HH:mm MMM dd");
		// String dateTimeStr = endDateTime.format(formatter);							// Convert date time to str w/ custom format
		return endDateTime;
	}
	
	/**
	 * Books in all automatic and/or manual vans
	 * as instructed by the booking request.
	 * 
	 * Takes in the new booking parameters such as
	 * the start/end date and time and also the
	 * number of auto/manual vans requested.
	 */
	public static void bookAllVans(int numAuto, int numManual, int bookingID, LocalDateTime startBooking, LocalDateTime endBooking) {
		boolean overlap = false;
		while (numAuto != 0) {															// book in all autos
			System.out.println("--- BOOKING AUTO --------- AUTO LEFT = " + numAuto);
			overlap = system.makeVanBooking("Automatic", bookingID, startBooking, endBooking);
			numAuto--;
			if (overlap == true) break;
		}
		while (numManual != 0) {														// book in all manuals
			System.out.println("--- BOOKING MANUAL --------- MANUAL LEFT = " + numManual);
			overlap = system.makeVanBooking("Manual", bookingID, startBooking, endBooking);
			numManual--;
			if (overlap == true) break;
		}
	}

	
	// VanRentalSystem X Read input line-by-line + Parses information into VanSystem
	//				   - #### Methods:
	//				   X Creates a HashMap of <Key DepotName, Object CamperVan>
	//				   X Creates a van object
	//				   X Add van object to VanSystem's ArrayList<CamperVan> (will be in order of declaration)
	//				   X Hash van into HashMap<DepotName, CamperVan> (mainly for "Printing" functionality
	//				   - #### Stores information about:
	//				   X HashMap<key DepotName, object CamperVan>	(mainly for "Printing" functionality
	
	// VanSystem	   - ENTRY POINT FOR VANS + BOOKINGS
	//				   - #### Methods:
	//				   X Creates a VanBooking object (identifier: bookingID)
	//				   X BOOKING: Looks through its ArrayList of vans.
	//				   X 		  For each CamperVan, check if it is available @specified booking time
	//				   X		      Compare REQUESTED TIME / EXISTING TIME in each VanBooking object in Van's List
	//				   X		  If success, add VanBooking object to CamperVan's ArrayList<VanBooking>
	//				   X		  Else check next CamperVan.
	//				   - CHANGES: Change = Cancellation + ReBook
	//				   X		  Perform cancellation
	//				   X		  Perform booking with updated request
	//				   X CANCELLATIONS: Look through its ArrayList of vans.
	//				   X				For each CamperVan
	//				   X					If VanBooking.ID = Cancellation.ID
	//				   X					Delete VanBooking object from ArrayList<VanBooking>
	//				   - #### Stores information about:
	//				   X Integer requestID = bookingID, changeID, cancellationID
	//				   X ArrayList<CamperVan> For each booking, change, cancel, add(CamperVan)     
	//						 For each CamperVan in List
	//						 	  String currDepot
	//							  If currDepot.equals("nothing)					   // First depot instance
	//								 Print CamperVan.depotName
	//								 currDepot = CamperVan.depotName	
	//								 Print currDepot
	//							  Else If !currDepot.equals(CamperVan.depotName)   // New depot instance
	//								 Print ";"
	//								 Print CamperVan.depotName
	//								 currDepot = CamperVan.depotName
	//							  Print CamperVan.name
	//				   - Resulting string should be String: Booking + requestID + (currDepot1 + van.name + van.name + currDepot2 + van.name + van.name)
	//				   - Clear List after every booking, change, cancel
	
	// VanBooking      - VANBOOKING OBJECT (identifier: BookingID)
	//				   - #### Methods:
	//				   - Facilitates performing date/time availability checks for CamperVans
	//				   - Facilitates performing auto/manual checks for CamperVans
	//				   - #### Stores information about:
	//				   X Current BookingID, VanBooking's LocalDateTime start, LocalDateTime end
	
	// CamperVan	   X CAMPERVAN OBJECT (identifier: vanName)
	// 				   - #### Stores information about:
	//				   X DepotName, VanName, VanType, ArrayList<VanBookings
	
	// PrintClass	   - Class to print all the correct output
	//				   - #### Booking
	//				   - String = nothing
	//				   - Called upon by VanSystem per booking, change, cancellation
	
	// OUTPUT FORMAT
	// Booking [id] [depot1] [van, van, van]; [depot2] [van]	// PrintClass
	// Change [id] [depot1] [van]; [depot2] [van] 				// PrintClass
	// Cancel [id]												// Simple Cancel
	// Booking rejected											// VanSystem -> Booking check fails -> print "Booking rejected"
	// [PrintDepot] [van] [startDateTime] [endDateTime]			// VanRentalSystem -> HashMap<DepotName> -> Iterate through every CamperVan -> Print [van.name + startDate + endDate]
	
	static HashMap<String, CamperVan> depotMap = new HashMap<String, CamperVan>();
	static VanSystem system = new VanSystem();
	
	/**
	 * Parses input line-by-line and issues commands.
	 * @param args
	 */
	public static void main(String[] args) {
		Scanner sc = null;
		try {
			sc = new Scanner(new FileReader(args[0])); 
			while (sc.hasNextLine()) { 
				String line = sc.nextLine();
				String[] input = line.split("\\s+");
				
				// Parses depot / van declaration string
				if (input[0].equals("Location")) {
					// Location <depot> <vanName> <vanType> <comment>
					String depotName = input[1];
					String vanName = input[2];
					String vanType = input[3];
					
					CamperVan van = new CamperVan(depotName, vanName, vanType);
					depotMap.put(depotName, van);
					system.realVanList.add(van);

					// Testing size of realVanList					 ################ REMOVE ############################################
					int realSize = system.realVanList.size();
					System.out.println("Adding more vans. SIZE IS NOW: " + realSize);
					for (int i = 0; i < realSize; i++) {
						CamperVan van1 = system.realVanList.get(i);
						System.out.println(van1);
					}
							
				// Skip comments and empty lines	
				} else if (input[0].equals("#") || input[0].isEmpty()) {
					continue;
				// Booking request
				} else if (input[0].equals("Request")) {
					System.out.println("------------------------------------------------ BOOKING REQUEST");
					int bookingID = Integer.parseInt(input[1]);
					LocalDateTime startBooking = setStart(input[2], input[3], input[4]);
					LocalDateTime endBooking = setEnd(input[5], input[6], input[7]);

					int numAuto, numManual = 0;
					if (input[9].equals("Automatic")) {				// determine # auto/man bookings
						numAuto = Integer.parseInt(input[8]);
						numManual = 0;
					} else {
						numManual = Integer.parseInt(input[8]);
						numAuto = 0;
					}
					
					if (line.length() > 41) {						// determine #auto/man bookings
						if (input[11].equals("Automatic")) {
							numAuto = Integer.parseInt(input[10]);
						} else {
							numManual = Integer.parseInt(input[10]);
						}
					}
					
					bookAllVans(numAuto, numManual, bookingID, startBooking, endBooking);
					
					//						 For each CamperVan in List
					//						 	  String currDepot
					//							  If currDepot.equals("nothing)					   // First depot instance
					//								 Print CamperVan.depotName
					//								 currDepot = CamperVan.depotName	
					//								 Print currDepot
					//							  Else If !currDepot.equals(CamperVan.depotName)   // New depot instance
					//								 Print ";"
					//								 Print CamperVan.depotName
					//								 currDepot = CamperVan.depotName
					//							  Print CamperVan.name
					
					// Printing this shit
					// Booking [id] [depot1] [van, van, van]; [depot2] [van]
					
					/*
					// BOOKING: Printing depot name
					String starting = "Booking " + bookingID;
					int printSize = system.vanListPrint.size();
					System.out.println(printSize);
					for (int i = 0; i < printSize; i++) {
						CamperVan van = system.vanListPrint.get(i);
						String currDepot = "nothing";
						if (currDepot.equals("nothing")) {
							currDepot = van.depot;
							starting += currDepot;
						} else if (!(currDepot.equals(van.depot))) {
							starting += ";";
							currDepot = van.depot;
							starting += currDepot;
						}
						starting += van.name;
					}
					System.out.println(starting);
					*/
					
					/*
					// Testing size of realVanList
					int realSize = system.realVanList.size();
					for (int i = 0; i < realSize; i++) {
						CamperVan van = system.realVanList.get(i);
						System.out.println(van);
					}
					*/
					
				} else if (input[0].equals("Change")) {
					System.out.println("------------------------------------------------ BOOKING CHANGE");
					// Clear vanList for printing purposes
					system.vanListPrint.clear();
					// Make a change
					int changeID = Integer.parseInt(input[1]);
					LocalDateTime changeStart = setStart(input[2], input[3], input[4]);	// determine booking start
					LocalDateTime changeEnd = setEnd(input[5], input[6], input[7]);		// determine end date

					int numAuto, numManual = 0;
					if (input[9].equals("Automatic")) {				// determine # auto/man bookings
						numAuto = Integer.parseInt(input[8]);
						numManual = 0;
					} else {
						numManual = Integer.parseInt(input[8]);
						numAuto = 0;
					}
					
					if (line.length() > 41) {						// determine #auto/man bookings
						if (input[11].equals("Automatic")) {
							numAuto = Integer.parseInt(input[10]);
						} else {
							numManual = Integer.parseInt(input[10]);
						}
					}

					system.deleteVanBooking(changeID);				// Cancel previous booking
					bookAllVans(numAuto, numManual, changeID, changeStart, changeEnd);
					
				} else if (input[0].equals("Cancel")) {
					System.out.println("------------------------------------------------ BOOKING CANCELLATION");
					int cancelID = Integer.parseInt(input[1]);
					system.deleteVanBooking(cancelID);				// Cancel previous booking
					System.out.println("Cancel" + " " +cancelID);
					
				} else if (input[0].equals("Print")) {
					System.out.println("------------------------------------------------ PRINT THINGS");
					// Print bookings of all vehicles at specified depot in order of vehicle declarations
				}
				
				// DATE TIME PRINT FORMAT
				// hour     HH:MM   (24:00 system)
				// month    MMM     (JAN, FEB, MAR)
				// day/date DD      (1-31)
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