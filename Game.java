package socialIsland;

import edu.princeton.cs.algs4.Graph;
import edu.princeton.cs.algs4.In;

public class Game implements GameMethods {

	public static void main(String[] args) {

		Character[] characters = GameMethods.initializeCharacters(6);
		GameMethods.setEnvironment(characters);
		Graph g = new Graph(characters.length);

		GameMethods.checkForClicksCharacters(characters, g);
		// TODO
		// While loop checks for character clicks
		// trigger interaction
		

	}

}
