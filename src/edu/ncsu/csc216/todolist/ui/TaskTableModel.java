package edu.ncsu.csc216.todolist.ui;

import java.util.Date;
import javax.swing.table.AbstractTableModel;

import edu.ncsu.csc216.todolist.model.Category;

class TaskTableModel extends AbstractTableModel {
	private static final long serialVersionUID = 5954551753060998701L;
	private String[] colNames = {"ID", "Title", "Details", "Start Date", "Due Date", "Completed Date", "Complete", "Category"};
	private Object[][] data;
	
	public TaskTableModel(Object[][] data) {
		super();
		this.data = data;
	}
	public int getRowCount() {
		return data.length;
	}
	public int getColumnCount() {
		return colNames.length;
	}
	public String getColumnName(int col) {
		return colNames[col];
	}
	public Object getValueAt(int row, int col) {
		return data[row][col];
	}
	public void setValueAt(Object value, int row, int col) {
        data[row][col] = value;
        fireTableCellUpdated(row, col);	
	}
	public TaskData getTaskRowData(int row) {
		return new TaskData((String)data[row][0], (String)data[row][1], (String)data[row][2], (Date)data[row][3],
							(Date)data[row][4], (Date)data[row][5], (Boolean)data[row][6], (Category)data[row][7]);
	}
	public void setTaskRowData(int row, TaskData d) {
		data[row] = d.getDataArray(); 
	}
}
