package socialIsland;

import edu.princeton.cs.algs4.Graph;
import edu.princeton.cs.algs4.In;

public class Game implements GameMethods {

	/**
	 * This is the main Game method that implements GameMethods and calls the 
	 * functions to set up the environment and handle gameplay.
	 * 
	 * @author Eliza Kitchens & Syed Mujibur Rahman (Mujib)
	 */
	
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
