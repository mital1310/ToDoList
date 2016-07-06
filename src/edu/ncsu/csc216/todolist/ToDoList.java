package edu.ncsu.csc216.todolist;

import java.io.*;
import java.util.*;

import edu.ncsu.csc216.todolist.model.CategoryList;
import edu.ncsu.csc216.todolist.model.TaskList;

/**
 * The main class for the ToDoList program. Holds references to the top-level
 * data structures that contain Task and Category objects and acts as the
 * controller between the model and the GUI presentation view.
 * 
 * @author David Wright
 * @author mital
 * @version 1.0
 */
public class ToDoList extends Observable implements Serializable, Observer {
	/**
	 * Serial Version UID for class ToDoList.
	 */
	private static final long serialVersionUID = 34992L;

	/**
	 * Increment for increasing the capacity of the array of TaskLists.
	 */
	private static final int RESIZE = 3;
	/**
	 * Array holding different TaskList.
	 */
	private TaskList[] tasks;
	/**
	 * number of TaskLists in the array.
	 */
	private int numLists;
	/**
	 * Instance of class CategoryList as categories.
	 */
	private CategoryList categories;
	/**
	 * name of file.
	 */
	private String filename;
	/**
	 * notifies of any changes.
	 */
	private boolean changed;
	/**
	 * counter for number of taskLists in the ToDoList.
	 */
	private int nextTaskListNum;

	/**
	 * Constructs ToDoList and initializes its field.
	 */
	public ToDoList() {
		this.nextTaskListNum = 1;
		tasks = new TaskList[RESIZE];
		String taskListId = "TL" + nextTaskListNum;
		TaskList newList = new TaskList("New List", taskListId);
		tasks[0] = newList;
		tasks[0].addObserver(this);
		numLists++;
		this.categories = new CategoryList();
		categories.addObserver(this);
		this.changed = false;
	}

	/**
	 * returns true if the list is changed, false otherwise.
	 * 
	 * @return true if the list is changed, false otherwise.
	 */
	public boolean isChanged() {
		return this.changed;
	}

	/**
	 * Sets the value of boolean changed.
	 * 
	 * @param changed
	 *            value to set.
	 */
	public void setChanged(boolean changed) {
		this.changed = changed;
	}

	/**
	 * returns the filename
	 * 
	 * @return file name
	 */
	public String getFilename() {
		return this.filename;
	}

	/**
	 * Sets the filename.
	 * 
	 * @param filename
	 *            to set.
	 * @throws IllegalArgumentException
	 *             if filename is null or empty.
	 */
	public void setFilename(String filename) {
		if (filename == null || filename.equals("")) {
			throw new IllegalArgumentException();
		}
		this.filename = filename;
	}

	/**
	 * returns the number of task lists in the toDoList.
	 * 
	 * @return number of task Lists.
	 */
	public int getNumTaskLists() {
		return this.numLists;
	}

	/**
	 * Returns the task list at given index.
	 * 
	 * @param index
	 *            index at which the list has to be removed.
	 * @return task list.
	 * @throws IndexOutOfBoundsException
	 *             if index is out of range.
	 */
	public TaskList getTaskList(int index) {
		if (index < 0 || index >= getNumTaskLists()) {
			throw new IndexOutOfBoundsException();
		}
		return this.tasks[index];
	}

	/**
	 * returns the categoryList
	 * 
	 * @return the categoryList
	 */
	public CategoryList getCategoryList() {
		return this.categories;
	}

	/**
	 * Method to add task List.
	 * 
	 * @return index at which the task list is added.
	 */
	public int addTaskList() {
		if (tasks.length == getNumTaskLists()) {
			TaskList[] increasedSize = new TaskList[tasks.length + RESIZE];
			for (int i = 0; i < tasks.length; i++) {
				increasedSize[i] = tasks[i];
			}
			tasks = increasedSize;
		}

		int index = getNumTaskLists();
		this.nextTaskListNum++;
		String taskListId = "TL" + nextTaskListNum;
		tasks[index] = new TaskList("New List", taskListId);
		tasks[index].addObserver(this);
		this.numLists++;
		setChanged();
		notifyObservers(tasks[index]);
		return index;
	}

	/**
	 * Removes task list at given index.
	 * 
	 * @param index
	 *            at which task list is to be removed
	 */
	public void removeTaskList(int index) {
		if (index < 0 || index >= getNumTaskLists()) {
			throw new IndexOutOfBoundsException();
		}

		int size = tasks.length;

		if (tasks.length > index && tasks[index] != null) {
			TaskList toBeRemoved = tasks[index];
			toBeRemoved.deleteObserver(this);
			for (int k = index; k < size - 1; k++) {
				tasks[k] = tasks[k + 1];
			}

			this.numLists--;
			setChanged();
			notifyObservers(toBeRemoved);
		}

	}

	/**
	 * Saves the CategoryList and the array of TaskLists to the given file using
	 * object serialization.
	 * 
	 * @param fname
	 *            filename to save ToDoList information to.
	 */
	public void saveDataFile(String fname) {
		try {
			FileOutputStream fileOut = new FileOutputStream(fname);
			ObjectOutputStream out = new ObjectOutputStream(fileOut);
			for (int i = 0; i < numLists; i++) {
				out.writeObject(tasks[i]);
			}
			out.writeObject(categories);
			out.writeObject(filename);
			out.writeInt(nextTaskListNum);
			changed = false;
			out.close();
			fileOut.close();
		} catch (IOException e) {
			System.err.println("An error occurred while saving file " + fname);
			e.printStackTrace(System.err);
		}
	}

	/**
	 * Opens a data file with the given name and creates the data structures
	 * from the serialized objects in the file.
	 * 
	 * @param fname
	 *            filename to create ToDoList information from.
	 */
	public void openDataFile(String fname) {
		if (changed) {
			saveDataFile(filename);
		}
		try {
			FileInputStream fileIn = new FileInputStream(fname);
			ObjectInputStream in = new ObjectInputStream(fileIn);
			ArrayList<TaskList> temp = new ArrayList<TaskList>();
			Object tl = in.readObject();
			while (tl instanceof TaskList) {
				TaskList l = (TaskList) tl;
				l.addObserver(this);
				temp.add(l);
				tl = in.readObject();
			}
			tasks = new TaskList[RESIZE];
			tasks = temp.toArray(tasks);
			numLists = temp.size();
			categories = (CategoryList) tl;
			categories.addObserver(this);
			filename = (String) in.readObject();
			nextTaskListNum = (int) in.readInt();
			changed = false;
			in.close();
			fileIn.close();

		} catch (IOException e) {
			System.err.println("An error occurred while reading file " + fname);
			e.printStackTrace(System.err);
		} catch (ClassNotFoundException c) {
			System.err.println("Error reconstructing ToDoList from file "
					+ fname);
			c.printStackTrace(System.err);
		}

	}

	/**
	 * Method is called when taskList and categoryList observed changes. it
	 * notifies observer of changes.
	 * 
	 * @param o
	 *            object
	 * @param arg
	 *            argument
	 */
	public void update(Observable o, Object arg) {
		setChanged(true);
		setChanged();
		notifyObservers(arg);
	}

}