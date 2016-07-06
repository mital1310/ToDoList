package edu.ncsu.csc216.todolist.ui;

import javax.swing.table.AbstractTableModel;

class CatTableModel extends AbstractTableModel {
	private static final long serialVersionUID = 5954551753060998701L;
	private String[] colNames = {"ID", "Category Name", "Category Description"};
	private Object[][] data;
	
	public CatTableModel(Object[][] data) {
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
	public CategoryData getCatRowData(int row) {
		return new CategoryData((String)data[row][0], (String)data[row][1], (String)data[row][2]);
	}
	public void setCatRowData(int row, CategoryData d) {
		setValueAt(d.categoryID, row, 0);
		setValueAt(d.categoryName, row, 1);
		setValueAt(d.categoryDesc, row, 2);
	}
    
}
