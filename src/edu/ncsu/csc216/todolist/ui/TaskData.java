package edu.ncsu.csc216.todolist.ui;

import java.util.Date;

import edu.ncsu.csc216.todolist.model.Category;

/**
 * Simple class for transfering data within the GUI via events,
 * thus package-level visibility
 * 
 * @author David R. Wright
 *
 */

class TaskData {

	public String taskID;
	public String title;
	public String details;
	public Date startDateTime;
	public Date dueDateTime;
	public Date completedDateTime;
	public boolean completed;
	public Category category;
	
	public TaskData() {
		this("", "", "", null, null, null, false, null);
	}
	
	public TaskData(String id, String ti, String det, Date start, 
			Date due, Date comp, boolean compl, Category cat) {
		title = ti;
		details = det;
		category = cat;
		startDateTime = start;
		dueDateTime = due; 
		completedDateTime = comp;
		completed = compl;
		taskID = id;
	}
	
	public Object[] getDataArray() {
		Object[] data = new Object[8];
		data[0] = taskID;
		data[1] = title;
		data[2] = details;
		data[3] = startDateTime;
		data[4] = dueDateTime;
		data[5] = completedDateTime;
		data[6] = completed;
		data[7] = category;
		return data;
	}
}
