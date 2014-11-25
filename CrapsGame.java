import javax.swing.*;

import java.awt.*;
import java.awt.event.*;

public class CrapsGame extends JPanel{
	static Dice d1;
	static Dice d2;
	
	int roll1;
	int roll2;
	
	float pointBet;				//Bets on point
	float dontpointBet;			//Bets on don't point
	float house;				//Casino's earnings on player loses
	
	int point;					//current point
	boolean push;
	float money;
	
	JLabel lbl_money;
	JTextField txtfld_betPoint;
	JTextField txtfld_betDontPoint;
	JButton rollPoint;
	
	JPanel gamePanel;	
	String message;
	JLabel message_lbl;

    public CrapsGame() {
    	d1 = new Dice();
    	d2 = new Dice();
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
    	pointBet = 0;
    	dontpointBet = 0;
    	
    	//if both fields are empty
    	if(txtfld_betPoint.getText().length() != 0 & txtfld_betDontPoint.getText().length() !=0){
    		JOptionPane.showMessageDialog(null, "You can not bet on both point and don't point!");
    		txtfld_betPoint.setText("");
    		txtfld_betDontPoint.setText("");
    	}
    	//if both fields are 0
    	else if(txtfld_betPoint.getText() == "0" && txtfld_betDontPoint.getText() == "0"){
    		JOptionPane.showMessageDialog(null, "You must place a bet greater than 0 on point or don't point to play");
    	}
    	
    	//if betPoint contains a value and betdontPoint is empty or 0
    	else if(txtfld_betPoint.getText().length() != 0 && (txtfld_betDontPoint.getText().length() == 0 || txtfld_betDontPoint.getText() =="0")){
    		try{
    			pointBet = Float.parseFloat(txtfld_betPoint.getText()); //
    		}catch(NumberFormatException nfe){
    			JOptionPane.showMessageDialog(null, "Please included only digits in your bet");
    			txtfld_betPoint.setText("");
    		}
    		if(pointBet <=0){
    			JOptionPane.showMessageDialog(null, "Must bet more than 0");
    			txtfld_betPoint.setText("");
    		}
    		else{
    			money -= pointBet;
    			lbl_money.setText("" + money);
    			makePoint();
    		}
    	}
    	//if betdontPoint contains a value and betPoint is empty or 0
    	else if(txtfld_betDontPoint.getText().length() != 0 && (txtfld_betPoint.getText().length() == 0 || txtfld_betPoint.getText() == "0")){    		
    		try{
    			dontpointBet = Float.parseFloat(txtfld_betDontPoint.getText()); //    			
    		}catch(NumberFormatException nfe){
    			JOptionPane.showMessageDialog(null, "Please included only digits in your bet");
    			txtfld_betDontPoint.setText("");
    		}
    		if(dontpointBet <=0){
    			JOptionPane.showMessageDialog(null, "Must bet more than 0");
    			txtfld_betDontPoint.setText("");
    		}
    		else{
    			money -= dontpointBet;
    			lbl_money.setText("" + money);
    			makePoint();
    		}
    	}
    	
    	//else something I didn't plan for went wrong!
    	else{
    		JOptionPane.showMessageDialog(null, "Something I didn't plan for went wrong!");
    	}
    }    
    
    public void makePoint(){
    	JOptionPane.showMessageDialog(null, "Rolling for point");
    	roll1 = getD1();
    	roll2 = getD2();
    	
    	gamePanel = new JPanel(new BorderLayout());    	
    	message = "You rolled: " + getD1() + " & " + getD2() +" = " + getTotalRoll();
    	message_lbl = new JLabel(message);
    	message_lbl.setHorizontalAlignment(SwingConstants.CENTER);
    	gamePanel.add(message_lbl, BorderLayout.NORTH);    	
    	
    	DicePanel dp = new DicePanel();
    	dp.setPreferredSize(new Dimension(80, 30));
    	
    	gamePanel.add(dp, BorderLayout.SOUTH);
    	
    	JOptionPane.showMessageDialog(null, gamePanel, "Your Roll", JOptionPane.PLAIN_MESSAGE );
    	switch(getTotalRoll()){
    		case 2:
    		case 3:
    			//Craps, You lose, don't point wins
    			JOptionPane.showMessageDialog(null, "Craps, point loses!");
    			push = false; //don't point wins 
    			pointLoses();
    			break;
    		case 12:
    			//Craps, You lose, don't point is pushed
    			JOptionPane.showMessageDialog(null, "Craps, point loses, don't point is pushed!");
    			push = true; //don't point doesn't win
    			pointLoses();
    			break;
    		case 7:
    		case 11:
    			//Natural, point wins
    			JOptionPane.showMessageDialog(null, "Natural, point wins!");
    			pointWins();
    			break;
    		default:
    			//point is established
    			point = getTotalRoll();
    			JOptionPane.showMessageDialog(null, "You rolled point: " + point);
    			playPoint();
    			break;    				
    	}
    }
    
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
    		
    		JOptionPane.showMessageDialog(null, gamePanel, "Your Roll", JOptionPane.PLAIN_MESSAGE);
        	
    		if(getTotalRoll()==point){
    			JOptionPane.showMessageDialog(null, "Point wins");
    			playGame = false;
    			pointWins();
    		}else if(getTotalRoll() == 7){
    			JOptionPane.showMessageDialog(null, "Don't point wins");
    			playGame = false;
    			pointLoses();
    		}else{
    			JOptionPane.showMessageDialog(null, "No luck this time, roll again");
    			
    		}
    	}while(playGame == true);
    } 	
    public void pointLoses(){
    	if(push == true){
    		//point loses, don't point is pushed
    		house += pointBet;
    		money += dontpointBet;
    		lbl_money.setText(""+money);
    	}else{
    		//point loses, don't point wins
    		house += pointBet;
    		house -= dontpointBet*2;
    		money += dontpointBet*2;
    		lbl_money.setText(""+money);
    	}
    }
    
    public void pointWins(){
    	house -= pointBet*2;
    	money += pointBet*2;
    	lbl_money.setText(""+money);
    	house += dontpointBet;   	
    }
    
    public int getD1(){
    	d1.rollDice();
    	return d1.getRoll();
    }
    
    public int getD2(){
    	d2.rollDice();
    	return d2.getRoll();
    }
    
    public int getTotalRoll(){
    	int thisRoll = d1.getRoll()+d2.getRoll();
    	return thisRoll;
    } 
}

class DicePanel extends JPanel{
	public void paintComponent(Graphics g){		
		
		Image icon = new ImageIcon("C:\\Users\\maniacalbrain\\Desktop\\die"+CrapsGame.d1.getRoll()+".png").getImage();
		//Image icon = new ImageIcon("die"+CrapsGame.d1.getRoll()+".png").getImage();
		Image icon2 = new ImageIcon("C:\\Users\\maniacalbrain\\Desktop\\die"+CrapsGame.d2.getRoll()+".png").getImage();
		
		//width of JOptionPane is 242.
		g.drawImage(icon, 96, 0, 20, 20, this);
		g.drawImage(icon2, 126, 0, 20, 20, this);
	}
}