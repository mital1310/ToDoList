package edu.ncsu.csc216.todolist.model;

import java.io.Serializable;
import java.util.Observable;

/**
 * Category class represents the categories for task. Category class has name,
 * description and category id.
 * 
 * @author mital
 * 
 */
public class Category extends Observable implements Comparable<Category>,
		Serializable {
	/**
	 * Serial version Id for class Category
	 */
	private static final long serialVersionUID = 459188L;
	/**
	 * name for category
	 */
	private String name;
	/**
	 * description for category
	 */
	private String description;
	/**
	 * category id for category
	 */
	private String categoryId;

	/**
	 * Constructor for class category.
	 * 
	 * @param name
	 *            name for category
	 * @param description
	 *            description for category
	 * @param categoryId
	 *            throws IllegalArgumentException if name or task id is null or
	 *            empty.
	 */
	public Category(String name, String description, String categoryId) {

		if (name == null || categoryId == null || name.equals("")
				|| categoryId.equals("")) {
			throw new IllegalArgumentException();
		}

		this.name = name;
		this.description = description;
		this.categoryId = categoryId;
	}

	/**
	 * method to notify observers and set changes.
	 */
	private void changedAndNotify() {
		setChanged();
		notifyObservers(this);
	}

	/**
	 * returns name
	 * 
	 * @return name
	 */
	public String getName() {
		return name;
	}

	/**
	 * sets name for category
	 * 
	 * @param name
	 *            to set
	 */
	public void setName(String name) {
		if (name == null || name.equals("")) {
			throw new IllegalArgumentException();
		}
		this.name = name;
		changedAndNotify();
	}

	/**
	 * returns description
	 * 
	 * @return description
	 */
	public String getDescription() {
		return description;
	}

	/**
	 * sets description for category
	 * 
	 * @param description
	 *            to set
	 */
	public void setDescription(String description) {
		if (description == null || description.equals("")) {
			throw new IllegalArgumentException();
		}
		this.description = description;
		changedAndNotify();
	}

	/**
	 * returns category id
	 * 
	 * @return category id
	 */
	public String getCategoryID() {
		return categoryId;
	}

	/**
	 * sets category id for category
	 * 
	 * @param categoryId
	 *            to set
	 */
	public void setCategoryId(String categoryId) {
		if (categoryId == null || categoryId.equals("")) {
			throw new IllegalArgumentException();
		}
		this.categoryId = categoryId;
		changedAndNotify();
	}

	/**
	 * compares two category based on category id
	 * 
	 * @param o
	 *            Object to compare
	 * @return integer for compareTo
	 */
	@Override
	public int compareTo(Category o) {
		int i;
		i = this.categoryId.compareTo(o.getCategoryID());
		return i;
	}

	/**
	 * hash code method for class hash code
	 * 
	 * @return result for method hashcode
	 */
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result
				+ ((categoryId == null) ? 0 : categoryId.hashCode());
		return result;
	}

	/**
	 * checks to see if two objects are equal. returns true if equal and false
	 * otherwise.
	 * 
	 * @param obj
	 *            object to check if it equals this object
	 * @return true if 2 objects are equal, false otherwise.
	 */
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Category other = (Category) obj;
		if (categoryId == null) {
			if (other.categoryId != null)
				return false;
		} else if (!categoryId.equals(other.categoryId))
			return false;
		return true;
	}

	/**
	 * returns string representation of category.
	 * 
	 * @return string for representing categories.
	 */
	@Override
	public String toString() {
		return name + " " + "(" + categoryId + ")";
	}

}
