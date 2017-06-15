/**************************************************************
 *                   STRATEGY DESIGN PATTERN				  *
 **************************************************************/

// This example is a program which sets a state / number
// Using the Observer Pattern, the decimal state / number
// can be output as Binary or Hex.

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
	@Override
	public int mathOperation(int num1, int num2) {
		return num1 + num2;
	}
}

/**
 * CONCRETE STRATEGY (SUBTRACT) - Implementation of the strategy interface
 */
public class OperationSubtract implements Strategy {
	@Override
	public int mathOperation(int num1, int num2) {
		return num1 - num2;
	}
}

/**
 * CONTEXT CLASS - Used by the client to choose a strategy
 */
public class Context {
	private Strategy strat;
	
	public Context(Strategy strat) {
		this.strat = strat;
	}
	
	public int executeStrategy(int num1, int num2) {
		return strat.mathOperation(num1, num2);
	}
}
