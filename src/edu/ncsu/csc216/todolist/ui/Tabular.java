package edu.ncsu.csc216.todolist.ui;

/**
 * Classes that implement this interface provide a method for extracting 
 * a list of elements as a 2-dimensional array, where each element
 * of the list is represented as a "row" and each field in an element is a 
 * "column" in the 2D array.
 * @author David Wright
 * @version 1.0
 *
 */
public interface Tabular {
	
	/**
	 * Get an array-based representation of the elements in a list
	 * 
	 * @return A 2-dimensional array representation of list elements,
	 *         where each element of the list is represented as a 
	 *         "row" and each field in an element is a "column" in 
	 *         the returned array.
	 */
	public Object[][] get2DArray();

}
