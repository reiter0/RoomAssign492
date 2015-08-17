import java.awt.*;
import java.awt.event.*;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.Statement;
import java.util.Vector;

import javax.swing.*;
import javax.swing.table.*;
import javax.swing.event.*;

public class createTable extends JFrame implements ActionListener, TableModelListener {

 	private JTable table;
 	private JButton getValue;
	// JDBC driver name and database URL
	static final String JDBC_DRIVER = "com.mysql.jdbc.Driver";  
	static final String DB_URL = "jdbc:mysql://L-1D39-492/roomAssign";

	//  Database credentials
	static final String USER = "java";
	static final String PASS = "mysql";

	public createTable(Vector<String>columnNames,Vector<Vector<Object>>B) {

		getContentPane().setLayout( new FlowLayout() );

		DefaultTableModel tableModel;

//		Object[] columnNames = new Object[]{"Column 1","Column 2","Column 3"};
//		Object[][] rowData = {
//			{"1", "2", "3"},
//			{"4", "5", "6"},
//			{"7", "8", "9"},
//			{"10", "11", "12"},
//			{"13", "14", "15"},
//			{"16", "17", "18"},
//			{"19", "20", "21"},
//			{"22", "23", "24"}
//		};

		tableModel = new DefaultTableModel(B, columnNames);
		tableModel.addTableModelListener(this);

		table = new JTable(tableModel);
		table.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
		JScrollPane scrollPane = new JScrollPane(table);
		table.setPreferredScrollableViewportSize(new Dimension(600, 100));
		getContentPane().add(scrollPane);

		getValue = new JButton( "Get Value" );
		getValue.addActionListener( this);
		getContentPane().add( getValue );

		setVisible( true );
		setSize( 800, 200 );
	}

	public void tableChanged( TableModelEvent e ) 
	{
		DefaultTableModel model = (DefaultTableModel)e.getSource();
		int row = e.getFirstRow();
		int column = e.getColumn();

		String cellValue = String.valueOf( table.getValueAt(row, column) );

		JOptionPane.showMessageDialog(this,
			"Value at (" + row + "," + column + ") changed to " + "\'" + cellValue + "\'");
		update(row,column,cellValue);
	}

	public void actionPerformed( ActionEvent evt ) 
	{
		int row = table.getSelectedRow();
		int column = table.getSelectedColumn();
		String name = table.getValueAt(table.getSelectedRow(),row).toString();
		System.out.println("NAMEMMMMMMMM " + name);
		if ( evt.getSource() == getValue ) {
			String value = String.valueOf( table.getValueAt(row,column) );
			JOptionPane.showMessageDialog( this,
				"Value at (" + row + "," + column + ") is " + "\'" + value + "\'");
		}
		
	}
	/*************************************************************************************
	 * Update
	 * @param id
	 * @param name
	 * @param time
	 * @return
	 *************************************************************************************/
	public Boolean update(int row,int column,String time)
	{
		String sql = "Update roomNum SET NAME='"+name+"',TIME='"+time+"' WHERE ID ='"+id+"'";
		try
		{
			Connection con = DriverManager.getConnection(DB_URL, USER, PASS);
			Statement s = con.prepareStatement(sql);
			s.execute(sql);
			return true;
		}
		catch(Exception ex)
		{
			ex.printStackTrace();
			return false;
		}
		
	}

  	public static void main ( String[] args ) 
  	{
    		Table frm = new Table();
    		frm.setVisible(true);
    		frm.setSize( 320, 200 );
	}
}