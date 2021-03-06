import java.util.*;

/**
 * This is a Bank Account
 * @author brianlam
 * Declare invariants here
 * @invariant blahblah
 */

public class BankAccount {
	
	int balance;
	int limit;
	GregorianCalendar currDate;
	
	// Constructor for creating new bank account
	public BankAccount() {
		this.balance = 0;
		this.limit = 800;
		this.currDate = new GregorianCalendar();
	}
	
	public void limit() {
		this.limit = 800;
	}
	
	public void deposits(int amount) {
		balance += amount;
	}
	
	/**
	 * Withdraw money from account
	 * @param amount
	 * @pre (balance > 0) and (amount >= balance)
	 * @post balance <= 0
	 */
	
	public boolean withdraw(int amount) {
		GregorianCalendar newDate = new GregorianCalendar();
		// check if same day for the limit
		if (currDate != newDate) {
			limit = 800;
		}
		// check if limit has been reached
		if (limit < amount) {
			return false;
		}
		// withdraw and update balance, limit
		balance -= amount;
		limit -= amount;
		// update current date to new date
		currDate = newDate;
		
		return true;
	}
	
	public int getBalance() {
		return balance;
	}
}
