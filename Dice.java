import java.util.Random;

/**
 *Dice Object
 *
 * <P> Dice will hold a random value from 1 to 6. rollDice will set a new random value, getRoll will return the current value
 * @author James Nagle<maniacalcorp@gmail.com>
 */

public class Dice {	
	/**
	 * value represents the current value, from 1 to 6, of the dice
	 */
	private int value;
	
	/**
	 * Rolls the dice, changing the int held by value;
	 */
	public void rollDice(){
		Random roll = new Random();
		this.value = roll.nextInt(6)+1;
	}
	/**
	 * Returns the current value of the dice
	 * 
	 * @return 		the value (face) of the dice
	 */
	public int getRoll() {
		return value;
	}     
}