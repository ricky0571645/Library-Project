import java.awt.CardLayout;
import java.awt.Color;
import java.awt.Container;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.OutputStream;
import java.util.Scanner;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import javax.swing.UIManager;


public class TesterClass extends JFrame
{
	private Container contents;
	private JButton studentButton, administratorButton, enterButton;
	private JLabel studentInfo;
	private JTextField idInput;
	private JPasswordField idInputP;
	//lists
	private Administrator adminList;
	private Students studentList;
	private static Library itemList;
	//JPanels
	private StudentOptionsWindow studentOptionsPanel;
	private AdminOptionsPanel adminOptionsPanel;
	//
	private boolean shouldFill = true, goneBack = false;
	private CardLayout cardLayout = new CardLayout();
	private JPanel cards = new JPanel(cardLayout);
	private JPanel introPanel;
	private TesterClass tester;
	
	//THIS IS THE MAIN METHOD!!!
	public static void main(String args[])
	throws FileNotFoundException, IOException
	{	
		TesterClass gui = new TesterClass();
		Scanner studentDoc = new Scanner(new File("students.txt"));
		Scanner adminDoc = new Scanner(new File("administrators.txt"));
		Scanner itemDoc = new Scanner(new File("items.txt"));
		
		gui.studentList = new Students();
		gui.adminList = new Administrator();
		gui.itemList = new Library();
		
		
		gui.fillStudentsArray(gui.studentList, studentDoc);
		gui.fillAdminArray(gui.adminList, adminDoc);
//		gui.fillItemArray(itemList, itemDoc);
		
	    try
		{
//			serialize(itemList);
			deserialize();
		}
		catch(Exception e)
		{
			System.out.println("fdasdfa");
		}
		
	}
	
	public TesterClass()
	throws FileNotFoundException
	{
		
		super("Library Application");
		
		
		
		contents = getContentPane();
		introPanel = new JPanel(new GridBagLayout());
		GridBagConstraints c = new GridBagConstraints();
		
		tester = this;
	    if (shouldFill) {
	    //natural height, maximum width
	    c.fill = GridBagConstraints.HORIZONTAL;
	    }
		
		//Add buttons to front window
		studentButton = new JButton("Student");
		studentButton.setBackground(Color.decode("#7ec0ee"));
		c.fill = GridBagConstraints.HORIZONTAL;
		c.ipady = 20;
		c.weightx = 0.5;
	    c.gridx = 0;
	    c.gridy = 0;
	    introPanel.add(studentButton, c);
	    
		administratorButton = new JButton("Administrator");
		administratorButton.setBackground(Color.decode("#7ec0ee"));
		c.fill = GridBagConstraints.HORIZONTAL;
		c.ipady = 20;
	    c.weightx = 0.5;
	    c.gridx = 1;
	    c.gridy = 0;
	    introPanel.add(administratorButton, c);
		
		enterButton = new JButton("");
		enterButton.setBackground(Color.decode("#98ff98"));
		idInputP = new JPasswordField(0);
		
		//Add labels after selection
		studentInfo = new JLabel("");
		c.fill = GridBagConstraints.HORIZONTAL;
	    c.ipady = 20;      //make this component tall
	    c.weightx = 1.0;
	    c.gridwidth = 3;
	    c.gridx = 0;
	    c.gridy = 1;
	    introPanel.add(studentInfo, c);
		    
		
		ButtonHandler bh = new ButtonHandler();
		studentButton.addActionListener(bh);
		administratorButton.addActionListener(bh);
		enterButton.addActionListener(bh);
		
		c.fill = GridBagConstraints.HORIZONTAL;
	    c.ipady = 20;      //make this component tall
	    c.weightx = 0.5;
	    c.gridx = 0;
	    c.gridy = 5;
		
	    introPanel.setBackground(Color.white);
	    //Add all the cards to cardLayout!!!-------------
	    cards.add(introPanel, "intro");
	    
	   
	    
	    contents.add(cards);
		setSize(300, 300);
		setVisible(true);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}
	
	private class ButtonHandler implements ActionListener
	{
		boolean studentButtonPressed, adminButtonPressed;
		public void actionPerformed(ActionEvent action)
		{
			GridBagConstraints c = new GridBagConstraints();
			if(action.getSource() == studentButton)
			{
				adminButtonPressed = false;
				studentInfo.setText("Please enter your Student I.D.");
				
				//add the input option
				idInputP.setColumns(10);
				c.fill = GridBagConstraints.HORIZONTAL;
			    c.ipady = 40;      //make this component tall
			    c.weightx = 0.0;
			    c.gridwidth = 3;
			    c.gridx = 0;
			    c.gridy = 2;
			    introPanel.add(idInputP, c);
			    
				enterButton.setText("Enter");
				c.fill = GridBagConstraints.HORIZONTAL;
			    c.ipady = 20;      //make this component tall
			    c.weightx = 0.0;
			    c.gridwidth = 3;
			    c.gridx = 0;
			    c.gridy = 3;
			    introPanel.add(enterButton, c);
				studentButtonPressed = true;
			}
			
			if(action.getSource() == administratorButton)
			{
				studentButtonPressed = false;
				studentInfo.setText("Please enter your Administrator I.D.");
				
				//add the input option
				idInputP.setColumns(10);
				c.fill = GridBagConstraints.HORIZONTAL;
			    c.ipady = 40;      //make this component tall
			    c.weightx = 0.0;
			    c.gridwidth = 3;
			    c.gridx = 0;
			    c.gridy = 2;
			    introPanel.add(idInputP, c);
			   
			    
				enterButton.setText("Enter");
				c.fill = GridBagConstraints.HORIZONTAL;
			    c.ipady = 20;      //make this component tall
			    c.weightx = 0.0;
			    c.gridwidth = 3;
			    c.gridx = 0;
			    c.gridy = 3;
			    introPanel.add(enterButton, c);
				adminButtonPressed = true;
			}
			
			if(action.getSource() == enterButton)
			{
				String id = idInputP.getText();
				
				if(goneBack == true)
				{
					 try
						{
							serialize(itemList);
						}
						catch(Exception e)
						{
							System.out.println("fdasdfa");
						}
					 
					 
					 enterButton.setText("Enter");
				}
				
				if(studentList.searchStudent(id) && studentButtonPressed == true && goneBack == false) 
				{
					studentOptionsPanel = new StudentOptionsWindow (studentList.getStudentArray().get(studentList.getIndexofMatch(id)));
					cards.add(studentOptionsPanel, "studentOption");
					studentOptionsPanel.itemList = itemList;
					studentOptionsPanel.currentStudent = studentList.getStudentArray().get(studentList.getIndexofMatch(id));
			    	studentOptionsPanel.setTester(tester);
					swapView("studentOption");
					setSize(400, 400);
					idInputP.setText("");
					enterButton.setText("Save");
					goneBack = true;
					
					
				}
				
				
				else if(adminList.searchAdmin(id) && adminButtonPressed == true && goneBack == false)
				{
					adminOptionsPanel = new AdminOptionsPanel();
					cards.add(adminOptionsPanel, "adminOption");
					adminOptionsPanel.itemList = itemList;
					adminOptionsPanel.studentList = studentList;
					adminOptionsPanel.setTester(tester);
					swapView("adminOption");
					setSize(400, 400);
					idInputP.setText("");
					goneBack = true;
					enterButton.setText("Save");
				}
				else
				{
					JOptionPane error = new JOptionPane();
					if(goneBack == false)error.showMessageDialog(null, "Invalid ID");
					if(goneBack == true)error.showMessageDialog(null, "Data Saved");
					goneBack = false;
					idInputP.setText("");
				}
			}
		}
	}
	
	public void fillStudentsArray(Students studentList, Scanner studentDoc)
	throws FileNotFoundException
	{
		String line= "", name = "", lastName = "", id = "", email = "", phone = "";
		while(studentDoc.hasNextLine())
		{
			line = studentDoc.nextLine();
			Scanner currentLine = new Scanner(line);
			while(currentLine.hasNext())
			{
				name = currentLine.next();
				lastName = currentLine.next();
				id = currentLine.next();
				email = currentLine.next();
				phone = currentLine.next();
				studentList.add(new Student(new Person(name, lastName, id,email ,phone ))) ;
			}
		}
	}
	

	public void fillAdminArray(Administrator adminList, Scanner studentDoc)
	throws FileNotFoundException
	{
		String line= "", name = "", lastName = "", id = "", email = "", phone = "";
		while(studentDoc.hasNextLine())
		{
			line = studentDoc.nextLine();
			Scanner currentLine = new Scanner(line);
			while(currentLine.hasNext())
			{
				name = currentLine.next();
				lastName = currentLine.next();
				id = currentLine.next();
				email = currentLine.next();
				phone = currentLine.next();
				adminList.add(new Person(name, lastName, id,email ,phone )) ;
			}
		}
	}
	
	public void fillItemArray(Library itemList, Scanner itemDoc)
	{
		String line = "", title = "", subject = "", author = "", publisher = "", yearPublished = "", type = "";
		boolean status;
		while(itemDoc.hasNextLine())
		{
			line = itemDoc.nextLine();
			Scanner currentLine = new Scanner(line);
			currentLine.useDelimiter(",");
			
			title = currentLine.next();
			subject = currentLine.next();
			author = currentLine.next();
			publisher = currentLine.next();
			yearPublished = currentLine.next();
			status = Boolean.parseBoolean(currentLine.next());
			type = currentLine.next();
			itemList.add(new Item(title, subject, author, publisher , yearPublished, status, type )) ;
		}
		
	}//end of fill item array
	
	public static void serialize(Library p) throws Exception {
	      ObjectOutputStream cout = null;
	      try
	      {
	      
	         OutputStream outputFile = 
	               new FileOutputStream( "Serialized" );
	         //object output stream allows you to output an object as a whole      
	          cout = new ObjectOutputStream( outputFile );
	          cout.writeObject(p);
	       }
	       catch ( IOException ioException )
	       {
	           System.err.println( "****Error opening file." );
	        } 
	                   
	      cout.close();
	   }   
	   
	   public static void deserialize() throws Exception {
	     // p= new ArrayList<Person>();
			InputStream inputFile = 
	         new FileInputStream( "Serialized" );
	      ObjectInputStream cin = 
	         new ObjectInputStream( inputFile );
	 
				 
	            itemList  =(Library) cin.readObject() ;
					//p.sort();
//					System.out.println(p);
					boolean morePass =true;
					for (int i=1; i<itemList.getSize() && morePass; i++)
						morePass= false;
						for(int j=0; j<(itemList.getSize()-1); j++)
						{
						   if (itemList.getItemArray().get(j).compareTo(itemList.getItemArray().get(j+1)))
							{
							    Item p1 = itemList.getItemArray().get(j);
								 Item p2 = itemList.getItemArray().get(j+1);
								 itemList.add(p1) ;
								 itemList.add(p2) ;
								 morePass = true;
							} 
							}
					 
//					System.out.println(p);
			
	   }
	
	public void swapView(String key) 
	{
	      cardLayout.show(cards, key);
	}
	
	public JPanel getCards()
	{
		return cards;
	}
	

}
