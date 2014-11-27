import javax.swing.*;
import java.awt.Toolkit;
import java.awt.*;
import java.awt.event.*;


public class CrapsGameDriver extends JFrame{
	private static final int frameWidth = 300;
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
	
//	private String
	
	
	
	public static void main(String[] args){
		CrapsGameDriver cgd = new CrapsGameDriver();	
		cgd.setVisible(true);
	}
	
	public CrapsGameDriver(){
		setTitle("Craps Game");
		setSize(frameWidth, frameHeight);
		Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		setLocation(((screenSize.width/2)-(frameWidth/2)), ((screenSize.height/2)-(frameHeight/2)));
		
		//Get a reference to the ContentPane
		Container cont = getContentPane();
		//Create a panel to hold all of the cards
		mainDriverPanel = new JPanel(new CardLayout());
		//Create a reference to the cardlayout
		cardLayout = (CardLayout)(mainDriverPanel.getLayout());
		
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
		
		//Create the Profile Item
		profileMenuItem = new JMenuItem("Profile");
		newGameMenuItem.addActionListener(new MenuClicked());
		fileMenu.add(profileMenuItem);
		
		//Create the Quit Item
		quitMenuItem = new JMenuItem("Quit");
		//quitMenuItem.addActionListener(new QuitListener); //TODO create QuitListener
		fileMenu.add(quitMenuItem);
		
		//Create the Rules Menu
		rulesMenu = new JMenu("Rules");
		menuBar.add(rulesMenu);
		
		//Create the 		
		JPanel rulesPanel = new JPanel();
		rulesPanel.add(new JLabel("Random Text"));
		mainDriverPanel.add(rulesPanel, "Rules");
		
		rulesMenuItem = new JMenuItem("Rules");	
		rulesMenuItem.addActionListener(new MenuClicked());				
		rulesMenu.add(rulesMenuItem);
		
		//Create a new Craps Game and add to mainPanel
		CrapsGame cg = new CrapsGame();
		mainDriverPanel.add(cg, "New Game");
		
		//Add New Game to the Panel when the panel is first created
		cardLayout.show(mainDriverPanel, "New Game");
		
		//Add the mainPanel to the ContentPane reference
		cont.add(mainDriverPanel);
	}
	
	
	
	class MenuClicked implements ActionListener{
		public void actionPerformed(ActionEvent e){
			cardLayout.show(mainDriverPanel, e.getActionCommand());
		}
	}

}

//TODO Reference http://alvinalexander.com/blog/post/jfc-swing/how-center-jframe-java-swing
//TODO Reference http://www.math.uni-hamburg.de/doc/java/tutorial/uiswing/layout/card.html

