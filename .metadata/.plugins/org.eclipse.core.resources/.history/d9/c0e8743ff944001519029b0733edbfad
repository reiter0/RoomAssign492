import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;


public class Updater extends javax.swing.JFrame 
{
	public Updater()
	{
		initComponents();
	}
	@SuppressWarnings("unchecked");
	private void retrieveBtnActionPerformed(java.awt.event.ActionEvent evt)
	{
		DefaultTableModel dm = new jTable2SQL().getData();
		jTable1.setModel(dm);
	}
	private void addBtnActionPerformed(java.awt.event.ActionEvent evt)
	{
		if(new jTable2SQL().add(nameTxt.getText(), timeTxt.getText()))
		{
			JOptionPane.showMessageDialog(null, "Successfully Inserted");
			
			//CLEAR TXT
			nameTxt.setText("");
			timeTxt.setText("");
		}
		else
		{
			JOptionPane.showMessageDialog(null, "Not Saved");
		}
	}
	private void jTable1MouseClicked(java.awt.event.ActionEvent evt)
	{	
		String name = jTable1.getValueAt(jTable1.getSelectedRow(),1).toString();
		nameTxt.setText("name");
		String time = jTable1.getValueAt(jTable1.getSelectedRow(),2).toString();
		timeTxt.setText("time");
	}
	private void updateBtnActionPerformed(java.awt.event.ActionEvent evt)
	{
		int index = jTable1.getSelectedRow().toString();
		String id=jTable1.getValueAt(index,0).toString();
		
		if(new jTable2SQL().update(id,nameTxt.getText(),timeTxt.getText()))
		{
			JOptionPane.showMessageDialog(null, "Successfully Inserted");
			//CLEAR TXT
			nameTxt.setText("");
			timeTxt.setText("");
		}
		else
		{
			JOptionPane.showMessageDialog(null, "Not Updated");
		}
		
	}
	private void DeleteActionPerformed(java.awt.event.ActionEvent evt)
	{
		
	}
	private void clearBtnActionPerformed(java.awt.event.ActionEvent evt)
	{
		jTable1.setModel(new DefaultTableModel());
	}
}
