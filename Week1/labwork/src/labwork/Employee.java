package labwork;

public class Employee {
	
	private String name;
	private int salary;
	// constructor
	public Employee(String name, int salary) {
		this.name = name;		// "this" is a reference to the current object.
		this.salary = salary;	// it is used to pass
	}
	
	public int getSalary() {
		return salary;
	}
	
	public String toString() {
		return getClass().getName() +
				"name = " + name + ", salary = " + salary + "]";
	}
}
