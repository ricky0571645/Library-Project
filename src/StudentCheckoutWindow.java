import java.awt.Color;
import java.awt.Container;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.ListSelectionModel;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.table.DefaultTableModel;

//YOU STILL NEED TO RETURN ARRAYLIST IN LIBRARY CLASS FOR OTHER SEARCH TYPES
//AND CHANGE THE METHODS HERE AS WELL
public class StudentCheckoutWindow extends JPanel
{
	//creating GUI interface
	private JButton authorSearch, subjectSearch, keywordSearch, titleSearch;
	private JButton addToStudentItem, returnButton;
	private JLabel options, result;
	private JTextField searchField;
	
	//create a variable itemList of type Library
	public static Library itemList;
	private DefaultTableModel defaultTable;
	private JScrollPane jScrollPane;
	private JTable resultTable;
	private ArrayList<Item> itemArrayResult;
	public static Student currentStudent;
	private int rowIndex;
	private JOptionPane message;
	private boolean authorClicked, subjectClicked, keywordClicked, titleClicked;
	private TesterClass tester;
	
	//cant create anything with vars since they are not initialized until later
	public StudentCheckoutWindow(Library itemDummy)
	{
		setLayout(new GridBagLayout());
		GridBagConstraints c = new GridBagConstraints();
		
		defaultTable = new DefaultTableModel();
		resultTable = new JTable(defaultTable);
		ListSelectionModel selectionModel = resultTable.getSelectionModel();
		resultTable.setSelectionMode(selectionModel.SINGLE_SELECTION);
		
		ListHandler handler = new ListHandler();
		selectionModel.addListSelectionListener(handler);
		
		defaultTable.addColumn("Title");
		defaultTable.addColumn("Subject");
		defaultTable.addColumn("Author");
		defaultTable.addColumn("Type");
		
		jScrollPane = new JScrollPane(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS, JScrollPane.HORIZONTAL_SCROLLBAR_ALWAYS);
		jScrollPane.setViewportView(resultTable);
		
		JPanel grid = new JPanel();
		grid.setLayout(new GridLayout(2,2));
		grid.setAlignmentX(CENTER_ALIGNMENT);
		
		
		searchField = new JTextField(15);
		
		options = new JLabel("Search");
		titleSearch = new JButton("Title");
		titleSearch.setBackground(Color.decode("#7ec0ee"));
		authorSearch = new JButton("Author");
		authorSearch.setBackground(Color.decode("#7ec0ee"));
		subjectSearch = new JButton("Subject");
		subjectSearch.setBackground(Color.decode("#7ec0ee"));
		keywordSearch = new JButton("Keyword");
		keywordSearch.setBackground(Color.decode("#7ec0ee"));
		returnButton = new JButton("Back");
		returnButton.setBackground(Color.decode("#ff6666"));
		
		c.fill = GridBagConstraints.HORIZONTAL;
	    c.ipady = 10;      //make this component tall
	    c.weightx = 1.0;
	    c.gridx = 0;
	    c.gridy = 0;
	    add(options, c);
	    
	    c.fill = GridBagConstraints.PAGE_END;
	    c.weightx = 0;
	    c.gridx = 1;
	    c.gridy = 0;
	    add(returnButton, c);
	    
	    c.fill = GridBagConstraints.BOTH;
	    c.ipady = 20;      //make this component tall
	    c.weightx = .5;
	    c.gridwidth = 3;
	    c.gridx = 0;
	    c.gridy = 1;
		add(searchField, c);
		
		grid.add(authorSearch);
		grid.add(subjectSearch);
		grid.add(keywordSearch);
		grid.add(titleSearch);
		
		c.fill = GridBagConstraints.BOTH;
	    c.ipady = 20;      //make this component tall
	    c.weightx = .5;
	    c.gridwidth = 3;
	    c.gridx = 0;
	    c.gridy = 3;
		add(grid, c);
		
		c.fill = GridBagConstraints.BOTH;
		c.gridx = 0;
	    c.gridy = 4;
		add(add(jScrollPane), c);
		
		addToStudentItem = new JButton("Add Item to Checkout");
		addToStudentItem.setBackground(Color.decode("#98ff98"));
		c.fill = GridBagConstraints.BOTH;
		c.gridx = 0;
	    c.gridy = 5;
	    add(addToStudentItem, c);
		
	    refreshTable(itemDummy);
	    itemArrayResult = itemDummy.getItemArray();
	    
	    
		titleSearch.addActionListener(new ActionListener() 
		{
			
			@Override
			public void actionPerformed(ActionEvent e) 
			{
				authorClicked = subjectClicked = keywordClicked = false;
				titleClicked = true;
				defaultTable.setRowCount(0);
				
				String input = searchField.getText();
				itemArrayResult = itemList.searchTitle(input);
				
				if(itemArrayResult != null)
				{
					for(int i = 0; i < itemArrayResult.size(); i++)
					{
						if(itemArrayResult.get(i).getStatus() == true)
						defaultTable.addRow(new Object[]{itemArrayResult.get(i).getTitle(), itemArrayResult.get(i).getSubject(), itemArrayResult.get(i).getAuthor(), itemArrayResult.get(i).getType()});
					}
				}
				
			}
		});
		
		authorSearch.addActionListener(new ActionListener() 
		{

			@Override
			public void actionPerformed(ActionEvent e) 
			{
				titleClicked = subjectClicked = keywordClicked = false;
				authorClicked = true;
				defaultTable.setRowCount(0);
				
				String input = searchField.getText();
				itemArrayResult = itemList.searchAuthor(input);
						
				if(itemArrayResult != null)
				{
					for(int i = 0; i < itemArrayResult.size(); i++)
					{
						if(itemArrayResult.get(i).getStatus() == true)
						defaultTable.addRow(new Object[]{itemArrayResult.get(i).getTitle(), itemArrayResult.get(i).getSubject(), itemArrayResult.get(i).getAuthor(), itemArrayResult.get(i).getType()});
					}
				}
				
			}
		});
		
		subjectSearch.addActionListener(new ActionListener() 
		{
				
			@Override
			public void actionPerformed(ActionEvent e) 
			{
				titleClicked = authorClicked = keywordClicked = false;
				subjectClicked = true;
				defaultTable.setRowCount(0);
				
				String input = searchField.getText();
				itemArrayResult = itemList.searchSubject(input);
				
					
				if(itemArrayResult != null)
				{
					for(int i = 0; i < itemArrayResult.size(); i++)
					{
						if(itemArrayResult.get(i).getStatus() == true)
						defaultTable.addRow(new Object[]{itemArrayResult.get(i).getTitle(), itemArrayResult.get(i).getSubject(), itemArrayResult.get(i).getAuthor(), itemArrayResult.get(i).getType()});
					}
				}
				
			}
		});
		
		keywordSearch.addActionListener(new ActionListener() 
		{
				
			@Override
			public void actionPerformed(ActionEvent e) 
			{
				titleClicked = authorClicked = keywordClicked = false;
				subjectClicked = true;
				defaultTable.setRowCount(0);
				
				String input = searchField.getText();
				itemArrayResult = itemList.searchKeyword(input);
					
				if(itemArrayResult != null)
				{
					for(int i = 0; i < itemArrayResult.size(); i++)
					{
						if(itemArrayResult.get(i).getStatus() == true)
						defaultTable.addRow(new Object[]{itemArrayResult.get(i).getTitle(), itemArrayResult.get(i).getSubject(), itemArrayResult.get(i).getAuthor(), itemArrayResult.get(i).getType()});
					}
				}
				
			}
		});
		

		
		addToStudentItem.addActionListener(new ActionListener() 
		{
			@Override
			public void actionPerformed(ActionEvent e) 
			{
				//make array list retrun
				message = new JOptionPane();
				message.showMessageDialog(null, "Item has been added to " + currentStudent.getPerson().getName() + "'s Checkout");
				int i = 0;
				currentStudent.addCheckedOut(itemArrayResult.get(rowIndex));
				while(i < itemList.getItemArray().size())
				{
					if(itemList.getItemArray().get(i).compareTo(itemArrayResult.get(rowIndex)))
					{
						itemList.getItemArray().remove(i);
					}
					i++;
				}
				i = 0;
//				if(subjectClicked) subjectSearch.doClick();
//				if(authorClicked) authorSearch.doClick();
//				if(keywordClicked) keywordSearch.doClick();
//				if(titleClicked) titleSearch.doClick();
				refreshTable(itemList);
			}
		});
		
		//go back to the previous 
		returnButton.addActionListener(new ActionListener() 
		{
				
			@Override
			public void actionPerformed(ActionEvent e) 
			{
				if(tester != null)
				{
					tester.swapView("studentOption");
					tester.setSize(400, 400);
				}
			}
		});
		
		
		
	}
	//---------------------------------------------------------------------
	//refreshes table to show current item in list
	
	
	private void refreshTable(Library itemDummy)
	{
		defaultTable.setRowCount(0);
		for(int i = 0; i < itemDummy.getItemArray().size(); i++)
		{
			Item result = itemDummy.getItemArray().get(i);
			defaultTable.addRow(new Object[]{result.getTitle(), result.getSubject(), result.getAuthor(), result.getType()});
		}
	}
		
		
	private class ListHandler implements ListSelectionListener
	{
	   public void valueChanged(ListSelectionEvent ise)
		{
		   rowIndex = resultTable.getSelectedRow();
		}
	}
	
	public void setTester(TesterClass tester)
	{
		this.tester = tester;
	}

	
	
}
