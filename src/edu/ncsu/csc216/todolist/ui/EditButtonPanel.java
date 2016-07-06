/**
 * 
 */
package edu.ncsu.csc216.todolist.ui;

import java.awt.*;
//import java.awt.event.ActionEvent;
//import java.awt.event.ActionListener;
//import java.util.ArrayList;

import javax.swing.*;
import javax.swing.border.EtchedBorder;
//import javax.swing.event.ChangeEvent;
//import javax.swing.event.ChangeListener;


/**
 * @author David R. Wright
 *
 */
public class EditButtonPanel extends JPanel {
	
	private static final long serialVersionUID = 2424320044323956092L;
	
	private JButton saveB;
	private JButton cancelB;
	private JButton addB;
	//private ArrayList<ActionListener> actionListeners = new ArrayList<ActionListener>();
	
	public EditButtonPanel() {
		super();
		setLayout(new BoxLayout(this, BoxLayout.LINE_AXIS));
		add(Box.createHorizontalGlue());
		add(getAddB());
		add(Box.createRigidArea(new Dimension(10, 0)));
		add(getSaveB());
		add(Box.createRigidArea(new Dimension(10, 0)));
		add(getCancelB());
		setBorder(BorderFactory.createEtchedBorder(EtchedBorder.RAISED));
	}
	
	JButton getAddB() {
		if (null == addB) {
			addB = new JButton("Add");
			addB.setVerticalTextPosition(AbstractButton.CENTER);
			addB.setHorizontalTextPosition(AbstractButton.CENTER);
			addB.setActionCommand("add");
			addB.setEnabled(false);
			//addB.addActionListener(this);
			addB.setActionCommand("add");
		}
		return addB;
		
	}
	
	JButton getSaveB() {
		if (null == saveB) {
			saveB = new JButton("Save");
			saveB.setVerticalTextPosition(AbstractButton.CENTER);
			saveB.setHorizontalTextPosition(AbstractButton.CENTER);
			saveB.setActionCommand("save");
			saveB.setEnabled(false);
			//saveB.addActionListener(this);
			saveB.setActionCommand("save");
		}
		return saveB;
		
	}
	
	JButton getCancelB() {
		if (null == cancelB) {
			cancelB = new JButton("Cancel");
			cancelB.setVerticalTextPosition(AbstractButton.CENTER);
			cancelB.setHorizontalTextPosition(AbstractButton.CENTER);
			cancelB.setEnabled(false);
			//cancelB.addActionListener(this);
			cancelB.setActionCommand("cancel");
		}
		return cancelB;
	}
	
	/*public void stateChanged(ChangeEvent e) {
		if (e.getSource().equals("save")) {
			getSaveB().setEnabled(true);
			getAddB().setEnabled(false);
		}
		else if (e.getSource().equals("add")) {
			getSaveB().setEnabled(false);
			getAddB().setEnabled(true);
		}
		else {
			getSaveB().setEnabled(false);
			getAddB().setEnabled(false);
		}
	}

	public void actionPerformed(ActionEvent e) {
		notifyActionListeners(e);		
	}

	public void addActionListener(ActionListener l) {
		actionListeners.add(l);
	}
	
	public void removeActionListener(ActionListener l) {
		actionListeners.remove(l);
	}
	
	public void notifyActionListeners(ActionEvent e) {
		java.util.Iterator<ActionListener> i = actionListeners.iterator();
		while (i.hasNext()) {
			i.next().actionPerformed(e);
		}
	}*/
}
