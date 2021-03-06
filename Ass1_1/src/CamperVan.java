/**
 * COMP2911 VanRentalSystem Assignment 1
 * Date: April 2017
 * Author: Brian Lam
 */

import java.time.LocalDateTime;
import java.util.*;

/**
 * A single CamperVan with it's associated bookings.
 *
 * This class provides methods for placing bookings,
 * changes and cancellations for the room.
 */
public class CamperVan {
	
	boolean checkedBooking;
	String depot;
    String name;
    String type;
    ArrayList<VanBooking> bookings;
	
	/**
	 * Constructs a new van with the van's name.
	 * @param vanName
	 */
	public CamperVan(String depotName, String vanName, String vanType) {
		this.checkedBooking = false;
		this.depot = depotName;
		this.name = vanName;
		this.type = vanType;
		this.bookings = new ArrayList<VanBooking>();
	}
	/**
	 * Bubble sort on CamperVan's bookings
	 */
	public void sortVanBookings() {
		int bookingSize = bookings.size();
		// Iterate through Van Bookings
		for (int i = 0; i < bookingSize-1; i++) {
			for (int j = 0; j < bookingSize-1; j++) {
				VanBooking bCurr = bookings.get(j);
				//System.out.println("CURR START: " + bCurr.start);
				VanBooking bNext = bookings.get(j+1);
				//System.out.println("NEXT START: " + bNext.start);
				// If curr start > next start, then swap.
				if (bCurr.start.isAfter(bNext.start) == true) {
					VanBooking temp = bCurr;
					bookings.set(j, bNext);
					bookings.set(j+1, temp);
				}
			}
		}
	}
}
