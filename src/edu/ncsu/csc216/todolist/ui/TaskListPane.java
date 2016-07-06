/**
 * 
 */
package edu.ncsu.csc216.todolist.ui;

import java.awt.Color;

import javax.swing.*;
import javax.swing.table.*;

import java.util.*;

import edu.ncsu.csc216.todolist.model.TaskList;


/**
 * @author David R. Wright
 *
 */
public class TaskListPane extends JScrollPane implements Observer {

	private static final long serialVersionUID = -2210716111020406799L;
	
	private TaskList tasks;
	private TaskTableModel ttm;
	private JTable table;
	private int[] colWidths = {50, 300, 1, 175, 175, 175, 75, 100};
	
		
	public TaskListPane(TaskList c) {
		super();
		tasks = c;
		tasks.addObserver(this);
		ttm = new TaskTableModel(tasks.get2DArray());
		initView();
	}
	
	TaskTableModel getTTM() {
		return ttm;
	}
	
	JTable getTable() {
		return table;
	}
	
	private void initView() {
		table = new JTable(ttm);
		for (int i=0; i<colWidths.length; i++) {
			TableColumn col = table.getColumnModel().getColumn(i);
			col.setPreferredWidth(colWidths[i]);
		}
		table.removeColumn(table.getColumnModel().getColumn(2)); // remove Details column from view
		table.setSelectionMode(javax.swing.ListSelectionModel.SINGLE_SELECTION);
		table.setFillsViewportHeight(true);
		setViewportView(table);
		setBorder(BorderFactory.createLineBorder(Color.black));
	}
	
	void clearSelection() {
		table.clearSelection();
	}
	
	public void update(Observable o, Object arg) {
		if (o instanceof TaskList) {
			TaskList tl = (TaskList)o;
			if (tl.size() != ttm.getRowCount()) {
				 ttm = new TaskTableModel(tl.get2DArray());
				 table.setModel(ttm);
			}
			else {
				Object[][] arr = tl.get2DArray();
				for (int i=0; i<arr.length; i++) {
					for (int j=0; j<ttm.getColumnCount(); j++) {
						ttm.setValueAt(arr[i][j], i, j);
					}
				}
			}
		}
	}
	
	
	
}
