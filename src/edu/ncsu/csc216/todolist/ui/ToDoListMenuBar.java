/**
 * 
 */
package edu.ncsu.csc216.todolist.ui;

import javax.swing.*;
import java.awt.event.KeyEvent;

/**
 * @author David R. Wright
 *
 */
public class ToDoListMenuBar extends JMenuBar {
	
	private static final long serialVersionUID = 49171L;
	
	private static final String[] fileMenuNames = {"Open File", "Save File", "Exit"};
	private static final int[] fileMenuKeys = {KeyEvent.VK_O, KeyEvent.VK_S, KeyEvent.VK_X};
	private static final String[] fileMenuCmds = {"11", "12", "13"};
	private static final String[] taskListMenuNames = {"Rename Task List", "New Task List", "Delete Task List"};
	private static final int[] taskListMenuKeys = {KeyEvent.VK_R, KeyEvent.VK_N, KeyEvent.VK_D};
	private static final String[] taskListMenuCmds = {"21", "22", "23"};
	private static final String[] tasksMenuNames = {"Add New Task", "Delete Task"};
	private static final int[] tasksMenuKeys = {KeyEvent.VK_A, KeyEvent.VK_D};
	private static final String[] tasksMenuCmds = {"31", "32"};
	private static final String[] categoryMenuNames = {"Add Category", "Delete Category"};
	private static final int[] categoryMenuKeys = {KeyEvent.VK_A, KeyEvent.VK_D};
	private static final String[] categoryMenuCmds = {"41", "42"};
	private ToDoListGUI parent;
	
	public ToDoListMenuBar(ToDoListGUI parent) {
		super();
		this.parent = parent;
		initMenu();
	}
	
	private void initMenu() {
		this.add(fileMenu());
		this.add(taskListMenu());
		this.add(taskMenu());
		this.add(categoryMenu());
	}
	
	private JMenu fileMenu() {
		JMenu menu = new JMenu("File");
		menu.setMnemonic(KeyEvent.VK_F);
		addMenuItems(menu, fileMenuNames, fileMenuKeys, fileMenuCmds);
		return menu;
	}
	
	private JMenu taskListMenu() {
		JMenu menu = new JMenu("Task List");
		menu.setMnemonic(KeyEvent.VK_L);
		addMenuItems(menu, taskListMenuNames, taskListMenuKeys, taskListMenuCmds);
		return menu;
	}
	
	private JMenu taskMenu() {
		JMenu menu = new JMenu("Tasks");
		menu.setMnemonic(KeyEvent.VK_T);
		addMenuItems(menu, tasksMenuNames, tasksMenuKeys, tasksMenuCmds);
		return menu;
	}
	
	
	private JMenu categoryMenu() {
		JMenu menu = new JMenu("Categories");
		menu.setMnemonic(KeyEvent.VK_C);
		addMenuItems(menu, categoryMenuNames, categoryMenuKeys, categoryMenuCmds);
		return menu;
	}
	
	private void addMenuItems(JMenu menu, String[] itemNames, int[] hotkeys, String[] cmds) {
		for (int i=0; i<itemNames.length; i++) {
			JMenuItem item = new JMenuItem(itemNames[i], hotkeys[i]);
			item.setActionCommand(cmds[i]);
			item.addActionListener(parent);
			menu.add(item);
		}
	}
	
	
	

}
