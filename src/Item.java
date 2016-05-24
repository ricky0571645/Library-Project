import java.util.StringTokenizer;
import java.io.Serializable;


public class Item implements Serializable
{
	String title;
	String subject;
	String author;
	String publisher;
	String yearPublished;//validated?
	boolean status;
	String type; // can only be book, journal, audio cd, or video cd
	
	public Item(String t, String s, String a, String p, String y, boolean stat, String type)
	{
		title = t;
		subject = s;
		author = a;
		publisher = p;
		setYearPublished(y);
		status = stat;
		setType(type);
	}
	
	public Item()
	{
		title = "";
		subject = "";
		author = "";
		publisher = "";
		yearPublished = "";
		status = false;
		type = "";
	}
	
	public boolean contains(String word)
	{
		StringTokenizer token = new StringTokenizer(this.title);
		while(token.hasMoreTokens())
			if(token.nextToken().equalsIgnoreCase(word)) return true;
		return false;
	}
	
	public String toString()
	{
		String toReturn;
		toReturn = this.title + "," + this.subject;
		toReturn += "," + author + "," + this.publisher;
		toReturn += "," + this.yearPublished + "," + this.status + "," + this.type;
		return toReturn;
	}
	
	public boolean validateDate(String date)
	{
		if(this.yearPublished == date) return true;
		else return false;
	}
	
	//compares to another person by last name
	public boolean compareTo(Item other)
	{
		if(!other.title.equalsIgnoreCase(this.title)) return false;
		else if(!other.author.equalsIgnoreCase(this.author)) return false;
		else return true;
	}
//----------------------Setters and Getters----------------------
	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getSubject() {
		return subject;
	}

	public void setSubject(String subject) {
		this.subject = subject;
	}

	public String getAuthor() {
		return author;
	}

	public void setAuthor(String author) {
		this.author = author;
	}

	public String getPublisher() {
		return publisher;
	}

	public void setPublisher(String publisher) {
		this.publisher = publisher;
	}

	public String getYearPublished() {
		return yearPublished;
	}

	public void setYearPublished(String yearPublished) {
		if(yearPublished.length() == 4 );
			this.yearPublished = yearPublished;
	}

	public boolean getStatus() {
		return status;
	}

	public void setStatus(boolean status) {
		this.status = status;
	}

	public String getType() 
	{
		return type;
	}

	public void setType(String type) 
	{
		if(type.equalsIgnoreCase("book") || type.equalsIgnoreCase("cd") || type.equalsIgnoreCase("dvd")
				|| type.equalsIgnoreCase("journal"))
			this.type = type;
	}
	
	
	

}
