import javax.swing.*;

public class CrapsGame extends JPanel{
	Dice d1;
	Dice d2;
	int currd1;
	int currd2;
	int point;
	boolean push;

    public CrapsGame() {
    	JPanel crapsPane = new JPanel();    	
    	//Dice d1 = new Dice();
		//Dice d2 = new Dice();
		//push = false;
		//point = 0;     
    }
    public void takeBets(){
    	//take bets
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