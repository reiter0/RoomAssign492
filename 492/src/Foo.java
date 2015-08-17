import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;

public class Foo extends JFrame 
{
	static JPanel panel = new JPanel();
	public Foo() 
	{

	}

	public static void main(String[] args) 
	{

		new Foo();
		String[] combo = {"1","2","3"};
		comboBox(combo);

	}	
	public static void comboBox(String [] combo)
	{	

		JFrame frame = new JFrame("Room List");
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setSize(500, 500);
		frame.setLocation(430, 100);

		
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
			public void actionPerformed(ActionEvent e) 
			{
				// TODO Auto-generated method stub
				System.out.println(field.getText());
			}
		});
		
		String[] columnNames = {"First Name",
                "Last Name",
                "Sport",
                "# of Years",
                "Vegetarian"};
		Object[][] data = {
			    {"Kathy", "Smith",
			     "Snowboarding", new Integer(5), new Boolean(false)},
			    {"John", "Doe",
			     "Rowing", new Integer(3), new Boolean(true)},
			    {"Sue", "Black",
			     "Knitting", new Integer(2), new Boolean(false)},
			    {"Jane", "White",
			     "Speed reading", new Integer(20), new Boolean(true)},
			    {"Joe", "Brown",
			     "Pool", new Integer(10), new Boolean(false)}
			};
		Object[][] newData = {{"Cody", "Reiter","Video Games",new Integer(17), new Boolean(false)}};
		
		JTable table = new JTable(data, columnNames);
		JScrollPane scrollPane = new JScrollPane(table);
		panel.add(scrollPane);
		JTable newTable = new JTable(newData,columnNames);
		
		btn.addActionListener(new java.awt.event.ActionListener() 
		{
			@Override
			public void actionPerformed(ActionEvent arg0) 
			{
				//scrollPane = new JScrollPane(newTable);
			}
		});
		//table.setFillsViewportHeight(true);
	}
}