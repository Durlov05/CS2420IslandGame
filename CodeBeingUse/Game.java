package socialIsland;

import edu.princeton.cs.algs4.Graph;

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
		double [] xCoordinates = new double[characters.length];
		double [] yCoordinates = new double[characters.length];
		for (int i = 1; i <characters.length; i++ ) {
			xCoordinates[i] = characters[i].getxCoordinate();
			yCoordinates[i] = characters[i].getyCoordinate();
		}
	
		Graph g = new Graph(characters.length);

		GameMethods.checkForClicksCharacters(characters, g, xCoordinates, yCoordinates);
		
		

	}

}


