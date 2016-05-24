import java.awt.Color;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.ListSelectionModel;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.table.DefaultTableModel;


public class StudentPenalizeRemovePanel extends JPanel
{
	private DefaultTableModel defaultTable;
	private JScrollPane jScrollPane;
	private JTable resultTable;
	private int rowIndex;
	private String idReturn;
	private boolean searchProcessed;
	private ArrayList<Student> studentArrayResult;
	//array list
	public static Library itemList;
	public static Students studentList;
	
	private TesterClass tester;
	//j components
	private JButton penalizeButton, searchButton, removeAccountButton, goBackButton;
	private JLabel searchLabel;
	private JTextField searchField;
	private JPanel buttonPanel, searchPanel;
	private JOptionPane message;
	
	public StudentPenalizeRemovePanel(Students studentListDummy)
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
		
		
		//create search tag and -------------------
		searchPanel = new JPanel(new GridLayout(1, 2));
		searchField = new JTextField(15);
		searchPanel.add(searchField);
		searchButton = new JButton("Search by ID");
		searchButton.setBackground(Color.decode("#98ff98"));
		searchPanel.add(searchButton);
		
		c.fill = GridBagConstraints.BOTH;
		c.weightx = .75;
		c.gridx = 0;
		c.gridy = 0;
		add(searchPanel, c);
		
		//--------------
		
		//create list handler and tags for columns
		ListHandler handler = new ListHandler();
		selectionModel.addListSelectionListener(handler);
		defaultTable.addColumn("Name");
		defaultTable.addColumn("Last Name");
		defaultTable.addColumn("ID");
		defaultTable.addColumn("Email");
		
		jScrollPane = new JScrollPane(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS, JScrollPane.HORIZONTAL_SCROLLBAR_ALWAYS);
		jScrollPane.setViewportView(resultTable);
		
		c.fill = GridBagConstraints.BOTH;
		c.weightx = .75;
		c.gridx = 0;
		c.gridy = 1;
		add(jScrollPane, c);
		
		//create and color book buttons
		goBackButton = new JButton("Back");
		goBackButton.setBackground(Color.decode("#ff6666"));
		
		penalizeButton = new JButton("Lock/Unlock Account");
		penalizeButton.setBackground(Color.decode("#7ec0ee"));
		
		removeAccountButton = new JButton("Remove Account");
		removeAccountButton.setBackground(Color.decode("#7ec0ee"));
		
		buttonPanel = new JPanel(new GridLayout(1, 3));
		buttonPanel.add(goBackButton);
		buttonPanel.add(penalizeButton);
		buttonPanel.add(removeAccountButton);
		c.gridx = 0;
		c.gridy = 2;
		add(buttonPanel, c);
		
		refreshTable(studentListDummy);
		studentArrayResult = studentListDummy.getStudentArray();
		
		
		//go back to the previous 
		goBackButton.addActionListener(new ActionListener() 
		{
				
			@Override
			public void actionPerformed(ActionEvent e) 
			{
				//swap the view
				if(tester != null)
				{
					tester.swapView("adminOption");
					tester.setSize(400, 400);
				}
			}
		});
		
		//penalize the user
		penalizeButton.addActionListener(new ActionListener() 
		{
				
			@Override
			public void actionPerformed(ActionEvent e) 
			{
				if(searchProcessed == false)
				{
					if(studentList.getStudentArray().get(rowIndex).getPerson().isGoodStanding() == true)
					{
						studentList.getStudentArray().get(rowIndex).getPerson().setGoodStanding(false);
						message = new JOptionPane();
						message.showMessageDialog(null, "Student has been penalized."); 
					}
					else
					{
						studentList.getStudentArray().get(rowIndex).getPerson().setGoodStanding(true);
						message = new JOptionPane();
						message.showMessageDialog(null, "Student is no longer penalized."); 
					}
				}
	        	else
	        	{
	        		for(int i = 0; i < studentList.getStudentArray().size(); i++)
	        		{
	        			Person result = studentList.getStudentArray().get(i).getPerson();
	        			if(result.getId().equals(idReturn))
	        			{
	        				Student currentUser = studentList.getStudentArray().get(i);
			        		if(currentUser.getPerson().isGoodStanding() == true)
							{
			        			currentUser.getPerson().setGoodStanding(false);
								message = new JOptionPane();
								message.showMessageDialog(null, "Student has been penalized."); 
							}
			        		else
							{
			        			currentUser.getPerson().setGoodStanding(true);
								message = new JOptionPane();
								message.showMessageDialog(null, "Student is no longer penalized."); 
							}
	        			}
        			}
	        	}
				
				
			}
		});
		
		//remove the user REMEMBER TO REMOVE THEIR ITEMS AND PLACE THEM BACK IN QUEUE
		removeAccountButton.addActionListener(new ActionListener() 
		{
				
			@Override
			public void actionPerformed(ActionEvent e) 
			{
				message = new JOptionPane();
				int reply = message.showConfirmDialog(null, "Are you sure you want to remove this user?","", JOptionPane.YES_NO_OPTION);
		        if (reply == JOptionPane.YES_OPTION) 
		        {
		        	if(searchProcessed == false)
		        	{
		        		Student currentUser = studentList.getStudentArray().get(rowIndex);
		        		currentUser.getCheckout().size();
		        		for(int i = 0; i < currentUser.getCheckout().size(); i++)
		        		{
		        			itemList.add(currentUser.getCheckout().get(i));
		        			currentUser.getCheckout().remove(i);
		        		}
		        		studentList.remove(currentUser);
		        	}
		        	else
		        	{
		        		for(int i = 0; i < studentList.getStudentArray().size(); i++)
		        		{
		        			Person result = studentList.getStudentArray().get(i).getPerson();
		        			if(result.getId().equals(idReturn))
		        			{
		        				Student currentUser = studentList.getStudentArray().get(i);
		        				for(int j = 0; j < currentUser.getCheckout().size(); j++)
				        		{
				        			itemList.add(currentUser.getCheckout().get(i));
				        			currentUser.getCheckout().remove(i);
				        		}
				        		studentList.remove(currentUser);
		        			}
		        		}
		        		searchProcessed = false;
		        		
		        	}
		        	try {
		                BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(new File("students.txt")));
		                for(int i = 0; i < studentList.getStudentArray().size(); i++)
		                {
		                	bufferedWriter.write(studentList.getStudentArray().get(i).toString() + "\n");
		                }
		                bufferedWriter.close();
		            	} catch (IOException o) {
		            	}
		        	refreshTable(studentList);
		        }
		        
			}
		});
		
		//search for a user by id
		searchButton.addActionListener(new ActionListener() 
		{
				
			@Override
			public void actionPerformed(ActionEvent e) 
			{
				searchProcessed = true;
				String id = searchField.getText();
				if(studentList.searchStudent(id))
				{
					defaultTable.setRowCount(0);
					for(int i = 0; i < studentList.getStudentArray().size(); i++)
					{
						Person result = studentList.getStudentArray().get(i).getPerson();
						if(result.getId().equals(id))
						{
							idReturn = result.getId();
							defaultTable.addRow(new Object[]{result.getName(), result.getLastName(), result.getId(), result.getEmail()});
						}
					}
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
	private void refreshTable(Students studentList)
	{
		defaultTable.setRowCount(0);
		for(int i = 0; i < studentList.getStudentArray().size(); i++)
		{
			Person result = studentList.getStudentArray().get(i).getPerson();
			defaultTable.addRow(new Object[]{result.getName(), result.getLastName(), result.getId(), result.getEmail()});
		}
	}
	//sets tester which is meant for switching panels
	public void setTester(TesterClass tester)
	{
		this.tester = tester;
	}
}
