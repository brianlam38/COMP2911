import java.util.Calendar;


public class BankAccount {

	private int balance;
	private int withdrawalLeft;
	private Calendar lastWithdrawal; 
	
	public BankAccount() {
		balance = 0;
		withdrawalLeft = 800;
		lastWithdrawal = Calendar.getInstance();
	}
	
	/**
	 * Method to deposit money into the bank account
	 * @param amount the amount to be deposited
	 * @precondition amount > 0
	 */
	public void deposit(int amount) {
		balance += amount; 
	}
	
	/**
	 * Method to withdraw money from the bank account
	 * @param amount the amount to be withdrawn
	 * @precondition amount > 0
	 */
	public boolean withdraw(int amount) {
		Calendar currDate = Calendar.getInstance();
		// if it is a new day, reset the withdrawal amount to 800
		if (currDate.get(Calendar.DATE) != lastWithdrawal.get(Calendar.DATE)) {
			withdrawalLeft = 800; 
		}
		if (withdrawalLeft - amount >= 0) {
			balance -= amount; 
			withdrawalLeft -= amount; 
			lastWithdrawal = currDate;
			return true;
		} else {
			return false;
		}
	}
	
	/**
	 * Method to get the current balance of bank account
	 * @return balance as an int
	 */
	public int getBalance() {
		return balance; 
	}
	
	/**
	 * Method to get the amount of withdrawal left in the bank account for the day
	 * @return withdrawalLeft as an int
	 */
	public int withdrawalLeft() {
		return withdrawalLeft;
	}
	
	/**
	 * Method to get the date that was the last withdraw
	 * @return lastWithdrawal as a Calendar object
	 */
	public Calendar lastWithdrawal() {
		return lastWithdrawal;
	}
	
	/**
	 * Method to set the last withdrawal date
	 * @param date to be used as last withdrawal date
	 */
	public void setLastWithdrawal(Calendar date) {
		lastWithdrawal = date;
	}
}
