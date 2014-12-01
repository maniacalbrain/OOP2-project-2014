import java.io.Serializable;

/**
 * Player Object.
 * 
 * <P> Player creates a Player and extends from Person. All Players will have 100 money when they are first created. Includes methods
 * to get and set the Players money total.
 * 
 * @author James Nagle<maniacalcorp@gmail.com>
 */

public class Player extends Person implements Serializable{	
	
	private static final long serialVersionUID = 1L;	
	private float money;
	
	//Player constructor
	public Player(String name){
		super(name);
		money = 100;
	}
	
	//method to return the Players money
	public float getMoney(){
		return money;
	}
	
	//method to set the Players money ie after they have won/lost a bet
	public void setMoney(float money){
		this.money = money;
	}
}
