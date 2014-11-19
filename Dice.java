//Dice will return a randomly generated value between 1 and 6
import java.util.Random;

public class Dice {	
	
	private int value;
	
	public void rollDice(){
		Random roll = new Random();
		this.value = roll.nextInt(6)+1;
	}
	
	public int getRoll() {
		return value;
	}     
}