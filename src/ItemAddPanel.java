import java.awt.Color;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.FileWriter;
import java.io.IOException;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.TitledBorder;



public class ItemAddPanel extends JPanel
{
	
	private JButton computeButton, cancelButton;
	private JLabel addTitle, addSubject, addAuthor, addPublisher, addYearPublished, addType, emptyField;
	private JTextField titleField, yearField, subjectField, authorField, publisherField, typeField;
	private JPanel grid, gridTwo;
	public static Library itemList;
	private TesterClass tester;
	private JOptionPane message;
	
//	title = t;
//	subject = s;
//	author = a;
//	publisher = p;
//	yearPublished = y;
//	status = stat;
//	this.type = type;
	
	public ItemAddPanel ()
	{
		//set layout to gridlayout
		setLayout(new GridLayout(7,2));
		setBorder(BorderFactory.createLineBorder(Color.GRAY));
		TitledBorder border;
		border = BorderFactory.createTitledBorder("Add Item");
		setBorder(border);
		
		
		addTitle = new JLabel("Title", JLabel.CENTER);
		addTitle.setBorder(BorderFactory.createLineBorder(Color.decode("#7ec0ee")));
		add(addTitle);
		titleField = new JTextField(15);
		add(titleField);
		
		
		addSubject = new JLabel("Subject", JLabel.CENTER);
		addSubject.setBorder(BorderFactory.createLineBorder(Color.decode("#7ec0ee")));
		add(addSubject);
		subjectField = new JTextField(15);
		add(subjectField);
		
		addAuthor = new JLabel("Author", JLabel.CENTER);
		addAuthor.setBorder(BorderFactory.createLineBorder(Color.decode("#7ec0ee")));
		add(addAuthor);
		authorField = new JTextField(15);
		add(authorField);
		
		addPublisher = new JLabel("Publisher", JLabel.CENTER);
		addPublisher.setBorder(BorderFactory.createLineBorder(Color.decode("#7ec0ee")));
		add(addPublisher);
		publisherField = new JTextField();
		add(publisherField);
		
		addType = new JLabel("Type", JLabel.CENTER);
		addType.setBorder(BorderFactory.createLineBorder(Color.decode("#7ec0ee")));
		add(addType);
		typeField = new JTextField();
		add(typeField);
		
		addYearPublished = new JLabel("Year Published", JLabel.CENTER);
		addYearPublished.setBorder(BorderFactory.createLineBorder(Color.decode("#7ec0ee")));
		add(addYearPublished);
		yearField = new JTextField();
		add(yearField);
		
		
		cancelButton = new JButton("Cancel");
		cancelButton.setBackground(Color.decode("#ff6666"));
		cancelButton.setForeground(Color.white);
		add(cancelButton);
		
		computeButton = new JButton("Add Item");
		computeButton.setForeground(Color.white);
		computeButton.setBackground(Color.decode("#7ec0ee"));
		add(computeButton);
		
		computeButton.addActionListener(new ActionListener() 
		{
			
			@Override
			public void actionPerformed(ActionEvent e) 
			{
				String title = titleField.getText();
				String subject = subjectField.getText();
				String author = authorField.getText();
				String publisher = publisherField.getText();
				String published = yearField.getText();
				String type = typeField.getText();
				
				if(!type.equalsIgnoreCase("book") && !type.equalsIgnoreCase("cd") && !type.equalsIgnoreCase("dvd") && !type.equalsIgnoreCase("journal"))
				{
					message = new JOptionPane();
					message.showMessageDialog(null, "You have entered an invalid type.");
				}
				
				else if(published.length() != 4)
				{
					message = new JOptionPane();
					message.showMessageDialog(null, "Year should be #### format.");
				}
				
				else
				{
					type.toLowerCase();
					itemList.add(new Item(title, subject, author, publisher, published,true, type));
					Item addedItem = itemList.getItemArray().get(itemList.getSize()-1);
					 try 
					 {
				            FileWriter fileWriter = new FileWriter("items.txt", true);
				            if(itemList.getItemArray().size() == 1) fileWriter.write(addedItem.toString());
				            else
				            	fileWriter.write("\n" + addedItem.toString());

				            fileWriter.close();
				        } catch (IOException er) {
				            
				        }
					message = new JOptionPane();
					message.showMessageDialog(null, "Item added successfully!");
					if(tester != null)
					{
						tester.swapView("adminOption");
						tester.setSize(400, 400);
					}
				}
			
			}
		});
		

		cancelButton.addActionListener(new ActionListener() 
		{
			
			@Override
			public void actionPerformed(ActionEvent e) 
			{
				
				if(tester != null)
				{
					tester.swapView("adminOption");
					tester.setSize(400, 400);
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