package lab5;

import java.util.ArrayList;
import java.util.Iterator;

public class ArrayListSet<E> implements Set<E> { // makes only arraylistset iterable 
	
	private ArrayList<E> elements;

	public ArrayListSet() {  // creates empty array list
		// initialise elements, we want it to be an empty arraylist
		elements = new ArrayList<E>();
	}
	
	public ArrayListSet(ArrayListSet<E> se) {
		// copy constructor?
		// creates a copy of that set?
		//elements = se.elements; // shallow copy, if you change one of the lists, you change both
		elements = new ArrayList<E>(se.elements); // duplicates the arraylist
	}
 
	public boolean equals (Object o) { // can return true of any type of set
		if (o instanceof Set<?>) {
			Set<?> se = (Set<?>) o;
			// continue on to use double equals to compare items
			// eg for iterator 
			for (Object item : se) {  // compare all items in se to see if it in current set
				// equal if same items and same size
				elements.contains(item); 
			}
		}
		return false;
	}
	
	// you'll want to add more methods like 'adding to a set' 


	@Override
	public Set<E> union(Set<E> other) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Set<E> intersection(Set<E> other) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public boolean contains(E obj) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public Iterator<E> iterator() {
		// TODO Auto-generated method stub
		return null;
	}

}