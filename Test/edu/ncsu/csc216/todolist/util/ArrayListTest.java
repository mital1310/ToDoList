package edu.ncsu.csc216.todolist.util;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

/**
 * ArrayListTest class tests the functionality and coverage of class ArrayList
 * 
 * @author mital
 * 
 */
public class ArrayListTest {
	/**
	 * Instance of ArrayList
	 */
	private ArrayList list;

	/**
	 * sets up and initializes fields.
	 * 
	 * @throws Exception
	 */
	@Before
	public void setUp() throws Exception {
		list = new ArrayList();
		ArrayList list1 = new ArrayList(10);
		try {
			list1 = new ArrayList(-4);
			fail();
		} catch (IllegalArgumentException e) {
			list1 = new ArrayList(10);
			assertEquals(0, list1.size());
		}
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
		list.add("o");
		assertEquals(1, list.size());
		try {
			list.add(null);
			fail();
		} catch (NullPointerException e) {
			assertEquals(1, list.size());
		}
		try {
			list.add("o");
			fail();
		} catch (IllegalArgumentException e) {
			assertEquals(1, list.size());
		}

	}

	/**
	 * test method for add at specific position
	 */
	@Test
	public void testAddIntObject() {
		list.add(0, "0");
		list.add(1, "1");
		assertEquals(2, list.size());

		try {
			list.add(-1, "-1");
			fail();
		} catch (IndexOutOfBoundsException e) {
			assertEquals(2, list.size());
		}
		try {
			list.add(7, "-1");
			fail();
		} catch (IndexOutOfBoundsException e) {
			assertEquals(2, list.size());
		}

		try {
			list.add(1, null);
			fail();
		} catch (NullPointerException e) {
			assertEquals(2, list.size());
		}

		try {
			list.add(2, "1");
			fail();
		} catch (IllegalArgumentException e) {
			assertEquals(2, list.size());
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

		try {
			list.remove(-1);
			fail();
		} catch (IndexOutOfBoundsException e) {
			assertEquals(1, list.size());
		}

		try {
			list.remove(6);
			fail();
		} catch (IndexOutOfBoundsException e) {
			assertEquals(1, list.size());
		}
	}

	/**
	 * test method for get
	 */
	@Test
	public void testGet() {
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
	 * test method for indexOf
	 */
	@Test
	public void testIndexOf() {
		list.add("1");
		assertEquals(0, list.indexOf("1"));
		assertEquals(-1, list.indexOf("5"));
		try {
			list.indexOf(null);
		} catch (NullPointerException e) {
			assertEquals(1, list.size());
		}
	}

}
