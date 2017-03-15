package lab2;

import java.util.ArrayList;
import java.util.Calendar; 
import java.util.LinkedList;

/**
 * (what the class does)
 * comment for all public classes and methods
 * 
 * invariants -:
 * make sure its true at the end of every constructor
 * make sure its true for every method - assume its true, then prove its true at the end
 * if method doesn't use the variable, say that e.g. 'balance' isn't affected
 * make sure variables are private so they cant be altered by someone else 
 * 
 * hash maps might be useful in the assignment at one stage, maps an object of one type to an object of another type
 * mapping an integer to a bank account (Integer -> BankAccount), if you want to find a bank account, you can hash
 * the integer and you can get the bank account (look it up) they're not ordered in any way
 * @author z5015763
 *
 */

public class BankAccount {

	    Calendar lastWithdraw; 
	    private int balance;
	    int[] arr; // declaring an array, dont specify size of array, constructor does this, almost never use this
	    ArrayList<Integer> list; // very dynamic, this is what you should use, dont need to worry about size
	                       // whenever you create arraylist, you have to say what its storing ie. Integer 
	                        // Integer is an object that stores an int - ints arent objects so you cant have an
	                       // ArrayList of them - it must be an object
	    LinkedList<Integer> listLinked; 
	    
	    public BankAccount (int balance) {
	    	this.balance = balance; 
	    	
	    	arr = new int[10]; // cant change size, will tell you that you've exceeded the size
	    	list = new ArrayList<Integer>();
	    	
	    	//list.get(0)
	    	
	    	for(Integer i : list) {     // type of object you're iterating through (i) : name of list (list)
	    		//... goes through the array list 
	    	}
	    	
	    }
	    
	    /**
	     * @pre amount > 0
	     * @param amount
	     */
	    
	    public void withdraw (int amount) {
	    	
	    	
	    }
	    
	    /**
	     * Make a deposit of amount
	     * @pre amount > 0
	     * @param amount
	     */
	    
	    public void deposit (int amount) {
	    	
	    }
}
