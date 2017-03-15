import java.util.Hashtable;


public class Student {

	private Hashtable<String, Course> passedCourses;
	private Hashtable<String, Course> enrolledCourses;
	private Hashtable<String, Session> enrolledSessions;
	
	public Student() {
		passedCourses = new Hashtable<String, Course>();
		enrolledCourses = new Hashtable<String, Course>();
		enrolledSessions = new Hashtable<String, Session>(); 
	}
	
	public void addPassedCourse(Course course) {
		passedCourses.put(course.getName(), course);
	}
	
	public void enrolInCourse(Course course) {
		enrolledCourses.put(course.getName(), course);
	}
	
	public void enrolInSession(Session session) {
		enrolledSessions.put(session.getName(), session);
	}
	
	public boolean hasPassed(String course) {
		if (passedCourses.containsKey(course)) {
			return true;
		} else {
			return false;
		}
	}
}
