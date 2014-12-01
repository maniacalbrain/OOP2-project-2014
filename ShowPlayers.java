import java.io.EOFException;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.util.ArrayList;


public class ShowPlayers {
	
	public static void main(String[] args){
		ArrayList<Player> playerArray = new ArrayList<Player>();
		try(ObjectInputStream ois = new ObjectInputStream(new FileInputStream(new File("players")))){
			playerArray = (ArrayList) ois.readObject();
			//Player player = null;
			//while((player=(Player)ois.readObject())!=null){
			//playerArray.add(player);
			//}			
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch(EOFException e){
			//Do nothing, catches final null thrown when array is fully read
		}catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} 
		
		for(Player plr : playerArray){
			System.out.println(""+plr.getUsername());
			System.out.println(""+plr.getMoney());
		}
	}

}
