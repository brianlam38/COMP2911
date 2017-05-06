
public class Move {
	private int x;
	private int y;
	private int value;
	
	public Move (int x, int y, int value) {
		this.setX(x);
		this.setY(y);
		this.setValue(value);
	}

	public int getValue() {
		return value;
	}

	public void setValue(int value) {
		this.value = value;
	}

	public int getY() {
		return y;
	}

	public void setY(int y) {
		this.y = y;
	}

	public int getX() {
		return x;
	}

	public void setX(int x) {
		this.x = x;
	}
}
