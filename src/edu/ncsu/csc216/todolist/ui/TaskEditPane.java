/**
 * 
 */
package edu.ncsu.csc216.todolist.ui;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.*;

import javax.swing.*;
import javax.swing.event.*;

import edu.ncsu.csc216.todolist.model.CategoryList;
import edu.ncsu.csc216.todolist.model.Category;

/**
 * @author David R. Wright
 *
 */
class TaskEditPane extends JPanel implements Observer {

	private static final long serialVersionUID = 5479139338455751629L;
	
	private TaskData data;
	private CategoryList categories;
	private JTextField taskID, taskTitle;
	private JComboBox<Category> taskCat;
	private JTextArea taskDetails;
	private JSpinner taskStart, taskDue, taskCompleted;
	private JCheckBox complete;
	private boolean add;
	private boolean edit;
	
	
	public TaskEditPane(CategoryList cl) {
 		this(new TaskData(), cl);
	}
	
	public TaskEditPane(TaskData d, CategoryList cl) {
		super();
		add = false;
		edit = false;
		data = d;
		categories = cl;
		categories.addObserver(this);
		init();
	}
	
	
	private void init() {
		setLayout(new BoxLayout(this, BoxLayout.PAGE_AXIS));
		setBorder(BorderFactory.createLineBorder(Color.black));
		initView();
	}
	
	private void initView() {
		JPanel p = new JPanel(new FlowLayout(FlowLayout.LEADING));
		p.add(new JLabel("Task ID: ", SwingConstants.LEFT));
		p.add(getTaskID());
		p.add(Box.createRigidArea(new Dimension(0,5)));
		p.add(new JLabel("Task Title: ", SwingConstants.LEFT));
		p.add(getTaskTitle());
		p.add(Box.createRigidArea(new Dimension(0,5)));
		p.validate();
		this.add(p);
		p = new JPanel(new FlowLayout(FlowLayout.LEADING));
		p.add(new JLabel("Category: ", SwingConstants.LEFT));
		p.add(getCategory());
		p.add(Box.createRigidArea(new Dimension(0,5)));
		p.validate();
		this.add(p);
		p = new JPanel(new FlowLayout(FlowLayout.LEADING));
		p.add(new JLabel("Start Date & Time: ", SwingConstants.LEFT));
		p.add(getTaskStartSpinner());
		p.add(Box.createRigidArea(new Dimension(0,5)));
		p.validate();
		this.add(p);
		p = new JPanel(new FlowLayout(FlowLayout.LEADING));
		p.add(new JLabel("Due Date & Time: ", SwingConstants.LEFT));
		p.add(getTaskDueSpinner());
		p.add(Box.createRigidArea(new Dimension(0,5)));
		p.validate();
		this.add(p);
		p = new JPanel(new FlowLayout(FlowLayout.LEADING));
		p.add(new JLabel("Completed Date & Time: ", SwingConstants.LEFT));
		p.add(getTaskCompletedSpinner());
		p.add(Box.createRigidArea(new Dimension(0,5)));
		p.add(new JLabel("Completed? ", SwingConstants.LEFT));
		p.add(getComplete());
		p.add(Box.createRigidArea(new Dimension(0,5)));
		p.validate();
		this.add(p);
		p = new JPanel(new FlowLayout(FlowLayout.LEADING));
		p.add(new JLabel("Task Details: ", SwingConstants.LEFT));
		p.add(Box.createRigidArea(new Dimension(0,5)));
		p.validate();
		this.add(p);
		p = new JPanel(new FlowLayout(FlowLayout.LEADING));
		p.add(getTaskDetails());
		p.validate();
		this.add(p);
	}
		
	JSpinner getTaskStartSpinner() {
		if (null == taskStart) {
			taskStart = new JSpinner( new SpinnerDateModel() );
			JSpinner.DateEditor timeEditor = new JSpinner.DateEditor(taskStart, "EEE MMM d, yyyy HH:mm");
			taskStart.setEditor(timeEditor);
			((JSpinner.DateEditor)taskStart.getEditor()).getTextField().setVisible(false);
			((JSpinner.DateEditor)taskStart.getEditor()).getTextField().setColumns(20);
			((JSpinner.DateEditor)taskStart.getEditor()).getTextField().setHorizontalAlignment(JTextField.RIGHT);
		}
		return taskStart;
	}
	
	JSpinner getTaskDueSpinner() {
		if (null == taskDue) {
			taskDue = new JSpinner( new SpinnerDateModel() );
			JSpinner.DateEditor timeEditor = new JSpinner.DateEditor(taskDue, "EEE MMM d, yyyy HH:mm");
			taskDue.setEditor(timeEditor);
			((JSpinner.DateEditor)taskDue.getEditor()).getTextField().setVisible(false);
			((JSpinner.DateEditor)taskDue.getEditor()).getTextField().setColumns(20);
			((JSpinner.DateEditor)taskDue.getEditor()).getTextField().setHorizontalAlignment(JTextField.RIGHT);
		}
		return taskDue;
	}
	
	JSpinner getTaskCompletedSpinner() {
		if (null == taskCompleted) {
			taskCompleted = new JSpinner( new SpinnerDateModel() );
			JSpinner.DateEditor timeEditor = new JSpinner.DateEditor(taskCompleted, "EEE MMM d, yyyy HH:mm");
			taskCompleted.setEditor(timeEditor);
			((JSpinner.DateEditor)taskCompleted.getEditor()).getTextField().setVisible(false);
			((JSpinner.DateEditor)taskCompleted.getEditor()).getTextField().setColumns(20);
			((JSpinner.DateEditor)taskCompleted.getEditor()).getTextField().setHorizontalAlignment(JTextField.RIGHT);
		}
		return taskCompleted;
	}
	
	Date getTaskStart() {
		return (Date)getTaskStartSpinner().getModel().getValue();
	}
	
	Date getTaskDue() {
		return (Date)getTaskDueSpinner().getModel().getValue();
	}
	
	Date getTaskCompleted() {
		return (Date)getTaskCompletedSpinner().getModel().getValue();
	}
	
	JTextField getTaskID() {
		if (null == taskID) {
			taskID = new JTextField();
			taskID.setColumns(5);
			taskID.setEditable(false);
			taskID.setVisible(true);
			taskID.setHorizontalAlignment(SwingConstants.LEFT);
		}
		return taskID;
	}
	
	JTextField getTaskTitle() {
		if (null == taskTitle) {
			taskTitle = new JTextField();
			taskTitle.setColumns(50);
			taskTitle.setEditable(false);
			taskTitle.setVisible(true);
			taskTitle.setHorizontalAlignment(SwingConstants.LEFT);
		}
		return taskTitle;
	}
	
	JComboBox<Category> getCategory() {
		if (null == taskCat) {
			Category[] cats = new Category[categories.size()+1];
			for (int i=0; i<categories.size(); i++) {
				cats[i+1] = categories.getCategoryAt(i);
			}
			taskCat = new JComboBox<Category>(cats);
			taskCat.setVisible(true);
		}
		return taskCat;
	}
	
	JTextArea getTaskDetails() {
		if (null == taskDetails) {
			taskDetails = new JTextArea(7, 80);
			taskDetails.setEditable(false);
			taskDetails.setVisible(true);
			taskDetails.setLineWrap(true);
			taskDetails.setAutoscrolls(true);
		}
		return taskDetails;
	}
	
	JCheckBox getComplete() {
		if (null == complete) {
			complete = new JCheckBox();
			complete.setVisible(true);
			complete.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					JCheckBox b = (JCheckBox)e.getSource();
					if (b.isSelected()) {
						setTaskCompleted(new Date());
					}
					else {
						setTaskCompleted(null);
					}
				}
			});
		}
		return complete;
	}
	
	void setTaskStart(Date d) {
		if (null == d) {
			getTaskStartSpinner().getModel().setValue(new Date());
			((JSpinner.DateEditor)taskStart.getEditor()).getTextField().setVisible(false);
		}
		else {
			getTaskStartSpinner().getModel().setValue(d);
			((JSpinner.DateEditor)taskStart.getEditor()).getTextField().setVisible(true);
		}
	}
	
	void setTaskDue(Date d) {
		if (null == d) {
			getTaskDueSpinner().getModel().setValue(new Date());
			((JSpinner.DateEditor)taskDue.getEditor()).getTextField().setVisible(false);
		}
		else {
			getTaskDueSpinner().getModel().setValue(d);
			((JSpinner.DateEditor)taskDue.getEditor()).getTextField().setVisible(true);
		}
	}
	
	void setTaskCompleted(Date d) {
		if (null == d || !complete.isSelected()) {
			getTaskCompletedSpinner().getModel().setValue(new Date());
			((JSpinner.DateEditor)taskCompleted.getEditor()).getTextField().setVisible(false);
		}
		else {
			getTaskCompletedSpinner().getModel().setValue(d);
			((JSpinner.DateEditor)taskCompleted.getEditor()).getTextField().setVisible(true);
		}
	}
	
	boolean isAddMode() {
		return add;
	}
	
	boolean isEditMode() {
		return edit;
	}
	
	void enableAdd() {
		if (!add) {
			add = true;
			edit = false;
			clearFields();	
		}
	}
	
	void disableAdd() {
		add = false;
		clearFields();
	}
	
	void enableEdit(TaskData d) {
		if (!edit) {
			edit = true;
			data = d;
			fillFields();
		}
	}
	
	void disableEdit() {
		edit = false;
		clearFields();
	}
	
	boolean fieldsNotEmpty() {
		return getTaskTitle().getDocument().getLength() != 0 && getTaskDetails().getDocument().getLength() != 0 &&
			   getTaskStart() != null && getTaskDue() != null && 0 != getCategory().getSelectedIndex();
	}

	void setTaskData(TaskData d) {
		data = d;
		fillFields();
	}
	
	void addFieldListener(EventListener l) {
		getTaskTitle().getDocument().addDocumentListener((DocumentListener)l);
		getTaskDetails().getDocument().addDocumentListener((DocumentListener)l);
		getTaskStartSpinner().addChangeListener((ChangeListener)l);
		getTaskDueSpinner().addChangeListener((ChangeListener)l);
		getTaskCompletedSpinner().addChangeListener((ChangeListener)l);
		
	}

	void fillFields() {
		if (null == data) {
			getTaskID().setText("");
			getTaskTitle().setText("");
			getTaskDetails().setText("");
			setTaskStart(null);
			setTaskDue(null);
			setTaskCompleted(null);
			getComplete().setSelected(false);
			getCategory().setSelectedItem(0);
			getTaskID().setEditable(false);
			getTaskTitle().setEditable(false);
			getTaskDetails().setEditable(false);
			getComplete().setEnabled(false);
			getCategory().setEnabled(false);
		}
		else {
			getTaskID().setText(data.taskID);
			getTaskTitle().setText(data.title);
			getTaskDetails().setText(data.details);
			setTaskStart(data.startDateTime);
			setTaskDue(data.dueDateTime);
			setTaskCompleted(data.completedDateTime);
			getComplete().setSelected(data.completed);
			getCategory().setSelectedItem(data.category);
		}
		if (add) {
			getTaskTitle().setEditable(true);
			getTaskDetails().setEditable(true);
			setTaskStart(new Date());
			setTaskDue(new Date());
			getComplete().setEnabled(true);
			getCategory().setEnabled(true);
		}
		if (edit) {
			getTaskTitle().setEditable(true);
			getTaskDetails().setEditable(true);
			getComplete().setEnabled(true);
			getCategory().setEnabled(true);
		}
	}
	
	void clearFields() {
		data = null;
		fillFields();
	}
	
	TaskData getFields() {
		return new TaskData(getTaskID().getText(), getTaskTitle().getText(), getTaskDetails().getText(), 
				 			getTaskStart(), getTaskDue(), getTaskCompleted(), getComplete().isSelected(),
				 			(Category)getCategory().getSelectedItem());
	}
	
	public void update(Observable o, Object arg) {
		if (o instanceof CategoryList) {
			categories = (CategoryList)o;
			Category[] cats = new Category[categories.size()+1];
			cats[0] = null;
			for (int i=0; i<categories.size(); i++) {
				cats[i+1] = categories.getCategoryAt(i);
			}
			getCategory().setModel(new DefaultComboBoxModel<Category>(cats));
		}
		
	}
}
