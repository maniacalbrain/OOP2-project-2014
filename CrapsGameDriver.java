import javax.swing.*;

import java.awt.*;


public class CrapsGameDriver extends JFrame{
	public static void main(String[] args){
		CrapsGameDriver cgd = new CrapsGameDriver();		
		cgd.setVisible(true);
		//CrapsGame craps = new CrapsGame();
		//craps.takeBets();
		//craps.makePoint(); 
		//craps.rollPoint() while loop, continue looping until outcome
		//System.out.println(craps.getD1());
		//System.out.println(craps.getD2());
		//System.out.println(craps.getTotalRoll());
	}
	
	public CrapsGameDriver(){
		setTitle("Craps Game");
		setSize(300, 200);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		
		Container cont = getContentPane();
		CrapsGame cg = new CrapsGame();
		cont.add(cg);
	}

}