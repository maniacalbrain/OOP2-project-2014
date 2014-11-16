//Dice will return a randomly generated value between 1 and 6
import java.util.Random;

public class Dice {	
	
	public int getRoll(){
		Random roll = new Random();
		return roll.nextInt(6)+1;
	}       
}