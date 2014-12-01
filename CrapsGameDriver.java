import java.awt.*;
import java.awt.event.*;
import java.io.*;
import java.util.ArrayList;
import javax.swing.*;


public class CrapsGameDriver extends JFrame{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
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
	private JPanel loginPage;
	private JPanel signupPage;
	private JPanel profilePanel;
	private JPanel rulesPanel;
	private JLabel header;
	
	ArrayList<Player> playerArray = new ArrayList<Player>();
	Player player = null;
	CrapsGame cg;
	
	
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
		setDefaultCloseOperation(DO_NOTHING_ON_CLOSE);
		WindowListener exitListener = new WindowAdapter() {

            @Override
            public void windowClosing(WindowEvent e) {
            	Object[] options = {"Yes", "No"};
    			int n = JOptionPane.showOptionDialog(null, "Are you sure you want to quit?",
    					"Quit?", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE, null, options, options[0]);
    			if(n == 0){
    				try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(new File("players")))){
    					oos.writeObject(playerArray);
    					
    				} catch (FileNotFoundException e1) {
    					// Auto-generated catch block
    					e1.printStackTrace();
    				} catch (IOException e2) {
    					// Auto-generated catch block
    					e2.printStackTrace();
    				}

    				System.exit(0);
    			}else{
    				
    			}
            }
        };
        addWindowListener(exitListener);
		
		
		
		
		//load up the players
		try(ObjectInputStream ois = new ObjectInputStream(new FileInputStream(new File("players")))){
			playerArray = (ArrayList) ois.readObject();
		} catch (FileNotFoundException e) {
			//Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			//Auto-generated catch block
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			//Auto-generated catch block
			e.printStackTrace();
		}


		//Get a reference to the ContentPane
		Container cont = getContentPane();
		//Create a panel to hold all of the cards
		mainDriverPanel = new JPanel(new CardLayout());
		//Create a reference to the cardlayout
		cardLayout = (CardLayout)(mainDriverPanel.getLayout());
				
		//create and display login panel. This will be the first panel the user sees
		loginPanel = createLoginPanel();
		mainDriverPanel.add(loginPanel, "Login");
		cardLayout.show(mainDriverPanel, "Login");	
		
		//Create and add the login page
		loginPage = createLogin();
		mainDriverPanel.add(loginPage, "Login Page");
		
		//Create and add the signup page
		signupPage = createSignup();
		mainDriverPanel.add(signupPage, "Signup Page");
		
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
		
		
		//Add the mainPanel to the ContentPane reference
		cont.add(mainDriverPanel);
		
	}
	
	//Creating LoginPanel, first panel shown to the user
	public JPanel createLoginPanel(){
		panel = new JPanel(new GridLayout(0,1));
		JButton login = new JButton("Login");
		login.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e){
				cardLayout.show(mainDriverPanel, "Login Page");
			}
		});
		panel.add(login);
		
		JButton signup = new JButton("Sign Up");
		signup.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e){
				cardLayout.show(mainDriverPanel, "Signup Page");
			}
		});
		panel.add(signup);
		return panel;
	}
	
	//method for creating the Profile Panel
	public JPanel createProfile(){
		panel = new JPanel();
					
			JLabel lbl_profile = new JLabel("Username");
			JLabel lbl_profile_username = new JLabel(player.getUsername());
			JLabel lbl_profile2 = new JLabel("Money");
			JLabel lbl_profile_money = new JLabel(""+player.getMoney());
			panel.add(lbl_profile);
			panel.add(lbl_profile_username);
			panel.add(lbl_profile2);
			panel.add(lbl_profile_money);			
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
	
	public JPanel createLogin(){
		panel = new JPanel(new FlowLayout(FlowLayout.LEFT));
		JLabel lbl_login = new JLabel("Login");
		JTextField txtfld_login = new JTextField(20);
		JButton btn_login = new JButton("Login");
		btn_login.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e){
				for(Player plr : playerArray){
					if(plr.getUsername().equals(txtfld_login.getText())){
						player = plr;
						cg = new CrapsGame(player);
						mainDriverPanel.add(cg, "New Game");
						cardLayout.show(mainDriverPanel, "New Game");
						break;
					}
				}
				if (player == null){
					//TODO reference http://docs.oracle.com/javase/tutorial/uiswing/components/dialog.html
					Object[] options = {"Try again", "Signup"};
					int n = JOptionPane.showOptionDialog(null, "That player does not exist, please sign up or use a different name",
							"Error", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE, null, options, options[0]);
					if(n == 0){
						cardLayout.show(mainDriverPanel, "Login Page");
					}else{
						cardLayout.show(mainDriverPanel, "Signup Page");
					}					
				}
			}
		});
		panel.add(lbl_login);
		panel.add(txtfld_login);
		panel.add(btn_login);
		return panel;
	}
	
	public JPanel createSignup(){
		panel = new JPanel(new FlowLayout(FlowLayout.LEFT));
		JLabel lbl_signup = new JLabel ("Username");
		JTextField txtfld_signup = new JTextField(20);
		JButton btn_signup = new JButton("Signup");
		btn_signup.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e){
				
				player = new Player(txtfld_signup.getText());
				playerArray.add(player);
				cg = new CrapsGame(player);
				mainDriverPanel.add(cg, "New Game");
				cardLayout.show(mainDriverPanel, "New Game");
			}
		});
		panel.add(lbl_signup);
		panel.add(txtfld_signup);
		panel.add(btn_signup);
		return panel;
	}
	
	//ActionListener called for any Menu Item click bar Quit
	class MenuClicked implements ActionListener{
		public void actionPerformed(ActionEvent e){
			//If the user tries to start a new game before logining prompt the user to login
			if (e.getActionCommand() == "New Game" && player == null){
				JOptionPane.showMessageDialog(null, "Must login first");
				cardLayout.show(mainDriverPanel, "Login");
			}
			//If the user tries to view profile before logining in prompt the user to login in
			else if (e.getActionCommand() == "Profile" && player == null){
				JOptionPane.showMessageDialog(null, "Must login first");
				cardLayout.show(mainDriverPanel, "Login");
			}
			//If the user tries to view profile after logging in create that profile and display it to them
			else if (e.getActionCommand() == "Profile" && player != null){
				profilePanel = createProfile();
				mainDriverPanel.add(profilePanel, "Profile");
				cardLayout.show(mainDriverPanel, "Profile");
			}			
			else
			cardLayout.show(mainDriverPanel, e.getActionCommand());
		}
	}
	
	//ActionListener called for the Quit menu Item
	class QuitListener implements ActionListener{
		public void actionPerformed(ActionEvent e){
			//TODO reference http://docs.oracle.com/javase/tutorial/uiswing/components/dialog.html
			Object[] options = {"Yes", "No"};
			int n = JOptionPane.showOptionDialog(null, "Are you sure you want to quit?",
					"Quit?", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE, null, options, options[0]);
			if(n == 0){
				try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(new File("players")))){
					oos.writeObject(playerArray);
					
				} catch (FileNotFoundException e1) {
					// Auto-generated catch block
					e1.printStackTrace();
				} catch (IOException e2) {
					// Auto-generated catch block
					e2.printStackTrace();
				}

				System.exit(0);
			}else{
				
			}
		}
	}

}

//TODO Reference http://alvinalexander.com/blog/post/jfc-swing/how-center-jframe-java-swing
//TODO Reference http://www.math.uni-hamburg.de/doc/java/tutorial/uiswing/layout/card.html
//TODO Reference https://docs.oracle.com/javase/tutorial/uiswing/layout/visual.html

