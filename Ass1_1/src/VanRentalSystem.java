/**
 * COMP2911 VanRentalSystem Assignment 1
 * Date: April 2017
 * Author: Brian Lam
 */

import java.io.*;
import java.util.*;
import java.time.*;
import java.time.format.DateTimeFormatter;

/**
 * VanRentalSystem reads in input, parses it and then executes commands to the rest of the system.
 */
public class VanRentalSystem {
	
	static VanSystem system = new VanSystem();

	/**
	 * Converts month string to integer form.
	 */
	private static int monthToInt(String month) {
		String[] months = {"Jan", "Feb", "Mar", "Apr", "May", "Jun",
							"Jul", "Aug", "Sep", "Oct", "Nov", "Dec"};
		for (int i = 0; i < 12; i++) {
			if (month.equals(months[i])) return i+1;
		}
		return 0;
	}
	/**
	 * Creates a start date/time object
	 */
	public static LocalDateTime setStart(String hourStr, String monthStr, String dateStr) {
		int hour = Integer.parseInt(hourStr);
		int month = monthToInt(monthStr);
		int date = Integer.parseInt(dateStr);
		LocalDateTime startDateTime = LocalDateTime.of(2017, month, date, hour, 0, 0, 0);

		return startDateTime;
	}
	/**
	 * Creates an end date/time object
	 */
	public static LocalDateTime setEnd(String hourStr, String monthStr, String dateStr) {
		int hour = Integer.parseInt(hourStr);
		int month = monthToInt(monthStr);
		int date = Integer.parseInt(dateStr);
		LocalDateTime endDateTime = LocalDateTime.of(2017, month, date, hour, 0, 0, 0);
		return endDateTime;
	}
	/**
	 * Formats start/end date and time into a combined string
	 */
	public static String formatDateTime(LocalDateTime start, LocalDateTime end) {
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("HH:mm MMM dd");
		String startStr = start.format(formatter);
		String endStr = end.format(formatter);	
		String startAndEnd = startStr + " " + endStr;
		return startAndEnd;
	}
	/**
	 * Books in all automatic and/or manual vans as instructed by the booking request.
	 * 
	 * Takes in the new booking parameters such as the start/end date and time and also the
	 * number of auto/manual vans requested.
	 */
	public static boolean bookAllVans(int numAuto, int numManual, int bookingID, LocalDateTime startBooking, LocalDateTime endBooking) {
		boolean noVanFound = false;
		// Book in all auto vans
		while (numAuto != 0) {
			//System.out.println("--- BOOKING AUTO --------- AUTO LEFT = " + numAuto);
			noVanFound = system.makeVanBooking("Automatic", bookingID, startBooking, endBooking);
			numAuto--;
			// No van can be found for requested period, remove partial bookings
			if (noVanFound == true) {
				System.out.println("Booking rejected");
				system.deleteVanBooking(bookingID);
				return false;
			}
		}
		// Book in all manual vans
		while (numManual != 0) {
			//System.out.println("--- BOOKING MANUAL --------- MANUAL LEFT = " + numManual);
			noVanFound = system.makeVanBooking("Manual", bookingID, startBooking, endBooking);
			numManual--;
			// No van can be found for requested period, remove partial bookings
			if (noVanFound == true) {
				System.out.println("Booking rejected");
				system.deleteVanBooking(bookingID);
				return false;
			}
		}
		return true;
	}
	/**
	 * Prints current request output
	 */
	public static void printBookingRequest(int bookingID) {
		String starting = "Booking " + bookingID + " ";
		int printSize = system.vanListPrint.size();
		String currDepot = "nothing";
		// Loop through van print list
		for (int i = 0; i < printSize; i++) {
			CamperVan van = system.vanListPrint.get(i);
			// First depot
			if (currDepot.equals("nothing")) {
				currDepot = van.depot;
				starting += currDepot;
				starting += " " + van.name;
			// Following new depots
			} else if (!(currDepot.equals(van.depot))) {
				starting += ";";
				currDepot = van.depot;
				starting += " " + currDepot;
				starting += " " + van.name;
			} else {
				starting += ", " + van.name;
			}
		}
		System.out.println(starting);
		system.vanListPrint.clear();
	}
	/**
	 * Prints all van bookings in a specified depot.
	 */
	public static void printDepotBookings(String requestedDepot) {
		int vanList = system.realVanList.size();
		for (int i = 0; i < vanList; i++) {
			CamperVan van = system.realVanList.get(i);
			if (van.depot.equals(requestedDepot)) {
				van.sortVanBookings();
				int bookingList = van.bookings.size();
				for (int j = 0; j < bookingList; j++) {
					VanBooking booking = van.bookings.get(j);
					String startAndEnd = formatDateTime(booking.start, booking.end);
					System.out.println(requestedDepot + " " + van.name + " " + startAndEnd);
				}
			}
		}
	}
	
	/**
	 * Parses input line-by-line and issues commands.
	 */
	public static void main(String[] args) {
		Scanner sc = null;
		try {
			sc = new Scanner(new FileReader(args[0])); 
			while (sc.hasNextLine()) { 
				String line = sc.nextLine();
				String[] input = line.split("\\s+");			
				// #### Parses depot / van declaration string
				if (input[0].equals("Location")) {
					String depotName = input[1];
					String vanName = input[2];
					String vanType = input[3];		
					CamperVan van = new CamperVan(depotName, vanName, vanType);
					system.realVanList.add(van);			
				// #### Skip comments and empty lines	
				} else if (input[0].equals("#") || input[0].isEmpty()) {
					continue;
				// #### Booking request
				} else if (input[0].equals("Request")) {
					int bookingID = Integer.parseInt(input[1]);
					LocalDateTime startBooking = setStart(input[2], input[3], input[4]);
					LocalDateTime endBooking = setEnd(input[5], input[6], input[7]);
					// determine # auto/man bookings
					int numAuto, numManual = 0;
					if (input[9].equals("Automatic")) {				
						numAuto = Integer.parseInt(input[8]);
						numManual = 0;
					} else {
						numManual = Integer.parseInt(input[8]);
						numAuto = 0;
					}
					// determine #auto/man bookings
					if (line.length() > 41) {						
						if (input[11].equals("Automatic")) {
							numAuto = Integer.parseInt(input[10]);
						} else {
							numManual = Integer.parseInt(input[10]);
						}
					}
					
					boolean bookingSuccess = bookAllVans(numAuto, numManual, bookingID, startBooking, endBooking);
					if (bookingSuccess == false) continue;
					
					// Clear all CamperVan's mark for checked booking
					int listSize = system.realVanList.size();
					for (int i = 0; i < listSize; i++) {
						CamperVan van = system.realVanList.get(i);
						van.checkedBooking = false;
					}
					
					// Print formatted booking request output
					printBookingRequest(bookingID);

					
				} else if (input[0].equals("Change")) {
					//System.out.println("###################################################################### BOOKING CHANGE");
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
					
					// Clear all CamperVan's mark for checked booking
					int listSize = system.realVanList.size();
					for (int i = 0; i < listSize; i++) {
						CamperVan van = system.realVanList.get(i);
						van.checkedBooking = false;
					}
					
					// CHANGE: Cancel + Re-book
					String starting = "Change " + changeID + " ";
					int printSize = system.vanListPrint.size();
					String currDepot = "nothing";
					// Loop through van print list
					for (int i = 0; i < printSize; i++) {
						CamperVan van = system.vanListPrint.get(i);
						//System.out.println("Previous Depo = " + currDepot);
						//System.out.println("Current Depot = " + van.depot);
						// First depot
						if (currDepot.equals("nothing")) {
							currDepot = van.depot;
							starting += currDepot;
							starting += " " + van.name;
						// Following new depots
						} else if (!(currDepot.equals(van.depot))) {
							starting += ";";
							currDepot = van.depot;
							starting += " " + currDepot;
							starting += " " + van.name;
						} else {
							starting += ", " + van.name;
						}
					}
					System.out.println(starting);
					system.vanListPrint.clear();
					
				} else if (input[0].equals("Cancel")) {
					//System.out.println("###################################################################### BOOKING CANCELLATION");
					int cancelID = Integer.parseInt(input[1]);
					system.deleteVanBooking(cancelID);				// Cancel previous booking
					System.out.println("Cancel" + " " + cancelID);
					
				} else if (input[0].equals("Print")) {
					//System.out.println("###################################################################### PRINT THINGS");
					String requestedDepot = input[1];
					printDepotBookings(requestedDepot);

				}
			}
		}
		catch (FileNotFoundException e) {
		}
		finally {
			if (sc != null) sc.close();
		}
	}
}