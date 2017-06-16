/**************************************************************
 *                   COMPOSITE DESIGN PATTERN				  *
 **************************************************************/

/**
 * COMPONENT INTERFACE
 */
public interface Component {
	public int getCost();
	public String getName(); 
}

/**
 * COMPOSITE OBJECT IMPLEMENTATION
 * @invariant ???
 */
public class Assembly implements Component {
	private String name;
	private int totalPrice; 
	private ArrayList<Component> array;

	/**
	 * Composite constructor
	 */
	public Assembly(String name) {
		this.name = name;
		this.array = new ArrayList<Component>(); 
	}
	
	/**
	 * Method adds a component to the array
	 * @post component exists in the array
	 */
	public void addComponent(Component c) {
		array.add(c);
	}
	
	/**
	 * Method removes a component from the array
	 * @pre component exists
	 * @post component is not present in array
	 */
	public void removeComponent(Component c) {
		array.remove(c);
	}
	
	/**
	 * Method returns total cost of all components in the list
	 * @pre array != null
	 * @post totalPrice is updated
	 * @return totalPrice as an int
	 */
	public int getCost() {
		totalPrice = 0;
		for (Component c : array) {
			totalPrice += c.getCost();
		}
		return totalPrice; 
	}
	/**
	 * Method returns the name of this Composite object
	 * @return name of composite object
	 */
	public String getName() {
		return name; 
	}
}

/**
 * LEAF OBJECT IMPLEMENTATION
 * @invariant ???
 */
public class Item implements Component {
	private String name;
	private int price; 
	
	/**
	 * Leaf constructor
	 */
	public Item(String name, int price) {
		this.name = name;
		this.price = price;
	}

	/**
	 * Method to retrieve cost of leaf item
	 * @return price of the leaf item as an int
	 */
	@Override
	public int getCost() {
		return price; 
	}
	
	/**
	 * Method to retrieve name of leaf itme
	 * @return name of the leaf item as a String
	 */
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




