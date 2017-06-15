import java.util.ArrayList;
import java.util.Iterator;

/**
 *  Assembly = Composite Object
 *  E.g. Case, Motherboard
 */
public class Assembly implements Component {

	private String name;
	private int price; 
	private ArrayList<Component> array;
	
	// Constructor
	public Assembly(String name) {
		this.name = name;
		this.array = new ArrayList<Component>(); 
	}
	
	// Add a new component to the list
	public void addComponent(Component comp) {
		array.add(comp);
	}
	
	// Remove component from the list
	public void removeComponent(Component comp) {
		array.remove(comp);
	}
	
	// Return cost of all the items
	@Override
	public int getCost() {
		price = 0;
		Iterator<Component> iterator = array.iterator(); 
		while (iterator.hasNext()) {
			price += iterator.next().getCost(); 
		}
		return price; 
	}
	
	// Return name of Composite
	public String getName() {
		return name; 
	}
}
