package edu.ncsu.csc216.todolist;

import edu.ncsu.csc216.todolist.ui.ToDoListGUI;

public class ToDoListMain {

	public static void main(String[] args) {

		// Schedule a job for the event-dispatching thread:
		// creating and showing this application's GUI.
		javax.swing.SwingUtilities.invokeLater(new Runnable() {
			public void run() {
				createAndShowGUI();
			}
		});
	}

	/**
	 * Create the GUI and show it. For thread safety, this method should be
	 * invoked from the event-dispatching thread.
	 */
	private static void createAndShowGUI() {
		ToDoList tdl = new ToDoList();
		ToDoListGUI frame = new ToDoListGUI(tdl);
		// Display the window.
		frame.pack();
		frame.setVisible(true);
	}

}