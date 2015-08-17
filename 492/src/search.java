/*
 * File: search.java
 * Description: GUI intended to manage searching the database of rooms and classes for scheduling
 * Author: John Phillips
 * Date last modified: 8/13/15
 */

import java.awt.*;
import java.awt.event.*;
import java.util.*;
import javax.swing.*;

public class search extends JFrame
								  implements ActionListener {
				  
	// instance variables
	private JComboBox dayBox, timeBox, placeBox, instructBox, courseBox; // the UI component
	private String[] daysList, timeList, placeList,  instructList, courseList; // an array containing the elements of the dropdown menu
	private JLabel header, days, times, places,  instructors, courses, blank; // a text component of the interface indicating which box is which
	private String day, time, place, instructor, course; // a string containing the selection from each box
	private JButton searchButton; // button to click to add the class
	GridLayout layout = new GridLayout(0,2); // grid layout for the main panel
	final static int maxGap = 20; // constant used for formatting
	
	
	public search(String title) {
		
		super(title);
		JLabel header = new JLabel ("Search: ");
		JLabel blank = new JLabel ("");
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
	
	public void addComponents(final Container panel) {
		// set up main panel
		
		panel.setLayout(layout);
		JButton b = new JButton("meh");
		Dimension buttonSize = b.getPreferredSize();
		panel.setPreferredSize(new Dimension((int)(buttonSize.getWidth() * 2.5) +maxGap, (int)(buttonSize.getHeight() * 3.5) + maxGap * 2));

		// do these things
		JLabel days = new JLabel("Days: ");
		JLabel times = new JLabel("Time: ");
		JLabel places = new JLabel("Location: ");
		JLabel instructors = new JLabel("Instructor: ");
		JLabel courses = new JLabel("Courses: ");	
		JButton searchButton = new JButton("Search   ->");
		searchButton.addActionListener(this);
		searchButton.setActionCommand("search");
		JLabel header = new JLabel("Search for");
		JLabel blank = new JLabel(" a class");

		panel.add(header);
		panel.add(blank);

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

		
		panel.add(searchButton);
	} // addComponents(final Container panel)
	
	
	public void actionPerformed(ActionEvent e) {
		
		if(e.getActionCommand().equals("search")) {
			System.out.println("Search Go!");
		}
		else if(e.getActionCommand().equals("day")) {
			// do whatever
		}
		else if(e.getActionCommand().equals("time")) {
			// whatever
		}
		else if(e.getActionCommand().equals("course")) {
			// you know
		}
		else if(e.getActionCommand().equals("place")) {

		}
		else if(e.getActionCommand().equals("instructor")) {

		}
	}// actionPerformed(ActionEvent e)
	
	
	private static void createAndShow() {
		// Create and set up the window
		search frame = new search("Search For a Class");
		frame.setSize(200, 400);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.addComponents(frame);
		//frame.pack();
		frame.setVisible(true);
	} //createAndShow
	
	public static void main(String[] args) {
		javax.swing.SwingUtilities.invokeLater(new Runnable() {
			public void run() {
				createAndShow();
			}
		});
	} // main
	
	
	
	
	
	
	
	
	
	
} // end class search