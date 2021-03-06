/**
 *  Inheritance example
 * 
 * 	UML
 *  There are "six arrows to remember"
 *  
 *  The manager class is a "Subclass" of the Employee class
 *  The Manager extends to the Employee
 * 
 */

import java.util.Calendar;

public class Manager extends Employee {
	
	private Calendar hireDate; // this can be changed if it is public.
							   // "mutable" = private
	/*
	 * Constructor
	 */
	public Manager(String name, int salary, Calendar hireDate)
	{
		super(name, salary);	// super is a special compiler directive that calls constructor of super class
		this.hireDate = hireDate;
	}
	
	public boolean equals(Object other)
	{
		if(!super.equals(other)) return false;
		Manager otherManager = (Manager) other;
		return hireDate.equals(otherManager.hireDate);
	}
	
	/**
	 * standard clone
	 */
	public Manager clone()
	{
		Manager cloned = (Manager) super.clone(); // calling super.clone (method of employee class)
		cloned.hireDate = (Calendar) hireDate.clone(); // Copy calendar object, using that as hireDate of the clone
		return cloned;
	}
	
	public String toString()
	{
		return super.toString() +
				"[hireDate = " + hireDate + "]";	// Java is smart enough to return str + int together
	}
}
