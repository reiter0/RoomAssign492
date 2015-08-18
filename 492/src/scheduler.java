/*************************************************************************************
 * By: Bo Aye, Cody Reiter, John Phillips
 * File: scheduler.java
 * Description: Class that reads in text files to use for building a JTable
 * 				and also handles the connection to and updates to the MYSql 
 * 				database containing the data. 
 * Date last modified: 8/18/2015
 ***********************************************************************************/
import java.awt.BorderLayout;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.sql.Connection;
import java.sql.Date;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Time;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Vector;

import javax.swing.AbstractAction;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.event.CellEditorListener;
import javax.swing.event.TableModelEvent;
import javax.swing.event.TableModelListener;
import javax.swing.table.DefaultTableModel;

import com.mysql.jdbc.ResultSetMetaData;
import com.sun.corba.se.spi.orbutil.fsm.Action;
import com.sun.xml.internal.ws.api.server.Container;

public class scheduler extends JPanel implements ActionListener,TableModelListener
{ 	
	// JDBC driver name and database URL
	static final String JDBC_DRIVER = "com.mysql.jdbc.Driver";  
	static final String DB_URL = "jdbc:mysql://L-1D39-492/roomAssign";

	//  Database credentials
	static final String USER = "java";
	static final String PASS = "mysql";

	public static void main(String[]args) throws IOException 
	{	
		//Load class list into an ArrayList
		ArrayList<String> roomS = new ArrayList<String>();

		// Create a BufferedReader from a FileReader.
		BufferedReader reader = new BufferedReader(new FileReader("roomList.txt"));

		// Loop over lines in the file and print them.
		while (true) 
		{
			String line = reader.readLine();
			if(line != null)
			{	
				roomS.add(line);
			}	
			else 
			{
				break;
			}
		}
		Collections.sort(roomS);
		
		String [] combo = roomS.toArray(new String[roomS.size()]);
//		System.out.println("Sorted Room List");
//				for(int i = 0;i<combo.length;i++)
//				{
//					System.out.println(combo[i]);
//				}
		reader.close();
		comboBox(combo);
		// Close the BufferedReader.

	} // main(String[] args)
	
	/*******************************************************************************
	 * comboxBox(String [] combo)
	 * Description: method to handle the setting up and functions of the
	 * 				JFrame containing the initial menu
	 ******************************************************************************/
	public static void comboBox(String [] combo)
	{	
		// Set up a new JFrame with title, Room List, that closes when you hit the 'X' button in its corner
		JFrame frame = new JFrame("Room List");
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setSize(500, 200);
		frame.setLocation(430, 100);

		// Add a new JPanel where things are going to go, then add it to the frame
		JPanel panel = new JPanel();
		frame.add(panel);

		// Add this label that tells the user what to do to the frame
		JLabel lbl = new JLabel("Select room and click OK");
		panel.add(lbl);
		lbl.setVisible(true);

		// The combo box containing the list of rooms for the user to select from
		final JComboBox<String> classList = new JComboBox<String>(combo);
		frame.add(classList);
		frame.setVisible(true);
		classList.setVisible(true);
		classList.setEditable(true);
		panel.add(classList);

		JButton btn = new JButton("OK"); // an OK button
		panel.add(btn);
		
		// add the panel to the frame
		frame.add(panel);
		
		// this actionListener controls what happens upon pressing the OK button
		btn.addActionListener(new java.awt.event.ActionListener() 
		{
			public void actionPerformed(ActionEvent arg0) 
			{
				Object contents = classList.getSelectedItem();
				//System.out.println(contents);
				try 
				{
					connected(contents.toString());
					
				} catch (SQLException e) 
				{
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				
			} // actionPerformed(ActionEvent arg0)
			
		}); // btn.addActionListener(new java.awt.event.ActionListener())

		/***************************************************************************/
		JLabel lbl2 = new JLabel("Enter CRN Number to DELETE");
		panel.add(lbl2);

		lbl2.setVisible(true);

		final JTextField field = new JTextField(20);
		panel.add(field);
		
		//frame.add(field);

		JButton btn2 = new JButton("OK");
		panel.add(btn2);		      
		frame.add(panel);
		btn2.addActionListener(new java.awt.event.ActionListener() 
		{
			public void actionPerformed(ActionEvent arg0) 
			{	
				String input = field.getText();
				//Object contents = classList.getSelectedItem();
//				System.out.println(input);
				try 
				{
					connectedToDelete(input);
					
				} catch (SQLException e) 
				{
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				
			}
			
		});
		
		
	}
	/*************************************************************************
	 * connected(String A)
	 * Description: Connects to the MySql database and retrieves data to set
	 * 				up and then build an editable JTable
	 ************************************************************************/
	public static void connected(String A) throws SQLException
	{
		System.out.println(A);
		Connection conn = null;
		Statement stmt = null;
		String roomQue = null;
		
		try
		{
		      //STEP 2: Register JDBC driver
		      Class.forName("com.mysql.jdbc.Driver");

		      //STEP 3: Open a connection
		      System.out.println("Connecting to a selected database...");
		      conn = DriverManager.getConnection(DB_URL, USER, PASS);
		      System.out.println("Connected database successfully...");
		      
	    	  //STEP 4: Execute a query
		      System.out.println("Inserting records into the table...");
		      stmt = conn.createStatement();
		      if(A.equals("#All Rooms"))
		      {
		    	  roomQue = "SELECT * FROM roomNum;";
		      }
		      else
		      {
		    	  roomQue = "SELECT * FROM roomNum where location ='"+A+"';";
		      }
		      ResultSet resultRoom = stmt.executeQuery(roomQue);
		     
		      DefaultTableModel tableModel = new DefaultTableModel();
		      tableModel= buildTableModel(resultRoom);
		      tableModel.addTableModelListener(null);
		      
		
		       JTable table = new JTable(tableModel);
		       table.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
		       JScrollPane scrollPane = new JScrollPane(table);

		      
		} catch (ClassNotFoundException e) 
		{
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		finally
		{
		      //finally block used to close resources
		      try
		      {
		         if(stmt!=null)
		            conn.close();
		      }
	    	  catch(SQLException se)
		      {
		      }// do nothing
		      try
		      {
		         if(conn!=null)
		            conn.close();
		      }catch(SQLException se)
		      {
		         se.printStackTrace();
		      }
		      System.out.println("Goodbye!");	
		} // finally
		

	} // connected(String A)

	/********************************************************************************
	 * CONNECTED TO ADD
	 * 
	 * 
	 ********************************************************************************/
	public static void connectedToAdd(String A) throws SQLException
	{	
		//System.out.println("DO SOEMTHING");
		//System.out.println(A);
		Connection conn = null;
		Statement stmt = null;
		String roomQue = null;
		try
		{
			 //STEP 2: Register JDBC driver
		      Class.forName("com.mysql.jdbc.Driver");

		      //STEP 3: Open a connection
		      System.out.println("Connecting to a selected database...");
		      conn = DriverManager.getConnection(DB_URL, USER, PASS);
		      System.out.println("Connected database successfully...");
		      
		      //STEP 4: Execute a query
		      System.out.println("Inserting records into the table...");
		      stmt = conn.createStatement();
		      
		      roomQue = "SELECT * FROM roomNum where Location ='"+A+"';";
		      ResultSet resultRoom = stmt.executeQuery(roomQue);
		      //System.out.println(resultRoom.toString());
//		      Time startTime =null;
//		      Time endTime = null;
//		      
//		      while(resultRoom.next())
//		      {
//		    	 
//		    	  
//		    	  String start = resultRoom.getString("Start_time");
//		    	  if(start != null)
//		    	  {	  
//		    		  startTime = java.sql.Time.valueOf(start);
//		   
//		    	  }
//		    	  String end = resultRoom.getString("End_time");
//		    	  if(end != null)
//		    	  {	  
//		    		 endTime = java.sql.Time.valueOf(end);
//
//		    	  }
//
//		    	  System.out.println(startTime);
//		    	  System.out.println(endTime);
//
//		      }		
		      
		}
		catch (ClassNotFoundException e) 
		{
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		finally
		{
		      //finally block used to close resources
		      try
		      {
		         if(stmt!=null)
		            conn.close();
		      }
		      catch(SQLException se)
		      {
		      }// do nothing
		      try
		      {
		         if(conn!=null)
		            conn.close();
		      }catch(SQLException se)
		      {
		         se.printStackTrace();
		      }
		      System.out.println("Goodbye!");	
		}
	}
	/********************************************************************************
	 * connectedToDelete(String A)
	 * Description: Connects to the MySQL database and then allows for the 
	 * deletion of rows based on their unique CRN (course reference number)
	 ********************************************************************************/
	public static void connectedToDelete(String A) throws SQLException
	{
		System.out.println(A);
		Connection conn = null;
		Statement stmt = null;
		String roomQue = null;
		
		try
		{
		      //STEP 2: Register JDBC driver
		      Class.forName("com.mysql.jdbc.Driver");

		      //STEP 3: Open a connection
		      System.out.println("Connecting to a selected database...");
		      conn = DriverManager.getConnection(DB_URL, USER, PASS);
		      System.out.println("Connected database successfully...");
		      
		      //STEP 4: Execute a query
		      System.out.println("Inserting records into the table...");
		      stmt = conn.createStatement();
		      roomQue = "delete from roomNum where CRN ='"+A+"';";
		      stmt.executeUpdate(roomQue);
	
		     
		      
		} catch (ClassNotFoundException e) 
		{
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		finally
		{
		      //finally block used to close resources
		      try
		      {
		         if(stmt!=null)
		            conn.close();
		      }
		      catch(SQLException se)
		      {
		      }// do nothing
		      try
		      {
		         if(conn!=null)
		            conn.close();
		      }catch(SQLException se)
		      {
		         se.printStackTrace();
		      }
		      System.out.println("Goodbye!");	
		}
		

	} // connectedToDelete(String A)


	/*******************************************************************************
	 * buildTableModel(ResultSet rs)
	 * Description: Method to get the data and metadata together and build the table
	 *******************************************************************************/
	public static DefaultTableModel buildTableModel(ResultSet rs)throws SQLException 
	{

		ResultSetMetaData metaData = (ResultSetMetaData) rs.getMetaData();

		// names of columns
		Vector<String> columnNames = new Vector<String>();
		int columnCount = metaData.getColumnCount();
		for (int column = 1; column <= columnCount; column++) 
		{
			columnNames.add(metaData.getColumnName(column));
		}

		// data of the table
		Vector<Vector<Object>> data = new Vector<Vector<Object>>();
		while (rs.next()) 
		{
			Vector<Object> vector = new Vector<Object>();
			for (int columnIndex = 1; columnIndex <= columnCount; columnIndex++) 
			{
				vector.add(rs.getObject(columnIndex));
			}
			data.add(vector);
		} // while(rs.next())
		
		int lastID=getLastID();
		System.out.println("LAST ID IS "+lastID);
		Vector<Object> blanky = new Vector<Object>();
		blanky.add(lastID+1);
		for (int columnIndex = 1; columnIndex < columnCount; columnIndex++) 
		{
			blanky.add("");
		}
		data.add(blanky);
		DefaultTableModel tableModel = new DefaultTableModel(data,columnNames);
		createTable tb1 = new createTable(columnNames,data);
		return tableModel;
	} // buildTableModel(ResultSet rs)
	
	
	/***************************************************************************
	 * getLastID()
	 * Description: method to get the last ID value so the table can be appended
	 ***************************************************************************/
	public static int getLastID()
	{
		String sql = "SELECT MAX(ID) from roomNum;";
		ResultSet rs;
		int lastID =0;
		try
		{
			Connection con = DriverManager.getConnection(DB_URL, USER, PASS);
			Statement s = con.createStatement();
			rs = s.executeQuery(sql);
			while(rs.next())
			{
				lastID = rs.getInt(1);
			}
			return lastID;
			
		}
		catch(Exception ex)
		{
			ex.printStackTrace();
		}
		return lastID;
		
		
	} // getLastID()
	
	@Override
	public void actionPerformed(ActionEvent arg0) {
		// TODO Auto-generated method stub
		
	}
	@Override
	public void tableChanged(TableModelEvent arg0) {
		// TODO Auto-generated method stub
		
	}
} // scheduler.java