package edu.ncsu.csc216.todolist.model;

import java.io.Serializable;
import java.util.Date;
import java.util.Observable;
import java.util.Observer;

import edu.ncsu.csc216.todolist.ui.Tabular;
import edu.ncsu.csc216.todolist.util.LinkedList;

/**
 * tList class represents list of Tasks. and its id.
 * 
 * @author mital
 * 
 */
public class TaskList extends Observable implements Tabular, Serializable,
		Observer {
	/**
	 * Serial Version UID for class tList
	 */
	private static final long serialVersionUID = 98734509L;
	/**
	 * name of task list.
	 */
	private String name;
	/**
	 * counter for tasks in the list.
	 */
	private int nextTaskNum;
	/**
	 * String representing the id for task list.
	 */
	private String tListID;
	/**
	 * Instance of class LinkedList to store tasks.
	 */
	private LinkedList tList;
	/**
	 * number of columns for 2D array.
	 */
	private static final int NUM_COLUMNS = 8;

	/**
	 * Constructor for class tList. initializes counter to 1. initializes its
	 * field and the instance of LinkedList class.
	 * 
	 * @param name
	 *            for list.
	 * @param tListId
	 *            for list.
	 * @throws IllegalArgumentException
	 *             if name or id is empty or null.
	 */
	public TaskList(String name, String tListId) {
		if (name == null || name.equals("") || tListId == null
				|| tListId.equals("")) {
			throw new IllegalArgumentException();
		}
		this.nextTaskNum = 1;
		this.tList = new LinkedList();
		this.name = name;
		this.tListID = tListId;

	}

	/**
	 * private method to notify observers and set changes.
	 */
	private void changedAndNotify() {
		setChanged();
		notifyObservers(this);
	}

	/**
	 * returns name of the task list.
	 * 
	 * @return name of task list.
	 */
	public String getName() {
		return name;
	}

	/**
	 * method to set name
	 * 
	 * @param name
	 *            to set for tList.
	 * @throws IllegalArgumentException
	 *             if name is null or empty.
	 */
	public void setName(String name) {
		if (name == null || name.equals("")) {
			throw new IllegalArgumentException();
		}
		this.name = name;
		changedAndNotify();
	}

	/**
	 * returns task list id
	 * 
	 * @return tListID for tList.
	 */
	public String getTaskListID() {
		return this.tListID;
	}

	/**
	 * adds task to the list sorting them by due dates.
	 * 
	 * @param title
	 *            of task
	 * @param description
	 *            of task
	 * @param start
	 *            date of task
	 * @param due
	 *            date of task
	 * @param c
	 *            category of task
	 * @return true if added.
	 */
	public boolean addTask(String title, String description, Date start,
			Date due, Category c) {
		boolean added = false;
		String taskId = this.tListID + "-T" + nextTaskNum;
		Task t = new Task(title, description, start, due, c, taskId);
		if (tList.size() == 0) {
			boolean b = tList.add(t);
			if (b) {
				added = true;
				t.addObserver(this);
				changedAndNotify();

			}
		} else {
			for (int i = 0; i < tList.size(); i++) {
				if (t.compareTo((Task) tList.get(i)) < 0) {
					tList.add(i, t);
					t.addObserver(this);
					added = true;
					changedAndNotify();
					break;
				}

				else if (tList.size() - 1 == i) {
					tList.add(tList.size(), t);
					t.addObserver(this);
					changedAndNotify();
					added = true;
					break;
				}
			}
		}
		this.nextTaskNum++;
		return added;
	}

	/**
	 * returns task at given index.
	 * 
	 * @param index
	 *            at which the task is wanted
	 * @return task at given index
	 * @throws IndexOutOfBoundsException
	 *             if index is out of range.
	 */
	public Task getTaskAt(int index) {
		if (index < 0 || index >= size()) {
			throw new IndexOutOfBoundsException();
		}
		return (Task) tList.get(index);
	}

	/**
	 * returns index of given id of task.
	 * 
	 * @param id
	 *            of task
	 * @return index if task is found, -1 if not.
	 */
	public int indexOf(String id) {
		for (int i = 0; i < size(); i++) {
			if (((Task) tList.get(i)).getTaskID().equals(id)) {
				return i;
			}
		}
		return -1;
	}

	/**
	 * returns size of the list
	 * 
	 * @return size of the list.
	 */
	public int size() {
		return this.tList.size();
	}

	/**
	 * returns true if empty, false otherwise.
	 * 
	 * @return true if list is empty, false otherwise.
	 */
	public boolean isEmpty() {
		return this.tList.isEmpty();
	}

	/**
	 * Removes task matching the specified id.
	 * 
	 * @param id
	 *            of task to be removed.
	 * @return true if removed successfully, false otherwise.
	 */
	public boolean removeTask(String id) {
		boolean removed = false;
		for (int i = 0; i < size(); i++) {
			if (((Task) tList.get(i)).getTaskID().equals(id)) {
				Task remove = (Task) tList.remove(i);
				removed = true;
				remove.deleteObserver(this);
				changedAndNotify();
				break;
			}
		}
		return removed;
	}

	/**
	 * Removes task from given index.
	 * 
	 * @param index
	 *            at which task is to be removed
	 * @return removed task
	 * @throws IndexOutOfBoundsException
	 *             if index is out of bounds.
	 */
	public Task removeTaskAt(int index) {
		if (index < 0 || index >= size()) {
			throw new IndexOutOfBoundsException();
		}
		Task remove = (Task) tList.remove(index);
		remove.deleteObserver(this);
		changedAndNotify();
		return remove;

	}

	/**
	 * Creates a 2D array of Tasks. Rows representing each tasks while columns
	 * represent id, title, details, start time, end time, completed time,
	 * boolean for completion and category.
	 * 
	 * @return 2D array.
	 */
	@Override
	public Object[][] get2DArray() {
		Object[][] array = new Object[tList.size()][NUM_COLUMNS];
		for (int i = 0; i < array.length; i++) {
			array[i][0] = ((Task) tList.get(i)).getTaskID();
			array[i][1] = ((Task) tList.get(i)).getTitle();
			array[i][2] = ((Task) tList.get(i)).getDetails();
			array[i][3] = ((Task) tList.get(i)).getStartDateTime();
			array[i][4] = ((Task) tList.get(i)).getDueDateTime();
			array[i][5] = ((Task) tList.get(i)).getCompletedDateTime();
			array[i][6] = ((Task) tList.get(i)).isCompleted();
			array[i][7] = ((Task) tList.get(i)).getCategory();

		}
		return array;
	}

	/**
	 * Method is called whenever task changes in the tList. Notify observers and
	 * set changes.
	 * 
	 * @param o
	 *            The Object that is being observed
	 * @param arg
	 *            The argument passed in to notifyObservers()
	 */
	@Override
	public void update(Observable o, Object arg) {
		if (tList.contains((Task) o)) {
			setChanged();
			notifyObservers(arg);
		}
	}

}
