
public class Edge {
	private int start;
	private int end;
	private int weight;
	
	public Edge(int s, int e, int w) {
		this.start = s;
		this.end = e;
		this.weight = w;
	}
	
	public int getStartID() {
		return start;
	}
	
	public int getEndID() {
		return end;
	}
	
	public int getWeight() {
		return weight;
	}
}
