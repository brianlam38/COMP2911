/**
 * COMP2911 VanRentalSystem Assignment 1
 * Date: April 2017
 * Author: Brian Lam
 */

import java.time.LocalDateTime;

/**
 * Represents one single booking for a CamperVan.
 * 
 * A VanBooking object is created and attached to
 * each CamperVan if a valid booking is made of
 * the van. It can also be deleted whenever the
 * main VanRentalSystem asks for a change or deletion.
 */
public class VanBooking {
	
	String type;
	int ID;
	LocalDateTime start;
	LocalDateTime end;
	
	// Constructor
	public VanBooking(String type, int bookingID, LocalDateTime startDT, LocalDateTime endDT) {
		this.type = type;
		this.ID = bookingID;
		this.start = startDT;
		this.end = endDT;
	}
}
