import javax.swing.*;

import java.awt.*;
import java.awt.event.*;
import java.io.IOException;


public class CrapsGameDriver extends JFrame{
	private static final int frameWidth = 310;
	private static final int frameHeight = 200;
	private JMenuBar menuBar;
	private JMenu fileMenu;
	private JMenu rulesMenu;
	private JMenuItem newGameMenuItem;
	private JMenuItem profileMenuItem;
	private JMenuItem quitMenuItem;
	private JMenuItem rulesMenuItem;
	private JPanel mainDriverPanel;
	private CardLayout cardLayout;
	private JPanel panel;
	private JPanel loginPanel;
	private JPanel profilePanel;
	private JPanel rulesPanel;
	private JLabel header;
	
	
	public static void main(String[] args){
		CrapsGameDriver cgd = new CrapsGameDriver();	
		cgd.setVisible(true);
	}
	
	public CrapsGameDriver(){
		//Create screen, set title, size and location
		setTitle("Craps Game");
		setSize(frameWidth, frameHeight);
		setResizable(false);
		Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
		setLocation(((screenSize.width/2)-(frameWidth/2)), ((screenSize.height/2)-(frameHeight/2)));
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		
		//Get a reference to the ContentPane
		Container cont = getContentPane();
		//Create a panel to hold all of the cards
		mainDriverPanel = new JPanel(new CardLayout());
		//Create a reference to the cardlayout
		cardLayout = (CardLayout)(mainDriverPanel.getLayout());
		
		//create and display login panel
		loginPanel = createLoginPanel();
		mainDriverPanel.add(loginPanel, "Login");
		cardLayout.show(mainDriverPanel, "Login");
		
		//TODO creat login and sign up cards
		
		//Create and set menubar
		menuBar = new JMenuBar();
		setJMenuBar(menuBar);
		
		//Create and add the File Menu
		fileMenu = new JMenu("File");
		menuBar.add(fileMenu);
		
		//Create the File Item
		newGameMenuItem = new JMenuItem("New Game");
		newGameMenuItem.addActionListener(new MenuClicked());		
		fileMenu.add(newGameMenuItem);		
		
		//Create the Profile Item, profile panel and add it to the cardlayout
		profileMenuItem = new JMenuItem("Profile");
		profileMenuItem.addActionListener(new MenuClicked());
		profilePanel = createProfile();
		mainDriverPanel.add(profilePanel, "Profile");
		fileMenu.add(profileMenuItem);
		
		//Create the Quit Item
		quitMenuItem = new JMenuItem("Quit");
		quitMenuItem.addActionListener(new QuitListener()); 
		fileMenu.add(quitMenuItem);
		
		//Create the Rules Menu
		rulesMenu = new JMenu("Rules");
		menuBar.add(rulesMenu);
		
		//Create the Rules Item, rules panel and add it to the cardlayout	
		rulesMenuItem = new JMenuItem("Rules");	
		rulesMenuItem.addActionListener(new MenuClicked());	
		rulesPanel = createRules(); 
		mainDriverPanel.add(rulesPanel, "Rules");
		rulesMenu.add(rulesMenuItem);
		
		//Create a new Craps Game and add to mainPanel
		CrapsGame cg = new CrapsGame();
		mainDriverPanel.add(cg, "New Game");
		
		//Add New Game to the Panel when the panel is first created
		//TODO cardLayout.show(mainDriverPanel, "New Game");
		
		//Add the mainPanel to the ContentPane reference
		cont.add(mainDriverPanel);
	}
	
	public JPanel createLoginPanel(){
		panel = new JPanel(new GridLayout(0,1));
		panel.add(new JButton("Login"));
		panel.add(new JButton("Sign up"));
		return panel;
	}
	
	//method for creating the Profile Panel
	public JPanel createProfile(){
		panel = new JPanel(new FlowLayout(FlowLayout.LEFT));
		panel.add(new JLabel("Hello"));
		return panel;
	}
	
	//method for creating the Rules Panel
	public JPanel createRules(){
		panel = new JPanel(new GridLayout(0,1));
		header = new JLabel("Rules of Craps");
		header.setHorizontalAlignment(SwingConstants.CENTER);
		panel.add(header);
		panel.add(new JLabel("-Place a bet and roll for point."));
		panel.add(new JLabel("-On a roll of 2 or 3 don't point wins. don't point is"));
		panel.add(new JLabel(" pushed on a 12."));
		panel.add(new JLabel("-On a 7 or 11 point wins."));
		panel.add(new JLabel("-Any other number is \"point\". Roll that number"));
		panel.add(new JLabel(" again and point wins, roll a 7 and don't point wins."));
		return panel;
		
	}
	
	//ActionListener called for any Menu Item click bar Quit
	class MenuClicked implements ActionListener{
		public void actionPerformed(ActionEvent e){
			//if e.getActionCommand == "New Game" and Player is null - must have a player 
			cardLayout.show(mainDriverPanel, e.getActionCommand());
		}
	}
	
	//ActionListener called for the Quit menu Item
	class QuitListener implements ActionListener{
		public void actionPerformed(ActionEvent e){
			JOptionPane.showMessageDialog(null, "Are you sure you want to quit?");
		}
	}

}

//TODO Reference http://alvinalexander.com/blog/post/jfc-swing/how-center-jframe-java-swing
//TODO Reference http://www.math.uni-hamburg.de/doc/java/tutorial/uiswing/layout/card.html
//TODO Reference https://docs.oracle.com/javase/tutorial/uiswing/layout/visual.html

