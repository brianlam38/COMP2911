import java.util.*;
import java.io.*;

public class CamperVan {

	public String name;
	public String type;
	
	// CamperVan constructor
	public CamperVan(String vanName, String vanType) {
		this.name = vanName;
		this.type = vanType;
	}
	
	// Returns name of van
	public String getVanName() {
		return this.name;
	}
	
	// Does it need to set its own name???
	
	// Doesn't need to know its current location, as Depot should store that info
	// Otherwise, it will have dependency on Depot	
}
