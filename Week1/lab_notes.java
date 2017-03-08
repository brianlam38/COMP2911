// Steepest learing curve = learning java language (should be primary aim in weeks 2-3)
// In order to understand "good" design, firsty ou need to have a rough idea of implementation

// PRESENTATION MATTERS - assume your code / design will be read by others
// You will be marked by how well your code is written in exams / assignments.

// OOP
// Programming of classes basically
// Instead of Procedural like C, each class will contain a "main function" which runs the program
// OOP groups different components of that code into sections that are related to each other

// Your design consists of what CLASSES YOU DEFINE and how the classes interact with each other

// CLASSES
// Consists of MEMBERS (attributes)
	// What information is stored in this class
// METHODS (i.e functions)
	// Operations / actions.
	// External classes can call these functions or within your own class

// All compoennts defined as:
	// Public - Accessible by everyone
	// Private

// All classes will have the following "fundamental" methods
	// Constructor - declare an instance of the class
	// Getters / setters - grabs/changes information
		// E.g. you need to call a getter function to grab info off an object
		// E.g. Employee e1 = new Employee;
		//      e1.getSalary();
	// Common oeprations like:
		// equals - input another object, method tells us if it is equal to our current object
		// toString - prints the main info of the object
		// clone - duplicate the current object
	Public Employee clone() { // pseudocode
		// clone returns a copy of whatever you're calling it on
		// You will return a copy of Employee with its attributes
		return new Employee (this.getSalary(), this.getName());
	}
	// Mutable types - you're really just returning the pointer.
		// Calendar c = new Cal;
		// Calendar d = c;
		// If you call d.month = Feb, that will also change the value of c.
	// Immutable types (i.e. int, string) - Creates a whole new area of memory.
		// int a = 8, int b = a;
		// both a and b = 8.
		// However if you change b = 6, then a = 8 and b = 6. (a is not affected)


