import java.util.Calendar;

/**
 * BankAccount handles the depositing and withdrawing of funds.
 * @invariant balance >= 0
 */
public class BankAccount {

	private float balance;
	private float withdrawalLeft;
	private Calendar lastWithdrawal; 
	
	  /**
	   * Constructor for the BankAccount class
	   * @pre balance, withdrawLeft are non-negative.
	   * @post withdrawal is set to limit amount,
	   * 	   lastWithdrawal is set to current time and date of object being created
	   */
	public BankAccount() {
		this.balance = 0;
		this.withdrawalLeft = 800;
		this.lastWithdrawal = Calendar.getInstance();
	}
	
	/**
	 * Method to deposit money into the bank account
	 * @param amount the amount to be deposited
	 * @pre   		 amount > 0
	 * @post 		 balance = balance + amount
	 */
	public void deposit(float amount) {
		balance += amount; 
	}
	
	/**
	 * Method to withdraw money from the bank account
	 * @param amount the amount to be withdrawn
	 * @pre amount > 0 and balance >= amount
	 * @post balance = balance - amount
	 */
	public boolean withdraw(float amount) {
		Calendar currDate = Calendar.getInstance();
		// if it is a new day, reset the withdrawal amount to 800
		if (currDate.get(Calendar.DATE) != lastWithdrawal.get(Calendar.DATE)) {
			withdrawalLeft = 800; 
		}
		if (withdrawalLeft - amount >= 0) {
			balance -= amount; 
			withdrawalLeft -= amount; 
			setLastWithdrawal(currDate);
			return true;
		} else {
			return false;
		}
	}
	
	/**
	 * Method to get the current balance of bank account
	 * @return balance as an int
	 */
	public float getBalance() {
		return balance; 
	}
	
	/**
	 * Method to get the amount of withdrawal left in the bank account for the day
	 * @return withdrawalLeft as a float
	 */
	public float getWithdrawalLeft() {
		return withdrawalLeft;
	}
	
	/**
	 * Method to get the date that was the last withdraw
	 * @return lastWithdrawal as a Calendar object
	 */
	public Calendar getLastWithdrawal() {
		return lastWithdrawal;
	}
	
	/**
	 * Method to set the last withdrawal date
	 * @param date to be used as last withdrawal date
	 * @pre lastWithdrawal is older than date
	 */
	public void setLastWithdrawal(Calendar date) {
		lastWithdrawal = date;
	}
}
