import java.util.ArrayList;


public class Unit {
	private int x;
	private int y;
	
	public Unit (int a, int b) {
		x = a; 
		y = b;
	}
	
	public int getX () {
		return x;
	}
	
	public int getY () {
		return y;
	}
	
	public Boolean equals (Unit b) {
		if (x == b.getX() && y == b.getY()) {
			return true;
		}
		return false;
	}
	
	public ArrayList<Unit> getPeers(Unit a) {
		ArrayList<Unit> toReturn = new ArrayList<Unit>();
		for (int i = 0; i < 9; i++) {
			if (i != a.getX()) {
				toReturn.add(new Unit(i, a.getY()));
			}
		}
		for (int i = 0; i < 9; i++) {
			if (i != a.getY()) {
				toReturn.add(new Unit(a.getX(), i));
			}
		}
		int first = a.getX()/3;
		int second = a.getY()/3;
		//System.out.println("unit x" + first + " y" + second);
		for (int i = first*3; i < first*3 + 3; i++) {
			for (int j = second*3; j < second*3 + 3; j++) {
				Unit buffer = new Unit(i, j);
				if (toReturn.contains(buffer) == false && buffer.equals(a) == false) {
					toReturn.add(buffer);
				}
			}
		}
		return toReturn;
	}
	
	public ArrayList<Unit> getBox(Unit a) {
		ArrayList<Unit> toReturn = new ArrayList<Unit>();
		int first = a.getX()%3;
		int second = a.getY()%3;
		for (int i = first*3; i < first*3 + 3; i++) {
			for (int j = second*3; j < second*3 + 3; j++) {
				Unit buffer = new Unit(i, j);
				if (buffer.equals(a) == false) {
					toReturn.add(buffer);
				}
			}
		}
		return toReturn;
	}
}
