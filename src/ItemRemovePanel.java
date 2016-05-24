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


public class ItemRemovePanel extends JPanel
{
	private DefaultTableModel defaultTable;
	private JScrollPane jScrollPane;
	private JTable resultTable;
	private int rowIndex, searchIndex;
	private ArrayList<Item> itemArrayResult;
	//array list
	public static Library itemList;
	public static Students studentList;
	
	private TesterClass tester;
	//j components
	private JButton searchButton, removeItemButton, goBackButton;
	private JTextField searchField;
	private JPanel buttonPanel, searchPanel;
	private JOptionPane message;
	
	public ItemRemovePanel (Library itemListDummy)
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
		searchButton = new JButton("Search by Title");
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
		defaultTable.addColumn("Title");
		defaultTable.addColumn("Subject");
		defaultTable.addColumn("Author");
		defaultTable.addColumn("Publisher");
		
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
		
		
		removeItemButton = new JButton("Remove Item");
		removeItemButton.setBackground(Color.decode("#7ec0ee"));
		
		buttonPanel = new JPanel(new GridLayout(1, 2));
		buttonPanel.add(goBackButton);
		buttonPanel.add(removeItemButton);
		c.gridx = 0;
		c.gridy = 2;
		add(buttonPanel, c);
		
		refreshTable(itemListDummy);
		itemArrayResult = itemListDummy.getItemArray();
		
		
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
		
		
		
		//remove the user REMEMBER TO REMOVE THEIR ITEMS AND PLACE THEM BACK IN QUEUE
		removeItemButton.addActionListener(new ActionListener() 
		{
				
			@Override
			public void actionPerformed(ActionEvent e) 
			{
				message = new JOptionPane();
				int i = 0;
				int reply = message.showConfirmDialog(null, "Are you sure you want to remove this item?","", JOptionPane.YES_NO_OPTION);
		        if (reply == JOptionPane.YES_OPTION) 
		        {
//		        	if(itemArrayResult.equals(itemList.getItemArray()))
//		        		itemList.getItemArray().remove(rowIndex);
//		        	else
//		        	{
//		        		itemList.getItemArray().remove(searchIndex);
		        		while(i < itemList.getItemArray().size())
						{
							if(itemList.getItemArray().get(i).compareTo(itemArrayResult.get(rowIndex)))
							{
								System.out.println(itemList.getItemArray().get(i));
								itemList.getItemArray().remove(i);
							}
							i++;
						}
//		        	}
	        		try {
		                BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(new File("items.txt")));
		                for(int j = 0; j < itemList.getItemArray().size(); j++)
		                {
		                	if(j == itemList.getItemArray().size()-1) bufferedWriter.write(itemList.getItemArray().get(j).toString());
		                	else
		                		bufferedWriter.write(itemList.getItemArray().get(j).toString() + "\n");
		                }
		                bufferedWriter.close();
		            	} catch (IOException o) {
		            	}
		        	refreshTable(itemList);
		        }
		        
			}
		});
		
		//search for a user by id
		searchButton.addActionListener(new ActionListener() 
		{
				
			@Override
			public void actionPerformed(ActionEvent e) 
			{
				defaultTable.setRowCount(0);
				
				String title = searchField.getText();
				itemArrayResult = itemList.searchTitle(title);
				if(itemArrayResult != null)
				{
					for(int i = 0; i < itemArrayResult.size(); i++)
					{
						if(itemArrayResult.get(i).getStatus() == true)
						defaultTable.addRow(new Object[]{itemArrayResult.get(i).getTitle(), itemArrayResult.get(i).getSubject(), itemArrayResult.get(i).getAuthor(), itemArrayResult.get(i).getPublisher()});
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
	private void refreshTable(Library itemDummy)
	{
		defaultTable.setRowCount(0);
		for(int i = 0; i < itemDummy.getItemArray().size(); i++)
		{
			Item result = itemDummy.getItemArray().get(i);
			defaultTable.addRow(new Object[]{result.getTitle(), result.getSubject(), result.getAuthor(), result.getPublisher()});
		}
	}
		
	//sets tester which is meant for switching panels
	public void setTester(TesterClass tester)
	{
		this.tester = tester;
	}
}
