/**
 * Simple tester for our set implementation.
 * @author brianlam
 *
 */

public class SetTester {
	
	public static void main(String[] args) {
		// Create an arrayList set = setOne
		ArrayListSet<Fruit> setOne = new ArrayListSet<>();
		setOne.add(new Apple("apple1"));
		
		// Create an arrayList set = setTwo
		ArrayListSet<Fruit> setTwo = new ArrayListSet<>();
		setTwo.add(new Apple("apple2"));
		
		// Combine setOne and setTwo into setThree
		Set<Fruit> setThree = setOne.union(setTwo);
		
		// Iterate over the set
		Iterator<Fruit> i = setThree.iterator();
	}
}