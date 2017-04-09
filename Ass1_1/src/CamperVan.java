import java.util.*;

/**
 * A single CamperVan with it's associated bookings.
 *
 * This class provides methods for placing bookings,
 * changes and cancellations for the room.
 */
public class CamperVan {
	
	String depot;
    String name;
    String type;
    ArrayList<VanBooking> bookings;
	
	/**
	 * Constructs a new van with the van's name.
	 * @param vanName
	 */
	public CamperVan(String depotName, String vanName, String vanType) {
		this.depot = depotName;
		this.name = vanName;
		this.type = vanType;
		this.bookings = new ArrayList<VanBooking>();
	}

}
