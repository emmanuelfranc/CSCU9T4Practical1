// GUI and main program for the Training Record
package com.stir.cscu9t4practical1;

import java.awt.*;
import java.awt.event.*;
import java.util.*;
import javax.swing.*;
import java.lang.Number;

public class TrainingRecordGUI extends JFrame implements ActionListener {

	private JTextField name = new JTextField(30);
	private JTextField day = new JTextField(2);
	private JTextField month = new JTextField(2);
	private JTextField year = new JTextField(4);
	private JTextField hours = new JTextField(2);
	private JTextField mins = new JTextField(2);
	private JTextField secs = new JTextField(2);
	private JTextField dist = new JTextField(4);
	private JTextField terrainTf = new JTextField(5);
	private JTextField tempoTf = new JTextField(5);
	private JTextField whereTf = new JTextField(6);
	private JTextField recoveryTf = new JTextField(3);
	private JTextField repetitionsTf = new JTextField(3);
	
	private JLabel labn = new JLabel(" Name:");
	private JLabel labd = new JLabel(" Day:");
	private JLabel labm = new JLabel(" Month:");
	private JLabel laby = new JLabel(" Year:");
	private JLabel labh = new JLabel(" Hours:");
	private JLabel labmm = new JLabel(" Mins:");
	private JLabel labs = new JLabel(" Secs:");
	private JLabel labdist = new JLabel(" Distance (km):");
	private JLabel terrainLb = new JLabel("Terrain: ");
	private JLabel tempoLb = new JLabel("Tempo: ");
	private JLabel whereLb = new JLabel("Where: ");
	private JLabel recoveryLb = new JLabel("Recovery: ");
	private JLabel repetitionsLb = new JLabel("Repetitions: ");
	
	private JButton addR = new JButton("Add");
	private JButton lookUpByDate = new JButton("Look Up");
	private JButton findAllByDate = new JButton("Find All By Date");
	private JButton remove = new JButton("remove");
	
	private String[] entries = {"Select Entry: ","Sprint Entry", "Cycle Entry", "Swim Entry"};
	private JComboBox comboBox = new JComboBox<String>(entries);

	private TrainingRecord myAthletes = new TrainingRecord();

	private JTextArea outputArea = new JTextArea(5, 50);

	public static void main(String[] args) {
		TrainingRecordGUI applic = new TrainingRecordGUI();
	} // main

	// set up the GUI
	public TrainingRecordGUI() {
		super("Training Record");
		setLayout(new FlowLayout());
		add(comboBox);
		comboBox.addActionListener(this);
		add(labn);
		add(name);
		name.setEditable(true);
		add(labd);
		add(day);
		day.setEditable(true);
		add(labm);
		add(month);
		month.setEditable(true);
		add(laby);
		add(year);
		year.setEditable(true);
		add(labh);
		add(hours);
		hours.setEditable(true);
		add(labmm);
		add(mins);
		mins.setEditable(true);
		add(labs);
		add(secs);
		secs.setEditable(true);
		add(labdist);
		add(dist);
		dist.setEditable(true);
		add(recoveryLb);
		recoveryLb.setVisible(false);
		add(recoveryTf);
		recoveryTf.setVisible(false);
		add(repetitionsLb);
		repetitionsLb.setVisible(false);
		add(repetitionsTf);
		repetitionsTf.setVisible(false);
		add(terrainLb);
		terrainLb.setVisible(false);
		add(terrainTf);
		terrainTf.setVisible(false);
		add(tempoLb);
		tempoLb.setVisible(false);
		add(tempoTf);
		tempoTf.setVisible(false);
		add(whereLb);
		whereLb.setVisible(false);
		add(whereTf);
		whereTf.setVisible(false);
		add(addR);
		addR.addActionListener(this);
		add(remove);
		remove.addActionListener(this);
		remove.setEnabled(false);
		add(lookUpByDate);
		add(findAllByDate);
		lookUpByDate.addActionListener(this);
		lookUpByDate.setEnabled(false);
		findAllByDate.addActionListener(this);
		findAllByDate.setEnabled(false);
		add(outputArea);
		outputArea.setEditable(false);
		setSize(720, 200);
		setVisible(true);
		blankDisplay();

		// To save typing in new entries while testing, uncomment
		// the following lines (or add your own test cases)

	} // constructor

	// listen for and respond to GUI events
	public void actionPerformed(ActionEvent event) {
		String message = "";
//		try {
		if (event.getSource()==comboBox) {
			if (comboBox.getSelectedIndex() ==0) {
				message = "Select an Entry";
			}
			if(comboBox.getSelectedIndex() == 1) {
				recoveryLb.setVisible(true);
				recoveryTf.setVisible(true);
				repetitionsLb.setVisible(true);
				repetitionsTf.setVisible(true);
				
				terrainLb.setVisible(false);
				terrainTf.setVisible(false);
				tempoLb.setVisible(false);
				tempoTf.setVisible(false);
				
				whereLb.setVisible(false);
				whereTf.setVisible(false);
			}
			else if(comboBox.getSelectedIndex()== 2) {
				terrainLb.setVisible(true);
				terrainTf.setVisible(true);
				tempoLb.setVisible(true);
				tempoTf.setVisible(true);
				
				recoveryLb.setVisible(false);
				recoveryTf.setVisible(false);
				repetitionsLb.setVisible(false);
				repetitionsTf.setVisible(false);
				
				whereLb.setVisible(false);
				whereTf.setVisible(false);
			}
			else if (comboBox.getSelectedIndex() == 3) {
				whereLb.setVisible(true);
				whereTf.setVisible(true);
				
				recoveryLb.setVisible(false);
				recoveryTf.setVisible(false);
				repetitionsLb.setVisible(false);
				repetitionsTf.setVisible(false);
				
				terrainLb.setVisible(false);
				terrainTf.setVisible(false);
				tempoLb.setVisible(false);
				tempoTf.setVisible(false);
			}
		}
			if (event.getSource() == addR) {
				if (!name.getText().isEmpty()) {
					message = addEntry("generic");
					lookUpByDate.setEnabled(true);
					findAllByDate.setEnabled(true);
					remove.setEnabled(true);
				} else {
					message = "Please input a valid value";
				}
			}
			if (event.getSource() == lookUpByDate) {
				message = lookupEntry();
			}
			if (event.getSource() == findAllByDate) {
				message = lookUpAllEntry();
			}
			if (event.getSource() == remove) {
				message = removeEntry();
			}
			outputArea.setText(message);
			blankDisplay();
//		} catch (Exception e) {
//			message = "Please input an valid value";
//		}
	} // actionPerformed

	private String removeEntry() {
		// TODO Auto-generated method stub
		String n = name.getText();
		int m = Integer.parseInt(month.getText());
		int d = Integer.parseInt(day.getText());
		int y = Integer.parseInt(year.getText());
		
		String message = myAthletes.removeEntry(n, d, m, y);
		
		return message;
	}

	public String addEntry(String what) {
		String message = "Record added\n";
		System.out.println("Adding " + what + " entry to the records");
		String n = name.getText();
		int m = Integer.parseInt(month.getText());
		int d = Integer.parseInt(day.getText());
		int y = Integer.parseInt(year.getText());
		float km = java.lang.Float.parseFloat(dist.getText());
		int h = Integer.parseInt(hours.getText());
		int mm = Integer.parseInt(mins.getText());
		int s = Integer.parseInt(secs.getText());
		String whr = whereTf.getText();
		int rep = Integer.parseInt(repetitionsTf.getText());
		int rec = Integer.parseInt(recoveryTf.getText());
		String ter = terrainTf.getText();
		String tem = tempoTf.getText();
		
		if (comboBox.getSelectedIndex() == 1) {
			SprintEntry sprints = new SprintEntry(n, d, m, y, h, mm, s, km, rep, rec);
			myAthletes.addEntry(sprints);
		}
		else if (comboBox.getSelectedIndex() == 2) {
			CycleEntry cycle = new CycleEntry(n, d, m, y, h, mm, s, km, ter, tem);
			myAthletes.addEntry(cycle);
		}
		else if (comboBox.getSelectedIndex() == 3) {
			SwimEntry cycle = new SwimEntry(n, d, m, y, h, mm, s, km, whr);
			myAthletes.addEntry(cycle);
		}
		else {
			Entry e = new Entry(n, d, m, y, h, mm, s, km);
			myAthletes.addEntry(e);
		}
		return message;
	}

	public String lookupEntry() {
		int m = Integer.parseInt(month.getText());
		int d = Integer.parseInt(day.getText());
		int y = Integer.parseInt(year.getText());
		outputArea.setText("looking up record ...");
		String message = myAthletes.lookupEntry(d, m, y);
		return message;
	}

	public String lookUpAllEntry() {
		int m = Integer.parseInt(month.getText());
		int d = Integer.parseInt(day.getText());
		int y = Integer.parseInt(year.getText());
		outputArea.setText("looking up record...");
		String message = myAthletes.lookupAllEntries(d, m, y);

		return message;
	}

	public void blankDisplay() {
		name.setText("");
		day.setText("");
		month.setText("");
		year.setText("");
		hours.setText("");
		mins.setText("");
		secs.setText("");
		dist.setText("");

	}// blankDisplay
		// Fills the input fields on the display for testing purposes only

	public void fillDisplay(Entry ent) {
		name.setText(ent.getName());
		day.setText(String.valueOf(ent.getDay()));
		month.setText(String.valueOf(ent.getMonth()));
		year.setText(String.valueOf(ent.getYear()));
		hours.setText(String.valueOf(ent.getHour()));
		mins.setText(String.valueOf(ent.getMin()));
		secs.setText(String.valueOf(ent.getSec()));
		dist.setText(String.valueOf(ent.getDistance()));
	}
	public void chooseEntry() {
		
	}

} // TrainingRecordGUI
