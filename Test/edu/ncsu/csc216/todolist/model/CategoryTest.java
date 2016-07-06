package edu.ncsu.csc216.todolist.model;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

/**
 * CategoryTest class tests the functionality and coverage for class Category
 * 
 * @author mital
 * 
 */
public class CategoryTest {
	/**
	 * Instance of Category
	 */
	private Category category;
	/**
	 * Instance of Category
	 */
	private Category category2;
	/**
	 * Instance of Category
	 */
	private Category category3;

	/**
	 * Sets up and initializes fields
	 * 
	 * @throws Exception
	 */
	@Before
	public void setUp() throws Exception {
		category = new Category("Work", "finish your work", "C1");
		category2 = new Category("Work", "finish your work", "C1");
		category3 = new Category("Play", "Play", "c2");
		
		try {
			category3 = new Category(null, null, null);
			fail();
		} catch (IllegalArgumentException e) {
			category3 = new Category("Play", "Play", "c2");
			assertEquals("Play", category3.getName());
		}
		
	
	}

	/**
	 * Test for method HashCode
	 * 
	 */
	@Test
	public void testHashCode() {
		assertTrue(category.hashCode() == category2.hashCode());
	}

	/**
	 * Test for method getName
	 */
	@Test
	public void testGetName() {
		assertEquals("Work", category.getName());

	}

	/**
	 * Test for method setName
	 */
	@Test
	public void testSetName() {
		category2.setName("nameChanged");
		assertEquals("nameChanged", category2.getName());

		try {
			category2.setName("");
			fail();
		} catch (IllegalArgumentException e) {
			category2.setName("nameChanged");
			assertEquals("nameChanged", category2.getName());
		}

		try {
			category3.setName(null);
			fail();
		} catch (IllegalArgumentException e) {
			category3.setName("name changed");
			assertEquals("name changed", category3.getName());
		}
	}

	/**
	 * Test for method getDescription
	 */
	@Test
	public void testGetDescription() {
		assertEquals("finish your work", category.getDescription());
	}

	/**
	 * Test for method setDescription
	 */
	@Test
	public void testSetDescription() {
		category2.setDescription("title changed");
		assertEquals("title changed", category2.getDescription());

		try {
			category2.setDescription("");
			fail();
		} catch (IllegalArgumentException e) {
			category2.setDescription("title changed");
			assertEquals("title changed", category2.getDescription());
		}

		try {
			category2.setDescription(null);
			fail();
		} catch (IllegalArgumentException e) {
			category2.setDescription("title changed");
			assertEquals("title changed", category2.getDescription());
		}
	}

	/**
	 * Test for method getCategoryID
	 */
	@Test
	public void testGetCategoryId() {
		assertEquals("C1", category.getCategoryID());
	}

	/**
	 * Test for method setCategoryID
	 */
	@Test
	public void testSetCategoryId() {
		category2.setCategoryId("C2");
		assertEquals("C2", category2.getCategoryID());

		try {
			category2.setCategoryId("");
			fail();
		} catch (IllegalArgumentException e) {
			category2.setCategoryId("C2");
			assertEquals("C2", category2.getCategoryID());
		}

		try {
			category2.setCategoryId(null);
			fail();
		} catch (IllegalArgumentException e) {
			category2.setCategoryId("C2");
			assertEquals("C2", category2.getCategoryID());
		}
	}

	/**
	 * Test for method compareTo
	 */
	@Test
	public void testCompareTo() {
		assertTrue(category.compareTo(category2) == 0);
	}

	/**
	 * Test for method equalsObject
	 */
	@Test
	public void testEqualsObject() {

		assertTrue(category.equals(category2));
		assertFalse(category.equals(category3));
		assertFalse(category == null);

	}

	/**
	 * test for method ToString
	 */
	@Test
	public void testToString() {
		assertEquals("Work (C1)", category.toString());
	}

}
