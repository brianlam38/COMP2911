import java.util.*;
import java.io.*;

public class VanDepot {
	String name;
	CamperVan van = new CamperVan(vanName, vanType);	// Create van object
	
	// Depot constructor
	public VanDepot(String depotName) {
		this.name = depotName;	}
	
	public void addVan(CamperVan van) {
		vanMap.put(van.getVanName(), van);
	}
	
	// Returns depot name (called by system)
	public String getDepotName() {
		return this.name;
	}
	
	// Returns list of vans inside the depot (called by system)
	public String getVanList() {
		return "lolTest";
	}
	
	// Sets list of vans inside the depot
	public String setVanList() {
		return "lolTest";
	}
}
