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