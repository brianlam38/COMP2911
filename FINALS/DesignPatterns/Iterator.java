/**************************************************************
 *                   ITERATOR DESIGN PATTERN				  *
 **************************************************************/

// Example: A collection of books, using an iterator to iterate through the collection

/**
 * ITERATOR INTERFACE - Narrates the navigation method
 */
public interface Iterator {
	public boolean hasNext();
	public Object next();
}

/**
 * CONTAINER INTERFACE - Runs the Iterator interface
 *
 */
public interface Container {
	public Iterator createIterator();
}

/**
 * CONTAINER CONCRETE CLASS - Implements the Container
 * ITERATOR CONCRETE NESTED CLASS - Implements the Iterator interface
 */
// A collection of books and it uses an iterator to iterate through the collection.
public class BookCollection implements Container {
	private String titles[] = {"Design Patterns", "1", "2", "3", "4"};
	
	@Override
	public Iterator createIterator() {
		BookIterator result = new BookIterator();
		return result;
	}
	
	public class BookIterator implements Iterator {
		private int index = 0;
		
		@Override
		public boolean hasNext() {
			if (index < titles.length) {
				return true;
			} else {
				return false;
			}
		}
		
		@Override
		public Object next() {
			if (this.hasNext()) {
				return titles[index++];
			} else {
				return null;
			}
		}
	}
}


* Iterator Usage: A separate class with a main to do operations.
*
*      Iterator it = BookCollection.createIterator();
*      while (it.hasNext()) {
*      		Object book = it.next();
*      		print(element);
*      {
*/