/* 
 * FIle: interface.java
 * Date last modified: 8/11/2015
 * 
 */

import java.awt.event.*;
import javax.swing.*;

class gui extends JFrame {
	
	
	// Constructor
	public gui() {
		setTitle("Closable Frame");
		setSize(300, 200);
		setLocation(10, 200);
		
		// Window Listeners
		addWindowListener(new WindowAdapter() {
			public void windowClosing(WindowEvent e) {
				System.exit(0);
			} // windowClosing
		} ); //addWindowListener
	} // end default constructor
	
	public static void showInterrface() {
		JFrame frame = new JFrame("HelloWorldSwing");
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		JLabel label = new JLabel("Hello World");
		frame.getContentPane().add(label);
		
		frame.pack();
		frame.setVisible(true);
	}
	
	public static void main(String[] args) {
		javax.swing.SwingUtilities.invokeLater(new Runnable() {
			public void run() {
				showInterrface();
			}
		});
		
	} // main
} // class interrface