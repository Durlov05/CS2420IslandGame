package socialIsland;

import edu.princeton.cs.algs4.Graph;
import edu.princeton.cs.algs4.In;

public class Game implements GameMethods {

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
		// TODO
		// While loop checks for character clicks
		// trigger interaction
		

	}

}
