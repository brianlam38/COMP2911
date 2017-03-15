
public class Session {

	private String name; 
	private String room;
	private int time; 
	
	public Session(String name, String room, int time) {
		this.name = name;
		this.room = room;
		this.time = time;
	}
	
	public String getName() {
		return name;
	}
}
