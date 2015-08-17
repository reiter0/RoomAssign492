import java.awt.*;
import java.awt.event.*;
import java.util.*;
import javax.swing.*;



public class everything extends JFrame implements ActionListener {

	// instance variables
	//private JLabel header1, header2, room, day, time, instructor, course;
	private JButton roomButton, dayButton, timeButton, instructButton, courseButton;
	
	GridLayout layout = new GridLayout(0, 1);
	final static int maxGap = 20;
	
	public everything(String title) {
		super(title);
		
		
	} // everything(String title)
	
	public void addComponents(final Container panel) {
		panel.setLayout(layout);
		JButton b = new JButton("");
		Dimension buttonSize = b.getPreferredSize();
		panel.setPreferredSize(new Dimension((int)(buttonSize.getWidth() * 2.5) +maxGap, (int)(buttonSize.getHeight() * 3.5) + maxGap * 2));

		// initialize the things
		JLabel header1 = new JLabel("Everything");
		JLabel header2 = new JLabel("Sorted by");
		
		JButton roomButton = new JButton("Location");
		JButton dayButton = new JButton("Days");
		JButton timeButton = new JButton("Time");
		JButton instructButton = new JButton("Instructor");
		JButton courseButton = new JButton("Course");
		
		// set up the buttons
		roomButton.addActionListener(this);
		roomButton.setActionCommand("room");
		dayButton.addActionListener(this);
		dayButton.setActionCommand("day");
		timeButton.addActionListener(this);
		timeButton.setActionCommand("time");
		instructButton.addActionListener(this);
		instructButton.setActionCommand("instructor");
		courseButton.addActionListener(this);
		courseButton.setActionCommand("course");
		
		// actually add the things
		panel.add(header1);
		panel.add(header2);
		panel.add(courseButton);
		panel.add(instructButton);
		panel.add(dayButton);
		panel.add(timeButton);
		panel.add(roomButton);
		
	} // addComponents(final Container panel)
	
	private static void createAndShow() {
		// Create and set up the window
				everything frame = new everything("View Everything");
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

	} // main(String[] args)

	public void actionPerformed(ActionEvent e) {
		if(e.getActionCommand().equals("day")) {
			// do whatever
		}
		else if(e.getActionCommand().equals("time")) {
			// whatever
		}
		else if(e.getActionCommand().equals("course")) {
			// you know
		}
		else if(e.getActionCommand().equals("room")) {

		}
		else if(e.getActionCommand().equals("instructor")) {

		}
		
	}

	
	
	
	
	
	
	
	
} // class everything