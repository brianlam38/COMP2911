/**
 * MUTABLE vs. IMMUTABLE EXAMPLE
 */
public class MutableClass {

	private int value;
	
	public MutableClass(int aValue) { // changeable value
		value = aValue;
	}
	
	public void setValue(int aValue) { // setter
		value = aValue;
	}
	
	public getValue() { // getter
		return value;
	}
}

public class ImmutableClass {
	
	private final int value;

	public ImmutableClass(final int value) { // changed the constructor to say Immutable instead of mutable
		value = aValue; 	// the value is now set and can't be changed anymore
	}
	
	public final getValue() {
		return value;
	}
}

/**
 * CONSTRUCTORS
 */

// A constructor is a block of code that creates an instance of a class / creating an object often with params specifying the initial state.
// It constructs the values i.e. provides data for the object.
// A constructor name must be the same as its class name
// A constructor must have no explicit return type

// Refer to above code for examples

/**
 * METHODS
 */
// A Java method is a collection of statements that are grouped together to perform an operation.
// When you call the System.out.println() method, the system executes several statements to display a
// message on the console.

/**
 * STRINGS
 */

// The String class represents character strings. All string literals in Java such as "abc" are implemented as instances of this class
// Strings are constant, values can't be changed after creation.

// Because string objects are Immutable, they can be shared. Example:
	String str = "abc";
// is equivalent to:
	char data[] = {'a', 'b', 'c'};
	String str = new String(data);

// Examples of how strings can be used:
	System.out.println("abc");
	String cde = "cde";
	System.out.println("abc" + cde);
	String c = "abc".substring(2,3); // Substring returns a substring of this string.
	String d = cde.substring(2,3);   // The substring begins with the index a and extends to end of the index b
									// If it is use as cde.substring(2); then it grabs from index 2 to the end of string

// The String class includes methods for examining individuals chars of the sequence, for comparing strings,
// for extracting strings, creating a copy of a string with all chars translated to upper/lowercase.



