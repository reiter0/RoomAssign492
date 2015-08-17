/*
 *  File: addClass.java
 *  Description: class intended to provide a GUI used for adding classes to a database
 *  Author: John Phillips
 *  Date last modified: 8/13/2015
 */

import java.awt.*;
import java.awt.event.*;
import java.util.*;
import javax.swing.*;


public class addClass extends JFrame
									  implements ActionListener {
	
	
	// instance variables
	// there's a combo box, string[], JLabel, and string for each quality this is
	// handling since there will be a dropdown menu for each one
	private JComboBox dayBox, timeBox, placeBox, instructBox, courseBox; // the UI component
	private String[] daysList, timeList, placeList,  instructList, courseList; // an array containing the elements of the dropdown menu
	private JLabel days, times, places,  instructors, courses; // a text component of the interface indicating which box is which
	private String day, time, place, instructor, course; // a string containing the selection from each box
	private JButton addButton; // button to click to add the class
	GridLayout layout = new GridLayout(0,2); // grid layout for the main panel
	final static int maxGap = 20; // constant used for formatting
	
	public addClass(String title) {
		
		super(title); // call the JFrame constructor to get a title on the window
		
		// JLabels for labeling things on the interface panel

		
		// these lists will eventually be taken in from an input text file for versatility and to appease Dr. Matthews  
		String[] daysList = { // list containing all possible combinations of days a class can be on
				"",
				"MW",
				"TR",
				"MWF",
				"M",
				"T",
				"W",
				"R",
				"F",
				"S",
		};

		String[] timeList = { // list containing all possible time slots a class can occupy
				"",
				"8:30 - 9:20",
				"9:40 - 10:30",
				"10:50 - 11:40",
				"12:00 - 12:50",
				"1:10 - 2:00", 
				"2:20 - 3:10",
				"3:30 - 4:20",
				"4:40 - 5:30",
				"5:50 - 6:40",
				"8:30 - 9:45",
				"10:05 - 11:20",
				"11:40 - 12:55",
				"1:15 - 2:30",
				"2:50 - 4:05",
				"4:25 - 5:40",
				"6:00 - 7:15",
		};
		String[] placeList = { // list containing all possible locations for a class
				"",
				"SWGN 2A31",
				"SWGN 1D15",
				"SWGN 2A27",
				"300M A103",
				"300M B103",
				"SMWLT 205",
				"Mars",
				"Atlantis",
				"Funky Town",
				"Shadowmoon Valley",
				"Yo Mama's House"
		};
		String[] instructList = { // all possible instructors
				"",
				"Dr. Matthews",
				"Prof. Fenner",
				"Dr. Buell",
				"Mr. Miyagi",
				"Ms. Frizzle",
				"Pai Mei",
				"Neil DeGrasse Tyson",
				"Dr. Dre"
		};
		String[] courseList = { // all courses
				"",
				"CSCE 101",
				"CSCE 145",
				"CSCE 146",
				"CSCE 210",
				"CSCE 211",
				"CSCE 490",
				"CSCE 492"
			
		};
		
		
		// Combo boxes - one for each list of qualities
		dayBox = new JComboBox(daysList);
		dayBox.addActionListener(this); 			// this enables an action listener on this combo box associated
		dayBox.setActionCommand("day");		//  with this class so we can make action methods here
		
		
		timeBox = new JComboBox(timeList);
		timeBox.addActionListener(this);
		timeBox.setActionCommand("time");
		
		placeBox = new JComboBox(placeList);
		placeBox.addActionListener(this);
		placeBox.setActionCommand("place");
		
		instructBox = new JComboBox(instructList);
		instructBox.addActionListener(this);
		instructBox.setActionCommand("instructor");
		
		courseBox = new JComboBox(courseList);
		courseBox.addActionListener(this);
		courseBox.setActionCommand("course");
		
		
		// "prevent action events from being fired when the up/down arrow keys are used"
		dayBox.putClientProperty("JComboBox.isTableCellEditor",  Boolean.TRUE);
		timeBox.putClientProperty("JComboBox.isTableCellEditor",  Boolean.TRUE);
		placeBox.putClientProperty("JComboBox.isTableCellEditor",  Boolean.TRUE);
		instructBox.putClientProperty("JComboBox.isTableCellEditor",  Boolean.TRUE);
		courseBox.putClientProperty("JComboBox.isTableCellEditor",  Boolean.TRUE);

	} // end default constructor	
	
	
	
	/*
	 * Method for initially setting up the panel and adding components to it
	 */
	public void addComponents(final Container panel) {
		// set up main panel
		
		panel.setLayout(layout);
		JButton b = new JButton("meh");
		Dimension buttonSize = b.getPreferredSize();
		panel.setPreferredSize(new Dimension((int)(buttonSize.getWidth() * 2.5) +maxGap, (int)(buttonSize.getHeight() * 3.5) +maxGap * 2));
		
		// do these thing s
		JLabel days = new JLabel("Days: ");
		JLabel times = new JLabel("Time: ");
		JLabel places = new JLabel("Location: ");
		JLabel instructors = new JLabel("Instructor: ");
		JLabel courses = new JLabel("Courses: ");	
		JButton addButton = new JButton("Add Class");
		addButton.addActionListener(this);
		addButton.setActionCommand("add");
		JLabel l1 = new JLabel("Add a "); // these are split into two so they can occupy an entire row
		JLabel l2 = new JLabel("Class");
		
		// actually add stuff
		panel.add(l1);
		panel.add(l2);
		
		panel.add(courses);
		panel.add(courseBox);
		
		panel.add(instructors);
		panel.add(instructBox);
		
		panel.add(days);
		panel.add(dayBox);
		
		panel.add(times);
		panel.add(timeBox);
		
		panel.add(places);
		panel.add(placeBox);
		
		panel.add(addButton);
		
	} // addComponents
	
	
	/*
	 * Method for handling actions on interface components
	 * combo boxes in this case
	 */
	public void actionPerformed(ActionEvent e) {
		String dayMaybe = (String)dayBox.getSelectedItem();
		String placeMaybe = (String)placeBox.getSelectedItem();
		String timeMaybe = (String)timeBox.getSelectedItem();
		String courseMaybe = (String)courseBox.getSelectedItem();
		String instructorMaybe = (String)instructBox.getSelectedItem();
		
		
		if(e.getActionCommand().equals("add")) {
			System.out.println("Add!");
		}
		else if(e.getActionCommand().equals("day")) {
			System.out.println(dayMaybe);
		}
		else if(e.getActionCommand().equals("time")) {
			System.out.println(timeMaybe);
		}
		else if(e.getActionCommand().equals("course")) {
			System.out.println(courseMaybe);
		}
		else if(e.getActionCommand().equals("place")) {
			System.out.println(placeMaybe);
		}
		else if(e.getActionCommand().equals("instructor")) {
			System.out.println(instructorMaybe);
		}
		
	} //actionPerformed
	
	private static void createAndShow() {
		// Create and set up the window
		addClass frame = new addClass("Add A Class");
		frame.setSize(200, 400);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.addComponents(frame);
		//frame.pack();
		frame.setVisible(true);
	} //createAndShow
	
	/*
	 * Main method
	 */
	public static void main(String[] args) {
		javax.swing.SwingUtilities.invokeLater(new Runnable() {
			public void run() {
				createAndShow();
			}
		});
	} // main
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
} // end class addClass