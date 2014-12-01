import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.text.DecimalFormat;

/**
 * CrapsGame Object
 * 
 * @author James Nagle<maniacalcorp@gmail.com>
 *
 */
public class CrapsGame extends JPanel{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
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
	
	DecimalFormat df = new DecimalFormat("#0.##");
	
	JLabel lbl_money;
	JLabel lbl_betPoint;
	JTextField txtfld_betPoint;
	JLabel lbl_betDontPoint;
	JTextField txtfld_betDontPoint;
	JButton rollPoint;
	
	JPanel gamePanel;	
	String message;
	JLabel message_lbl;
	Player player;

    public CrapsGame(Player player) {
    	this.player = player;
    	d1 = new Dice();
    	d2 = new Dice();
    	JPanel mainFrame = new JPanel(new BorderLayout());
    	JPanel crapsPane = new JPanel(new GridLayout(0,2)); //TODO Sort out layout!!
    	JPanel buttonPane = new JPanel(new FlowLayout());
    	
    	money = player.getMoney();
    	//TODO sort out name
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
    	
    	//else something I didn't plan for went wrong!
    	else{
    		JOptionPane.showMessageDialog(this, "Something I didn't plan for went wrong!");
    	}
    }    
    
    public void makePoint(){
    	JOptionPane.showMessageDialog(this, "Rolling for point");
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
    	
    	JOptionPane.showMessageDialog(this, gamePanel, "Your Roll", JOptionPane.PLAIN_MESSAGE );
    	switch(getTotalRoll()){
    		case 2:
    		case 3:
    			//Craps, You lose, don't point wins
    			JOptionPane.showMessageDialog(this, "Craps, point loses!");
    			push = false; //don't point wins 
    			pointLoses();
    			break;
    		case 12:
    			//Craps, You lose, don't point is pushed
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
    public void pointLoses(){
    	if(push == true){
    		//point loses, don't point is pushed
    		house += pointBet;
    		money += dontpointBet;
    		lbl_money.setText("Money: "+money);
    		player.setMoney(money);
    		txtfld_betPoint.setText("");
    		txtfld_betDontPoint.setText("");
    	}else{
    		//point loses, don't point wins
    		house += pointBet;
    		house -= dontpointBet*2;
    		money += dontpointBet*2;
    		lbl_money.setText("Money: "+ df.format(money));
    		player.setMoney(money);
    		txtfld_betPoint.setText("");
    		txtfld_betDontPoint.setText("");
    	}
    }
    
    public void pointWins(){
    	house -= pointBet*2;
    	money += pointBet*2;
    	lbl_money.setText("Money: "+ df.format(money));
    	player.setMoney(money);
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
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public void paintComponent(Graphics g){	
		
		//TODO Reference images https://openclipart.org/user-detail/orsonj
		
		Image icon = new ImageIcon("dice\\die"+CrapsGame.d1.getRoll()+".png").getImage();
		Image icon2 = new ImageIcon("dice\\die"+CrapsGame.d2.getRoll()+".png").getImage();
		
		//width of JOptionPane is 242.
		g.drawImage(icon, 96, 0, 20, 20, this);
		g.drawImage(icon2, 126, 0, 20, 20, this);
	}
}