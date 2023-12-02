package socialIsland;


import java.util.List;

import edu.princeton.cs.algs4.Graph;
import edu.princeton.cs.algs4.ST;

/**
 * The Game class implements GameMethods and orchestrates the setup of the game
 * environment and handles game play mechanics.
 * 
 * @author Eliza Kitchens & Syed Mujibur Rahman (Mujib)
 */

public class Game implements GameMethods {

	/**
	 * The main method initiates the game by initializing characters and setting up the
	 * environment. Then it implements game play.
	 */

	public static void main(String[] args) {

		ST<String, GameObjects> objectsInGame = new ST<String, GameObjects>(); 
		Character[] characters = GameMethods.initializeCharacters(6);
		List<Plant> plants = GameMethods.initializePlants(characters);
		
		double[] xCoordinates = new double[characters.length];
		double[] yCoordinates = new double[characters.length];
		
		//store x and y coordinates of the tree locations for use in the game play methods
		double [] plantxCoordinates = new double[plants.size()];
		double [] plantyCoordinates = new double[plants.size()];
		
		//fill character coordinate arrays
		for (int i = 1; i < characters.length; i++) {
			xCoordinates[i] = characters[i].getxCoordinate();
			yCoordinates[i] = characters[i].getyCoordinate();
		}
		
		//fill plant coordinate arrays
		for (int i = 1; i < plants.size(); i++) {
					plantxCoordinates[i] = plants.get(i).getxCoordinate();
					plantyCoordinates[i] = plants.get(i).getyCoordinate();
				}
		
		objectsInGame.put("water", new GameObjects(xCoordinates[1], yCoordinates[1]-0.2, 2, 0)); 
		objectsInGame.put("present", new GameObjects(xCoordinates[2]+0.2, yCoordinates[2], 4, 0));
		objectsInGame.put("pretzel", new GameObjects(xCoordinates[3]+0.1, yCoordinates[3], 2, 0));
		
		GameMethods.setEnvironment(characters, plants, objectsInGame);

		//store x and y coordinates of the character locations for use in game play methods
		
		
		Graph g = new Graph(characters.length);

		GameMethods.checkForClicksCharacters(characters, g, xCoordinates, yCoordinates, plantxCoordinates, plantyCoordinates, 
				objectsInGame);

	}


}
