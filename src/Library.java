import java.io.Serializable;
import java.util.ArrayList;
import java.io.Serializable;


public class Library implements Serializable
{
	//arraylist of items variable
	private ArrayList<Item> items;
	
	//constructor that creates an arraylist of items
	public Library()
	{
		items = new ArrayList<Item>();
	}
	
	//get the size of the arraylist
	public int getSize()
	{
		return items.size();
	}
	
	//add item to arraylist ony if it does not already have it
	public boolean add(Item item)
	{
		if(items.contains(item)) return false;
		else
		{
			items.add(item);
			return true;
		}
	}
	
	//only remove the item if it exists
	public boolean remove(Item item)
	{
		if(!items.contains(item)) return false;
		else
		{
			items.remove(item);
			return true;
		}
	}
	
	//searches for input title and if it matches then it returns an arraylist
	//with the same words as the input
	public ArrayList<Item> searchTitle (String input)
	{
		ArrayList<Item> correctTitle = new ArrayList<Item>();
		for(int i = 0; i < items.size(); i++)
		{
			if(input.equalsIgnoreCase(items.get(i).getTitle()))
				correctTitle.add(items.get(i));
		}
		
		//if the arraylist is empty then return null
		if(correctTitle.size() == 0) return null;
		else
			return correctTitle;
	}
	

	//searches for input title and if it matches then it returns an arraylist
	//with the same words as the input
	 public ArrayList<Item> searchAuthor (String author)
	{
		ArrayList<Item> correctAuthor = new ArrayList<Item>();
		for(int i = 0; i < items.size(); i++)
		{
			if(author.equalsIgnoreCase(items.get(i).getAuthor()))
				correctAuthor.add(items.get(i));
		}
		
		//if the arraylist is empty then return null
		if(correctAuthor.size() == 0) return null;
		else
			return correctAuthor;
	}
	 
	
	public ArrayList<Item> searchSubject(String input)
	{
		ArrayList<Item> correctSubject = new ArrayList<Item>();
		for(int i = 0; i < items.size(); i++)
		{
			if(input.equalsIgnoreCase(items.get(i).getSubject()))
			{
				
				correctSubject.add(items.get(i));
			}
		}
		if(correctSubject.size() == 0) return null;
		else
			return correctSubject;
	}

	
	public ArrayList<Item> searchKeyword(String keyword)
	{
		ArrayList<Item> correctKeyword = new ArrayList<Item>();
		for(int i = 0; i < items.size(); i++)
		{
			if(items.get(i).contains(keyword))
				correctKeyword.add(items.get(i));
		}
		
		if(correctKeyword.size() == 0) return null;
		else
			return correctKeyword;
	}
	
	public String toString()
	{
		String toReturn = "";
		for(int i = 0; i < items.size(); i++)
		{
			toReturn += items.get(i).getTitle() + " " + items.get(i).getAuthor();
		}
		return toReturn;
	}
	
	//turn arraylist into regular array
	public Item[] toArray()
	{
	     Item[] it = new Item[items.size()];
	     for(int i = 0; i < items.size(); i++)
	         it[i] = items.get(i);
	     return it;
	 }
	
	public ArrayList<Item> getItemArray()
	{
		return items;
	}

}
