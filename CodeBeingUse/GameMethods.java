package socialIsland;

import java.util.ArrayList;
import java.util.List;

import edu.princeton.cs.algs4.Graph;
import edu.princeton.cs.algs4.StdDraw;
import edu.princeton.cs.algs4.StdOut;
import edu.princeton.cs.algs4.StdRandom;

public interface GameMethods {
	public double houseScale = 0.3;
	public int screenSize = 900;

	/**
	 * Draws a green island window and the characters (houses and animals) in a
	 * random arrangement. Initializes the characters with a name, image, and random
	 * location.
	 * 
	 * @author Eliza Kitchens & Syed Mujibur Rahman (Mujib)
	 */

	public static void setEnvironment(Character[] characters) {

		// Set the game screen size
		StdDraw.setCanvasSize(screenSize, screenSize);

		// create the title
		StdDraw.setTitle("Social Island");

		// Set the background color to green
		StdDraw.clear(StdDraw.GREEN);

		// set a small border so houses aren't drawn near the edges
		StdDraw.setScale(-.05, 1.05);

		// Draw characters
		for (int i = 1; i < characters.length; i++) {

			StdDraw.picture(characters[i].getxCoordinate(), characters[i].getyCoordinate(), characters[i].getImage(),
					houseScale, houseScale);
		}
	}

	// optionals:
	// draw player character
	// draw frienship status
	// draw other island details

	public static Character[] initializeCharacters(int numCharacters) {
		// construct characters (store in array of Character objects)
		Character[] characters = new Character[numCharacters];
		List<PointOnMap> houseCoordinates = new ArrayList<PointOnMap>();

		for (int i = 1; i < numCharacters; i++) {
			double distanceToHouse = 100.0;
			double distanceBtwHouses = 0.4;
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
					for (int j = 0; j < houseCoordinates.size(); j++) {
						distanceToHouse = Math.sqrt(Math.pow((houseCoordinates.get(j).getX() - x), 2)
								+ Math.pow((houseCoordinates.get(j).getY() - y), 2));
						if (distanceToHouse < distanceBtwHouses) {
							// System.out.println("Distance Too Small.");
							break;
						}

					}
				}

				if (count++ > 100) {
					System.out.println("Count was exceeded.");
					break;
				}

			} while (distanceToHouse < distanceBtwHouses);

			PointOnMap houseLocation = new PointOnMap(x, y);
			houseCoordinates.add(houseLocation);

			characters[i] = new Character("House" + i, "src/socialIsland/Resources/house" + i + ".png", x, y, i);
			// test print
			for (int j = 1; j < numCharacters; j++) {
				StdOut.println(characters[j]);
			}
		}
		return characters;
	}

	/**
	 * Re draws game environment. Serves as a way to "close" pop-up windows.
	 * 
	 * @param xCoordinates
	 * @param yCoordinates
	 * @author Eliza Kitchens
	 */
	public static void drawEnvironment(double[] xCoordinates, double[] yCoordinates) {
		// Set the game screen size
		StdDraw.setCanvasSize(screenSize, screenSize);

		// create the title
		StdDraw.setTitle("Social Island");

		// Set the background color to green
		StdDraw.clear(StdDraw.GREEN);

		// set a small border so houses aren't drawn near the edges
		StdDraw.setScale(-.05, 1.05);

		// redraw houses
		for (int i = 1; i < xCoordinates.length; i++) {
			StdDraw.picture(xCoordinates[i], yCoordinates[i], "src/socialIsland/Resources/house" + i + ".png",
					houseScale, houseScale);
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
		double buttonSize = houseScale;

		// checks the 4 quadrants of the character image using the coordinate of image
		return mouseX >= x - buttonSize / 2 && mouseX <= x + buttonSize / 2 && mouseY >= y - buttonSize / 2
				&& mouseY <= y + buttonSize / 2;
	}

	public static void checkForClicksCharacters(Character[] characters, Graph g, double[] xCoordinates,
			double[] yCoordinates) {
		// Continuous loop to check for mouse clicks
		boolean mousePressed = false;
		while (true) {
			mousePressed = StdDraw.isMousePressed();
			if (mousePressed) {
				double mouseX = StdDraw.mouseX();
				double mouseY = StdDraw.mouseY();
				System.out.println("Mouse Location: " + mouseX + ", " + mouseY);
				// check if the coordinates of your mouse match the coordinates of a character
				for (int i = 1; i < 6; i++) {
					if (isMouseOverButton(mouseX, mouseY, characters[i].getxCoordinate(),
							characters[i].getyCoordinate())) {
						interaction(i, g, xCoordinates, yCoordinates);
					}
				}
				try {
					Thread.sleep(1_000);
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}

				mousePressed = false;
//                if (!StdDraw.isMousePressed())
//                    break;
			}
		}
	}

	public static int checkForClicksButtons() {
		// Continuous loop to check for mouse clicks
		boolean mousePressed = false;

		while (true) {
			mousePressed = StdDraw.isMousePressed();
			if (mousePressed) {
				double mouseX = StdDraw.mouseX();
				double mouseY = StdDraw.mouseY();
				System.out.println("Mouse Location: " + mouseX + ", " + mouseY);
				try {
					Thread.sleep(1_000);
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}

				// TODO: create custom isMouseOverButton for this
				if (isMouseOverButton(mouseX, mouseY, 0.73, 0.2)) {
					mousePressed = false;
					return 1; // positive
				} else if (isMouseOverButton(mouseX, mouseY, 0.73, 0.10)) {
					mousePressed = false;
					return 0; // neutral
				} else if (isMouseOverButton(mouseX, mouseY, 0.73, 0.0)) {
					mousePressed = false;
					return -1; // negative
				} else {
					mousePressed = false;
					return 0;
				}
			}
		}

	}

	private static void openPopupWindow(int i) {
		// Implement the logic to open a pop-up window
		// For example, you can create another StdDraw window
		// and draw whatever content you want in that window
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
			StdDraw.picture(0.73, 0.2, "src/socialIsland/Resources/Thanks for the present.png", buttonScale,
					buttonScale);
			StdDraw.picture(0.73, 0.1, "src/socialIsland/Resources/not right now.png", buttonScale, buttonScale);
			StdDraw.picture(0.73, 0.00, "src/socialIsland/Resources/I don't like your presents.png", buttonScale,
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

	public static void interaction(int vertex, Graph g, double[] xCoordinates, double[] yCoordinates) {
		// draw rectangle, question dialogue, 3 buttons
		openPopupWindow(vertex);
		// detect clicks while loop
		if (checkForClicksButtons() == 1) {
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
			drawEnvironment(xCoordinates, yCoordinates);
			// displays response
			StdDraw.picture(0.75, 0.50, "src/socialIsland/Resources/house" + vertex + "PosResponse.png", .5, .5);
			StdDraw.pause(4_000);
			// displays island
			drawEnvironment(xCoordinates, yCoordinates);
		}

		if (checkForClicksButtons() == -1) {
			g = RemoveEdge.RemoveEdgeMethod(g, 1, 2);
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
