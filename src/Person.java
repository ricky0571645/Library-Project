
public class Person 
{
	private String name;
	private String lastName;
	private String id;
	private String email;
	private String phone;
	private boolean goodStanding;
	
	public Person()
	{
		//nothing
	}
	
	public Person(String n, String l, String id, String email, String phone)
	{
		this.name = n;
		this.lastName = l;
		setId(id);
		setEmail(email);
		setPhone(phone);
		goodStanding = true;
		
	}
	//prints the toString of the class person
	public String toString()
	{
		String toReturn;
		toReturn = this.name + " " + this.lastName;
		toReturn += " " + id + " " + this.email;
		toReturn += " " + this.phone;
		return toReturn;
	}
	
	//validates the i.d. of the person
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
	
	//validates the email of the person
	private boolean validateEmail(String email)
	{
		boolean atMarker = false;
		for(int i = 0; i < email.length(); i++)
		{
			if(email.charAt(i) == '@') atMarker = true;
		}
		if(!atMarker) return false;
		else return true;
	}
	
	//validates the phone number
	private boolean validatePhone(String phone)
	{
		if(phone.length() != 10) return false;
		for(int i = 0; i < phone.length(); i++)
		{
			if(!Character.isDigit(phone.charAt(i))) return false;
		}
		return true;
	}
	
	//compares to another person by last name
	public boolean compareTo(Person other)
	{
		if(!other.lastName.equalsIgnoreCase(this.lastName)) return false;
		else return true;
	}
	
	boolean equals(Person other)
	{
		if(this.id.equalsIgnoreCase(other.id)) return false;
		else return true;
	}
	//----------------------Setters and Getters----------------------
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getLastName() {
		return lastName;
	}
	public void setLastName(String lastName) {
		this.lastName = lastName;
	}
	public String getId() {
		return id;
	}
	private void setId(String id) {
		if(validateId(id)) this.id = id;
		else throw new IllegalArgumentException();
	}
	public String getEmail() {
		
		return email;
	}
	private void setEmail(String email) {
		if(validateEmail(email)) this.email = email;
		else throw new IllegalArgumentException();
	}
	public String getPhone() {
		return phone;
	}
	private void setPhone(String phone) {
		if(validatePhone(phone)) this.phone = phone;
		else throw new IllegalArgumentException();
	}

	public boolean isGoodStanding() {
		return goodStanding;
	}

	public void setGoodStanding(boolean goodStanding) {
		this.goodStanding = goodStanding;
	}
}
