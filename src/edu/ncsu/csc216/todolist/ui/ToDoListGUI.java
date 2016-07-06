/**
 * 
 */
package edu.ncsu.csc216.todolist.ui;

import javax.swing.*;
import javax.swing.filechooser.FileNameExtensionFilter;

import java.awt.BorderLayout;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;

import edu.ncsu.csc216.todolist.*;
import edu.ncsu.csc216.todolist.model.TaskList;

/**
 * @author David Wright
 *
 */
public class ToDoListGUI extends JFrame implements ActionListener, WindowListener {

	
	private static final long serialVersionUID = 48371L;
	
	private ToDoList tdl;
	private JTabbedPane tabbedPane;
	private boolean openFile;
	
	public ToDoListGUI(ToDoList t) {
		super("ToDoList");
		setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
		tdl = t;
		openFile = false;
		initGUI();
		this.addWindowListener(this);
	}
	
	public ToDoList getToDoList() {
		return tdl;
	}
	
	private void initGUI() {
		setPreferredSize(new Dimension(1000, 650));
		setJMenuBar(new ToDoListMenuBar(this));
		Container contentPane = getContentPane();
		contentPane.setLayout(new BoxLayout(contentPane, BoxLayout.PAGE_AXIS));
		initTabbedPane();
	}
	
	private void initTabbedPane() {
		tabbedPane = new JTabbedPane();
		tabbedPane.addTab("Categories", new CategoryTab(tdl.getCategoryList()));
		for (int i=0; i<tdl.getNumTaskLists(); i++) {
			TaskList t = tdl.getTaskList(i);
			tabbedPane.addTab(t.getName(), new TaskTab(t, tdl.getCategoryList()));
		}
		tabbedPane.setSelectedIndex(1);
		//JPanel p = new JPanel();
		//p.add(tabbedPane, BorderLayout.CENTER);
		//p.setVisible(true);
		if (openFile) {
			getContentPane().removeAll();
			openFile = false;
		}
		getContentPane().add(tabbedPane, BorderLayout.CENTER);
		getContentPane().validate();
		//setContentPane(p);
		
	}
	
	public void actionPerformed(ActionEvent e) {
		if (null != e.getSource() && e.getSource() instanceof JMenuItem) {
			JMenuItem source = (JMenuItem)(e.getSource());
			String cmd = source.getActionCommand();
			switch (getInt(cmd)) {
				case 11:
					doOpenFile();
					break;
				case 12:
					doSaveFile();
					break;
				case 13:
					doExit();
					break;
				case 21: 
					doRenameTL();
					break;
				case 22:
					doNewTL();
					break;
				case 23:
					doDeleteTL();
					break;
				case 31:
					doAddT();
					break;
				case 32:
					doDeleteT();
					break;
				case 41:
					doAddC();
					break;
				case 42:
					doDeleteC();
					break;
				default:
					break;
			}
		}
	}
		

	
	protected void doOpenFile() {
		JFileChooser chooser = new JFileChooser("./");
	    FileNameExtensionFilter filter = new FileNameExtensionFilter("ToDoList files", "tdl");
	    chooser.setFileFilter(filter);
	    chooser.setMultiSelectionEnabled(false);
	    int returnVal = chooser.showOpenDialog(this);
	    if(returnVal == JFileChooser.APPROVE_OPTION) {
	       tdl.openDataFile(chooser.getSelectedFile().getName());
	       openFile = true;
	       initTabbedPane();
	    }
	}
	
	protected void doSaveFile() {
		JFileChooser chooser = new JFileChooser("./");
	    FileNameExtensionFilter filter = new FileNameExtensionFilter("ToDoList files", "tdl");
	    chooser.setFileFilter(filter);
	    chooser.setMultiSelectionEnabled(false);
	    int returnVal = chooser.showSaveDialog(this);
	    if(returnVal == JFileChooser.APPROVE_OPTION) {
	       String filename = chooser.getSelectedFile().getName();
	       tdl.setFilename(filename);
	       tdl.saveDataFile(filename);
	    }
	}
	
	protected void doExit() {
		if (tdl.isChanged()) {
			doSaveFile();
		}
		System.exit(NORMAL);
	}
	
	protected void doRenameTL() {
		int tab = tabbedPane.getSelectedIndex();
		if (tabbedPane.getComponentAt(tab) instanceof TaskTab) {
			String newName = tabbedPane.getTitleAt(tab);
			boolean loop = true;
			while (loop) {
				newName = (String)JOptionPane.showInputDialog(this, "Edit the name of this Task List",
																newName, JOptionPane.PLAIN_MESSAGE);
				if (null == newName || newName.length() == 0) {
					JOptionPane.showMessageDialog(this, "Task List name cannot be empty.", "Task List Error", JOptionPane.ERROR_MESSAGE);
				}
				if (null != newName) {
					tdl.getTaskList(tab - 1).setName(newName);
					tabbedPane.setTitleAt(tab, newName);
					loop = false;
				}
			}
		}
		else {
			JOptionPane.showMessageDialog(this, "Category List cannot be renamed.", "Task List Error", JOptionPane.ERROR_MESSAGE);
		}
	}
	
	protected void doNewTL() {
		int newList = tdl.addTaskList();
		TaskList t = tdl.getTaskList(newList);
		tabbedPane.addTab(t.getName(), new TaskTab(t, tdl.getCategoryList()));
	}
	
	protected void doDeleteTL() {
		int tab = tabbedPane.getSelectedIndex();
		if (0 == tab) {
			JOptionPane.showMessageDialog(this, "Task List not selected.", "Task List Error", JOptionPane.ERROR_MESSAGE);
			return;
		}
		if (1 == tdl.getNumTaskLists()) {
			JOptionPane.showMessageDialog(this, "Cannot delete last Task List.", "Task List Error", JOptionPane.ERROR_MESSAGE);
		}
		else {
			int optRes = JOptionPane.showConfirmDialog(this, "Confirm Task List Delete", null, JOptionPane.YES_NO_OPTION);
			if (tab > 0 && JOptionPane.YES_OPTION == optRes) {
				tdl.removeTaskList(tab-1);
				tabbedPane.remove(tab);
			}
		}
				
	}
	
	protected void doAddT() {
		int index = tabbedPane.getSelectedIndex();
		if (tabbedPane.getComponentAt(index) instanceof TaskTab) {
			((TaskTab)tabbedPane.getSelectedComponent()).addNewTask();
			
		}
		else {
			JOptionPane.showMessageDialog(this, "Task List not selected.", "Task List Error", JOptionPane.ERROR_MESSAGE);
			return;
		}
	}
	
	protected void doDeleteT() {
		int index = tabbedPane.getSelectedIndex();
		if (0 < index || 0 > index) {
			((TaskTab)tabbedPane.getSelectedComponent()).deleteTask();
		}
		else {
			JOptionPane.showMessageDialog(this, "Task List not selected.", "Task List Error", JOptionPane.ERROR_MESSAGE);
		}
	}
	
	protected void doAddC() {
		tabbedPane.setSelectedIndex(0);
		((CategoryTab)tabbedPane.getSelectedComponent()).addNewCategory();
	}
	
	protected void doDeleteC() {
		int index = tabbedPane.getSelectedIndex();
		if (0 != index) {
			JOptionPane.showMessageDialog(this, "Category List not selected.", "Category List Error", JOptionPane.ERROR_MESSAGE);
		}
		else {
			((CategoryTab)tabbedPane.getSelectedComponent()).deleteCategory();
		}
	}
	
	protected int getInt(String s) {
		try {
			return Integer.parseInt(s);
		}
		catch (NumberFormatException e) {
			return -1;
		}
	}

	public void windowOpened(WindowEvent e) {
		// not interested in this event
	}

	public void windowClosing(WindowEvent e) {
		doExit();
	}

	public void windowClosed(WindowEvent e) {
		// not interested in this event
	}

	public void windowIconified(WindowEvent e) {
		// not interested in this event
	}

	public void windowDeiconified(WindowEvent e) {
		// not interested in this event
	}

	public void windowActivated(WindowEvent e) {
		// not interested in this event
	}

	public void windowDeactivated(WindowEvent e) {
		// not interested in this event
	}

	
}
