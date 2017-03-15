import java.util.Hashtable;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;


public class EnrolmentSystem {

	private Hashtable <String, Course> availableCourses;
	private Student student;
	private Course course;
	
	public EnrolmentSystem(Student student, Course course) {
		this.student = student;
		this.course = course;
		availableCourses = new Hashtable<String, Course>();
	}
	
	public void runSystem() {
		boolean passedPrereqs = true;
		Set<String> prereqs = course.getPrerequisites();
		Iterator<String> iterator = prereqs.iterator();
		while (iterator.hasNext() && passedPrereqs == true) {
			String key = iterator.next(); 
			if (!student.hasPassed(key)) {
				passedPrereqs = false;
			}
		}
		if (passedPrereqs) {
			student.enrolInCourse(course);
			System.out.println("Enrolled Student");
			//Set<String> sessions = course.getSessions();
		}
	}
	
	public void addAvailableCourses(Course course) {
		availableCourses.put(course.getName(), course);
	}
}
