
public class testClass {

	public static void main (String[] args) {
		Item wheel1 = new Item("wheel1", 10);
		Item wheel2 = new Item("wheel2", 10);
		Item wheel3 = new Item("wheel3", 10);
		Item wheel4 = new Item("wheel4", 10);
		
		Assembly carFrame = new Assembly("carFrame");
		Assembly chassis = new Assembly("chassis"); 
		
		carFrame.addComponent(wheel1);
		carFrame.addComponent(wheel2);
		carFrame.addComponent(wheel3);
		carFrame.addComponent(wheel4);
		
		chassis.addComponent(carFrame);
		
		System.out.println(chassis.getCost());
		
		Component discountedChassis = new HalfOffDecorator(chassis); 
		
		System.out.println(discountedChassis.getCost());
	}
}
