import java.util.*;

public class Example<E> {
	
	ArrayList<E> storage;

	public Example() {
		this.storage = new ArrayList<E>();
	}
	
	public void add(E item) {
		this.storage.add(item);
	}
}

// <E> notation - specifies that the class deals with generic types
// Initialise an array that handles an unspecified type.
// "Add" method - takes in an unspecified type (E) and adds it to hte storage array.

// Example<String> e = new Example<string>();
// Example<Integer> e = new Example<Integer>();