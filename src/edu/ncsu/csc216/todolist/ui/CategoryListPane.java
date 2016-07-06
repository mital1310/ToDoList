/**
 * 
 */
package edu.ncsu.csc216.todolist.ui;

import java.awt.Color;
//import java.awt.event.ActionListener;
//import java.awt.event.ActionEvent;

import javax.swing.*;
//import javax.swing.event.*;
import javax.swing.table.*;

import java.util.*;

import edu.ncsu.csc216.todolist.model.CategoryList;

/**
 * @author David R. Wright
 *
 */
class CategoryListPane extends JScrollPane implements Observer {

	private static final long serialVersionUID = 9036210048399941619L;
	
	private CategoryList catList;
	private CatTableModel ctm;
	private JTable table;
	private int[] colWidths = {50, 250, 750};
	
	
	public CategoryListPane(CategoryList cl) {
		super();
		catList = cl;
		catList.addObserver(this);
		ctm = new CatTableModel(cl.get2DArray());
		initView();
	}
	
	void clearSelection() {
		table.clearSelection();
	}
	
	CatTableModel getCTM() {
		return ctm;
	}
	
	JTable getTable() {
		return table;
	}
	
	private void initView() {
		table = new JTable(ctm);
		for (int i=0; i<colWidths.length; i++) {
			TableColumn col = table.getColumnModel().getColumn(i);
			col.setPreferredWidth(colWidths[i]);
		}
		table.setSelectionMode(javax.swing.ListSelectionModel.SINGLE_SELECTION);
		table.setFillsViewportHeight(false);
		setViewportView(table);
		setBorder(BorderFactory.createLineBorder(Color.black));
	}
	
	public void update(Observable o, Object arg) {
		if (o instanceof CategoryList) {
			CategoryList cl = (CategoryList)o;
			if (cl.size() != ctm.getRowCount()) {
				 ctm = new CatTableModel(cl.get2DArray());
				 table.setModel(ctm);
			}
			else {
				Object[][] arr = cl.get2DArray();
				for (int i=0; i<arr.length; i++) {
					for (int j=0; j<ctm.getColumnCount(); j++) {
						ctm.setValueAt(arr[i][j], i, j);
					}
				}
			}
		}
	}
	
	
}
