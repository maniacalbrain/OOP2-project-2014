
public class Player extends Person{
	
	private float money;
	
	public Player(String name){
		super(name);
		money = 100;
	}
	
	public float getMoney(){
		return money;
	}
}
