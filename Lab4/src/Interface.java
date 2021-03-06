import java.util.*;

public interface Set<E> extends Iterable<E> {
	public void add(E e);
	public boolean remove(E e);
	public boolean doesSetcontain(E e);
	public Set<E> union(Set<E> e);
	public Set<E> intersect(Set<E> e);
	public Set<E> equals();
	// ... and more
}

// Allows access of a set/list's elements in order, without referring to an index.