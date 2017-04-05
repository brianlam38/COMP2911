import java.util.*;
import java.io.*;

public class VanDepot {
	String name;
	HashMap<String, CamperVan> vanMap = new HashMap<String, CamperVan>();	// DEPO HASHMAP <vanName key, Van obj>
	
	// Depot constructor
	public VanDepot(String depotName) {
		this.name = depotName;
	}
	
	// Add key->value pair for vans
	public void addVan(String vanName, String vanType) {
		CamperVan van = new CamperVan(vanName, vanType);
		vanMap.put(van.getVanName(), van);
		System.out.println(vanMap.get(vanName).name);
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
