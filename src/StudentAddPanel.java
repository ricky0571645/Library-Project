import java.awt.Color;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.TitledBorder;



public class StudentAddPanel extends JPanel
{
	
	private JButton computeButton, cancelButton;
	private JLabel addStudentName, addStudentId, addStudentEmail, addStudentPhone, addStudentLastName, emptyField;
	private JTextField studentNameField, studentLastNameField, IdField, studentEmailField, studentPhoneField;
	private JPanel grid, gridTwo;
	public static Students studentList;
	private TesterClass tester;
	private JOptionPane message;
	
	public StudentAddPanel ()
	{
		//set layout to gridlayout
		setLayout(new GridLayout(6,2));
		setBorder(BorderFactory.createLineBorder(Color.GRAY));
		TitledBorder border;
		border = BorderFactory.createTitledBorder("Add Student");
		setBorder(border);
		
		
		addStudentName = new JLabel("First Name", JLabel.CENTER);
		addStudentName.setBorder(BorderFactory.createLineBorder(Color.decode("#7ec0ee")));
		add(addStudentName);
		studentNameField = new JTextField(15);
		add(studentNameField);
		
		addStudentLastName = new JLabel("Last Name", JLabel.CENTER);
		addStudentLastName.setBorder(BorderFactory.createLineBorder(Color.decode("#7ec0ee")));
		add(addStudentLastName);
		studentLastNameField = new JTextField();
		add(studentLastNameField);
		
		addStudentId = new JLabel("Student ID", JLabel.CENTER);
		addStudentId.setBorder(BorderFactory.createLineBorder(Color.decode("#7ec0ee")));
		add(addStudentId);
		IdField = new JTextField(15);
		add(IdField);
		
		addStudentEmail = new JLabel("Email", JLabel.CENTER);
		addStudentEmail.setBorder(BorderFactory.createLineBorder(Color.decode("#7ec0ee")));
		add(addStudentEmail);
		studentEmailField = new JTextField(15);
		add(studentEmailField);
		
		addStudentPhone = new JLabel("Phone", JLabel.CENTER);
		addStudentPhone.setBorder(BorderFactory.createLineBorder(Color.decode("#7ec0ee")));
		add(addStudentPhone);
		studentPhoneField = new JTextField();
		add(studentPhoneField);
		
		
		cancelButton = new JButton("Cancel");
		cancelButton.setBackground(Color.decode("#ff6666"));
		cancelButton.setForeground(Color.white);
		add(cancelButton);
		
		computeButton = new JButton("Add Student");
		computeButton.setForeground(Color.white);
		computeButton.setBackground(Color.decode("#7ec0ee"));
		add(computeButton);
		
		computeButton.addActionListener(new ActionListener() 
		{
			
			@Override
			public void actionPerformed(ActionEvent e) 
			{
				//studentNameField, studentLastNameField, IdField, studentEmailField, studentPhoneField;
				String name = studentNameField.getText();
				String surname = studentLastNameField.getText();
				String id = IdField.getText();
				String email = studentEmailField.getText();
				String phone = studentPhoneField.getText();
				
				if(!email.contains("@"))
				{
					message = new JOptionPane();
					message.showMessageDialog(null, "Invalid email format.");
				}
				
				else if(!validatePhone(phone))
				{
					message = new JOptionPane();
					message.showMessageDialog(null, "Phone number should be in the following format: 1112223333");
				}
				
				else if(!validateId(id))
				{
					message = new JOptionPane();
					message.showMessageDialog(null, "ID should be 10 characters long.");
					
				}
				
				else if(studentList.searchStudent(id))
				{
					message = new JOptionPane();
					message.showMessageDialog(null, "ID already in use. Please try again!");
				}
				else
				{
					studentList.add(new Student(new Person(name, surname, id, email, phone)));
					Student addedStudent = studentList.getStudentArray().get(studentList.getStudentArray().size()-1);
					 try 
					 {
				            FileWriter fileWriter = new FileWriter("students.txt", true);
				            
				            fileWriter.write("\n" + addedStudent.getPerson().toString());

				            fileWriter.close();
				        } catch (IOException er) {
				            
				        }
					message = new JOptionPane();
					message.showMessageDialog(null, "Student added successfully!");
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
	
	
	
	private boolean validatePhone(String phone)
	{
		if(phone.length() != 10) return false;
		for(int i = 0; i < phone.length(); i++)
		{
			if(!Character.isDigit(phone.charAt(i))) return false;
		}
		return true;
	}
	
	private boolean validateId(String id)
	{
		if(id.length() != 10)
			return false;
		for(int i = 0; i < id.length(); i++)
		{
			if(Character.isAlphabetic(id.charAt(i))) return false;
		}
		return true;
		
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