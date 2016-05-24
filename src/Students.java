import java.util.ArrayList;


public class Students 
{
	
	private ArrayList<Student> students;
	
	public Students()
	{
		students = new ArrayList<Student>();
	}
	
	public void add (Student s)
	{
		students.add(s);
	}
	
	public void remove (Student s)
	{
		students.remove(s);
	}
	
	public boolean searchStudent(String id)
	{
		for(int i = 0; i < students.size(); i++)
			if(students.get(i).person.getId().equals(id)) return true;
		return false;
	}
	
	public int getIndexofMatch(String id)
	{
		for(int i = 0; i < students.size(); i++)
			if(students.get(i).person.getId().equals(id)) return i;
		return -1;
	}
	
	public String toString()
	{
		String toReturn = "";
		for(int i = 0; i < students.size(); i++)
		{
			toReturn += students.get(i).person.getName() + " " + students.get(i).person.getLastName();
		}
		return toReturn;
	}
	
	public ArrayList<Student> getStudentArray()
	{
		return students;
	}
}
