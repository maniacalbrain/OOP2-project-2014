import java.io.Serializable;

/**
 * Person object
 * 
 * <P>Person is the parent class of Player.
 @author James Nagle<maniacalcorp@gmail.com>
 */

public class Person implements Serializable{
	
	
	private static final long serialVersionUID = 1L;
	private String username;
	
	//default, no-args constructor to allow for serialization
	public Person(){		
	}
	
	//constructor
	public Person(String name){
		setUsername(name);		
	}
	
	//method to return Persons username
	public String getUsername(){
		return username;
	}
	
	//method to set Persons username (called only in the above constructor)
	public void setUsername(String name){
		username = name;
	}
	

}
