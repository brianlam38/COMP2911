/**
 * COMP2911 VanRentalSystem Assignment 1
 * Date: April 2017
 * Author: Brian Lam
 */

import java.io.*;
import java.util.*;
import java.time.*;

/**
 * This class represents the entry point for CamperVans and Van Bookings.
 * 
 * It handles all booking requests, changes and cancellations.
 * It also stores info for output printing purposes.
 *
 */
public class VanSystem {

	ArrayList<CamperVan> realVanList;		// True vanList
	ArrayList<CamperVan> vanListPrint;		// For printing output
	int requestID;							// For printing output

	/**
	 *  Makes a van booking instance.
	 *  
	 *  Performs the following checks:
	 *  1. Correct van type
	 *  2. No overlapping bookings
	 *  
	 *  If any checks are failed, "Booking rejected" is printed.
	 */
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
		//System.out.println("NEWBOOKING: " + newBooking);
		//System.out.println("NEWBOOKING ID: " + newBooking.ID);
		//System.out.println("NEWBOOKING TYPE: " + newBooking.type);
		//System.out.println("NEWBOOKING START: " + newBooking.start);
		//System.out.println("NEWBOOKING END: " + newBooking.end);
		boolean vanBooked = false;
		// Scan through CamperVan list	
		int arraySize = realVanList.size();
		for (int i = 0; i < arraySize; i++) {
			//System.out.println("/// CHECKING A CAMPERVAN ///");
			CamperVan van = realVanList.get(i);
			//System.out.println("VAN BEING CHECKED: " + van.name);
			if (van.checkedBooking == true) {
				//System.out.println(">>> SKIP VAN --- ALREADY CHECKED IN LAST SINGLE BOOKING ---");
				continue;
			}
			// Incorrect type
			if (!(van.type.equals(type))) {
				//System.out.println(">>> SKIP VAN --- INCORRECT TYPE ---");
				//System.out.println();
				continue;
			}
			// No existing bookings for CamperVan
			if (van.bookings.isEmpty()) {
				van.checkedBooking = true;
				van.bookings.add(newBooking);
				vanListPrint.add(van);
				//System.out.println("#### FIRST VAN BOOKING ##### = " + newBooking.ID);
				//System.out.println();
				vanBooked = true;
				break;
			}
			// Scan through all Bookings per CamperVan
			int numBookings = van.bookings.size();
			for (int j = 0; j < numBookings; j++) {		
				//System.out.println("/// CHECKING VANBOOKINGS INSIDE ///");
				VanBooking origBooking = van.bookings.get(j);
				//System.out.println("ORIGBOOKING: " + origBooking);
				//System.out.println("ORIGBOOKING: " + origBooking.type);
				//System.out.println("ORIGBOOKING: " + origBooking.ID);
				//System.out.println("ORIGBOOKING START: " + origBooking.start);
				//System.out.println("ORIGBOOKING END: " + origBooking.end);
				// Van already booked for current period. Check next van.
				if (newBooking.ID == origBooking.ID) {
					//System.out.println(">>> SKIP VAN --- VAN ALREADY BOOKED FOR CURRENT PERIOD ---");
					//System.out.println();
					break;
				}
				// Date overlaps, check next CamperVan
				if (dateOverlap(origBooking.start, origBooking.end, newBooking.start, newBooking.end) == true) {
					break;
				// Valid booking, add to CamperVan
				} else {
					van.checkedBooking = true;
					van.bookings.add(newBooking);
					vanListPrint.add(van);
					//System.out.println("#### ADDING VAN BOOKINGS #### = " + newBooking.ID);
					//System.out.println();
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
		//System.out.println("/// START BOOKING CANCELLATION | CANCEL ID: " + cancelID + " ///");
		int arraySize = realVanList.size();
		
		// Scan through CamperVan list
		for (int i = 0; i < arraySize; i++) {
			CamperVan van = realVanList.get(i);
			//System.out.println("--- CHECKING IN CAMPERVAN: " + van.name);
			if (van.bookings.isEmpty()) {
				//System.out.println("CAMPERVAN BOOKING LIST IS EMPTY - NOTHING TO REMOVE");
				continue;
			}
			int numBookings = van.bookings.size();
			// Scan through all Bookings per CamperVan
			for (int j = 0; j < numBookings; j++) {
				//System.out.println("/// CHECKING TO REMOVE VANBOOKING ///");
				VanBooking origBooking = van.bookings.get(j);
				//System.out.println("ORIGBOOKING ID: " + origBooking.ID);
				// If Booking = CancelID, remove object from Booking list
				if (origBooking.ID == cancelID) {
					van.bookings.remove(j);
					//System.out.println(">>> BOOKING CANCELLED");
					break;
				}
			}
		}
	}
	
	/**
	 * Compares start and end dates of two different periods to see if they overlap.
	 * 
	 * This is done by taking the larger of both start values, then the smaller of
	 * both end values. If the smallest end date > largest start date, then the booking
	 * overlaps.
	 */
	public boolean dateOverlap(LocalDateTime origStart, LocalDateTime origEnd, LocalDateTime newStart, LocalDateTime newEnd) {
		// determine MAX of original booking start vs. new booking start
		LocalDateTime largerStart;
		if (origStart.compareTo(newStart) == 1) {
			largerStart = origStart;
		} else {
			largerStart = newStart;
		}
		// determine MIN of original booking end vs. new booking end
		LocalDateTime smallerEnd;
		if (origEnd.compareTo(newEnd) == -1) {
			smallerEnd = origEnd;
		} else {
			smallerEnd = newEnd;
		}
		// if min end > max start, the booking overlaps
		if (smallerEnd.compareTo(largerStart) == 1) {
			return true;
		} else {
			return false;
		}
	}
}
