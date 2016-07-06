package edu.ncsu.csc216.todolist.model;

import static org.junit.Assert.*;

import java.util.Date;

import org.junit.Before;
import org.junit.Test;

/**
 * Class to test coverage and functionality of class TaskList.
 * 
 * @author mital
 * 
 */
public class TaskListTest {
	/**
	 * Instance of Task
	 */
	private Task task;
	/**
	 * Instance of Category
	 */
	private Category category;
	/**
	 * Instance of TaskList
	 */
	private TaskList list;
	/**
	 * Start date for task
	 */
	private Date startDate;
	/**
	 * End date for task
	 */
	private Date endDate;

	/**
	 * Sets up and initializes fields
	 * 
	 * @throws Exception
	 */
	@SuppressWarnings("deprecation")
	@Before
	public void setUp() throws Exception {

		startDate = new Date(2014, 1, 1);
		endDate = new Date(2014, 1, 2);
		category = new Category("Work", "Work", "C1");
		task = new Task("Hw", "Hw", startDate, endDate, category, "-T1");
		list = new TaskList("New List", "TL1");

		try {
			list = new TaskList("", "");
			fail();
		} catch (IllegalArgumentException e) {
			list = new TaskList("New List", "TL1");
		}

		try {
			list = new TaskList(null, null);
			fail();
		} catch (IllegalArgumentException e) {
			list = new TaskList("New List", "TL1");
		}
	}

	/**
	 * test for method getName
	 */
	@Test
	public void testGetName() {
		assertEquals("New List", list.getName());
	}

	/**
	 * test for method setName
	 */
	@Test
	public void testSetName() {
		list.setName("name changed");
		assertEquals("name changed", list.getName());

		try {
			list.setName(null);
			fail();
		} catch (IllegalArgumentException e) {
			assertEquals("name changed", list.getName());
		}

		try {
			list.setName("");
			fail();
		} catch (IllegalArgumentException e) {
			assertEquals("name changed", list.getName());
		}
	}

	/**
	 * test for method getTaskListId
	 */
	@Test
	public void testGetTaskListId() {
		assertEquals("TL1", list.getTaskListID());
	}

	/**
	 * test for method addTask
	 */
	@SuppressWarnings("deprecation")
	@Test
	public void testAddTask() {
		Date date = new Date(2013, 1, 1);
		list.addTask("a", "a", startDate, endDate, category);
		list.addTask("b", "b", startDate, endDate, category);
		list.addTask("c", "c", startDate, date, category);
		assertEquals(3, list.size());
	}

	/**
	 * test for method getTaskAt
	 */
	@Test
	public void testGetTaskAt() {
		list.addTask("a", "a", startDate, endDate, category);
		list.addTask("b", "b", startDate, endDate, category);
		Task task1 = new Task("b", "b", startDate, endDate, category, "TL1-T2");
		assertEquals(task1, list.getTaskAt(1));

		try {
			list.getTaskAt(-1);
			fail();
		} catch (IndexOutOfBoundsException e) {
			assertEquals(task1, list.getTaskAt(1));
		}

		try {
			list.getTaskAt(7);
			fail();
		} catch (IndexOutOfBoundsException e) {
			assertEquals(task1, list.getTaskAt(1));
		}

	}

	/**
	 * test for method indexOf
	 */
	@Test
	public void testIndexOf() {
		assertEquals(-1, list.indexOf("TL1-T1"));
		list.addTask("a", "a", startDate, endDate, category);
		assertEquals(0, list.indexOf("TL1-T1"));
	}

	/**
	 * test for method size
	 */
	@Test
	public void testSize() {
		assertEquals(0, list.size());
		list.addTask("a", "a", startDate, endDate, category);
		assertEquals(1, list.size());
	}

	/**
	 * test for method isEmpty
	 */
	@Test
	public void testIsEmpty() {
		assertTrue(list.isEmpty());
		list.addTask("a", "a", startDate, endDate, category);
		assertFalse(list.isEmpty());
	}

	/**
	 * test for method removeTask
	 */
	@Test
	public void testRemoveTask() {
		list.addTask("a", "a", startDate, endDate, category);
		list.addTask("b", "b", startDate, endDate, category);
		assertTrue(list.removeTask("TL1-T1"));
		assertFalse(list.removeTask("aa"));
	}

	/**
	 * test for method removeTaskAt
	 */
	@Test
	public void testRemoveTaskAt() {
		list.addTask("a", "a", startDate, endDate, category);
		list.addTask("b", "b", startDate, endDate, category);
		Task task1 = new Task("b", "b", startDate, endDate, category, "TL1-T2");
		assertEquals(task1, list.removeTaskAt(1));

		try {
			list.removeTaskAt(-1);
			fail();
		} catch (IndexOutOfBoundsException e) {
			assertEquals(1, list.size());
		}

		try {
			list.removeTaskAt(5);
			fail();
		} catch (IndexOutOfBoundsException e) {
			assertEquals(1, list.size());
		}
	}

	/**
	 * test for method get2DArray
	 */
	@Test
	public void testGet2DArray() {
		list.addTask("b", "b", startDate, endDate, category);
		Object[][] testArray = new Task[1][3];
		testArray = list.get2DArray();
		assertEquals("TL1-T1", testArray[0][0]);
	}

	/**
	 * test for method update
	 */
	@Test
	public void testUpdate() {
		task.addObserver(list);
		list.addTask("a", "a", startDate, endDate, category);
		list.update(task, list);
		assertEquals(1, list.size());
	}

}
