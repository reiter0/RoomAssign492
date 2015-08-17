import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import javax.swing.table.*;
import javax.swing.event.*;

public class Table extends JFrame implements ActionListener, TableModelListener {

 	private JTable table;
 	private JButton getValue;

	public Table() {

		getContentPane().setLayout( new FlowLayout() );

		DefaultTableModel tableModel;

		Object[] columnNames = new Object[]{"Column 1","Column 2","Column 3"};
		Object[][] rowData = {
			{"1", "2", "3"},
			{"4", "5", "6"},
			{"7", "8", "9"},
			{"10", "11", "12"},
			{"13", "14", "15"},
			{"16", "17", "18"},
			{"19", "20", "21"},
			{"22", "23", "24"}
		};

		tableModel = new DefaultTableModel(rowData, columnNames);
		tableModel.addTableModelListener(this);

		table = new JTable(tableModel);

		JScrollPane scrollPane = new JScrollPane(table);
		table.setPreferredScrollableViewportSize(new Dimension(280, 100));
		getContentPane().add(scrollPane);

		getValue = new JButton( "Get Value" );
		getValue.addActionListener( this);
		getContentPane().add( getValue );

		setVisible( true );
		setSize( 320, 200 );
	}

	public void tableChanged( TableModelEvent e ) {
		DefaultTableModel model = (DefaultTableModel)e.getSource();
		int row = e.getFirstRow();
		int column = e.getColumn();

		String cellValue = String.valueOf( table.getValueAt(row, column) );

		JOptionPane.showMessageDialog(this,
			"Value at (" + row + "," + column + ") changed to " + "\'" + cellValue + "\'");
	}

	public void actionPerformed( ActionEvent evt ) {
		int row = table.getSelectedRow();
		int column = table.getSelectedColumn();

		if ( evt.getSource() == getValue ) {
			String value = String.valueOf( table.getValueAt(row,column) );
			JOptionPane.showMessageDialog( this,
				"Value at (" + row + "," + column + ") is " + "\'" + value + "\'");
		}		
	}

  	public static void main ( String[] args ) {
    		Table frm = new Table();
    		frm.setVisible(true);
    		frm.setSize( 320, 200 );
	}
}