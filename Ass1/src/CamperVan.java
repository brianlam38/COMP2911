import java.util.*;
import java.io.*;
import java.time.*;

public class CamperVan {
	public String depot;
	public int order;
	public String name;
	public String type;
	LocalDateTime start;
	LocalDateTime end;
	
	// CamperVan constructor
	public CamperVan(String depotBelong, String vanName, String vanType) {
		this.depot = depotBelong;
		this.name = vanName;
		this.type = vanType;
	}
	
	// Sets start booking date
	public void setStartBooking(LocalDateTime startDT) {
		this.start = startDT;
	}
	// Sets end booking date
	public void setEndBooking(LocalDateTime endDT) {
		this.end = endDT;
	}
}
