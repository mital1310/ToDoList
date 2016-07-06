package edu.ncsu.csc216.todolist.util;

import java.io.Serializable;
import java.util.Arrays;

/**
 * ArrayList class represents ArrayList of objects.
 * 
 * @author mital
 * 
 */
public class ArrayList implements List, Serializable {
	/**
	 * Serial Version UID for class ArrayList.
	 */
	private static final long serialVersionUID = 28592L;
	/**
	 * Increasing capacity size
	 */
	private static final int RESIZE = 10;
	/**
	 * list of objects
	 */
	private Object[] list;
	/**
	 * size of list
	 */
	private int size;

	/**
	 * Constructs an ArrayList with initial capacity of 10
	 */
	public ArrayList() {
		this(RESIZE);
	}

	/**
	 * Constructs an ArrayList with parameter as capacity
	 * 
	 * @param capacity
	 *            to set
	 */
	public ArrayList(int capacity) {
		if (capacity < 1) {
			throw new IllegalArgumentException();
		}
		this.list = new Object[capacity];
	}

	/**
	 * returns the size of ArrayList. If size is greater than Integer.MAX_VALUE,
	 * it returns INTEGER.MAX_VALUE.
	 * 
	 * @return size of the list
	 */
	@Override
	public int size() {
		return Math.min(this.size, Integer.MAX_VALUE);

	}

	/**
	 * returns true if list is empty, false otherwise.
	 * 
	 * @return true if this list contains no elements, false otherwise
	 */
	@Override
	public boolean isEmpty() {
		return this.size == 0;
	}

	/**
	 * Returns true if this list contains the specified element.
	 * 
	 * @param o
	 *            element whose presence in this list is to be tested
	 * @return true if this list contains the specified element
	 */
	@Override
	public boolean contains(Object o) {
		if (o != null) {
			for (int i = 0; i < size(); i++)
				if (o.equals(this.list[i]))
					return true;
		}
		return false;
	}

	/**
	 * Appends the specified element to the end of this list
	 * 
	 * @param o
	 *            element to be appended to this list
	 * @return true if added
	 */
	@Override
	public boolean add(Object o) {
		if (o == null) {
			throw new NullPointerException();
		}
		if (this.contains(o)) {
			throw new IllegalArgumentException();
		}

		increaseCapacity(this.size + 1);
		list[size] = o;
		size++;
		return true;
	}

	/**
	 * increases the capacity of ArrayList.
	 * 
	 * @param i
	 *            initial capacity
	 */
	private void increaseCapacity(int i) {
		if (i > list.length) {
			int capacity = (list.length * 3) / 2 + 1;
			if (capacity < i) {
				capacity = i;
			}
			list = Arrays.copyOf(list, capacity);
		}

	}

	/**
	 * adds element to the specified index.
	 * 
	 * @throws NullPointerException
	 *             if object is null.
	 * @throws IndexOutOfBoundsException
	 *             if index is out of range.
	 * @throws IllegalArgumentException
	 *             if trying to add same element.
	 * @param index
	 *            at which object is to be added.
	 * @param element
	 *            object to be added.
	 */
	@Override
	public void add(int index, Object element) {
		if (index < 0 || index > size()) {
			throw new IndexOutOfBoundsException();
		}
		if (element == null) {
			throw new NullPointerException();
		}

		if (this.contains(element)) {
			throw new IllegalArgumentException();
		}

		increaseCapacity(this.size + 1);
		for (int k = size; k >= index + 1; k--) {
			list[k] = list[k - 1];
		}
		list[index] = element;
		size++;
	}

	/**
	 * removes element from specified index.
	 * 
	 * @throws IndexOutOfBoundsException
	 *             if index is out of range.
	 * 
	 * @param index
	 *            index at which object is to be removed.
	 * @return Removed Object
	 */
	@Override
	public Object remove(int index) {
		if (index < 0 || index >= size) {
			throw new IndexOutOfBoundsException();
		}
		Object oldObject = list[index];
		if (size > index) {
			for (int k = index; k < size - 1; k++) {
				list[k] = list[k + 1];
			}
			size--;
		}
		return oldObject;
	}

	/**
	 * Returns the element at the specified position in this list.
	 * 
	 * @param index
	 *            index of the element to return
	 * @return the element at the specified position in this list
	 * @throws IndexOutOfBoundsException
	 *             if the index is out of range (index < 0 || index >= size())
	 */
	@Override
	public Object get(int index) {
		if (index < 0 || index >= size()) {
			throw new IndexOutOfBoundsException();
		}
		return this.list[index];
	}

	/**
	 * Returns the index of the first occurrence of the specified element in
	 * this list, or -1 if this list does not contain the element.
	 * 
	 * @param o
	 *            element to search for
	 * @return the index of the first occurrence of the specified element in
	 *         this list, or -1 if this list does not contain the element
	 */
	@Override
	public int indexOf(Object o) {
		if (o == null) {
			throw new NullPointerException();
		}
		for (int i = 0; i < size; i++)
			if (o.equals(this.list[i]))
				return i;

		return -1;
	}
}
