package edu.ncsu.csc216.todolist.model;

import static org.junit.Assert.*;

import java.util.Date;

import org.junit.Before;
import org.junit.Test;

/**
 * TaskTest Class tests the functionality and coverage for class Task
 * 
 * @author mital
 * 
 */
public class TaskTest {
	/**
	 * Instance of Task
	 */
	private Task task;
	/**
	 * Instance of Task
	 */
	private Task task2;
	/**
	 * Instance of Task
	 */
	private Task task3;
	/**
	 * Instance of Category
	 */
	private Category category;
	/**
	 * Instance of Category
	 */
	private Category category2;
	/**
	 * start date
	 */
	private Date dateStart;
	/**
	 * end date
	 */
	private Date dateEnd;

	/**
	 * sets up and initializes fields.
	 * 
	 * @throws Exception
	 */
	@SuppressWarnings("deprecation")
	@Before
	public void setUp() throws Exception {
		category = new Category("Work", "important work", "C1");
		category2 = new Category("Work", "important work", "C2");
		dateStart = new Date(2014, 1, 1);
		dateEnd = new Date(2014, 1, 2);
		task = new Task("HomeWork", "Finish hw", dateStart, dateEnd, category,
				"T1");
		task2 = new Task("HomeWork", "Finish hw", dateStart, dateEnd, category,
				"T1");
		task3 = new Task("HomeWork", "Finish hw", dateStart, dateEnd, category,
				"T2");
		
		
		try {
			task3 = new Task("", null, null, null, null, null);
			fail();
		} catch (IllegalArgumentException e) {
			task3 = new Task("HomeWork", "Finish hw", dateStart, dateEnd,
					category, "T2");

		}
		

	}

	/**
	 * test method for hashCode
	 */
	@Test
	public void testHashCode() {

		assertTrue(task.hashCode() == task2.hashCode());
		assertFalse(task.hashCode() == task3.hashCode());
	}

	/**
	 * test method for getTitle
	 */
	@Test
	public void testGetTitle() {
		assertEquals("HomeWork", task.getTitle());
	}

	/**
	 * test method for setTitle
	 */
	@Test
	public void testSetTitle() {
		task2.setTitle("title changed");
		assertEquals("title changed", task2.getTitle());
		try {
			task2.setTitle(null);
			fail();

		} catch (IllegalArgumentException e) {
			task2.setTitle("title changed");
		}

		try {
			task2.setTitle("");
			fail();

		} catch (IllegalArgumentException e) {
			task2.setTitle("title changed");
		}
	}

	/**
	 * test method for getDetails
	 */
	@Test
	public void testGetDetails() {
		assertEquals("Finish hw", task.getDetails());
	}

	/**
	 * test method for setDetails
	 */
	@Test
	public void testSetDetails() {
		task2.setDetails("details changed");
		assertEquals("details changed", task2.getDetails());
	}

	/**
	 * test method for getStartDateTime
	 */
	@Test
	public void testGetStartDateTime() {
		assertEquals(dateStart, task.getStartDateTime());
	}

	/**
	 * test method for setStartDateTime
	 */
	@SuppressWarnings("deprecation")
	@Test
	public void testSetStartDateTime() {
		Date newStartDate = new Date(2014, 4, 4);
		task2.setStartDateTime(newStartDate);
		assertEquals(newStartDate, task2.getStartDateTime());
		try {
			task2.setStartDateTime(null);
			fail();

		} catch (IllegalArgumentException e) {
			task2.setStartDateTime(newStartDate);
		}
	}

	/**
	 * test method for getDueDateTime
	 */
	@Test
	public void testGetDueDateTime() {
		assertEquals(dateEnd, task.getDueDateTime());
	}

	/**
	 * test method for setDueDateTime
	 */
	@SuppressWarnings("deprecation")
	@Test
	public void testSetDueDateTime() {
		Date newEndDate = new Date(2014, 4, 4);
		task2.setDueDateTime(newEndDate);
		assertEquals(newEndDate, task2.getDueDateTime());
		try {
			task2.setDueDateTime(null);
			fail();

		} catch (IllegalArgumentException e) {
			task2.setStartDateTime(newEndDate);
		}
	}

	/**
	 * test method for getCompletedDateTime
	 */
	@SuppressWarnings("deprecation")
	@Test
	public void testGetCompletedDateTime() {
		Date completedDate = new Date(2014, 6, 6);
		task.setCompletedDateTime(completedDate);
		assertEquals(completedDate, task.getCompletedDateTime());
	}

	/**
	 * test method for setCompletedDateTime
	 */
	@SuppressWarnings("deprecation")
	@Test
	public void testSetCompletedDateTime() {
		Date completedDate = new Date(2014, 6, 6);
		task.setCompletedDateTime(completedDate);
		assertEquals(completedDate, task.getCompletedDateTime());
	}

	/**
	 * test method for isCompleted
	 */
	@Test
	public void testIsCompleted() {
		task.setCompleted(true);
		assertEquals(true, task.isCompleted());
	}

	/**
	 * test method for setCompleted
	 */
	@Test
	public void testSetCompleted() {
		task.setCompleted(true);
		assertEquals(true, task.isCompleted());
	}

	/**
	 * test method for getTaskId
	 */
	@Test
	public void testGetTaskID() {
		assertEquals("T1", task.getTaskID());
	}

	/**
	 * test method for setTaskId
	 */
	@Test
	public void testSetTaskID() {
		task2.setTaskID("T2");
		assertEquals("T2", task2.getTaskID());
		try {
			task2.setTaskID(null);
			fail();

		} catch (IllegalArgumentException e) {
			task2.setTaskID("T2");
		}

		try {
			task2.setTaskID("");
			fail();

		} catch (IllegalArgumentException e) {
			task2.setTaskID("T2");
		}
	}

	/**
	 * test method for getCategory
	 */
	@Test
	public void testGetCategory() {
		assertEquals(category, task.getCategory());
	}

	/**
	 * test method for setCategory
	 */
	@Test
	public void testSetCategory() {
		task2.setCategory(category2);
		assertEquals(category2, task2.getCategory());

		try {
			task2.setCategory(null);
			fail();

		} catch (IllegalArgumentException e) {
			task2.setCategory(category2);
		}
	}

	/**
	 * test method for compareTo
	 */
	@SuppressWarnings("deprecation")
	@Test
	public void testCompareTo() {
		Date newEndDate = new Date(2014, 4, 4);
		task3.setDueDateTime(newEndDate);
		assertTrue(task.compareTo(task3) < 0);

		Date newEndDate1 = new Date(2013, 4, 4);
		task3.setDueDateTime(newEndDate1);
		assertTrue(task.compareTo(task3) > 0);
	}

	/**
	 * test method for equalsObject
	 */
	@Test
	public void testEqualsObject() {
		assertTrue(task.equals(task));
		assertTrue(task.equals(task2));
		assertFalse(task.equals(task3));
		assertFalse(task == null);

	}

}
