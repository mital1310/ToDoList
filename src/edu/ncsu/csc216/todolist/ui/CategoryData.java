package edu.ncsu.csc216.todolist.ui;

/**
 * Simple class for transfering data within the GUI via events,
 * thus package-level visibility
 * 
 * @author David R. Wright
 *
 */
class CategoryData {

	public String categoryID;
	public String categoryName;
	public String categoryDesc;
	
	public CategoryData(String id, String name, String desc) {
		categoryID = id;
		categoryName = name;
		categoryDesc = desc;
	}

	public String toString() {
		return "ID: " + categoryID + "  Name: " + categoryName + "  Desc: " + categoryDesc;
	}
	
	public boolean equals(Object obj) {
		if (obj instanceof CategoryData) {
			CategoryData d = (CategoryData)obj;
			return d.categoryID.equals(this.categoryID) && 
				   d.categoryName.equals(this.categoryName) && 
				   d.categoryDesc.equals(this.categoryDesc);
		}
		return false;
	}
}