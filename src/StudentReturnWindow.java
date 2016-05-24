import java.awt.Color;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.ListSelectionModel;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.table.DefaultTableModel;


public class StudentReturnWindow extends JPanel
{
	private DefaultTableModel defaultTable;
	private JScrollPane jScrollPane;
	private JTable resultTable;
	private int rowIndex;
	public static Library itemList;
	public static Student currentStudent;
	private TesterClass tester;
	private JButton returnBookButton, goBackButton;
	private JPanel buttonPanel;
	private JOptionPane message;
	
	public StudentReturnWindow(Student importedStudent)
	{
		setLayout(new GridBagLayout());
		//set colors to layout
		setBorder(BorderFactory.createLineBorder(Color.YELLOW));
		GridBagConstraints c = new GridBagConstraints();
		setBackground(Color.white);
		defaultTable = new DefaultTableModel();
		resultTable = new JTable(defaultTable);
		ListSelectionModel selectionModel = resultTable.getSelectionModel();
		resultTable.setSelectionMode(selectionModel.SINGLE_SELECTION);
		
		//create list handler and tags for columns
		ListHandler handler = new ListHandler();
		selectionModel.addListSelectionListener(handler);
		defaultTable.addColumn("Title");
		defaultTable.addColumn("Subject");
		defaultTable.addColumn("Author");
		defaultTable.addColumn("Publisher");
		
		jScrollPane = new JScrollPane(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS, JScrollPane.HORIZONTAL_SCROLLBAR_ALWAYS);
		jScrollPane.setViewportView(resultTable);
		
		c.fill = GridBagConstraints.BOTH;
		c.weightx = .75;
		c.gridx = 0;
		c.gridy = 0;
		add(jScrollPane, c);
		
		//create and color book buttons
		returnBookButton = new JButton("Return Book");
		returnBookButton.setBackground(Color.decode("#7ec0ee"));
		goBackButton = new JButton("Back");
		goBackButton.setBackground(Color.decode("#ff6666"));
		
		buttonPanel = new JPanel(new GridLayout(1, 2));
		buttonPanel.add(returnBookButton);
		buttonPanel.add(goBackButton);
		c.gridx = 0;
		c.gridy = 2;
		add(buttonPanel, c);
		
		refreshTable(importedStudent);
		
		
		//go back to the previous 
		goBackButton.addActionListener(new ActionListener() 
		{
				
			@Override
			public void actionPerformed(ActionEvent e) 
			{
				//swap the view
				if(tester != null)
				{
					tester.swapView("studentOption");
					tester.setSize(400, 400);
				}
			}
		});
		
		//return a book that you have checked out
		returnBookButton.addActionListener(new ActionListener() 
		{
				
			@Override
			public void actionPerformed(ActionEvent e) 
			{
				if(currentStudent.getCheckout().size() > 0)
				{
					message = new JOptionPane();
					message.showMessageDialog(null, "Thank you for returning: " + currentStudent.getCheckout().get(rowIndex).getTitle());
					itemList.add(currentStudent.getCheckout().get(rowIndex));
					currentStudent.getCheckout().remove(rowIndex);
					defaultTable.setRowCount(0);
					refreshTable(currentStudent);
				}
			}
		});
	}
	
	//listens for the list selection
	private class ListHandler implements ListSelectionListener
	{
	   public void valueChanged(ListSelectionEvent ise)
		{
		   rowIndex = resultTable.getSelectedRow();
		}
	}
	
	//refreshes table to show current item in list
	private void refreshTable(Student studentDummy)
	{
		defaultTable.setRowCount(0);
		for(int i = 0; i < studentDummy.getCheckout().size(); i++)
		{
			Item result = studentDummy.getCheckout().get(i);
			defaultTable.addRow(new Object[]{result.getTitle(), result.getSubject(), result.getAuthor(), result.getPublisher()});
		}
	}
	
	//sets tester which is meant for switching panels
	public void setTester(TesterClass tester)
	{
		this.tester = tester;
	}
}
