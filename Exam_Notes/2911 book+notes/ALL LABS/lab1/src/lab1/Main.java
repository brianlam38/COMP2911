package lab1;

import java.util.Calendar;

public class Main {

	public static void main(String[] args) {
		Employee bob = new Employee("Bob", 5); // calls constructer in employee class == malloc in c
		System.out.println (bob.getName());
        Calendar c = Calendar.getInstance();
        c.set = (2015, 04, 18);
        // set all these then
        //put it in the manager field
	}
}