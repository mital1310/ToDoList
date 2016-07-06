/**
 * 
 */
package edu.ncsu.csc216.todolist.ui;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.*;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

import edu.ncsu.csc216.todolist.model.CategoryList;
import edu.ncsu.csc216.todolist.model.Category;

/**
 * @author David R. Wright
 *
 */
class CategoryTab extends JPanel implements DocumentListener, ActionListener, ListSelectionListener {

	private static final long serialVersionUID = 1077971879823499611L;

	private CategoryListPane listPane;
	private CategoryEditPane editPane;
	private EditButtonPanel buttonP;
	private boolean addMode;
	private boolean editMode;
	private CategoryList catList;
	
	public CategoryTab(CategoryList cl) {
		super();
		catList = cl;
		addMode = false;
		editMode = false;
		listPane = new CategoryListPane(cl);
		listPane.getTable().getSelectionModel().addListSelectionListener(this);
		editPane = new CategoryEditPane();
		editPane.addFieldListener(this);
		setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
		add(listPane);
		add(Box.createRigidArea(new Dimension(0,5)));
		add(editPane);
		add(Box.createRigidArea(new Dimension(0,5)));
		add(getButtons());
	}
	
	public void addNewCategory() {
		listPane.clearSelection();
		addMode = true;
		editPane.enableAdd();
	}
	
	public void deleteCategory() {
		int row = listPane.getTable().getSelectedRow();
		if (row >= 0) {
			CategoryData d = listPane.getCTM().getCatRowData(row);
			catList.removeCategory(d.categoryID);
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
			CategoryData d = editPane.getFields();
			editPane.setCategoryData(d);
			catList.addCategory(d.categoryName, d.categoryDesc);
			enableAdd(false);
			editPane.disableAdd();
		}
		else if (editMode && e.getActionCommand().equals("save")) {
			CategoryData d = editPane.getFields();
			editPane.setCategoryData(d);
			Category cat = catList.getCategoryAt(catList.indexOf(d.categoryID));
			cat.setDescription(d.categoryDesc);
			cat.setName(d.categoryName);
			listPane.clearSelection();
			enableSave(false);
			editPane.disableEdit();
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
        	CategoryData d = listPane.getCTM().getCatRowData(row);
        	editPane.setCategoryData(d);
        	editMode = true;
        	editPane.enableEdit(d);
        }
        else {
        	editMode = false;
        }
	}
	
}
