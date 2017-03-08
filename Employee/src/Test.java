/**
 * Simple program to test employee
 * @author brianlam
 *
 */

import java.util.Calendar;

public class Test {
	// Class for testing Employee / Manager + tests for your methods
	public static void main(String[] args)
	{
		// Using the Employee class to declare Manager object.
		Employee e = new Manager("Brad", 100000, Calendar.getInstance());
		System.out.println(e);
		Employee f = new Employee("Alan", 50000);
		System.out.println(f);
		System.out.println(f.toString());
	}
}
