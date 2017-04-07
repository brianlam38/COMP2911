import java.util.*;
import java.io.*;

public class VanDepot {

	ArrayList<CamperVan> vanList;
	String name;
	
	// Depot constructor
	public VanDepot(String depotName) {
		this.name = depotName;
		this.vanList = new ArrayList<CamperVan>();
	}
	
	// Adding new van object van list
	public void addVan(String vanName, String vanType, int vanOrder) {
		CamperVan van = new CamperVan(vanName, vanType, vanOrder);
		vanList.add(van);
		System.out.println("THIS VAN HAS BEEN ADDED: " + van.name);
	}
	
	/**
	 * Find an Auto in the depot then add it to current booking
	 * @param numAuto
	 * @param request
	 */
	public void bookAutoVan(int numAuto, Booking request) {
		int listSize = vanList.size();
		for (int i = 0; i < listSize; i++) {
			CamperVan validVan = vanList.get(i);
			if (numAuto == 0) {
				break;
			} else if (!(validVan.type.equals("Automatic"))) {
				continue;
			} else {
				request.vanList.add(validVan);
				numAuto--;
			}
		}
	}

	/**
	 * Find a Manual in the depot then add it to current booking
	 * @param numManual
	 * @param request
	 */
	public void bookManualVan(int numManual, Booking request) {
		int listSize = vanList.size();
		for (int i = 0; i < listSize; i++) {
			CamperVan validVan = vanList.get(i);
			if (numManual == 0) {
				break;
			} else if (!(validVan.type.equals("Manual"))) {
				continue;
			} else {
				request.vanList.add(validVan);
				numManual--;
			}
		}
	}

}

