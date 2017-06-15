/**************************************************************
 *             Set<E> INTERFACE			  *
 **************************************************************/
/*
 * SET: A collection of objects where there is no repetition, no order.
 * INTERAFACES: Equivalent of .h files in C. (Which ADT's etc. use)
 * NO CONSTRUCTOR: Interfaces have no constructor in the class.
 * 
 * Set implementation: Implement all methods in set + an iterator
 */

public interface Set<E> extends Iterable<E> {
	// operations on set membership
	public void addElement(E e);
	public void removeElement(E e);
	public boolean has(E e);
	
	// basic set operations
	public Set<E> union(Set<E> other);
	public Set<E> intersection(Set<E> other);
	public boolean isSubset(Set<E> other);
	public boolean equals(Object o); // check if two ArrayListSets are equal (not a single element E e)
}

/**************************************************************
 *             Set<E> IMPLEMENTATION AS ARRAYLIST			  *
 **************************************************************/

import java.util.ArrayList;
import java.util.Iterator;

/**
 * CLASS DESCRIPTION
 * @invariant No duplicates within the Set
 */
public class ArrayListSet<E> implements Set<E> {
	
	private ArrayList<E> array;
	
	/**
	 * ArrayListSet constructor
	 */
	public ArrayListSet() {
		this.array = new ArrayList<E>();
	}
	
	public void addElement(E e) {
		if (!this.has(e)) {
			this.array.add(e);
		}
	}
	public void removeElement(E e) {
		if (this.has(e)) {
			this.array.remove(e);
		}
	}
	public boolean has(E e) {
		if (this.array.contains(e)) {
			return true;
		} else {
			return false;
		}
	}
	public Iterator<E> iterator() {
		return array.iterator();
	}
	
	/**
	 * Union of two sets
	 * @param other is the second set to join with
	 * @return joint, a set containing both sets
	 */
	// Union two sets -> Return Set<E> joint
	public Set<E> union(Set<E> other) {
		ArrayListSet<E> joint = new ArrayListSet<E>();
		// add in current array elements
		for (E e: this.array) {
			joint.addElement(e);
		}
		// add in second array elements, skip duplicates
		for (E e : other) {
			if (!joint.has(e)) {
				joint.addElement(e);
			}
		}
		return joint;
 	}
	
	/**
	 * Intersection of two sets
	 * @param other is the second intersecting set
	 * @return x, a set containing both sets' intersecting elts
	 */
	public Set<E> intersection(Set<E> other) {
		ArrayListSet<E> x = new ArrayListSet<E>();
		for (E e: this.array) {
			if (other.has(e)) {
				x.addElement(e);
			}
		}
		return x;
	}
	
	/**
	 * Subset check
	 * @param other is the second set to check
	 * @return true or false
	 */
	public boolean isSubset(Set<E> other) {
		for (E e : this.array) {
			if (!other.has(e)) {
				return false;
			}
		}
		return true;
	}
	
	/**
	 * Equals check
	 * @param o is the object to check
	 * @return true or false
	 */
	@Override
	public boolean equals(Object o){
		if (o == this) return true;
		// other is empty
		if (o == null) return false;
		// other is not a Set
		if (!(o instanceof Set)) return false;
		// compare all items in s to see if it in current set
		Set<?> s = (Set<?>) o;
		for (Object item : s) {
			if(!array.contains(item)) return false;
		}
		return true;
	}
}