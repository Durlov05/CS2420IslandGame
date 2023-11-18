package socialIsland;

public class Game implements GameMethods {

	public static void main(String[] args) {
		
		Character[] characters = GameMethods.initializeCharacters(6);
		GameMethods.setEnvironment(characters);
		
		GameMethods.checkForClicksCharacters(characters);
		// TODO 
		//While loop checks for character clicks
		//trigger interaction
		

	}

}
