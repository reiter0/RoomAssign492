import java.awt.event.ActionEvent;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Vector;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.event.TableModelEvent;
import javax.swing.table.DefaultTableModel;

import com.mysql.jdbc.ResultSetMetaData;


public class displayJtable 
{
	// JDBC driver name and database URL
	static final String JDBC_DRIVER = "com.mysql.jdbc.Driver";  
	static final String DB_URL = "jdbc:mysql://L-1D39-492/roomAssign";

	//  Database credentials
	static final String USER = "java";
	static final String PASS = "mysql";
	private static JTable table;
 	private JButton getValue;
 	private static JFrame frame;
	private String[] titles = {
			"instructor",
			"courseName",
			"question",
			"nRed",
			"avRed",
			"mdRed",
			"devRed",
			"nBlue",
			"avBlue",
			"mdBlue",
			"devBlue",
			"nGreen",
			"avGreen",
			"mdGreen",
			"devGreen"
			
	};

	/**************************************************************************************
	 * Main Method
	 ******************************************************************************/
  	public static void main ( String[] args ) 
  	{
  		frame = new JFrame("StudentEval");
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setSize(500, 200);
		frame.setLocation(430, 100);
		JPanel panel = new JPanel();
		frame.add(panel);
		JLabel lbl = new JLabel("Press OK to View Student Evaluation Database");
		panel.add(lbl);
		lbl.setVisible(true);
		JButton btn = new JButton("OK"); // an OK button
		panel.add(btn);
		
		// add the panel to the frame
		frame.add(panel);
		panel.setVisible(true);
		frame.setVisible(true);
		
		// this actionListener controls what happens upon pressing the OK button
		btn.addActionListener(new java.awt.event.ActionListener() 
		{
			public void actionPerformed(ActionEvent arg0) 
			{
				
				try {
					connectedToEval();
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				
			} // actionPerformed(ActionEvent arg0)

			private void connectedToEval() throws SQLException 
			{
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
				      roomQue = "SELECT * FROM profile;";
				      			      
				      
				      ResultSet resultRoom = stmt.executeQuery(roomQue);
				     
				      DefaultTableModel tableModel = new DefaultTableModel();
				      tableModel= buildTableModel2(resultRoom);
				      tableModel.addTableModelListener(null);
				      
				      
				       table = new JTable(tableModel);
				       table.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
				       JScrollPane scrollPane = new JScrollPane(table);
				       frame.add(table);
				       table.setEnabled(false);
				      
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
				
				
			}
			
		}); // btn.addActionListener(new java.awt.event.ActionListener())

  	}
  	/*******************************************************************************
	 * buildTableModel(ResultSet rs)
	 * Description: Method to get the data and metadata together and build the table
	 *******************************************************************************/
	public static DefaultTableModel buildTableModel2(ResultSet rs)throws SQLException 
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
		DefaultTableModel tableModel = new DefaultTableModel(data,columnNames);
//		createTable tb1 = new createTable(columnNames,data);
		//displayJtable tb1 = new displayJtable(columnNames,data);
		return tableModel;
		
	}	
    	
}
