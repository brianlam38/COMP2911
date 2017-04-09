import java.io.*;
import java.util.*;
import java.time.*;

/**
 * This class represents the entry point
 * for vans and van bookings.
 * 
 * It handles all booking requests, changes
 * and cancellations. It also stores info
 * for output printing purposes.
 *
 */
public class VanSystem {

	ArrayList<CamperVan> realVanList;		// True vanList
	ArrayList<CamperVan> vanListPrint;		// purely for output printing purposes (Cleans every instance of a booking, change, cancellation)
	int requestID;							// purely for output printing purposes (Cleans every instance of a booking, change, cancellation)
	
	public VanSystem() {
		this.realVanList = new ArrayList<CamperVan>();
		this.vanListPrint = new ArrayList<CamperVan>();
	}
	
	/**
	 *  Makes a van booking instance.
	 *  
	 *  Performs the following checks:
	 *  1. Correct van type
	 *  2. No overlapping bookings
	 *  
	 *  If any checks are failed, "Booking rejected" is printed.
	 */
	public boolean makeVanBooking(String type, int bookingID, LocalDateTime startDT, LocalDateTime endDT) {
		VanBooking newBooking = new VanBooking(type, bookingID, startDT, endDT);
		System.out.println("NEWBOOKING: " + newBooking);
		System.out.println("NEWBOOKING ID: " + newBooking.ID);
		System.out.println("NEWBOOKING TYPE: " + newBooking.type);
		System.out.println("NEWBOOKING START: " + newBooking.start);
		System.out.println("NEWBOOKING END: " + newBooking.end);
		boolean vanBooked = false;
		// Scan through CamperVan list	
		int arraySize = realVanList.size();
		for (int i = 0; i < arraySize; i++) {
			System.out.println("/// CHECKING A CAMPERVAN ///");
			CamperVan van = realVanList.get(i);
			//System.out.println("VAN BEING CHECKED: " + van);
			if (!(van.type.equals(type))) {
				System.out.println(">>> SKIP VAN --- INCORRECT TYPE ---");
				System.out.println();
				continue;
			}
			// No existing bookings for CamperVan
			if (van.bookings.isEmpty()) {
				van.bookings.add(newBooking);
				vanListPrint.add(van);
				System.out.println("#### FIRST VAN BOOKING ##### = " + newBooking.ID);
				System.out.println();
				vanBooked = true;
				break;
			}
			// Scan through all Bookings per CamperVan
			int numBookings = van.bookings.size();
			for (int j = 0; j < numBookings; j++) {		
				System.out.println("/// CHECKING VANBOOKINGS INSIDE ///");
				VanBooking origBooking = van.bookings.get(j);
				System.out.println("ORIGBOOKING: " + origBooking.type);
				System.out.println("ORIGBOOKING: " + origBooking.ID);
				System.out.println("ORIGBOOKING START: " + origBooking.start);
				System.out.println("ORIGBOOKING END: " + origBooking.end);
				// Van already booked for current period. Check next van.
				if (newBooking.ID == origBooking.ID) {
					System.out.println(">>> SKIP VAN --- VAN ALREADY BOOKED FOR CURRENT PERIOD ---");
					System.out.println();
					break;
				}
				// Date overlaps, check next CamperVan
				if (dateOverlap(origBooking.start, origBooking.end, newBooking.start, newBooking.end) == true) {
					break;
				// Valid booking, add to CamperVan
				} else {
					van.bookings.add(newBooking);
					vanListPrint.add(van);
					System.out.println("#### ADDING VAN BOOKINGS #### = " + newBooking.ID);
					System.out.println();
					vanBooked = true;
					break;
				}
			}
			if (vanBooked == true) break;
		}
		// If all vans have been checked but no possible van exists for requested booking time
		if (vanBooked == false) {		
			return true;
		} else {
			return false;
		}
	}
	
	public void deleteVanBooking(int cancelID) {
		System.out.println("/// START BOOKING CANCELLATION | CANCEL ID: " + cancelID + " ///");
		int arraySize = realVanList.size();
		
		// Scan through CamperVan list
		for (int i = 0; i < arraySize; i++) {
			System.out.println("--- CHECKING IN CAMPERVAN ---");
			CamperVan van = realVanList.get(i);
			if (van.bookings.isEmpty()) {
				System.out.println("CAMPERVAN BOOKING LIST IS EMPTY - NOTHING TO REMOVE");
				continue;
			}
			int numBookings = van.bookings.size();
			// Scan through all Bookings per CamperVan
			for (int j = 0; j < numBookings; j++) {
				System.out.println("/// CHECKING TO REMOVE VANBOOKING ///");
				VanBooking origBooking = van.bookings.get(j);
				System.out.println("ORIGBOOKING ID: " + origBooking.ID);
				// If Booking = CancelID, remove object from Booking list
				if (origBooking.ID == cancelID) {
					van.bookings.remove(j);
					System.out.println(">>> BOOKING CANCELLED");
					break;
				} else {
					System.out.println(">>> BOOKING SKIPPED");
				}
			}
		}
	}
	/*
	public void copyVanBooking(String type, int bookingID, LocalDateTime startDT, LocalDateTime endDT) {
		VanBooking newBooking = new VanBooking(type, bookingID, startDT, endDT);
		int arraySize = realVanList.size();
		
		// Scan through CamperVan list
		for (int i = 0; i < arraySize; i++) {
			CamperVan van = vanListPrint.get(i);
			if (!(van.type.equals(type))) continue;
			int numBookings = van.bookings.size();
			
			// Scan through all Bookings per CamperVan
			for (int j = 0; j < numBookings; j++) {		
				VanBooking origBooking = van.bookings.get(j);
				// No existing bookings for CamperVan
				if (van.bookings.isEmpty()) {
					van.bookings.add(newBooking);
				// Date overlaps, break (fake van, so no need to print)
				} else if (dateOverlap(origBooking.start, origBooking.end, newBooking.start, newBooking.end) == true) {
					break;
				// Valid booking, add to CamperVan
				} else {
					van.bookings.add(newBooking);
				}
			}
		}
	}
	*/
	
	/**
	 * Compares start and end dates of two different
	 * periods to see if they overlap.
	 */
	public boolean dateOverlap(LocalDateTime origStart, LocalDateTime origEnd, LocalDateTime newStart, LocalDateTime newEnd) {
		LocalDateTime maxStart;
		if (origStart.compareTo(newStart) == 1) {	// Grab max(origStart, newStart)
			maxStart = origStart;
		} else {
			maxStart = newStart;
		}
		LocalDateTime minEnd;					// Grab min(origEnd, newEnd)
		if (origEnd.compareTo(newEnd) == -1) {
			minEnd = origEnd;
		} else {
			minEnd = newEnd;
		}
		if (minEnd.compareTo(maxStart) == 1) {	// if minEnd > maxStart, date overlaps
			return true;
		} else {
			return false;
		}
	}
	

}
