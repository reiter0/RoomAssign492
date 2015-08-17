
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
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Vector;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;

import com.mysql.jdbc.ResultSetMetaData;
import com.sun.xml.internal.ws.api.server.Container;


public class ThisIsCombineofReadAndDrop extends JPanel
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
		System.out.println("Sorted Room List");
		//		for(int i = 0;i<combo.length;i++)
		//		{
		//			System.out.println(combo[i]);
		//		}
		reader.close();
		comboBox(combo);
		// Close the BufferedReader.


	}
	/*******************************************************************************
	 * 
	 * @param combo
	 ******************************************************************************/
	public static void comboBox(String [] combo)
	{	
		
		JFrame frame = new JFrame("Room List");
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setSize(500, 500);
		frame.setLocation(430, 100);

		JPanel panel = new JPanel();
		frame.add(panel);

		JLabel lbl = new JLabel("Select room and click OK");
		panel.add(lbl);
		lbl.setVisible(true);

		final JComboBox<String> classList = new JComboBox<String>(combo);
		frame.add(classList);
		frame.setVisible(true);
		classList.setVisible(true);
		classList.setEditable(true);
		panel.add(classList);

		JButton btn = new JButton("OK");
		panel.add(btn);
		frame.add(panel);
		
//		 JTextField field = new JTextField(20);
//	     frame.add(field);
	        
		
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
				
			}
			
		});
		
		/***************************************************************************/
		JLabel lbl2 = new JLabel("Enter Course Number");
		panel.add(lbl2);
		lbl2.setVisible(true);

		final JTextField field = new JTextField(20);
		panel.add(field);
		String input = field.getText();
		//frame.add(field);

		JButton btn2 = new JButton("OK");
		panel.add(btn2);
		frame.add(panel);

		btn2.addActionListener(new ActionListener() 
		{
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				System.out.println(field.getText());
			}
		});

		
	}
	/***********************************************************************
	 * CONNECT TO CHECK LIST OF ROOM
	 * @param A
	 * @throws SQLException
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
		      roomQue = "SELECT * FROM roomNum where location ='"+A+"';";
		      ResultSet resultRoom = stmt.executeQuery(roomQue);
		     
		      JTable table = new JTable(buildTableModel(resultRoom));
		      table.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
		      JOptionPane.showMessageDialog(null, new JScrollPane(table));
		      
		      
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
		
		
	}
	/********************************************************************************
	 * 
	 * @param rs
	 * @return
	 * @throws SQLException
	 ********************************************************************************/
	public static void connected2ToDelete(String A) throws SQLException
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
		      //ResultSet resultRoom = stmt.executeQuery(roomQue);
		     
//		      JTable table = new JTable(buildTableModel(resultRoom));
//		      table.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
//		      //JScrollPane scrollPane = new JScrollPane(table);
//		      JOptionPane.showMessageDialog(null, new JScrollPane(table));
//		      panel.add(scrollPane);
//		      panel.revalidate();
		   
			  
		      //
		     
		      
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
		

	}
	/*************************************************************************************
	 * 
	 * @param rs
	 * @return
	 * @throws SQLException
	 *************************************************************************************/
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
		}

		return new DefaultTableModel(data, columnNames);

	}
}
