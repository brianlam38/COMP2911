/**
 * Simple class to represent an employee 
 * @author Brian Lam 
 *
 * An EMPLOYEE_CLASS with a bunch of EMPLOYEE_METHODS
 * Class names always begin with an UPPER LETTER
 * Method names always begin with a LOWER CASE LETTER (no underscores)
 * 
 */

public class Employee implements Cloneable { // implements = "This class is now cloneable"
	
	private String name;
	private int salary; // private. Always require private/public annotations in your programs
	
	public Employee(String name, int salary)
	{
		this.name = name;
		this.salary = salary;
	}
	
	/**
	 * Method to return an employee's salary
	 * @return int
	 */
	
	public int getSalary() // public
	{
		return salary;
	}
	
	public String toString()
	{
		return getClass().getName() +
				"name = " + name +", salary = " + salary + "]";	// Java is smart enough to return str + int together
	}
	
	/**
	 * standard equals
	 * "These employees are equal if they have the same name and salary"
	 */
	public boolean equals(Object other)
	{
		if (this == other) return true;
		if (other == null) return false;
		if (getClass() != other.getClass()) return false;
		Employee otherEmployee = (Employee) other;
		return name.equals(other) && salary == ((Employee) other).salary;
	}
	
	/**
	 * standard clone method for top-level hierarchy
	 */
	public Employee clone()
	{
		try
		{
			Employee cloned = (Employee) super.clone();
			return cloned;
		}
		catch (CloneNotSupportedException e)
		{
			return null;
		}
	}
}

// why getclass + getname instead of employee?
// we want this method to work for managers
// as managers inherit the method or even override
// in the test, we can define manager (instead of employee)
