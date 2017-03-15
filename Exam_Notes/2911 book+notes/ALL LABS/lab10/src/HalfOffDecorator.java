
public class HalfOffDecorator extends ComponentDecorator {
	
	public HalfOffDecorator(Component comp) {
		super(comp);
	}

	@Override
	public int getCost() {
		return applyDiscount(super.getCost());
	}
	
	private int applyDiscount(int cost) { 
		return cost/2; 
	}
}
