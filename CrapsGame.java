public class CrapsGame extends Dice{
	Dice d1 = new Dice();
	Dice d2 = new Dice();
	int currd1;
	int currd2;

    public CrapsGame() {
    	System.out.println("Starting Game!");
    	Dice d1 = new Dice();
    	Dice d2 = new Dice();
    	int roll1 = d1.getRoll();
    	int roll2 = d2.getRoll();
    	int total = roll1 + roll2;
    	System.out.println("You rolled: " + roll1 + " " + roll2 + "= " + total);
    	if(total == 2 || total == 3 || total == 12){
    		System.out.println("Craps!!! You lose!");
    	}else if(total == 7 || total == 11){
    		System.out.println("Natural!!! You win!");
      	}else{
      		int point = total;
      		boolean playGame = true;
      		System.out.println("Point is: "+ total);
      		do{
      			int nextd1 = d1.getRoll();
      			int nextd2 = d2.getRoll();
      			int nextRoll = nextd1+nextd2;
      			System.out.println("You rolled: " + nextd1 + " " + nextd2 + "= " + nextRoll);
      			if(nextRoll == point){
      				System.out.println("You win!");
      				playGame = false;
      			}else if(nextRoll == 7){
      				System.out.println("You loss!");
      				playGame = false;
      			}else{
      				System.out.println("Try again");
      				playGame = true;
      			}
      			
      		}while(playGame == true);
      	}
    
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
    //craps -  2, 3, 12 - pass line looses - don't pass win (don't pass are pushed on a 12)
    //natural - 7, 11 - pass line wins automaticlly, 
    //point - 4, 5, 6, 8, 9, 10 establishes point
    
    //take bets on pass and don't pass
    //roll = get rolls
    //if craps end turn
    //if natural win
    //if point - roll = point
    //keep rolling until point is equalled (pass wins) or 7 (pass looses)
    
    
}