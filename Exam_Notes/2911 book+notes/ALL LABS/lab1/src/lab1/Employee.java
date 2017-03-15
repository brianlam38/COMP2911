package lab1;

public class Employee {
    private String name; 
    private int salary; 

    public Employee(String name, int salary) {
    	this.name = name; 
    	this.salary = salary;
    }
    
    public String getName() {
        return name;
    }
    
    public int getSalary() {
    	return salary;
    }
    
    public boolean equals(Object o) {
    	if (o.getClass().equals(this.getClass())) {
    		Employee e = (Employee) o; // type classing object into an employee
    	    if (name.equals(e.name)) { 
    		    return true;
    	    } else {
    	    	return false;
    	    }
    	} else {
    	    return false;
        }
    }
}
