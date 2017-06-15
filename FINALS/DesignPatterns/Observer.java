/**************************************************************
 *                   OBSERVER DESIGN PATTERN				  *
 **************************************************************/

// This example is a program which sets a state / number
// Using the Observer Pattern, the decimal state / number
// can be output as Binary or Hex.

/**
 * SUBJECT - Attach / Detach observer operations.
 *           Maintains state and notifies observers of state changes.
 */
public class Subject {
	private List<Observer> observerList = new ArrayList<Observer>();
	private int state;
	
	/**
	 * Method to retrieve state value
	 * @return state as an integer
	 */
	public int getState() {
		return state;
	}
	
	/**
	 * Method to set state value
	 * @param newState is the new state value
	 * @post state is updated with new state value
	 */
	public void setState(int newState) {
		this.state = newState;
		notifyAll();
	}
	
	/**
	 * Method to add observer object to observer list
	 * @pre
	 */
	public void attach(Observer obs) {
		observerList.add(obs);
	}
	/**
	 * Method to notify observers
	 * @pre size of observer list > 0
	 * @post observers within the list are all updated
	 */
	public void notifyAll() {
		for (Observer obs : observerList) {
			obs.update();
		}
	}
}

/**
 * ABSTRACT OBSERVER   - Defines the operations to be used
 * 						 to notify this observer object.
 */
public abstract class Observer {
	protected Subject subject;
	public abstract void update();
}

/**
 * CONCRETE OBSERVER - Concrete implementaton of observer object.
 * 
 * Can be Binary, Hex or any other observer base
 */
public class BinaryObserver extends Observer {
	
	/**
	 * BinaryObserver Constructor
	 * @param s is the subject class
	 * @post BinaryObserver object is added to Observer list
	 */
	public BinaryObserver(Subject s) {
		this.subject = s;
		this.subject.attach(this);
	}
	/**
	 * Method to update this observer
	 * @post updated the observer
	 */
	@Override
	public void update() {
		print(BinaryString);
	}
}


