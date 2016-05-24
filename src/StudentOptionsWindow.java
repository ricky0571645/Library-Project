import java.awt.Color;
import java.awt.Container;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JComponent;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;




public class StudentOptionsWindow extends JPanel
{
	private Container contents;
	private JButton checkoutButton, returnBookButton, logOutButton;
	private JLabel weclomeLabel, studentIDLabel, emailLabel, phoneLabel, emptySpace;
	private JPanel grid;
	public static Student currentStudent;
	public static Library itemList;
	private TesterClass tester;
	private JOptionPane message;
	
	public StudentOptionsWindow (Student student)
	{
//		contents = getContentPane();
		setLayout(new GridBagLayout());
		setBorder(BorderFactory.createLineBorder(Color.black));
		GridBagConstraints c = new GridBagConstraints();
		
		
		emptySpace = new JLabel(" ");
	    //create welcome label
	    weclomeLabel = new JLabel("Welcome " + student.getPerson().getName() + "!");
	    if(student.getPerson().isGoodStanding() == false) 
	    {
	    	weclomeLabel.setForeground(Color.red);
	    	weclomeLabel.setText("Account has been penalized. You may not checkout items.");
	    }
	    c.fill = GridBagConstraints.REMAINDER;
	    c.ipady = 15;
	    c.weightx = 1;
	    c.gridx = 0;
	    c.gridy = 0;
	    add(weclomeLabel, c);
	    //create student info in second row
	    grid = new JPanel(new GridLayout(1, 3));
	    grid.setAlignmentX(CENTER_ALIGNMENT);
	    
	    studentIDLabel = new JLabel("ID: " + student.getPerson().getId());
	    emailLabel = new JLabel(student.getPerson().getEmail());
	    phoneLabel = new JLabel("       " + student.getPerson().getPhone());
	    
	    grid.add(studentIDLabel);
	    grid.add(emailLabel);
	    grid.add(phoneLabel);
	    grid.setBorder(BorderFactory.createLineBorder(Color.GRAY));
	    
	    c.gridx = 0;
	    c.gridy = 1;
	    add(grid, c);
	    
	    //empty spaces
	    c.gridx = 0;
	    c.gridy = 2;
	    add(emptySpace, c);
	    
	    c.gridx = 0;
	    c.gridy = 3;
	    add(emptySpace, c);
	    
	    //---------adding the button section ----------
	    returnBookButton = new JButton("Return Book");
	    returnBookButton.setForeground(Color.white);
	    returnBookButton.setBackground(Color.decode("#7ec0ee"));
	    
	    checkoutButton = new JButton("Checkout Book");
	    checkoutButton.setForeground(Color.white);
	    checkoutButton.setBackground(Color.decode("#7ec0ee"));
	    
	    logOutButton = new JButton("Log Out");
	    logOutButton.setBackground(Color.decode("#ffff80"));
	    
	    JPanel buttonGrid = new JPanel(new GridLayout(1, 2));
	    buttonGrid.add(returnBookButton);
	    buttonGrid.add(checkoutButton);
	    
	    c.gridx = 0;
	    c.gridy = 4;
	    add(buttonGrid, c);
	    
	    c.gridx = 0;
	    c.gridy = 5;
	    add(emptySpace, c);
	    
	    c.gridx = 0;
	    c.gridy = 6;
	    add(emptySpace, c);
	    
	    c.gridx = 0;
	    c.gridy = 7;
	    add(logOutButton, c);
	    
	    
	    
	    //choosing what to do with the checkoutbutton-------CHECKOUT BUTTON
	    checkoutButton.addActionListener(new ActionListener() 
		{
			
			@Override
			public void actionPerformed(ActionEvent e) 
			{
				if(currentStudent.getPerson().isGoodStanding())
				{
					StudentCheckoutWindow checkoutFrame = new StudentCheckoutWindow(itemList);
					checkoutFrame.itemList = itemList;
					checkoutFrame.currentStudent = currentStudent;
					if(tester != null)
					{
						checkoutFrame.setTester(tester);
						tester.getCards().add(checkoutFrame, "checkout");
						tester.swapView("checkout");
						tester.pack();
					}
				}
				else
				{
					message = new JOptionPane();
					message.showMessageDialog(null, "You are prohibited from checking items out."); 
				}
			}
		});
	    
	    returnBookButton.addActionListener(new ActionListener() 
		{
			
			@Override
			public void actionPerformed(ActionEvent e) 
			{
				
				StudentReturnWindow returnFrame = new StudentReturnWindow(currentStudent);
				returnFrame.itemList = itemList;
				returnFrame.currentStudent = currentStudent;
				if(tester != null)
				{
					returnFrame.setTester(tester);
					tester.getCards().add(returnFrame, "bookReturn");
					tester.swapView("bookReturn");
					tester.pack();
				}
			}
		});
	    
	    
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
	    
	    
	    
	    setBackground(Color.white);
		setSize(400, 400);
		setVisible(true);
		
	}
	
	
	
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