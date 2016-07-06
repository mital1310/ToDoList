package edu.ncsu.csc216.todolist.model;

import java.io.Serializable;
import java.util.Date;
import java.util.Observable;

/**
 * Task class represents a task. It keeps track of title, details, start, end
 * and due dates, category it belongs to and its id.
 * 
 * @author mital
 * 
 */
public class Task extends Observable implements Comparable<Task>, Serializable {

	/**
	 * Serial version UID of class task
	 */
	private static final long serialVersionUID = 7459L;
	/**
	 * title for task
	 */
	private String title;
	/**
	 * details for task
	 */
	private String details;
	/**
	 * start date and time for task
	 */
	private Date startDateTime;
	/**
	 * due date and time for task
	 */
	private Date dueDateTime;
	/**
	 * completed date and time for task
	 */
	private Date completedDateTime;
	/**
	 * boolean to see if task is completed or not
	 */
	private boolean completed;
	/**
	 * id for task
	 */
	private String taskId;
	/**
	 * Category for task
	 */
	private Category c;

	/**
	 * Task constructor
	 * 
	 * @param title
	 *            title for task
	 * @param details
	 *            details for task
	 * @param startDateTime
	 *            of task
	 * @param dueDateTime
	 *            of task
	 * @param c
	 *            of task
	 * @param taskId
	 *            of task
	 * @Throw IllegalArgumentException if title is null or empty, start date or
	 *        end date is null or task id is empty or null
	 */
	public Task(String title, String details, Date startDateTime,
			Date dueDateTime, Category c, String taskId) {

		if (startDateTime == null || dueDateTime == null || c == null
				|| title == null || taskId == null || title.equals("")
				|| taskId.equals("")) {
			throw new IllegalArgumentException();
		}

		this.title = title;
		this.details = details;
		this.startDateTime = startDateTime;
		this.dueDateTime = dueDateTime;
		this.taskId = taskId;
		this.c = c;
	}

	/**
	 * method to notify observers and set changes.
	 */
	private void changedAndNotify() {
		setChanged();
		notifyObservers(this);
	}

	/**
	 * returns title
	 * 
	 * @return title
	 */
	public String getTitle() {
		return title;
	}

	/**
	 * sets title
	 * 
	 * @param title
	 *            to set
	 */
	public void setTitle(String title) {
		if (title == null || title.equals("")) {
			throw new IllegalArgumentException();
		}
		this.title = title;
		changedAndNotify();
	}

	/**
	 * return details
	 * 
	 * @return details
	 */
	public String getDetails() {
		return details;
	}

	/**
	 * sets details
	 * 
	 * @param details
	 *            to set
	 */
	public void setDetails(String details) {
		this.details = details;
		changedAndNotify();
	}

	/**
	 * returns start date
	 * 
	 * @return start date
	 */
	public Date getStartDateTime() {
		return startDateTime;
	}

	/**
	 * sets start date and time
	 * 
	 * @param startDateTime
	 *            to set
	 */
	public void setStartDateTime(Date startDateTime) {
		if (startDateTime == null) {
			throw new IllegalArgumentException();
		}
		this.startDateTime = startDateTime;
		changedAndNotify();
	}

	/**
	 * returns due date
	 * 
	 * @return due date for task
	 */
	public Date getDueDateTime() {
		return dueDateTime;
	}

	/**
	 * sets due date and time
	 * 
	 * @param dueDateTime
	 *            to set
	 */
	public void setDueDateTime(Date dueDateTime) {
		if (dueDateTime == null) {
			throw new IllegalArgumentException();
		}
		this.dueDateTime = dueDateTime;
		changedAndNotify();
	}

	/**
	 * returns completed date
	 * 
	 * @return completed date and time
	 */
	public Date getCompletedDateTime() {
		return completedDateTime;
	}

	/**
	 * sets completed date and time
	 * 
	 * @param completedDateTime
	 *            to set
	 */
	public void setCompletedDateTime(Date completedDateTime) {
		this.completedDateTime = completedDateTime;
		changedAndNotify();
	}

	/**
	 * returns true if the task is completed
	 * 
	 * @return boolean completed
	 */
	public boolean isCompleted() {
		return completed;
	}

	/**
	 * sets boolean completed
	 * 
	 * @param completed
	 *            to set
	 */
	public void setCompleted(boolean completed) {
		this.completed = completed;
		changedAndNotify();
	}

	/**
	 * returns task id
	 * 
	 * @return taskId for task
	 */
	public String getTaskID() {
		return taskId;
	}

	/**
	 * Sets task id
	 * 
	 * @param taskId
	 *            to set
	 */
	public void setTaskID(String taskId) {
		if (taskId == null || taskId.equals("")) {
			throw new IllegalArgumentException();
		}
		this.taskId = taskId;
		changedAndNotify();
	}

	/**
	 * returns task's category
	 * 
	 * @return category for task
	 */
	public Category getCategory() {
		return c;
	}

	/**
	 * sets the category
	 * 
	 * @param c
	 *            Category to set
	 */
	public void setCategory(Category c) {
		if (c == null) {
			throw new IllegalArgumentException();
		}
		this.c = c;
		changedAndNotify();
	}

	/**
	 * compares 2 task based on it's due date
	 * 
	 * @param o
	 *            task to compare
	 * @return integer result of compareTo method.
	 */
	public int compareTo(Task o) {
		int i;
		i = this.dueDateTime.compareTo(o.getDueDateTime());
		return i;
	}

	/**
	 * hash code method for task
	 * 
	 * @return result of method hash code
	 */
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((taskId == null) ? 0 : taskId.hashCode());
		return result;
	}

	/**
	 * checks to see if two objects are equal. returns true if equal, false
	 * otherwise.
	 * 
	 * @param obj
	 *            object to check if it equals to this object.
	 * @return true if 2 objects are equal, false otherwise.
	 */
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Task other = (Task) obj;
		if (taskId == null) {
			if (other.taskId != null)
				return false;
		} else if (!taskId.equals(other.taskId))
			return false;
		return true;
	}

}
