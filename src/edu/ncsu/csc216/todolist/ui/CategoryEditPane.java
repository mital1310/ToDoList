/**
 * 
 */
package edu.ncsu.csc216.todolist.ui;

import java.awt.*;

import javax.swing.*;
import javax.swing.event.DocumentListener;

/**
 * @author David R. Wright
 *
 */
class CategoryEditPane extends JPanel {

	private static final long serialVersionUID = -3960029015921432795L;
	
	private CategoryData data;
	private JTextField catID, catName;
	private JTextArea catDesc;
	private boolean add;
	private boolean edit;
	
	
	public CategoryEditPane() {
		this(new CategoryData("", "", ""));
	}
	
	public CategoryEditPane(CategoryData d) {
		super();
		data = d;
		add = false;
		edit = false;
		init();
	}
	
	public void setCategoryData(CategoryData d) {
		data = d;
		fillFields();
	}
	
	private void init() {
		setLayout(new BoxLayout(this, BoxLayout.PAGE_AXIS));
		setBorder(BorderFactory.createLineBorder(Color.black));
		initView();
		fillFields();
	}
	
	private void initView() {
		JPanel p = new JPanel(new FlowLayout(FlowLayout.LEADING));
		p.add(new JLabel("Category ID: ", SwingConstants.LEFT));
		p.add(getCatID());
		this.add(p);
		p = new JPanel(new FlowLayout(FlowLayout.LEADING));
		p.add(new JLabel("Category Name: ", SwingConstants.LEFT));
		p.add(getCatName());
		this.add(p);
		p = new JPanel(new FlowLayout(FlowLayout.LEADING));
		p.add(new JLabel("Category Description: ", SwingConstants.LEFT));
		this.add(p);
		p = new JPanel(new FlowLayout(FlowLayout.LEADING));
		p.add(getCatDesc());
		this.add(p);
	}
	
	private JTextField getCatID() {
		if (null == catID) {
			catID = new JTextField(10);
			catID.setEditable(false);
			catID.setVisible(true);
			catID.setHorizontalAlignment(SwingConstants.LEFT);
		}
		return catID;
	}
	
	private JTextField getCatName() {
		if (null == catName) {
			catName = new JTextField(50);
			catName.setEditable(false);
			catName.setVisible(true);
			catName.setHorizontalAlignment(SwingConstants.LEFT);
		}
		return catName;
	}
	
	private JTextArea getCatDesc() {
		if (null == catDesc) {
			catDesc = new JTextArea(5, 70);
			catDesc.setEditable(false);
			catDesc.setVisible(true);
			catDesc.setLineWrap(true);
			catDesc.setAutoscrolls(true);
		}
		return catDesc;
	}
	
	void addFieldListener(DocumentListener l) {
		getCatName().getDocument().addDocumentListener(l);
		getCatDesc().getDocument().addDocumentListener(l);
	}

	void fillFields() {
		if (null == data) {
			catID.setText("");
			catName.setText("");
			catDesc.setText("");
			catName.setEditable(false);
			catDesc.setEditable(false);
		}
		else {
			catID.setText(data.categoryID);
			catName.setText(data.categoryName);
			catDesc.setText(data.categoryDesc);
		}
		if (add || edit) {
			catName.setEditable(true);
			catDesc.setEditable(true);
		}
		
	}
	
	void clearFields() {
		data = null;
		fillFields();
	}
	
	CategoryData getFields() {
		return new CategoryData(getCatID().getText(), getCatName().getText(), getCatDesc().getText());
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
	
	void enableEdit(CategoryData d) {
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
		return getCatName().getDocument().getLength() != 0 && getCatDesc().getDocument().getLength() != 0;
	}

	
}
