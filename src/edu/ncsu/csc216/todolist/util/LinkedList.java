package edu.ncsu.csc216.todolist.util;

import java.io.Serializable;

/**
 * LinkedList class represents LinkedList of objects.
 * 
 * @author mital
 * 
 */
public class LinkedList implements List, Serializable {

	/**
	 * Serial Version UID for class LinkedList.
	 */
	private static final long serialVersionUID = 349987L;
	/**
	 * size of the list
	 */
	private int size;
	/**
	 * front of the list
	 */
	private Node head;

	/**
	 * Inner class representing node for the class.
	 * 
	 * @author mital
	 * 
	 */
	private static class Node implements Serializable {
		/**
		 * Serial Version UID for class LinkedList.
		 */
		private static final long serialVersionUID = 484909840L;
		/**
		 * reference for next node
		 */
		private Node link;
		/**
		 * current value
		 */
		private Object value;

		/**
		 * Constructor for class Node
		 * 
		 * @param value
		 *            value of object
		 * @param link
		 *            next node
		 */
		private Node(Object value, Node link) {
			this.value = value;
			this.link = link;
		}
	}

	/**
	 * Constructor for class LinkedList.
	 */
	public LinkedList() {

		head = null;
		size = 0;
	}

	/**
	 * returns the size of ArrayList. If size is greater than Integer.MAX_VALUE,
	 * it returns INTEGER.MAX_VALUE.
	 * 
	 * @return size of the list.
	 */
	@Override
	public int size() {

		return Math.min(this.size, Integer.MAX_VALUE);

	}

	/**
	 * returns true if the list is empty, false otherwise
	 * 
	 * @return true if this list contains no elements, false otherwise
	 */
	@Override
	public boolean isEmpty() {
		return head == null;
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
		
		Node n1 = head;
		for (int i = 0; i < size; i++) {
			if (n1.value.equals(o)) {
				return true;
			}
			n1 = n1.link;
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
		} else {
			if (size() == 0) {
				head = new Node(o, null);
				size++;
				return true;
			} else {
				Node n1 = head;
				while (n1.link != null) {
					n1 = n1.link;
				}
				n1.link = new Node(o, null);
				size++;
				return true;
			}

		}
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
		if (index < 0 || index >= size) {
			throw new IndexOutOfBoundsException();
		}

		if (size() == 0) {
			return null;
		} else {
			Node n1 = head;
			for (int i = 0; i < index; i++) {
				n1 = n1.link;
			}
			return n1.value;
		}

	}

	/**
	 * adds element to the specified index.
	 * 
	 * @throws NullPointerException
	 *             if null element is passed.
	 * @throws IndexOutOfBoundsException
	 *             if index is out of range.
	 * @throws IllegalArgumentException
	 *             if trying to add same element.
	 * 
	 * @param index
	 *            at which the object is to be added.
	 * @param element
	 *            to be added.
	 */
	@Override
	public void add(int index, Object element) {

		if (index < 0 || index > size) {
			throw new IndexOutOfBoundsException();
		}

		if (element == null) {
			throw new NullPointerException();
		}

		if (this.contains(element)) {
			throw new IllegalArgumentException();
		}

		if (index == 0) {
			head = new Node(element, head);
			size++;
		} else if (head != null && index > 0) {
			Node n1 = head;
			while (n1 != null && index > 1) {
				n1 = n1.link;
				index--;
			}
			if (n1 != null) {
				n1.link = new Node(element, n1.link);
				size++;
			}
		}
	}

	/**
	 * removes element from specified index.
	 * 
	 * @throws IndexOutOfBoundsException
	 *             if index is out of range.
	 * 
	 * @param index
	 *            position at which the object has to be removed.
	 * @return Removed Object
	 */
	@Override
	public Object remove(int index) {
		if (index < 0 || index >= size) {
			throw new IndexOutOfBoundsException();
		} else {
			Node current = head;
			if (index == 0) {
				head = head.link;
				size--;
				return current.value;
			} else {
				for (int i = 0; i < index - 1; i++) {
					current = current.link;
				}
				Node n = current.link;
				current.link = current.link.link;
				size--;
				return n.value;
			}
		}
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
		Node n1 = head;
		for (int i = 0; i < size(); i++) {
			if (n1.value.equals(o)) {
				return i;
			}
			n1 = n1.link;
		}
		return -1;
	}

}
