package edu.ncsu.csc216.todolist.model;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

/**
 * Test class to test coverage and functionality of class CategoryList
 * 
 * @author mital
 * 
 */
public class CategoryListTest {
	/**
	 * Instance of category
	 */
	private Category category;
	/**
	 * Instance of CategoryList
	 */
	private CategoryList list;

	/**
	 * Sets up and initializes fields
	 * 
	 * @throws Exception
	 */
	@Before
	public void setUp() throws Exception {
		category = new Category("Work", "Work", "C1");
		list = new CategoryList();

	}

	/**
	 * test for method getName
	 */
	@Test
	public void testGetName() {
		assertEquals("Categories", list.getName());
	}

	/**
	 * test for method addCategory
	 */
	@Test
	public void testAddCategory() {
		list.addCategory("Work", "Work");
		list.addCategory("Play", "Play");
		list.addCategory("Job", "Job");
		assertEquals(3, list.size());
	}

	/**
	 * test for method getCategoryAt
	 */
	@Test
	public void testGetCategoryAt() {
		list.addCategory("Work", "Work");
		list.addCategory("Play", "Play");
		list.addCategory("Job", "Job");
		assertEquals(category, list.getCategoryAt(0));
		try {
			list.getCategoryAt(-1);
			fail();
		} catch (IndexOutOfBoundsException e) {
			assertEquals(category, list.getCategoryAt(0));
		}

		try {
			list.getCategoryAt(7);
			fail();
		} catch (IndexOutOfBoundsException e) {
			assertEquals(category, list.getCategoryAt(0));
		}
	}

	/**
	 * test for method indexOf
	 */
	@Test
	public void testIndexOf() {
		list.addCategory("Work", "Work");
		list.addCategory("Play", "Play");
		list.addCategory("Job", "Job");
		assertEquals(0, list.indexOf("C1"));
		assertEquals(-1, list.indexOf("C4"));
	}

	/**
	 * test for method indexOfName
	 */
	@Test
	public void testIndexOfName() {
		list.addCategory("Work", "Work");
		list.addCategory("Play", "Play");
		list.addCategory("Job", "Job");
		assertEquals(0, list.indexOfName("Work"));
		assertEquals(-1, list.indexOfName("hello"));
	}

	/**
	 * test for method size
	 */
	@Test
	public void testSize() {
		assertEquals(0, list.size());
		list.addCategory("Work", "Work");
		assertEquals(1, list.size());
	}

	/**
	 * test for method isEmpty
	 */
	@Test
	public void testIsEmpty() {
		assertTrue(list.isEmpty());
		list.addCategory("Work", "Work");
		assertFalse(list.isEmpty());
	}

	/**
	 * test for method removeCategoryAt
	 */
	@Test
	public void testRemoveCategoryAt() {
		list.addCategory("Work", "Work");
		list.addCategory("Play", "Play");
		list.addCategory("Job", "Job");
		assertEquals(category, list.removeCategoryAt(0));

		try {
			list.removeCategoryAt(-1);
			fail();
		} catch (IndexOutOfBoundsException e) {
			assertEquals(2, list.size());
		}

		try {
			list.removeCategoryAt(7);
			fail();
		} catch (IndexOutOfBoundsException e) {
			assertEquals(2, list.size());
		}
	}

	/**
	 * test for method removeCategory
	 */
	@Test
	public void testRemoveCategory() {
		list.addCategory("Work", "Work");
		list.addCategory("Play", "Play");
		list.addCategory("Job", "Job");
		assertTrue(list.removeCategory("C1"));
		assertFalse(list.removeCategory("afa"));
	}

	/**
	 * test for method get2DArray
	 * 
	 */
	@Test
	public void testGet2DArray() {
		list.addCategory("Work", "Work");
		Object[][] testArray = new Category[1][3];
		testArray = list.get2DArray();
		assertEquals("C1", testArray[0][0]);
	}

	/**
	 * test for method update
	 */
	@Test
	public void testUpdate() {
		category.addObserver(list);
		list.addCategory("Work", "Work");
		list.update(category, list);
		assertEquals(1, list.size());

	}

}
