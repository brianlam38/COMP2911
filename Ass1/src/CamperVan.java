import java.util.*;
import java.io.*;

public class CamperVan {

	public String vanName;
	public String vanType;
	
	// CamperVan constructor
	public void Campervan() {
		this.vanName = "lolTest";
		this.vanType = "lolTest";
	}
	
	// Returns name of van
	public String getVanName() {
		return this.vanName;
	}
	
	// Does it need to set its own name???
	
	// Doesn't need to know its current location, as Depot should store that info
	// Otherwise, it will have dependency on Depot	
}