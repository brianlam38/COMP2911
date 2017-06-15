/**************************************************************
 *                   COMPOSITE DESIGN PATTERN				  *
 **************************************************************/

/**
 * COMPONENT INTERFACE
 */
public interface Component {
	public int getCost(); 
	// include other possible operations
}

/**
 * COMPOSITE OBJECT IMPLEMENTATION
 */
public class Assembly implements Component {
	private String name;
	private int price; 
	private ArrayList<Component> array;

	public Assembly(String name) {
		this.name = name;
		this.array = new ArrayList<Component>(); 
	}
	
	// Add a new component to the list
	public void addComponent(Component c) {
		array.add(c);
	}
	// Remove component from the list
	public void removeComponent(Component c) {
		array.remove(c);
	}
	// Return cost of all the items
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

/**
 * LEAF OBJECT IMPLEMENTATION
 */
public class Item implements Component {
	private String name;
	private int price; 
	
	public Item(String name, int price) {
		this.name = name;
		this.price = price;
	}
	// Return price of Leaf
	@Override
	public int getCost() {
		return price; 
	}
	// Return name of Leaf
	public String getName() {
		return name; 
	}
}

/**************************************************************
 *                  + DECORATOR DESIGN PATTERN				  *
 **************************************************************/

/**
 * DECORATOR SUPER CLASS - Decorates Components
 */
public abstract class ComponentDecorator implements Component {
	private Component decoratedComponent; 
	
	// Constructor for decorated component
	public ComponentDecorator(Component c) {
		this.decoratedComponent = c; 
	}
	// Return cost of decorated component
	public int getCost() {
		return decoratedComponent.getCost(); 
	}
}

/**
 * "DIFFERENT DECORATORS"
 * E.g. 50% off, 25% off, 10% off
 */
public class halfOffDecorator extends ComponentDecorator {
	// Constructor for discounted
	public halfOffDecorator(Component c) {
		super(c);
	}
	// Get cost of decorated component
	@Override
	public int getCost() {
		return applyDiscount(super.getCost());
	}
	// Apply 50% discount decoration to component
	private int applyDiscount(int cost) { 
		return cost/2; 
	}
}




