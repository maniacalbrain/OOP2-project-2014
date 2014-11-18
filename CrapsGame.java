import javax.swing.*;

import java.awt.*;
import java.awt.event.*;

public class CrapsGame extends JPanel{
	Dice d1;
	Dice d2;
	
	int currd1;
	int currd2;
	
	float pointBet;
	float dontpointBet;
	
	int point;
	boolean push;
	float money;
	JLabel lbl_money;
	JTextField txtfld_betPoint;
	JTextField txtfld_betDontPoint;
	JButton rollPoint;
	
	String message;

    public CrapsGame() {
    	JPanel crapsPane = new JPanel(new GridLayout(0,2)); //TODO Sort out layout!!
    	
    	money = 100;
    	lbl_money = new JLabel(""+money);
    	crapsPane.add(lbl_money);
    	
    	txtfld_betPoint = new JTextField(5);
    	crapsPane.add(txtfld_betPoint);
    	
    	txtfld_betDontPoint = new JTextField("Don't Point", 5);
    	crapsPane.add(txtfld_betDontPoint);
    	
    	rollPoint = new JButton("Roll for Point");
    	rollPoint.addActionListener(new ActionListener(){
    		public void actionPerformed(ActionEvent e){
    			takeBets();
    		}
    	});
    	crapsPane.add(rollPoint);
    	
    	add(crapsPane);    	     
    }
    
    public void takeBets(){
    	pointBet = Float.parseFloat(txtfld_betPoint.getText()); //TODO Validate!
    	money -= pointBet;
    	lbl_money.setText("" + money);
    	//makePoint();
    	
    }    
    
    public void makePoint(){
    	System.out.println("Rolling for point");
    	System.out.println("You rolled: " + getD1() + " " + getD2() +" = " + getTotalRoll());
    	switch(getTotalRoll()){
    		case 2:
    		case 3:
    			//Craps, You lose, don't point wins
    			//public void lose(bool push)
    			break;
    		case 12:
    			//Craps, You lose, don't point is pushed
    			break;
    		case 7:
    		case 11:
    			//Naturual, You win
    			break;
    		default:
    			//point is established
    			//display message that point is established
    			//call playPoint()
    			break;    				
    	}
    }
    
    public void playPoint(){
    	boolean playGame = true;
    	do{
    		//roll for point
    		//if roll is equal to point - playGame = false - call pointWins()
    		//else if roll is equal to 7 point loses - playGame = false - call pointLoses()
    		//else do nothing(play again)
    	}while(playGame = true);
    } 
    	
    public void pointLoses(){
    	//if push is true
    	//point loses, don't point is pushed
    	//else
    	//point loses, don't point wins
    	
    	//game ends - gameOver()
    }
    
    public void pointWins(){ 
    	//point wins, don't point loses
    	
    	//game ends - gameOver()    	
    }
    
    public void gameOver(){
    	//ask if player wants to play again
    	//call takeBets()
    }
    
    public int getD1(){
    	currd1 = d1.getRoll();
    	return currd1;
    }
    
    public int getD2(){
    	currd2 = d2.getRoll();
    	return currd2;
    }
    
    public int getTotalRoll(){
    	return currd1+currd2;
    } 
}