import java.io.*;
import java.util.ArrayList;

/**
 * ShowPlayers
 * <P> ShowPlayers is a utility class that was used in testing the projects serilization. It can be ran to get a list of all
 * players on the system and their money totals.
 * @author James Nagle<maniacalcorp@gmail.com>
 */


public class ShowPlayers {
	
	public static void main(String[] args){
		//Create array to hold the input array
		ArrayList<Player> playerArray = new ArrayList<Player>();
		//try with resources to load array from file to the player array
		try(ObjectInputStream ois = new ObjectInputStream(new FileInputStream(new File("players")))){
			playerArray = (ArrayList) ois.readObject();		
		} catch (FileNotFoundException e) {
			//Auto-generated catch block
			e.printStackTrace();
		} catch(EOFException e){
			//Do nothing, catches final null thrown when array is fully read
		}catch (IOException e) {
			//Auto-generated catch block
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			//Auto-generated catch block
			e.printStackTrace();
		} 
		//list all players
		for(Player plr : playerArray){
			System.out.println(""+plr.getUsername());
			System.out.println(""+plr.getMoney());
		}
	}

}
