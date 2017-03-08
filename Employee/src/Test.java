/**
 * Simple program to test employee
 * @author brianlam
 *
 */

import java.util.Calendar;

public class Test {
	
	public static void main(String[] args)
	{
		Employee e = new Manager("Brad", 100000, Calendar.getInstance()); // Using the Employee class
		System.out.println(e); // e is an object
		Employee f = new Employee("Alan", 50000);
	}
}
