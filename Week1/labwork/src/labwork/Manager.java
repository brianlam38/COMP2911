package labwork;
import java.util.Calendar;

public class Manager extends Employee { // a subtype of Employee
	
	private Calendar hireDate; // private field for manager's hire date
	// constructor
	public Manager(int salary, String name, Calendar hireDate) {
		super(salary, name);	// by calling super, this is pretty much calling employee
								// equivalent of using this.name = name; this.salary = salary;
		this.hireDate = c;
	}
}
