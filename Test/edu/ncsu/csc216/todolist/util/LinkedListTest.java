package edu.ncsu.csc216.todolist.util;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

/**
 * LinkedListTest class tests the functionality and coverage of class LinkedList
 * 
 * @author mital
 * 
 */
public class LinkedListTest {
	/**
	 * Instance of LinkedList
	 */
	private LinkedList list;

	/**
	 * sets up and initializes fields.
	 * 
	 * @throws Exception
	 */
	@Before
	public void setUp() throws Exception {
		list = new LinkedList();
	}

	/**
	 * test method for size
	 */
	@Test
	public void testSize() {
		assertEquals(0, list.size());
	}

	/**
	 * test method for isEmpty
	 */
	@Test
	public void testIsEmpty() {
		assertTrue(list.isEmpty());
		list.add("1");
		assertFalse(list.isEmpty());
	}

	/**
	 * test method for contains
	 */
	@Test
	public void testContains() {

		assertFalse(list.contains("1"));
		list.add("1");
		assertTrue(list.contains("1"));
		
	}

	/**
	 * test method for add
	 */
	@Test
	public void testAddObject() {
		
		list.add("1");
		list.add("2");
		list.add("3");
		try{
			list.add(null);
			fail();
		} catch(NullPointerException e){
			assertEquals(3, list.size());
		}
		try{
			list.add("3");
			fail();
		} catch(IllegalArgumentException e){
			assertEquals(3, list.size());
		}
		
	}

	/**
	 * test method for get
	 */
	@Test
	public void testGet() {
		try {
			assertEquals(null, list.get(0));
			 fail();
		} catch (IndexOutOfBoundsException e) {
			assertEquals(0, list.size());
		}

		list.add("0");
		list.add("1");
		assertEquals("0", list.get(0));

		try {
			list.get(-1);
			fail();
		} catch (IndexOutOfBoundsException e) {
			assertEquals("0", list.get(0));
		}

		try {
			list.get(4);
			fail();
		} catch (IndexOutOfBoundsException e) {
			assertEquals("0", list.get(0));
		}
	}

	/**
	 * test method for add at specific position
	 */
	@Test
	public void testAddIntObject() {
		list.add(0, "0");
		list.add(1, "1");
		list.add(2, "2");
		list.add(3, "3");
		list.add(4, "4");
		assertEquals(5, list.size());

		try {
			list.add(-1, "-1");
			fail();
		} catch (IndexOutOfBoundsException e) {
			assertEquals(5, list.size());
		}
		try {
			list.add(7, "-1");
			fail();
		} catch (IndexOutOfBoundsException e) {
			assertEquals(5, list.size());
		}

		try {
			list.add(1, null);
			fail();
		} catch (NullPointerException e) {
			assertEquals(5, list.size());
		}

		try {
			list.add(2, "1");
			fail();
		} catch (IllegalArgumentException e) {
			assertEquals(5, list.size());
		}
	}

	/**
	 * test method for remove
	 */
	@Test
	public void testRemove() {
		list.add(0, "0");
		list.add(1, "1");
		assertEquals(2, list.size());
		list.remove(1);
		assertEquals(1, list.size());
		list.remove(0);
		assertEquals(0, list.size());

		try {
			list.remove(-1);
			fail();
		} catch (IndexOutOfBoundsException e) {
			assertEquals(0, list.size());
		}

		try {
			list.remove(6);
			fail();
		} catch (IndexOutOfBoundsException e) {
			assertEquals(0, list.size());
		}
	}

	/**
	 * test method for indexOf
	 */
	@Test
	public void testIndexOf() {
		list.add("0");
		assertEquals(0, list.indexOf("0"));
	}

}
