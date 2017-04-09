import java.util.*;
import java.time.*;

public class Booking {

	ArrayList<CamperVan> vanList;
	int ID;				// booking ID
	LocalDateTime start;// start date
	LocalDateTime end;	// end date
	int auto;			// # of Auto bookings
	int manual;			// # of Manual bookings
	
	// Booking constructor
	public Booking(int bookingID, LocalDateTime startBooking, LocalDateTime endBooking, int numAuto, int numManual) {
		this.ID = bookingID;
		this.start = startBooking;
		this.end = endBooking;
		this.auto = numAuto;
		this.manual = numManual;
		this.vanList = new ArrayList<CamperVan>();	// list of depots in booking
	}
}
