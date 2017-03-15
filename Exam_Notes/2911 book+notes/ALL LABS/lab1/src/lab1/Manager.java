package lab1;

public class Manager extends Employee {
	private Calendar hireDate;
	
    public Manager(String name, int salary, int hireDate) {
    	super(name, salary);
    	this.hireDate = hireDate;
    }
}
	