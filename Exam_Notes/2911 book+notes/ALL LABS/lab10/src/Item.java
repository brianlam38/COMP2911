
public class Item implements Component {

	private String name;
	private int price; 
	
	public Item(String name, int price) {
		this.name = name;
		this.price = price;
	}
	
	@Override
	public int getCost() {
		return price; 
	}
	
	public String getName() {
		return name; 
	}

}
