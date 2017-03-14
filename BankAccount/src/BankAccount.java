import java.util.*;

/**
 * Simple class to implement a bank account
 * @author brianlam
 *
 */

public class BankAccount {
	private int balance;
	
	public BankAccount() {
		this.balance = 0;
	}
	
	/**
	 * deposit amount into bank account
	 * @param amount
	 * @pre amount > 0
	 * @post balance >= 0
	 */
	
	public void deposit(int amount) {
		balance += amount;
	}
	
	/**
	 * withdraw amount from account
	 * @param amount
	 * @pre (amount > 0) and (balance >= amount)	// better to put logic all in one class
	 */
	
	public void withdraw(int amount) {
		balance -= amount;
	}
	
	public int getBalance() {
		return balance;
	}
}
