import java.io.Serializable;


public class Person implements Serializable{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String username;
	
	public Person(){
		
	}
	
	public Person(String name){
		username = name;
		
	}
	
	public String getUsername(){
		return username;
	}
	
	public void setUsername(String name){
		username = name;
	}
	

}
