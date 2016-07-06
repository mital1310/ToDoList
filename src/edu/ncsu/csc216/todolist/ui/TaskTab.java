package edu.ncsu.csc216.todolist.ui;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.*;
import javax.swing.event.*;

//import edu.ncsu.csc216.todolist.model.Category;
import edu.ncsu.csc216.todolist.model.Task;
import edu.ncsu.csc216.todolist.model.TaskList;
import edu.ncsu.csc216.todolist.model.CategoryList;

/**
 * @author David R. Wright
 *
 */
class TaskTab extends JPanel implements ActionListener, ListSelectionListener, DocumentListener, ChangeListener {

	private static final long serialVersionUID = 1077971879823499611L;

	private TaskListPane listPane;
	private TaskEditPane editPane;
	private EditButtonPanel buttonP;
	private boolean addMode;
	private boolean editMode;
	private TaskList taskList;
	
	
	
	public TaskTab(TaskList tl, CategoryList cl) {
		super();
		taskList = tl;
		listPane = new TaskListPane(tl);
		listPane.getTable().getSelectionModel().addListSelectionListener(this);
		taskList.addObserver(listPane);
		editPane = new TaskEditPane(cl);
		editPane.addFieldListener(this);
		setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
		add(listPane);
		add(Box.createRigidArea(new Dimension(0,5)), BorderLayout.CENTER);
		add(editPane);
		add(Box.createRigidArea(new Dimension(0,5)));
		add(getButtons());
	}
	
	public void addNewTask() {
		listPane.clearSelection();
		addMode = true;
		editPane.enableAdd();
	}
	
	public void deleteTask() {
		int row = listPane.getTable().getSelectedRow();
		if (row >= 0) {
			TaskData d = listPane.getTTM().getTaskRowData(row);
			taskList.removeTask(d.taskID);
			editPane.clearFields();
			listPane.clearSelection();
			enableAdd(false);
			enableSave(false);
		}
		else {
			JOptionPane.showMessageDialog(this, "Category not selected.", "Category List Error", JOptionPane.ERROR_MESSAGE);
		}
	}
	
	EditButtonPanel getButtons() {
		if (null == buttonP) {
			buttonP = new EditButtonPanel();
			buttonP.getAddB().addActionListener(this);
			buttonP.getSaveB().addActionListener(this);
			buttonP.getCancelB().addActionListener(this);
			buttonP.setVisible(true);
		}
		return buttonP;
	}
	
	private void disableButtons() {
		buttonP.getAddB().setEnabled(false);
		buttonP.getSaveB().setEnabled(false);
		buttonP.getCancelB().setEnabled(false);
	}

	private void enableAdd(boolean flag) {
		if (flag) {
			buttonP.getAddB().setEnabled(true);
			buttonP.getSaveB().setEnabled(false);
			buttonP.getCancelB().setEnabled(true);
			editPane.enableAdd();
			listPane.clearSelection();
		}
		else {
			addMode = false;
			disableButtons();
			editPane.disableAdd();
		}
	}
	
	private void enableSave(boolean flag) {
		if (flag) {
			buttonP.getAddB().setEnabled(false);
			buttonP.getSaveB().setEnabled(true);
			buttonP.getCancelB().setEnabled(true);
		}
		else {
			editMode = false;
			disableButtons();
			editPane.disableEdit();
		}
	}
	
	private void handleDocEvent(DocumentEvent e) {
		if (editPane.fieldsNotEmpty()) {
			if (addMode) {
				enableAdd(true);
			}
			else if (editMode) {
				enableSave(true);
			}
		}
		else {
			disableButtons();
		}
	}
	
	public void insertUpdate(DocumentEvent e) {
		handleDocEvent(e);
	}

	public void removeUpdate(DocumentEvent e) {
		handleDocEvent(e);
	}

	public void changedUpdate(DocumentEvent e) {
		handleDocEvent(e);
	}
	
	
	
	public void actionPerformed(ActionEvent e) {
		if (addMode && e.getActionCommand().equals("add")) {
			TaskData d = editPane.getFields();
			if (!d.startDateTime.before(d.dueDateTime)) {
				JOptionPane.showMessageDialog(this, "Due date must be after start date.", "Task Date Error", JOptionPane.ERROR_MESSAGE);
			}
			else if (d.completed && !d.startDateTime.before(d.completedDateTime)) {
				JOptionPane.showMessageDialog(this, "Completed date must be after start date.", "Task Date Error", JOptionPane.ERROR_MESSAGE);
			}
			else if (d.category == null) {
				JOptionPane.showMessageDialog(this, "Select a category.", "Category Error", JOptionPane.ERROR_MESSAGE);
			}
			else {
				editPane.setTaskData(d);
				taskList.addTask(d.title, d.details, d.startDateTime, d.dueDateTime, d.category);
				enableAdd(false);
				editPane.disableAdd();
			}
		}
		else if (editMode && e.getActionCommand().equals("save")) {
			TaskData d = editPane.getFields();
			if (!d.startDateTime.before(d.dueDateTime)) {
				JOptionPane.showMessageDialog(this, "Due date must be after start date.", "Task Date Error", JOptionPane.ERROR_MESSAGE);
			}
			else if (d.completed && !d.startDateTime.before(d.completedDateTime)) {
				JOptionPane.showMessageDialog(this, "Completed date must be after start date.", "Task Date Error", JOptionPane.ERROR_MESSAGE);
			}
			else if (d.category == null) {
				JOptionPane.showMessageDialog(this, "Select a category.", "Category Error", JOptionPane.ERROR_MESSAGE);
			}
			else {
				editPane.setTaskData(d);
				Task task = taskList.getTaskAt(taskList.indexOf(d.taskID));
				task.setTitle(d.title);
				task.setDetails(d.details);
				task.setStartDateTime(d.startDateTime);
				task.setDueDateTime(d.dueDateTime);
				task.setCompleted(d.completed);
				task.setCompletedDateTime(d.completedDateTime);
				task.setCategory(d.category);
				listPane.clearSelection();
				enableSave(false);
				editPane.disableEdit();
			}
		}
		else if (e.getActionCommand().equals("cancel")) {
			editPane.clearFields();
			if (addMode) {
				enableAdd(false);
				editPane.disableAdd();
			}
			if (editMode) {
				listPane.clearSelection();
				enableSave(false);
				editPane.disableEdit();
			}
		}
		
	}
	
	public void valueChanged(ListSelectionEvent e) {
		if (e.getValueIsAdjusting()) {
            return;
        }
        int row = listPane.getTable().getSelectedRow();
        if (row >= 0) {
        	TaskData d = listPane.getTTM().getTaskRowData(row);
        	editPane.setTaskData(d);
        	editMode = true;
        	editPane.enableEdit(d);
        }
        else {
        	editMode = false;
        }
	}

	public void stateChanged(ChangeEvent e) {
		// TODO Auto-generated method stub
		
	}
	
}