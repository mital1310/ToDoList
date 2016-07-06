package edu.ncsu.csc216.todolist;

import static org.junit.Assert.*;

import java.io.IOException;
import java.util.Observable;

import org.junit.Before;
import org.junit.Test;

import edu.ncsu.csc216.todolist.model.CategoryList;
import edu.ncsu.csc216.todolist.model.TaskList;

/**
 * ToDoListTest checks the coverage and functionality of class ToDoList.
 * @author mital
 *
 */
public class ToDoListTest {
	
	private ToDoList list;
	private TaskList taskList;
	private CategoryList categoryList;
	
	/**
	 * Sets up and initializes fields.
	 * @throws Exception
	 */
	@Before
	public void setUp() throws Exception {
		taskList = new TaskList("New List", "TL1");
		categoryList = new CategoryList();
		list = new ToDoList();
		
	}
	
	/**
	 * Test method for isChanged method in ToDoList.
	 */
	@Test
	public void testIsChanged() {
		assertFalse(list.isChanged());
	}
	
	/**
	 * Test method for setChanged method in ToDoList.
	 */
	@Test
	public void testSetChangedBoolean() {
		list.setChanged(true);
		assertTrue(list.isChanged());
	}
	/**
	 * Test method for getFilename method in ToDoList.
	 */
	@Test
	public void testGetFilename() {
		assertEquals(null, list.getFilename());
	}
	/**
	 * Test method for setFilename method in ToDoList.
	 */
	@Test
	public void testSetFilename() {
		list.setFilename("abc");
		assertEquals("abc", list.getFilename());
		try{
			list.setFilename(null);
			fail();
		} catch(IllegalArgumentException e){
			assertEquals("abc", list.getFilename());
		}
		try{
			list.setFilename("");
			fail();
		} catch(IllegalArgumentException e){
			assertEquals("abc", list.getFilename());
		}
	}
	
	/**
	 * Test method for getNumTaskLists method in ToDoList.
	 */
	@Test
	public void testGetNumTaskLists() {
		list.addTaskList();
		assertEquals(2, list.getNumTaskLists());
	}
	/**
	 * Test method for getTaskList method in ToDoList.
	 */
	@Test
	public void testGetTaskList() {
		
		assertEquals("New List", list.getTaskList(0).getName());
		
		try{
			assertEquals("New List", list.getTaskList(-1).getName());
			fail();
		} catch(IndexOutOfBoundsException e){
			assertEquals("New List", list.getTaskList(0).getName());
		}
		
		try{
			assertEquals("New List", list.getTaskList(8).getName());
			fail();
		} catch(IndexOutOfBoundsException e){
			assertEquals("New List", list.getTaskList(0).getName());
		}
	}
	
	/**
	 * Test method for getCategoryList method in ToDoList.
	 */
	@Test
	public void testGetCategoryList() {
		list.getCategoryList().addCategory("Work", "Work");
		categoryList.addCategory("Work", "Work");
		assertEquals(categoryList.getCategoryAt(0).getName(), list.getCategoryList().getCategoryAt(0).getName());
	}
	
	/**
	 * Test method for addTaskList method in ToDoList.
	 */
	@Test
	public void testAddTaskList() {
		list.addTaskList();
		list.addTaskList();
		list.addTaskList();
		assertEquals(4, list.getNumTaskLists());
	}
	/**
	 * Test method for removeTaskList method in ToDoList.
	 */
	@Test
	public void testRemoveTaskList() {
		list.addTaskList();
		list.addTaskList();
		list.addTaskList();
		assertEquals(4, list.getNumTaskLists());
		list.removeTaskList(0);
		assertEquals(3, list.getNumTaskLists());
		
		try{
			list.removeTaskList(-1);
			fail();
		} catch(IndexOutOfBoundsException e){
			assertEquals(3, list.getNumTaskLists());
		}
		
		try{
			list.removeTaskList(5);
			fail();
		} catch(IndexOutOfBoundsException e){
			assertEquals(3, list.getNumTaskLists());
		}
	}
	/**
	 * Test method for saveDataFile method in ToDoList.
	 */
	@Test
	public void testSaveDataFile() throws IOException {
		
	    list.saveDataFile("");
		list.saveDataFile("testFile.tdl");
		assertEquals(1, list.getNumTaskLists());

	}
	/**
	 * Test method for openDataFile method in ToDoList.
	 */
	@Test
	public void testOpenDataFile() throws ClassNotFoundException {
		 list.openDataFile("");
		 list.openDataFile("");
		 list.addTaskList();
		 list.saveDataFile("testFile.tdl");
		list.openDataFile("testFile.tdl");
		assertEquals(2, list.getNumTaskLists());
		
		
	}
	/**
	 * test method for update.
	 */
	@Test
	public void testUpdate() {
		Observable o = null;
		list.update(o, taskList);
		assertEquals(1, list.getNumTaskLists());
	}

}
