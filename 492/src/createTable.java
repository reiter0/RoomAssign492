/*********************************************************************************
 * By: John Phillips, Bo Aye, Cody Reiter
 * File: createTable.java
 * Description: class to build a table from our MySQL database that includes
 * 				all classes in the database in a user-specified location
 * Date last modified: 8/18/2015
 *********************************************************************************/
import java.awt.*;
import java.awt.event.*;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.Statement;
import java.util.Vector;

import javax.swing.*;
import javax.swing.table.*;
import javax.swing.event.*;

import java.sql.ResultSet;

public class createTable extends JFrame implements ActionListener, TableModelListener {

 	private JTable table;
 	private JButton getValue;
	// JDBC driver name and database URL
	static final String JDBC_DRIVER = "com.mysql.jdbc.Driver";  
	static final String DB_URL = "jdbc:mysql://L-1D39-492/roomAssign";

	//  Database credentials
	static final String USER = "java";
	static final String PASS = "mysql";
	
	// an array containing all the titles of the columns for the table
	private String[] titles = {
			"ID",
			"CRN",
			"Subj",
			"Crse",
			"Sec",
			"Cmp",
			"Cred",
			"Part_of_Term",
			"Title",
			"DayZ",
			"TIME",
			"Start_time",
			"End_time",
			"Cap",
			"Act",
			"Rem",
			"Instructor",
			"DateZ",
			"Location",
			"Attribute"
	};
	
	/**********************************************************************
	 * createTable(Vector<String>columnNames,Vector<Vector<Object>>B
	 * Description: Method to create the table that displays all classes at
	 * 				a certain user-specified location
	 **********************************************************************/
	public createTable(Vector<String>columnNames,Vector<Vector<Object>>B) 
	{

		getContentPane().setLayout( new FlowLayout() );

		DefaultTableModel tableModel;
		// Set up table model
		tableModel = new DefaultTableModel(B, columnNames);
		// Set up table listener
		tableModel.addTableModelListener(this);
		
		// build a new table that is editable for all columns except
		// for the first, containing each row's ID within the database
		table = new JTable(tableModel){
		    @Override
		    public boolean isCellEditable(int row, int column) 
		    {
		        return column == 0? false : true ;
		    }
		};
		
	
		
		table.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
		JScrollPane scrollPane = new JScrollPane(table);
		table.setPreferredScrollableViewportSize(new Dimension(1200, 500));
		table.revalidate();
		getContentPane().add(scrollPane);
		setVisible( true );
		setSize( 1300, 550 );

	}// END createTable(Vector<String>columnNames,Vector<Vector><Object>>B)
	/**********************************************************************
	 * tableChanged(TableModelEvent e)
	 * Description: method to handle changes in the table
	 *********************************************************************/
	@Override
	public void tableChanged( TableModelEvent e ) 
	{
		//DefaultTableModel model = (DefaultTableModel)e.getSource();
		int row = e.getFirstRow();
		int column = e.getColumn();
		System.out.println("ROW=" + row + " COLUMN=" + column);

		String cellValue = String.valueOf( table.getValueAt(row, column) );


		update(row,column,cellValue);
		
	}

	public void actionPerformed( ActionEvent evt ) 
	{

		
	}
	/*************************************************************************************
	 * update(int row, int column,String change)
	 * Description: method to handle updates to the MySQL database
	 *************************************************************************************/
	public Boolean update(int row,int column,String change)
	{	
		System.out.println("Row index "+row);
		System.out.println("Column index "+ column);
		String temp = titles[column];
		System.out.println("Title column "+ temp);
		System.out.println(table.getValueAt(row, column));
		System.out.println(table.getValueAt(row, 0));

		if(table.getValueAt(row,1) != "")
		{
			String sql = "INSERT INTO roomNum (ID,"+temp+") VALUES ('"+table.getValueAt(row, 0)+"','"+change+"') ON DUPLICATE KEY UPDATE "+temp+"='"+change+"';";
			//		String sql = "Update roomNum SET "+temp+"='"+change+"' where ID ='"+table.getValueAt(row, 0)+"';";
			try
			{
				Connection con = DriverManager.getConnection(DB_URL, USER, PASS);
				Statement s = con.prepareStatement(sql);
				s.execute(sql);
				return true;
			}
			catch(Exception ex)
			{
				
				JOptionPane.showMessageDialog(null,"Times overlap");
//				ex.printStackTrace(); 
				return false;
			}
		}
		else
		{
			return false;
		}
	}
	/**************************************************************************************
	 * Main Method
	 ******************************************************************************/
  	public static void main ( String[] args ) 
  	{
    		Table frm = new Table();
    		frm.setVisible(true);
    		frm.setSize( 320, 200 );
	}

}