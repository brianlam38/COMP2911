import java.util.*;
import java.io.*;

public class CamperVan {

	public int order;
	public String name;
	public String type;
	public int vacancy;	// 1 for vacant, 0 for not vacant
	
	// CamperVan constructor
	public CamperVan(String vanName, String vanType, int vanOrder) {
		this.order = vanOrder;
		this.name = vanName;
		this.type = vanType;
		this.vacancy = 1;
	}
	
	// Returns name of van
	public String getVanName() {
		return this.name;
	}
}
