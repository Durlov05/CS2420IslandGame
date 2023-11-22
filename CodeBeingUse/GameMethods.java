package socialIsland;

import edu.princeton.cs.algs4.Graph;
import edu.princeton.cs.algs4.StdDraw;
import edu.princeton.cs.algs4.StdOut;
import edu.princeton.cs.algs4.StdRandom;

public interface GameMethods {
	
	/**
	 * Draws a green island window and the characters (houses and animals) in a
	 * random arrangement. Initializes the characters with a name, image, and random
	 * location.
	 * 
	 * @author Eliza Kitchens & Syed Mujibur Rahman (Mujib)
	 */
	public static void setEnvironment(Character[] characters) {

		// Set the game screen size
		StdDraw.setCanvasSize(800, 800);

		// create the title
		StdDraw.setTitle("Social Island");

		// Set the background color to green
		StdDraw.clear(StdDraw.GREEN);

		// set a small border so houses aren't drawn near the edges
		StdDraw.setScale(-.05, 1.05);

		// Draw characters
		for (int i = 1; i < characters.length; i++) {

			StdDraw.picture(characters[i].getxCoordinate(), characters[i].getyCoordinate(), characters[i].getImage(),
					0.07, 0.07);
		}
	}

	// optionals:
	// draw player character
	// draw frienship status
	// draw other island details

	public static Character[] initializeCharacters(int numCharacters) {
		// construct characters (store in array of Character objects)
		Character[] characters = new Character[numCharacters];
		for (int i = 1; i < numCharacters; i++) {
			// Generate random coordinates for the button
			double x = StdRandom.uniformDouble();
			double y = StdRandom.uniformDouble();

			// TODO: add x and y to a hashset to prevent duplicates

			characters[i] = new Character("House" + i, "src/socialIsland/Resources/house" + i + ".jpeg", x, y, i);
			// test print
			for (int j = 1; j < numCharacters; j++) {
				StdOut.println(characters[j]);
			}
		}
		return characters;
	}

	public static void drawEnvironment(double[] xCoordinates, double[] yCoordinates) {
		// Set the game screen size
		StdDraw.setCanvasSize(800, 800);

		// create the title
		StdDraw.setTitle("Social Island");
		
		// Set the background color to green
		StdDraw.clear(StdDraw.GREEN);
		
		// set a small border so houses aren't drawn near the edges
		StdDraw.setScale(-.05, 1.05);
		
		// redraw houses
		for (int i = 1; i < xCoordinates.length; i++) {
		StdDraw.picture(xCoordinates[i], yCoordinates[i], "src/socialIsland/Resources/house" + i + ".jpeg", 0.07, 0.07);
		}

		// TODO:
		// redraw friendship status

	}

	/**
	 * Checks if the mouse is positioned over a button.
	 * 
	 * @param mouseX
	 * @param mouseY
	 * @param x
	 * @param y
	 * @return
	 */
	public static boolean isMouseOverButton(double mouseX, double mouseY, double x, double y) {
		// Check if the mouse is over the button
		double buttonSize = 0.07;

		// checks the 4 quadrants of the character image using the coordinate of image
		return mouseX >= x - buttonSize / 2 && mouseX <= x + buttonSize / 2 && mouseY >= y - buttonSize / 2
				&& mouseY <= y + buttonSize / 2;
	}

	public static void checkForClicksCharacters(Character[] characters, Graph g, double[] xCoordinates, double[] yCoordinates ) {
		// Continuous loop to check for mouse clicks
		while (true) {
			if (StdDraw.isMousePressed()) {
				double mouseX = StdDraw.mouseX();
				double mouseY = StdDraw.mouseY();

				// check if the coordinates of your mouse match the coordinates of a character
				for (int i = 1; i < 6; i++) {
					if (isMouseOverButton(mouseX, mouseY, characters[i].getxCoordinate(),
							characters[i].getyCoordinate())) {
						interaction(i, g, xCoordinates, yCoordinates);

					}
				}
			}
		}
	}

	public static int checkForClicksButtons() {
		// Continuous loop to check for mouse clicks
		while (true) {
			if (StdDraw.isMousePressed()) {
				double mouseX = StdDraw.mouseX();
				double mouseY = StdDraw.mouseY();

				// TODO: create custom isMouseOverButton for this
				if (isMouseOverButton(mouseX, mouseY, 0.52, 0.013)) {
					return 1;
				} else if (isMouseOverButton(mouseX, mouseY, 0.90, 0.013)) {
					return 0;
				}

			}
		}
	}

	private static void openPopupWindow(int i) {
		// Implement the logic to open a pop-up window
		// For example, you can create another StdDraw window
		// and draw whatever content you want in that window
		StdDraw.setPenColor(StdDraw.BOOK_LIGHT_BLUE);
		StdDraw.filledRectangle(.75, 0.25, .25, 0.25);
		switch (i) {
		case 1:
			StdDraw.picture(0.75, 0.25, "src/socialIsland/Resources/house1Question.png", .5, .5);

			break;
		case 2:
			StdDraw.picture(0.75, 0.25, "src/socialIsland/Resources/house2Question.png", .5, .5);
			StdDraw.pause(3_000);
			StdDraw.picture(0.52, 0.013, "src/socialIsland/Resources/Do you want some of my water.png", 0.5, 0.5);
			StdDraw.picture(0.90, 0.013, "src/socialIsland/Resources/You need water..png", 0.5, 0.5);
			break;
		case 3:
			StdDraw.picture(0.75, 0.25, "src/socialIsland/Resources/house3Question.png", .5, .5);
			break;
		case 4:
			StdDraw.picture(0.75, 0.25, "src/socialIsland/Resources/house1Question.png", .5, .5);
			break;
		case 5:
			StdDraw.picture(0.75, 0.25, "src/socialIsland/Resources/house1Question.png", .5, .5);
			break;

		}

//		StdDraw.textLeft(0.55, 0.07, "Yes! Let's be friends!");
//		StdDraw.textLeft(0.85, 0.07, "No way!");
		StdDraw.show();

	}

	public static void interaction(int vertex, Graph g, double[] xCoordinates, double[] yCoordinates) {
		// draw rectangle, question dialogue, 3 buttons
		openPopupWindow(vertex);
		// detect clicks while loop
		if (checkForClicksButtons() == 1) {
			g.addEdge(0, vertex);
			drawEnvironment(xCoordinates, yCoordinates);
			StdDraw.picture(0.75, 0.25, "src/socialIsland/Resources/house2PosResponse.png", .5, .5);
			
			StdDraw.pause(4_000);
			drawEnvironment(xCoordinates, yCoordinates);
			

		}
		// test print graph adjacency lists
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

		// switch:
		// 1. Positive: add friendship, potentially add item to queue
		// 2. Neutral: "Close window"
		// 3. Optional: Negative: if (edge exists) delete edge, redraw original board
		// drawEnvironment()
	}

}
