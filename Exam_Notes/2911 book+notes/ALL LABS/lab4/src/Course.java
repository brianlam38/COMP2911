import java.util.Hashtable;
import java.util.Set;


public class Course {

	private String courseName;
	private int pointValue; 
	private Hashtable<String, Course> prerequisites;
	private Hashtable<String, Session> sessions; 
	
	public Course(String name, int pointValue) {
		courseName = name;
		this.pointValue = pointValue; 
		prerequisites = new Hashtable<String, Course>();
		sessions = new Hashtable<String, Session>();
	}
	
	public String getName() {
		return courseName;
	}
	
	public int getPointValue() {
		return pointValue;
	}
	
	public void addPrereq(Course course) {
		prerequisites.put(course.getName(), course);
	}
	
	public void addSession(Session session) {
		sessions.put(session.getName(), session);
	}
	
	public Set<String> getPrerequisites() {
		return prerequisites.keySet();
	}
	
	public Set<String> getSessions() {
		return sessions.keySet();
	}
	
}
