import java.io.Serializable;

public class Player extends Person implements Serializable{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private float money;
	
	public Player(String name){
		super(name);
		money = 100;
	}
	
	public float getMoney(){
		return money;
	}
	
	public void setMoney(float money){
		this.money = money;
	}
}
