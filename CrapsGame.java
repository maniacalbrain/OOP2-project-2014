import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.text.DecimalFormat;

/**
 * CrapsGame Object
 * 
 * <P>Handles the logic of the Craps game. Takes and validates the bets, rolls the dice and determines the outcome of the rolls
 * 
 * @author James Nagle<maniacalcorp@gmail.com>
 *
 */
public class CrapsGame extends JPanel{
	
	private static final long serialVersionUID = 1L;
	static Dice d1;	/** First Dice */	
	static Dice d2;	/** Second Dice */	
	
	int roll1;		/**value of first dice*/
	int roll2;		/**value of second dice */	
	
	float pointBet;			/**Bets on point */		
	float dontpointBet;		/**Bets on don't point */
	float house;			/**The game's earnings on player loses */
	
	int point;				/**current point value if point roll did not result in a natural or craps*/
	boolean push;			/**boolean to determine if don't point is to be pushed (on the roll of a 12)*/
	float money;			/**money held by the player*/
	
	DecimalFormat df = new DecimalFormat("#0.##");	/**Decimal format to display the money value to two decimal points*/
	
	JLabel lbl_money;		/**Label to hold the money value*/
	JLabel lbl_betPoint;	/**Label placed next to the textfield to take bets on point*/
	JTextField txtfld_betPoint;	/**Textfield to take bets on point*/
	JLabel lbl_betDontPoint;	/**Label placed next to the textfield to take bets on don't point*/
	JTextField txtfld_betDontPoint;	/**Textfield to take bets on don't point*/
	JButton rollPoint;		/**Button to be pressed to roll for point*/
	
	JPanel gamePanel;		/**Panal to hold pictures of dice rolls and the current roll*/
	String message;			/**String cointaining the current rolls and the total roll*/
	JLabel message_lbl;		/**Label that the message will be displayed in*/
	
	Player player;			/**The current player*/

	
	/**
	 * CrapsGame Constructor
	 * 
	 * Takes a player as an arguement. This player will be either created (signup) or loaded from file(login) in the CrapsGameDriver 
	 * class. Creates crapsPane and ButtonPane which will hold all the labels, textfields and buttons and adds these to mainFrame which
	 * will be displayed. When the Roll button is pressed the takeBets method is called which validates the input in the textfields.
	 * @param player
	 */
    public CrapsGame(Player player) {
    	this.player = player;
    	d1 = new Dice();
    	d2 = new Dice();
    	JPanel mainFrame = new JPanel(new BorderLayout());
    	JPanel crapsPane = new JPanel(new GridLayout(0,2)); 
    	JPanel buttonPane = new JPanel(new FlowLayout());
    	
    	money = player.getMoney();
    	
    	JLabel name = new JLabel(player.getUsername());
    	crapsPane.add(name);
    	lbl_money = new JLabel("Money: " + df.format(money));
    	crapsPane.add(lbl_money);
    	    	
    	lbl_betPoint = new JLabel("Point");
    	crapsPane.add(lbl_betPoint);
    	
    	txtfld_betPoint = new JTextField(5);
    	crapsPane.add(txtfld_betPoint);
    	
    	lbl_betDontPoint = new JLabel("Don't Point");
    	crapsPane.add(lbl_betDontPoint);
    	
    	txtfld_betDontPoint = new JTextField();
    	crapsPane.add(txtfld_betDontPoint);
    	
    	rollPoint = new JButton("Roll for Point");
    	rollPoint.addActionListener(new ActionListener(){
    		public void actionPerformed(ActionEvent e){
    			takeBets();
    		}
    	});
    	
    	buttonPane.add(rollPoint);    	
    	mainFrame.add(crapsPane, BorderLayout.CENTER);
    	mainFrame.add(buttonPane, BorderLayout.SOUTH);
    	
    	add(mainFrame);   	
    }
    
    /**
     * takeBets() Method
     * 
     * Validates the content of the textfields. Makes sure that only one bet is entered, can not bet on both point and don't point, and
     * makes sure that bets are entered correctly: as a number greater than 0 and not more than the player holds. 
     * In the event that bets are entered correctly a message is displayed showing explaining the error and resetting both textfields to
     * empty.
     * When a bet is correctly entered the makePoint method is called.
     */
    public void takeBets(){
    	pointBet = 0;
    	dontpointBet = 0;
    	
    	//if both fields are empty
    	if(txtfld_betPoint.getText().length() != 0 & txtfld_betDontPoint.getText().length() !=0){
    		JOptionPane.showMessageDialog(this, "You can not bet on both point and don't point!");
    		txtfld_betPoint.setText("");
    		txtfld_betDontPoint.setText("");
    	}
    	//if both fields are 0
    	else if(txtfld_betPoint.getText() == "0" && txtfld_betDontPoint.getText() == "0"){
    		JOptionPane.showMessageDialog(this, "You must place a bet greater than 0 on point or don't point to play");
    	}
    	else if(txtfld_betPoint.getText().length() == 0 && txtfld_betDontPoint.getText().length()==0){
    		JOptionPane.showMessageDialog(this, "You must place a bet!");
    	}
    	
    	//if betPoint contains a value and betdontPoint is empty or 0
    	else if(txtfld_betPoint.getText().length() != 0 && (txtfld_betDontPoint.getText().length() == 0 || txtfld_betDontPoint.getText() =="0")){
    		try{
    			pointBet = Float.parseFloat(txtfld_betPoint.getText()); //
    		}catch(NumberFormatException nfe){
    			JOptionPane.showMessageDialog(this, "Please included only digits in your bet");
    			txtfld_betPoint.setText("");
    		}
    		if(pointBet <=0){
    			JOptionPane.showMessageDialog(this, "Must bet more than 0");
    			txtfld_betPoint.setText("");
    		}
    		else if(pointBet > money){
    			JOptionPane.showMessageDialog(this, "Can't bet more money than you have!");
    			txtfld_betPoint.setText("");
    		}
    		else{
    			money -= pointBet;
    			lbl_money.setText("Money: " + df.format(money));
    			makePoint();
    		}
    	}
    	//if betdontPoint contains a value and betPoint is empty or 0
    	else if(txtfld_betDontPoint.getText().length() != 0 && (txtfld_betPoint.getText().length() == 0 || txtfld_betPoint.getText() == "0")){    		
    		try{
    			dontpointBet = Float.parseFloat(txtfld_betDontPoint.getText()); //    			
    		}catch(NumberFormatException nfe){
    			JOptionPane.showMessageDialog(this, "Please included only digits in your bet");
    			txtfld_betDontPoint.setText("");
    		}
    		if(dontpointBet <=0){
    			JOptionPane.showMessageDialog(this, "Must bet more than 0");
    			txtfld_betDontPoint.setText("");
    		}
    		else if(dontpointBet > money){
    			JOptionPane.showMessageDialog(this, "Can't bet more money than you have!");
    			txtfld_betDontPoint.setText("");
    		}
    		else{
    			money -= dontpointBet;
    			lbl_money.setText("Money: " + df.format(money));
    			makePoint();
    		}
    	}
    }    
    
    
    /**
     * makePoint() method
     * 
     * Handles the logic of a craps game. Rolls the dice and returns if the player has rolled craps, a natural or point. If the player
     * rolls point playPoint method is called. Otherwise player pointWins method or pointLoses method is called as appropriate.
     */
    public void makePoint(){
    	JOptionPane.showMessageDialog(this, "Rolling for point");
    	roll1 = getD1();
    	roll2 = getD2();
    	
    	gamePanel = new JPanel(new BorderLayout());    	
    	message = "You rolled: " + getD1() + " & " + getD2() +" = " + getTotalRoll();
    	message_lbl = new JLabel(message);
    	message_lbl.setHorizontalAlignment(SwingConstants.CENTER);
    	gamePanel.add(message_lbl, BorderLayout.NORTH);    	
    	
    	//Calls a new DicePanel to display images of the dice that have been rolled
    	DicePanel dp = new DicePanel();
    	dp.setPreferredSize(new Dimension(80, 30));
    	
    	gamePanel.add(dp, BorderLayout.SOUTH);
    	
    	JOptionPane.showMessageDialog(this, gamePanel, "Your Roll", JOptionPane.PLAIN_MESSAGE );
    	switch(getTotalRoll()){
    		case 2:
    		case 3:
    			//Craps, point loses, don't point wins
    			JOptionPane.showMessageDialog(this, "Craps, point loses!");
    			push = false; //don't point wins 
    			pointLoses();
    			break;
    		case 12:
    			//Craps, point loses, don't point is pushed
    			JOptionPane.showMessageDialog(this, "Craps, point loses, don't point is pushed!");
    			push = true; //don't point doesn't win
    			pointLoses();
    			break;
    		case 7:
    		case 11:
    			//Natural, point wins
    			JOptionPane.showMessageDialog(this, "Natural, point wins!");
    			pointWins();
    			break;
    		default:
    			//point is established
    			point = getTotalRoll();
    			JOptionPane.showMessageDialog(this, "You rolled point: " + point);
    			playPoint();
    			break;    				
    	}
    }
    
    /**
     * playPoint() method
     * 
     * If the player rolled point in the makePoint method this method will loop until either point is rolled again, resulting in a call
     * to pointWins, or a 7 is rolled, resulting in a call to pointLoses.
     */    
    public void playPoint(){
    	boolean playGame = true;
    	do{
    		gamePanel = new JPanel(new BorderLayout());    	
        	message = "You rolled: " + getD1() + " & " + getD2() +" = " + getTotalRoll();
        	message_lbl = new JLabel(message);
        	message_lbl.setHorizontalAlignment(SwingConstants.CENTER);
        	gamePanel.add(message_lbl, BorderLayout.NORTH);    	
        	
        	DicePanel dp = new DicePanel();
        	dp.setPreferredSize(new Dimension(80, 30));
        	
        	gamePanel.add(dp, BorderLayout.SOUTH);
    		
    		JOptionPane.showMessageDialog(this, gamePanel, "Your Roll", JOptionPane.PLAIN_MESSAGE);
        	
    		if(getTotalRoll()==point){
    			JOptionPane.showMessageDialog(this, "Point wins");
    			playGame = false;
    			pointWins();
    		}else if(getTotalRoll() == 7){
    			JOptionPane.showMessageDialog(this, "Don't point wins");
    			playGame = false;
    			pointLoses();
    		}else{
    			JOptionPane.showMessageDialog(this, "No luck this time, roll again");
    			
    		}
    	}while(playGame == true);
    }
    
    /**
     * pointLoses() method
     * 
     * This method will be called if craps was rolled in the makePoint method or a 7 was rolled in the playPoint method 
     */
    public void pointLoses(){
    	if(push == true){
    		//point loses, don't point is pushed
    		money += dontpointBet;
    	}else{
    		//point loses, don't point wins    		
    		house -= dontpointBet*2;
    		money += dontpointBet*2;
    	}
    	
    	//update money, clear textfields
    	house += pointBet;		
		lbl_money.setText("Money: "+money);
		player.setMoney(money);
		txtfld_betPoint.setText("");
		txtfld_betDontPoint.setText("");
    }
    
    /**
     * pointWins() method
     * This method is called if a natural is rolled in the makePoint method or point was rolled in the playPoint method
     */
    public void pointWins(){
    	house -= pointBet*2;
    	money += pointBet*2;
    	lbl_money.setText("Money: "+ df.format(money));
    	player.setMoney(money);
    	house += dontpointBet;   	
    }
    
    /**
     * getD1() method
     * calls the rollDice() method to set a new value for this dice and then returns it
     * @return	the value of Dice d1
     */
    public int getD1(){
    	d1.rollDice();
    	return d1.getRoll();
    }
    
    /**
     * getD2() method
     * calls the rollDice() method to set a new value for this dice and then returns it
     * @return	the value of Dice d2
     */
    public int getD2(){
    	d2.rollDice();
    	return d2.getRoll();
    }
    
    /**
     * getTotalRoll() method
     * returns the combined value of the rolled dice 
     * @return	current total of d1 + d2
     */
    public int getTotalRoll(){
    	int thisRoll = d1.getRoll()+d2.getRoll();
    	return thisRoll;
    } 
}

/**
 * DicePanal class
 * creates a panel containing an image of both dice
 * @author James Nagle<maniacalcorp@gmail.com>
 *
 */
class DicePanel extends JPanel{
	private static final long serialVersionUID = 1L;

	public void paintComponent(Graphics g){	
		
		/*************************************************
		 * Title: Clipart of orsonj
		 * Author: orsonj
		 * Date: 2006
		 * Availability: https://openclipart.org/user-detail/orsonj
		 * Modified: Used images for the dice
		 *************************************************/
		
		Image icon = new ImageIcon("dice\\die"+CrapsGame.d1.getRoll()+".png").getImage();
		Image icon2 = new ImageIcon("dice\\die"+CrapsGame.d2.getRoll()+".png").getImage();
		
		//width of JOptionPane is 242.
		g.drawImage(icon, 96, 0, 20, 20, this);
		g.drawImage(icon2, 126, 0, 20, 20, this);
	}
}