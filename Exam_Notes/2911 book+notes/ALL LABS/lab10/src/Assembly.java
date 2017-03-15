import java.util.ArrayList;
import java.util.Iterator;


public class Assembly implements Component {

	private String name;
	private int price; 
	private ArrayList<Component> components;
	
	public Assembly(String name) {
		this.name = name;
		components = new ArrayList<Component>(); 
	}
	
	public void addComponent(Component comp) {
		components.add(comp);
	}
	
	public void removeComponent(Component comp) {
		components.remove(comp);
	}
	
	@Override
	public int getCost() {
		price = 0;
		Iterator<Component> iterator = components.iterator(); 
		while (iterator.hasNext()) {
			price += iterator.next().getCost(); 
		}
		return price; 
	}

	public String getName() {
		return name; 
	}
}
