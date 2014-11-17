import javax.swing.JFrame;


public class CrapsGameDriver extends JFrame{
	public static void main(String[] args){
		CrapsGame craps = new CrapsGame();
		//craps.makePoint() play through first roll - win, loss or establish point
		//craps.rollPoint() while loop, continue looping until outcome
		System.out.println(craps.getD1());
		System.out.println(craps.getD2());
		System.out.println(craps.getTotalRoll());
	}

}