import javax.swing.*;
import java.awt.Toolkit;
import java.awt.*;


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
		
		menuBar = new JMenuBar();
		setJMenuBar(menuBar);
		
		fileMenu = new JMenu("File");
		menuBar.add(fileMenu);
		
		newGameMenuItem = new JMenuItem("New Game");
		fileMenu.add(newGameMenuItem);
		profileMenuItem = new JMenuItem("Profile");
		fileMenu.add(profileMenuItem);
		quitMenuItem = new JMenuItem("Quit");
		fileMenu.add(quitMenuItem);
		
		rulesMenu = new JMenu("Rules");
		menuBar.add(rulesMenu);
		
		rulesMenuItem = new JMenuItem("Rules");
		rulesMenu.add(rulesMenuItem);
		
			
		Container cont = getContentPane();
		CrapsGame cg = new CrapsGame();
		cont.add(cg);
		//pack();
	}

}

//TODO Reference http://alvinalexander.com/blog/post/jfc-swing/how-center-jframe-java-swing