package edu.ncsu.csc216.todolist.model;

import java.io.Serializable;
import java.util.Observable;
import java.util.Observer;

import edu.ncsu.csc216.todolist.ui.Tabular;
import edu.ncsu.csc216.todolist.util.ArrayList;

/**
 * catList class represents list of categories.
 * 
 * @author mital
 * 
 */
public class CategoryList extends Observable implements Tabular, Serializable,
		Observer {
	/**
	 * Serial Version UID for class catList
	 */
	private static final long serialVersionUID = 984509L;
	/**
	 * name of category list.
	 */
	private String name = "Categories";
	/**
	 * counter for categories in the list.
	 */
	private int nextCategoryNum;
	/**
	 * instance of class ArrayList to store categories
	 */
	private ArrayList catList;
	/**
	 * number of columns for 2D array.
	 */
	private static final int NUM_COLUMNS = 3;

	/**
	 * Constructor for class catList. Initializes catList and counter to 1.
	 */
	public CategoryList() {
		this.catList = new ArrayList();
		this.nextCategoryNum = 1;
	}

	/**
	 * private method to notify observers and set changes.
	 */
	private void changedAndNotify() {
		setChanged();
		notifyObservers(this);
	}

	/**
	 * returns name of the list.
	 * 
	 * @return name of category list.
	 */
	public String getName() {
		return this.name;
	}

	/**
	 * Adds category to the category list.
	 * 
	 * @param name
	 *            name of category
	 * @param desc
	 *            description of category
	 * @return true if added
	 */
	public boolean addCategory(String name, String desc) {

		boolean added = false;
		String catId = "C" + nextCategoryNum;
		Category c = new Category(name, desc, catId);
		added = catList.add(c);
		c.addObserver(this);
		changedAndNotify();
		this.nextCategoryNum++;
		return added;
	}

	/**
	 * returns category at given index.
	 * 
	 * @param index
	 *            at which category is present.
	 * @return category at given index
	 * @throws IndexOutOfBoundsException
	 *             if index is out of range.
	 */
	public Category getCategoryAt(int index) {
		if (index < 0 || index >= size()) {
			throw new IndexOutOfBoundsException();
		}
		return (Category) catList.get(index);
	}

	/**
	 * returns index of given id of category.
	 * 
	 * @param id
	 *            of category
	 * @return index if category is found, -1 if not.
	 */
	public int indexOf(String id) {
		for (int i = 0; i < size(); i++) {
			if (((Category) catList.get(i)).getCategoryID().equals(id)) {
				return i;
			}
		}
		return -1;
	}

	/**
	 * returns index of given id of name.
	 * 
	 * @param name
	 *            of category
	 * @return index if category is found, -1 if not.
	 */
	public int indexOfName(String name) {
		for (int i = 0; i < size(); i++) {
			if (((Category) catList.get(i)).getName().equals(name)) {
				return i;
			}
		}
		return -1;
	}

	/**
	 * returns size of the list.
	 * 
	 * @return size of the list.
	 */
	public int size() {
		return this.catList.size();
	}

	/**
	 * returns true if list is empy, false otherwise.
	 * 
	 * @return true if list is empty, false otherwise.
	 */
	public boolean isEmpty() {
		return this.catList.isEmpty();
	}

	/**
	 * Removes category from given index.
	 * 
	 * @param index
	 *            at which category is to be removed
	 * @return removed category
	 * @throws IndexOutOfBoundsException
	 *             if index is out of bounds.
	 */
	public Category removeCategoryAt(int index) {
		if (index < 0 || index >= size()) {
			throw new IndexOutOfBoundsException();
		}
		Category remove = (Category) catList.remove(index);
		remove.deleteObserver(this);
		changedAndNotify();
		return remove;
	}

	/**
	 * Removes category matching the specified id.
	 * 
	 * @param id
	 *            of category to be removed.
	 * @return true if removed successfully, false otherwise.
	 */
	public boolean removeCategory(String id) {
		boolean removed = false;
		for (int i = 0; i < size(); i++) {
			if (((Category) catList.get(i)).getCategoryID().equals(id)) {
				Category remove = (Category) catList.remove(i);
				removed = true;
				remove.deleteObserver(this);
				changedAndNotify();
				break;
			}
		}
		return removed;
	}

	/**
	 * Creates a 2D array of Categories. Rows representing each category while
	 * columns represent id, name and description.
	 * 
	 * @return 2D array.
	 */
	@Override
	public Object[][] get2DArray() {
		Object[][] array = new Object[size()][NUM_COLUMNS];
		for (int i = 0; i < array.length; i++) {
			array[i][0] = ((Category) catList.get(i)).getCategoryID();
			array[i][1] = ((Category) catList.get(i)).getName();
			array[i][2] = ((Category) catList.get(i)).getDescription();
		}
		return array;
	}

	/**
	 * Method is called whenever category changes in the catList. Notify
	 * observers and set changes.
	 * 
	 * @param o
	 *            The Object that is being observed
	 * @param arg
	 *            The argument passed in to notifyObservers()
	 */
	@Override
	public void update(Observable o, Object arg) {
		if (catList.contains((Category) o)) {
			setChanged();
			notifyObservers(arg);
		}
	}

}
