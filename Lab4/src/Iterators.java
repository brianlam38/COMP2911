import java.util.ArrayList;
import java.util.Iterator;

public class Iterators {

	ArrayList<Integer> a = new ArryaList<Integer>();
	a.add(5);
	
	Iterator<Integer> it = a.iterator();
	it.hasNext(); // checks if next value exists
	int b = it.next(); // Gets the next item, moves iterator forward
}

// How to use Iterator:
ArrayList a = [0, 4, 7, 8;];
Iterator<Integer> i = a.iterator(); // grab the iterator
i.next() -> returns next element
// Pretty much same as using a for loop to iterate manually
// However, with the Iterator it still needs to be in a while loop etc.

