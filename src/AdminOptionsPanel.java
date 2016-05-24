import java.awt.Color;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.border.LineBorder;
import javax.swing.border.TitledBorder;



public class AdminOptionsPanel extends JPanel  
{
	private Container contents;
	private JButton logOutButton, addStudentButton, addItemButton;
	private JButton removeStudentButton, removeItemButton, searchStudentButton;
	private JLabel emptySpace, welcomeLabel;
	private JPanel grid, gridTwo;
	public static Students studentList;
	public static Library itemList;
	private TesterClass tester;
	
	public AdminOptionsPanel ()
	{
		setLayout(new GridBagLayout());
		setBorder(BorderFactory.createLineBorder(Color.black));
		TitledBorder border;
		border = BorderFactory.createTitledBorder("Administrator");
		setBorder(border);
		GridBagConstraints c = new GridBagConstraints();
		Dimension preferredSize = new Dimension(100, 10);
		
		
		emptySpace = new JLabel("");
		welcomeLabel = new JLabel("Welcome Administrator");
	    c.fill = GridBagConstraints.CENTER;
	    c.ipady = 15;
	    c.weightx = 1;
	    c.gridx = 0;
	    c.gridy = 0;
	    add(welcomeLabel, c);
	    
	    
	    //create buttons for admin to use on GRID layout
	    grid = new JPanel(new GridBagLayout());
	    grid.setAlignmentX(CENTER_ALIGNMENT);
	    grid.setBorder(new LineBorder(Color.black));
	    TitledBorder borderStudentMod;
	    borderStudentMod = BorderFactory.createTitledBorder("Student Options");
	    grid.setBorder(borderStudentMod);
	    
	    //create buttons for the item options
	    gridTwo = new JPanel(new GridBagLayout());
	    gridTwo.setAlignmentX(CENTER_ALIGNMENT);
	    gridTwo.setBorder(new LineBorder(Color.black));
	    TitledBorder borderItemMod;
	    borderItemMod = BorderFactory.createTitledBorder("Item Options");
	    gridTwo.setBorder(borderItemMod);
	    
	    //creating JButtons ------------------ creating JButtons
	    
	    addStudentButton = new JButton("Add Student");
	    addStudentButton.setForeground(Color.white);
	    addStudentButton.setBackground(Color.decode("#7ec0ee"));
	    
	    addItemButton = new JButton("Add Item");
	    addItemButton.setForeground(Color.white);
	    addItemButton.setBackground(Color.decode("#7ec0ee"));
	    
	    removeStudentButton = new JButton("Remove/Penalize Student");
	    removeStudentButton.setForeground(Color.white);
	    removeStudentButton.setBackground(Color.decode("#7ec0ee"));
	    
	    removeItemButton = new JButton("Remove Item");
	    removeItemButton.setForeground(Color.white);
	    removeItemButton.setBackground(Color.decode("#7ec0ee"));
	    
	    
	    logOutButton = new JButton("Log Out");
	    logOutButton.setBackground(Color.decode("#ffff80"));
	    
	    c.insets = new Insets(5, 5, 5, 5);
	    //-----------------Adding the JButtons ------------------
	    
	    c.gridx = 0;
	    grid.add(addStudentButton, c);
	    
	    c.gridx = 1;
	    emptySpace.setPreferredSize(preferredSize);
	    grid.add(emptySpace, c);
	    
	    c.gridx = 2;
	    grid.add(removeStudentButton, c);
	    
	    //------------grid two---
	    
	    c.gridx = 0;
	    gridTwo.add(addItemButton, c);
	    
//	    c.gridy = 1;
//	    emptySpace.setPreferredSize(preferredSize);
//	    grid.add(emptySpace, c);
	    
	    c.gridx = 1;
	    emptySpace.setPreferredSize(preferredSize);
	    gridTwo.add(emptySpace, c);
	    
	    c.gridx = 2;
	    gridTwo.add(removeItemButton, c);
	    
	    
	    
	    
	    //adding the grids to the panel
	    c.gridx = 0;
	    c.gridy = 1;
	    add(grid, c);
	    
	    c.gridy = 2;
	    emptySpace.setPreferredSize(preferredSize);
	    add(emptySpace, c);
	    
	    c.gridy = 3;
	    add(gridTwo, c);
	    
	    
	    
	    c.gridy = 4;
	    add(logOutButton, c);
	    
	    
	    //LOGOUT BUTTON----------
	    logOutButton.addActionListener(new ActionListener() 
		{
			
			@Override
			public void actionPerformed(ActionEvent e) 
			{
				
				if(tester != null)
				{
					tester.swapView("intro");
					tester.setSize(300, 300);
				}
			}
		});
	    
	  //add student BUTTON----------
	    addStudentButton.addActionListener(new ActionListener() 
		{
			
			@Override
			public void actionPerformed(ActionEvent e) 
			{
				
				if(tester != null)
				{
					StudentAddPanel addStudentFrame = new StudentAddPanel();
					addStudentFrame.studentList = studentList;
					if(tester != null)
					{
						addStudentFrame.setTester(tester);
						tester.getCards().add(addStudentFrame, "addStudent");
						tester.swapView("addStudent");
						tester.setSize(400,400);
					}
				}
			}
		});
	    
	    addItemButton.addActionListener(new ActionListener() 
		{
			
			@Override
			public void actionPerformed(ActionEvent e) 
			{
				
				if(tester != null)
				{
					ItemAddPanel addItemFrame = new ItemAddPanel();
					addItemFrame.itemList = itemList;
					if(tester != null)
					{
						addItemFrame.setTester(tester);
						tester.getCards().add(addItemFrame, "addItem");
						tester.swapView("addItem");
						tester.setSize(400,400);
					}
				}
			}
		});
	    
	    removeStudentButton.addActionListener(new ActionListener() 
		{
			
			@Override
			public void actionPerformed(ActionEvent e) 
			{
				
				if(tester != null)
				{
					StudentPenalizeRemovePanel remStudentFrame = new StudentPenalizeRemovePanel(studentList);
					remStudentFrame.studentList = studentList;
					remStudentFrame.itemList = itemList;
					if(tester != null)
					{
						remStudentFrame.setTester(tester);
						tester.getCards().add(remStudentFrame, "removeStudent");
						tester.swapView("removeStudent");
						tester.setSize(550, 550);
					}
				}
			}
		});
	    
	    removeItemButton.addActionListener(new ActionListener() 
		{
			
			@Override
			public void actionPerformed(ActionEvent e) 
			{
				
				if(tester != null)
				{
					ItemRemovePanel remItemFrame = new ItemRemovePanel(itemList);
					remItemFrame.studentList = studentList;
					remItemFrame.itemList = itemList;
					if(tester != null)
					{
						remItemFrame.setTester(tester);
						tester.getCards().add(remItemFrame, "removeItem");
						tester.swapView("removeItem");
						tester.setSize(550, 550);
					}
				}
			}
		});
	    
	    
	    setBackground(Color.white);
		setSize(400, 400);
		setVisible(true);
		
	}
	//HERE IS TO WRITE TO A FILE!!!
//	private void updateStudentFile(Students studentList)
//	throws FileNotFoundException
//	{
//		PrintWriter writer = new PrintWriter("students.txt");
//		for(int i = 0; i < studentList.getStudentArray().size(); i++)
//		{
//			Person student = studentList.getStudentArray().get(i).getPerson();
//			writer.println(student.getName() + " " + student.getLastName() + " " + student.getId() + " " + student.getEmail()
//					+ " " + student.getPhone());
//		}
//		writer.close();
//	}
	
	public void setTester(TesterClass tester)
	{
		this.tester = tester;
	}
	
}


 
/*
dispose();
setVisible(false);
studentFrame = new StudentJFrame();
studentFrame.fillItemArray(itemList, itemDoc);
studentFrame.itemList = itemList;
studentFrame.currentStudent = studentList.getStudentArray().get(studentList.getIndexofMatch(id));
*/