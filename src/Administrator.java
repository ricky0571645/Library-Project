import java.util.ArrayList;


public class Administrator 
{
	ArrayList<Person> administrators;
	
	Administrator()
	{
		administrators = new ArrayList<Person>();
	}
	
	public void add (Person p)
	{
		administrators.add(p);
	}
	
	public void remove (Person p)
	{
		administrators.remove(p);
	}
	
	public boolean search(Person p)
	{
		for(int i = 0; i < administrators.size(); i++)
			if(administrators.get(i).getId().equals(p)) return true;
		return false;
	}
	
	public boolean searchAdmin(String id)
	{
		for(int i = 0; i < administrators.size(); i++)
			if(administrators.get(i).getId().equals(id)) return true;
		return false;
	}
	
	public String toString()
	{
		String toReturn = "";
		for(int i = 0; i < administrators.size(); i++)
		{
			toReturn = administrators.toString();
		}
		return toReturn;
	}
}
