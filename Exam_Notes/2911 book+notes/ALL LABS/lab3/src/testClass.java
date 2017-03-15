
public class testClass {

	public static void main (String[] args) {
		BankAccount a = new BankAccount();
		ChequeAccount b = new ChequeAccount();
		
		a.deposit(900);
		a.withdraw(450);
		a.withdraw(350);
		System.out.println("Did things");
		if (a.withdraw(20)) {
			System.out.println("withdrew wrong");
		}
		
		b.deposit(900);
		b.withdraw(10);
		b.withdraw(10);
		b.withdraw(10);
		b.withdraw(10);
		b.withdraw(10);
		System.out.println("Did cheque things");
		if (b.withdraw(10)) {
			System.out.println("withdrew too many cheques");
		}
	}
}
