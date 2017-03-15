
public abstract class ComponentDecorator implements Component {
	
	protected Component decoratedComponent; 
	
	public ComponentDecorator(Component comp) {
		this.decoratedComponent = comp; 
	}

	public int getCost() {
		return decoratedComponent.getCost(); 
	}
	
}
