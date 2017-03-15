package lab6;

import java.util.ArrayList;

public interface Graph<E> {

	void addNode(E e); // can add node of any type 
	
	void addEdge(E from, E to); 
	
	boolean isNodeInGraph(E node);
	
	boolean isConnected(E from, E to);
	
	void removeNode(E e);
	
	void removeEdge(E from, E to);
	
	ArrayList<E> getAdjacentNodes(E e);
}
