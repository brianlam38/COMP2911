import java.util.*;

/**
 * Interface for a set E
 * Objects all of type E. Only add/remove elements of type E
 * @author brianlam
 *
 */

public interface Set<E> extends Iterable<E> {
	public void add(E e);
	public void remove(E e);
	public Set<E> union(Set<E> secondSet);   // another implementation: public void union() doens't return
											 // anything as it adds to itself instead of returning new combined set
	public Set<E> intersection(Set<E> secondSet);
	public boolean subset(Set<E> secondSet);
	public boolean contains(Object e);
	public int size();
}
