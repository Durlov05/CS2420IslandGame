package socialIsland;

import java.util.ArrayList;
import java.util.List;

import edu.princeton.cs.algs4.Graph;
import edu.princeton.cs.algs4.ST;
import edu.princeton.cs.algs4.StdDraw;
import edu.princeton.cs.algs4.StdOut;
import edu.princeton.cs.algs4.StdRandom;

/**
 * This interface defines methods related to setting up and interacting with a
 * game environment. It includes methods for initializing characters, drawing
 * the environment, checking mouse clicks, and handling interactions between
 * characters in the game.
 *
 * @author Eliza Kitchens & Syed Mujibur Rahman (Mujib)
 */

public interface GameMethods {
	/**
	 * The scale factor for the size of the houses.
	 */
	public double houseScale = 0.3;

	/**
	 * The size of the game screen.
	 */
	public int screenSize = 700;
	
	/**
	 * The Symbol Table used to store objects.
	 */
	
	

	/**
	 * Draws a green island window and arranges characters randomly. Initializes
	 * characters with names, images, and random locations.
	 *
	 * @param characters An array of Character objects representing the characters
	 *                   in the game.
	 */

	public static void setEnvironment(Character[] characters, List<Plant> plants, ST<String, GameObjects> objectsInGame) {

		// Set the game screen size
		StdDraw.setCanvasSize(screenSize, screenSize);

		// create the title
		StdDraw.setTitle("Social Island");

		// Set the background color to green grass
		StdDraw.clear(StdDraw.GREEN);
		StdDraw.picture(0.5, 0.5, "src/socialIsland/Resources/grass.png", 1, 1);

		// set a small border so houses aren't drawn near the edges
		StdDraw.setScale(-.05, 1.05);

		// Draw characters
		for (int i = 1; i < characters.length; i++) {

			StdDraw.picture(characters[i].getxCoordinate(), characters[i].getyCoordinate(), characters[i].getImage(),
					houseScale, houseScale);
		}

		// Draw the trees
		for (int k = 0; k < (plants.size() - 1); k++) {
			StdDraw.picture(plants.get(k).getxCoordinate(), plants.get(k).getyCoordinate(),
					"src/socialIsland/Resources/tree1.png", 0.10, 0.10);

		}
		
		// Draw the objects -- we didn't use a for loop because we only need to draw 2 items.
		
		GameObjects waterObject = objectsInGame.get("water");
		
		StdDraw.picture(waterObject.getxCoordinate(), waterObject.getyCoordinate(),
					"src/socialIsland/Resources/water.png", 0.10, 0.10);
		
		GameObjects presentObject = objectsInGame.get("present");
		
		StdDraw.picture(presentObject.getxCoordinate(), presentObject.getyCoordinate(),
				"src/socialIsland/Resources/present.png", 0.10, 0.10);
		
	}

	// optionals:
	// draw player character
	// draw frienship status
	// draw other island details

	/**
	 * Initializes characters with random coordinates and prevents them from being
	 * too close together. This method should be called before setEnvironment() in
	 * the main method.
	 *
	 * @param numCharacters The number of characters to initialize.
	 * @return An array of initialized Character objects.
	 */

	public static Character[] initializeCharacters(int numCharacters) {
		// construct characters (store in array of Character objects)
		Character[] characters = new Character[numCharacters];
		List<PointOnMap> houseCoordinates = new ArrayList<PointOnMap>();

		for (int i = 1; i < numCharacters; i++) {
			double distanceToHouse = 100.0;
			double distanceBtwHouses = 0.2;
			double x;
			double y;
			int count = 0;
			// Generate random coordinates for the button
			do {
				x = StdRandom.uniformDouble();
				y = StdRandom.uniformDouble();

				count++;

				// check that the distance between 2 points is greater than a specific value
				if (i > 1) {
					if (i == 2) {
						distanceToHouse = Math.sqrt(Math.pow((houseCoordinates.get(0).getX() - x), 2)
								+ Math.pow((houseCoordinates.get(0).getY() - y), 2));
						if (distanceToHouse < distanceBtwHouses && x < 0.75) {
							break;
						}
					}
					else {	
						for (int j = 0; j < houseCoordinates.size(); j++) {
							distanceToHouse = Math.sqrt(Math.pow((houseCoordinates.get(j).getX() - x), 2)
									+ Math.pow((houseCoordinates.get(j).getY() - y), 2));
							if (distanceToHouse < distanceBtwHouses) {
								// System.out.println("Distance Too Small.");
								break;
							}
	
						}
					}
				}

				if (count++ > 1000) {
					System.out.println("Count was exceeded.");
					break;
				}

			} while (distanceToHouse < distanceBtwHouses || (x > 0.5 && y < 0.27));

			PointOnMap houseLocation = new PointOnMap(x, y);
			houseCoordinates.add(houseLocation);

			characters[i] = new Character("House" + i, "src/socialIsland/Resources/house" + i + ".png", x, y, i);

		}
		// test print
		for (int j = 1; j < numCharacters; j++) {
			StdOut.println(characters[j]);
		}
		return characters;
	}

	/**
	 * Draws trees at random locations. Ensures that trees do not overlap with other
	 * houses.
	 * 
	 * @param characters
	 */
	public static List<Plant> initializePlants(Character[] characters) {
		List<Plant> trees = new ArrayList<Plant>();
		// the number of trees is the natural log of the number of characters
		int numTrees = ((int) Math.log(characters.length) + 10);
		for (int i = 0; i <= numTrees; i++) {
			double distanceToObj = 100.0;
			double distanceBtwObj = 0.15;
			double x;
			double y;
			int count = 0;
			// Generate random coordinates for the trees
			do {
				x = StdRandom.uniformDouble();
				y = StdRandom.uniformDouble();

				// how many times the points had to generated to get a point that wasn't too
				// close to a character
				count++;

				// check that the trees are not too close to the houses
				for (int j = 1; j < characters.length; j++) {
					distanceToObj = Math.sqrt(Math.pow((characters[j].getxCoordinate() - x), 2)
							+ Math.pow((characters[j].getyCoordinate() - y), 2));
					if (distanceToObj < distanceBtwObj) {
						// System.out.println("Distance Too Small.");
						break;
					}

				}

				if (count > 1000) {
					System.out.println("Count was exceeded.");
					break;
				}

			} while (distanceToObj < distanceBtwObj);

			Plant tree = new Plant(x, y);
			trees.add(tree);

		}
		return trees;
	}

	/**
	 * Re draws game environment. Serves as a way to "close" pop-up windows.
	 * 
	 * @param xCoordinates
	 * @param yCoordinates
	 * 
	 */
	public static void drawEnvironment(double[] xCoordinates, double[] yCoordinates, double[] treexCoordinates, 
			double[] treeyCoordinates, ST<String, GameObjects> objectsInGame) {
		// Set the game screen size
		StdDraw.setCanvasSize(screenSize, screenSize);

		// create the title
		StdDraw.setTitle("Social Island");

		// Set the background color to green
		StdDraw.clear(StdDraw.GREEN);
		StdDraw.picture(0.5, 0.5, "src/socialIsland/Resources/grass.png", 1, 1);

		// set a small border so houses aren't drawn near the edges
		StdDraw.setScale(-.05, 1.05);

		// redraw houses
		for (int i = 1; i < xCoordinates.length; i++) {
			StdDraw.picture(xCoordinates[i], yCoordinates[i], "src/socialIsland/Resources/house" + i + ".png",
					houseScale, houseScale);
		}
		
		//re draw the trees in the same location
		for (int i = 0; i < treexCoordinates.length; i++) {
			StdDraw.picture(treexCoordinates[i], treeyCoordinates[i], "src/socialIsland/Resources/tree1.png", 0.10, 0.10);
		}
		
		GameObjects waterObject = objectsInGame.get("water");
		
		// Draw the objects -- we didn't use a for loop because we only need to draw 2 items.
		if(waterObject.getCountofItem()==0) {
			
		
			StdDraw.picture(waterObject.getxCoordinate(), waterObject.getyCoordinate(),
					"src/socialIsland/Resources/water.png", 0.10, 0.10);
		}
		
		GameObjects presentObject = objectsInGame.get("present");
		
		if(presentObject.getCountofItem()==0) {
			
			
			StdDraw.picture(presentObject.getxCoordinate(), presentObject.getyCoordinate(),
					"src/socialIsland/Resources/present.png", 0.10, 0.10);
		}
		// TODO:
		//redraw trees
		// redraw friendship status

	}
	
	public static boolean isMouseOverObjectButtons(double mouseX, double mouseY, double ObjectX, double ObjectY) {
		// Check if the mouse is over the button
		double buttonWidthX = (0.8771 - 0.5958); // We determined the specific width by tracking our mouse clicks.
		double buttonHeightY = (0.06 - 0); // We determined the specific width by tracking our mouse clicks.
		// All buttons have the same width and height.

		// checks the 4 quadrants of the character image using the coordinate of image
		return mouseX >= ObjectX - buttonWidthX / 2 && mouseX <= ObjectX + buttonWidthX / 2 && mouseY >= ObjectY - buttonHeightY / 2
				&& mouseY <= ObjectY + buttonHeightY / 2;
	}
//	
//	public static String checkForClicksObjects (GameObjects[] gameobjects, Graph g, double[] xCoordinates,
//			double[] yCoordinates, double[] plantxCoordinates, double[] plantyCoordinates, ST<String, GameObjects> objectsInGame ) {
//		
//		boolean mousePressed = false;
//		while (true) {
//			mousePressed = StdDraw.isMousePressed();
//			if (mousePressed) {
//				double mouseX = StdDraw.mouseX();
//				double mouseY = StdDraw.mouseY();
//				System.out.println("Mouse Location: " + mouseX + ", " + mouseY);
//				// check if the coordinates of your mouse match the coordinates of a character
//				for (int i = 1; i < 3; i++) {
//					if (isMouseOverHouseButtons(mouseX, mouseY, gameobjects[i].getxCoordinate(),
//							gameobjects[i].getyCoordinate())) {
//						// PickedUpObject.
//						objectsInGame.put(, gameobjects[i]);
//					}
//				}
//				try {
//					Thread.sleep(1_000);
//				} catch (InterruptedException e) {
//					// TODO Auto-generated catch block
//					e.printStackTrace();
//				}
//
//				mousePressed = false;
////                if (!StdDraw.isMousePressed())
////                    break;
//			}
//		}
//		return "<> picked up.";
//	}
//	
	/**
	 * Checks if the mouse is positioned over a House button.
	 * 
	 * @param mouseX The x-coordinate of the mouse.
	 * @param mouseY The y-coordinate of the mouse.
	 * @param x      The x-coordinate of the button.
	 * @param y      The y-coordinate of the button.
	 * @return A boolean indicating whether the mouse is over the button.
	 */
	public static boolean isMouseOverHouseButtons(double mouseX, double mouseY, double x, double y) {
		// Check if the mouse is over the button
		double buttonSize = houseScale - 0.1; // houseScale seems to big for this.

		// checks the 4 quadrants of the character image using the coordinate of image
		return mouseX >= x - buttonSize / 2 && mouseX <= x + buttonSize / 2 && mouseY >= y - buttonSize / 2
				&& mouseY <= y + buttonSize / 2;
	}

	/**
	 * Checks if the mouse is positioned over a Response button.
	 * 
	 * @param mouseX The x-coordinate of the mouse.
	 * @param mouseY The y-coordinate of the mouse.
	 * @param x      The x-coordinate of the button.
	 * @param y      The y-coordinate of the button.
	 * @return A boolean indicating whether the mouse is over the button.
	 */

	public static boolean isMouseOverResponseButtons(double mouseX, double mouseY, double x, double y) {
		// Check if the mouse is over the button
		double buttonWidthX = (0.8771 - 0.5958); // We determined the specific width by tracking our mouse clicks.
		double buttonHeightY = (0.06 - 0); // We determined the specific width by tracking our mouse clicks.
		// All buttons have the same width and height.

		// checks the 4 quadrants of the character image using the coordinate of image
		return mouseX >= x - buttonWidthX / 2 && mouseX <= x + buttonWidthX / 2 && mouseY >= y - buttonHeightY / 2
				&& mouseY <= y + buttonHeightY / 2;
	}

	/**
	 * Handles interactions between characters based on mouse clicks.
	 *
	 * @param characters   An array of Character objects representing the characters
	 *                     in the game.
	 * @param g            The Graph representing character relationships.
	 * @param xCoordinates The x-coordinates of characters/houses.
	 * @param yCoordinates The y-coordinates of characters/houses.
	 */

	public static void checkForClicksCharacters(Character[] characters, Graph g, double[] xCoordinates,
			double[] yCoordinates, double[] plantxCoordinates, double[] plantyCoordinates, ST<String, GameObjects> objectsInGame) {
		// Continuous loop to check for mouse clicks
		boolean mousePressed = false;
		while (true) {
			mousePressed = StdDraw.isMousePressed();
			if (mousePressed) {
				double mouseX = StdDraw.mouseX();
				double mouseY = StdDraw.mouseY();
				mousePressed = false;
				System.out.println("Mouse Location: " + mouseX + ", " + mouseY);
				// check if the coordinates of your mouse match the coordinates of a character
				for (int i = 1; i < 6; i++) {
					if (isMouseOverHouseButtons(mouseX, mouseY, characters[i].getxCoordinate(),
							characters[i].getyCoordinate())) {
						g = interaction(i, g, xCoordinates, yCoordinates, plantxCoordinates, plantyCoordinates, objectsInGame);
					}	
				}
				
				try {
					Thread.sleep(250);
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				
				for (String key:objectsInGame.keys()) {
					
					GameObjects thisObjectClicked = objectsInGame.get(key);
			
					System.out.println("thisObjectClicked Key: " + key + " - Object: " + thisObjectClicked.toString());
					
					if (isMouseOverObjectButtons(mouseX, mouseY, thisObjectClicked.getxCoordinate(),
							thisObjectClicked.getyCoordinate())) {
						thisObjectClicked.setCountofItem(1);
						objectsInGame.put(key, thisObjectClicked);
						drawEnvironment(xCoordinates, yCoordinates, plantxCoordinates, plantyCoordinates, objectsInGame);
						for (String key2:objectsInGame.keys()) 
							System.out.println("All Keys Key: " + key2 + " - Object: " + objectsInGame.get(key2).toString());
					}
				}
				try {
					Thread.sleep(250);
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
//                if (!StdDraw.isMousePressed())
//                    break;
			}
			
			if (g.degree(0)==5) {
				StdDraw.picture(0.5, 0.5, "src/socialIsland/Resources/mayor.png", 1, 1);
				break;
			}
		}
	}

	/**
	 * Checks for mouse clicks on specific response buttons in the game window.
	 * 
	 * @return An integer representing the type of response button clicked: 1 for
	 *         positive response, 0 for neutral response, -1 for negative response,
	 *         0 if no valid response button is clicked.
	 */

	public static int checkForClicksButtons(int vertex, ST<String, GameObjects> objectsInGame) {
		// Continuous loop to check for mouse clicks
		boolean mousePressed = false;

		while (true) {
			mousePressed = StdDraw.isMousePressed();
			if (mousePressed) {
				double mouseX = StdDraw.mouseX();
				double mouseY = StdDraw.mouseY();
				System.out.println("Mouse Location: " + mouseX + ", " + mouseY);
				try {
					Thread.sleep(500);
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}

				if (isMouseOverResponseButtons(mouseX, mouseY, 0.73, 0.2171)) {
					mousePressed = false;  
					GameObjects waterObject = objectsInGame.get("water");
					GameObjects presentObject = objectsInGame.get("present");
					GameObjects pretzelObject = objectsInGame.get("pretzel");
					
					if (vertex == waterObject.getDestinationVertex() && waterObject.getCountofItem()==0)
						return 0; // neutral
					
					if (vertex == presentObject.getDestinationVertex() && presentObject.getCountofItem()==0)
						return 0; // neutral
					
					if (vertex == presentObject.getDestinationVertex() && presentObject.getCountofItem()>0)
						pretzelObject.setCountofItem(1);
						
					return 1; // positive
				} else if (isMouseOverResponseButtons(mouseX, mouseY, 0.73, 0.126)) {
					mousePressed = false;
					return 0; // neutral
				} else if (isMouseOverResponseButtons(mouseX, mouseY, 0.73, 0.0238)) {
					mousePressed = false;
					
					GameObjects pretzelObject = objectsInGame.get("pretzel");
					
					if (vertex == pretzelObject.getDestinationVertex() && pretzelObject.getCountofItem()==0)
						return 0; // neutral
					
					return -1; // negative
				} else {
					mousePressed = false;
					return 0;
				}
			}
		}

	}

	/**
	 * Method draws the interaction pop-up and interaction buttons.
	 * 
	 * @param i
	 */
	private static void openPopupWindow(int i) {

		double buttonScale = 0.5;

		switch (i) {
		case 1:
			StdDraw.picture(0.75, 0.50, "src/socialIsland/Resources/house1Question.png", .5, .5);
			StdDraw.pause(3_000);
			StdDraw.picture(0.73, 0.2, "src/socialIsland/Resources/I would love to be your friend.png", buttonScale,
					buttonScale);
			StdDraw.picture(0.73, 0.1, "src/socialIsland/Resources/not right now.png", buttonScale, buttonScale);
			StdDraw.picture(0.73, 0.00, "src/socialIsland/Resources/Nope.png", buttonScale, buttonScale);

			break;
		case 2:
			StdDraw.picture(0.75, 0.50, "src/socialIsland/Resources/house2Question.png", .5, .5);
			StdDraw.pause(3_000);
			StdDraw.picture(0.73, 0.2, "src/socialIsland/Resources/Do you want some of my water.png", buttonScale,
					buttonScale);
			StdDraw.picture(0.73, 0.1, "src/socialIsland/Resources/You need water..png", buttonScale, buttonScale);
			StdDraw.picture(0.73, 0.00, "src/socialIsland/Resources/have a pretzel.png", buttonScale, buttonScale);
			break;
		case 3:
			StdDraw.picture(0.75, 0.50, "src/socialIsland/Resources/house3Question.png", .5, .5);
			StdDraw.pause(3_000);
			StdDraw.picture(0.73, 0.2, "src/socialIsland/Resources/Thanks for the cake.png", buttonScale,
					buttonScale);
			StdDraw.picture(0.73, 0.1, "src/socialIsland/Resources/not right now.png", buttonScale, buttonScale);
			StdDraw.picture(0.73, 0.00, "src/socialIsland/Resources/I don't like cake.png", buttonScale,
					buttonScale);
			break;
		case 4:
			StdDraw.picture(0.75, 0.50, "src/socialIsland/Resources/house4Question.png", .5, .5);
			StdDraw.pause(3_000);
			StdDraw.picture(0.73, 0.2, "src/socialIsland/Resources/I have an extra present you can have!.png",
					buttonScale, buttonScale);
			StdDraw.picture(0.73, 0.1, "src/socialIsland/Resources/You need to get her a present.png", buttonScale,
					buttonScale);
			StdDraw.picture(0.73, 0.00, "src/socialIsland/Resources/no presents this year.png", buttonScale,
					buttonScale);
			break;
		case 5:
			StdDraw.picture(0.75, 0.50, "src/socialIsland/Resources/house5Question.png", .5, .5);
			StdDraw.pause(3_000);
			StdDraw.picture(0.73, 0.2, "src/socialIsland/Resources/Once upon a time....png", buttonScale, buttonScale);
			StdDraw.picture(0.73, 0.1, "src/socialIsland/Resources/I'll be back to tell you a story soon.png",
					buttonScale, buttonScale);
			StdDraw.picture(0.73, 0.00, "src/socialIsland/Resources/no way.png", buttonScale, buttonScale);
			break;

		}

		StdDraw.show();

	}

	/**
	 * Represents an interaction between characters based on a pop-up window.
	 *
	 * @param vertex       The vertex/character involved in the interaction.
	 * @param g            The Graph representing character relationships.
	 * @param xCoordinates The x-coordinates of characters/houses.
	 * @param yCoordinates The y-coordinates of characters/houses.
	 */

	public static Graph interaction(int vertex, Graph g, double[] xCoordinates, double[] yCoordinates, double[] plantxCoordinates, 
			double[] plantyCoordinates, ST<String, GameObjects> objectsInGame) {
		// draw rectangle, question dialogue, 3 buttons
		openPopupWindow(vertex);
		// detect clicks while loop

		int mouseReturned = checkForClicksButtons(vertex, objectsInGame);
		// If we don't put this in a variable, I think, this needs to run twice in the
		// if else
		// statement.

		if (mouseReturned == 1) { // Positive case
			// creates friendship edge
			boolean isAdj = false;
			for (int v : g.adj(0)) {
				if (v == vertex) {
					isAdj = true;
				}
			}
			if (!isAdj) {
				g.addEdge(0, vertex);
			}
			// erases buttons
			drawEnvironment(xCoordinates, yCoordinates, plantxCoordinates, plantyCoordinates, objectsInGame);
			// displays response
			StdDraw.picture(0.75, 0.50, "src/socialIsland/Resources/house" + vertex + "PosResponse.png", .5, .5);
			StdDraw.pause(3_000);
			// displays island
			drawEnvironment(xCoordinates, yCoordinates, plantxCoordinates, plantyCoordinates, objectsInGame);
		} else if (mouseReturned == -1) { // Negative case
			// creates friendship edge
			boolean isAdj = false;
			for (int v : g.adj(0)) {
				if (v == vertex) {
					isAdj = true;
				}
			}
			if (isAdj) {
				Graph newG = RemoveEdge.RemoveEdgeMethod(g, 0, vertex);
				g = newG;
			}

			// erases buttons
			drawEnvironment(xCoordinates, yCoordinates, plantxCoordinates, plantyCoordinates, objectsInGame);
			// displays response
			StdDraw.picture(0.75, 0.50, "src/socialIsland/Resources/Nope.png", .5, .5);
			StdDraw.pause(1_000);
			// displays island
			drawEnvironment(xCoordinates, yCoordinates, plantxCoordinates, plantyCoordinates, objectsInGame);
		} else { // Neutral case
					// erases buttons
			drawEnvironment(xCoordinates, yCoordinates, plantxCoordinates, plantyCoordinates, objectsInGame);
			// displays response
			StdDraw.pause(1_000);
		}
		// test print graph adjacency lists
		StdOut.println("Vertex: " + vertex);
		StdOut.println("Adjacency List: ");
		StdOut.println("----------------");
		// print adjacency list
		for (int v = 0; v < g.V(); v++) {
			StdOut.print(v + ": ");
			// make the first value not start with " ->"
			boolean first = true;
			// the method adj() will return adjacent vertices in depth-first order
			for (int i : g.adj(v)) {
				if (!first) {
					StdOut.print(" -> ");
				}
				StdOut.print(i);
				first = false; // this is set directly after the first value is printed

			}
			StdOut.println();
		}
		return g;

		// switch:
		// 1. Positive: add friendship, potentially add item to queue
		// 2. Neutral: "Close window"
		// 3. Optional: Negative: if (edge exists) delete edge, redraw original board
		// drawEnvironment()
	}
}