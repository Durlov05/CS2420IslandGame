package socialIsland;

import edu.princeton.cs.algs4.Graph;

/**
 * The Game class implements GameMethods and orchestrates the setup of the game
 * environment and handles gameplay mechanics.
 * 
 * @author Eliza Kitchens & Syed Mujibur Rahman (Mujib)
 */

public class Game implements GameMethods {

	/**
	 * The main method initiates the game by initializing characters, setting up the
	 * environment, and gameplay.
	 */

	public static void main(String[] args) {

		Character[] characters = GameMethods.initializeCharacters(6);
		GameMethods.setEnvironment(characters);

		double[] xCoordinates = new double[characters.length];
		double[] yCoordinates = new double[characters.length];

		for (int i = 1; i < characters.length; i++) {
			xCoordinates[i] = characters[i].getxCoordinate();
			yCoordinates[i] = characters[i].getyCoordinate();
		}

		Graph g = new Graph(characters.length);

		GameMethods.checkForClicksCharacters(characters, g, xCoordinates, yCoordinates);

	}

}
