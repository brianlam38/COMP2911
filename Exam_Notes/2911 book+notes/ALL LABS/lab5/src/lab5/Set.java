package lab5;

public interface Set<E> extends Iterable<E> { // to implement set, will need to implement all methods in set and iterable
    // interfaces are kinda the equivalent of .h files
	// What is a set? A collection of objects where there is no repetition, no order
	// no constructor because its an interface
	
	public Set<E> union(Set<E> other); // will return a set of strings if E is strings
	
	public Set<E> intersection(Set<E> other);
	
	public boolean contains (E obj); 
	
	// E is java generics, convention is 'E', when you create a set, E is saying I'm storing an object of type E
	// wherever you have E, it'll replace with the type of object you're storing 
	
	
	
}
