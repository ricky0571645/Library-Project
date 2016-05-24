import java.util.ArrayList;


public class Student
{
	Person person;
	private ArrayList<Item> checkout;
	
	Student(Person p)
	{
		person = p;
		checkout = new ArrayList<Item>();
	}
	
	boolean equals(Student other)
	{
		if(!person.getId().equalsIgnoreCase(other.getPerson().getId())) return false;
		else return true;
	}
	
	public void addCheckedOut(Item i)
	{
		checkout.add(i);
	}
	
	public String toString()
	{
		return person.toString();
	}
	
	public boolean hasItem(Item other)
	{
		for(int i = 0; i < checkout.size(); i++)
		{
			if(checkout.get(i).compareTo(other))
				return true;
		}
		return false;
	}
	
	public ArrayList<Item> getCheckout()
	{
		return checkout;
	}
	
	public Person getPerson()
	{
		return person;
	}
}
