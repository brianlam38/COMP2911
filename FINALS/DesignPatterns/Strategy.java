/**************************************************************
 *                   STRATEGY DESIGN PATTERN				  *
 **************************************************************/

// This example is a program which works with a few different behaviours

/**
 * CONTEXT CLASS - Used by the client to choose a strategy
 */
public class Context {
	private Strategy currStrat;
	/**
	 * Context constructor
	 * @param strat is the chosen concrete strategy
	 * @post currStrat is updated with chosen strategy
	 */
	public Context(Strategy strat) {
		this.currStrat = strat;
	}
	/**
	 * This method executes the current strategy
	 * @param
	 * @return
	 */
	public int executeStrategy(int num1, int num2) {
		return currStrat.mathOperation(num1, num2);
	}
}

/**
 * STRATEGY INTERFACE - Interface to select the strategy
 */
public interface Strategy {
	public int mathOperation(int num1, int num2);
}

/**
 * CONCRETE STRATEGY (ADD) - Implementation of the strategy interface
 */
public class OperationAdd implements Strategy {
	/**
	 * This method sums a pair of numbers
	 * @param
	 * @return
	 */
	@Override
	public int mathOperation(int num1, int num2) {
		return num1 + num2;
	}
}

/**
 * CONCRETE STRATEGY (SUBTRACT) - Implementation of the strategy interface
 */
public class OperationSubtract implements Strategy {
	/**
	 * This method sums a pair of numbers
	 * @param 
	 * @return
	 */
	@Override
	public int mathOperation(int num1, int num2) {
		return num1 - num2;
	}
}


