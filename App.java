package islandTest;

import edu.princeton.cs.algs4.StdDraw;
import edu.princeton.cs.algs4.StdOut;

public class App {

	public static void main(String[] args) {

		// Set the game screen size
		StdDraw.setCanvasSize(800, 800);

		// create the title
		StdDraw.setTitle("Social Island");

		// Set the background color to green
		StdDraw.clear(StdDraw.GREEN);

		// set a small border so houses aren't drawn near the edges
		StdDraw.setScale(-.05, 1.05);

		// Add 10 buttons with images at random locations
		addRandomImageButtons(10);

		// Show the green pop-up window
		StdDraw.show();

		// Continuous loop to check for mouse clicks
		while (true) {
			if (StdDraw.isMousePressed()) {
				double mouseX = StdDraw.mouseX();
				double mouseY = StdDraw.mouseY();

				// You can add logic here to handle button clicks
				if (isMouseOverButton(mouseX, mouseY, 0.5, 0.5, "src/islandTest/Resources/house1.jpeg")) {
					// Open a pop-up window or perform any action you desire
					openPopupWindow();
				}
			}
		}
	}

	/**
	 * Adds a specified number of houses to the island at random locations. TODO:
	 * get the image from the house class, store the location of the coordinates
	 * 
	 * @param numButtons
	 */
	private static void addRandomImageButtons(int numButtons) {
		// store the random x, y coordinates:
		double xCoordinates[] = new double[numButtons];
		double yCoordinates[] = new double[numButtons];
		// get the image from the House class (rn it loads from a file)
		String image = "src/islandTest/Resources/house1.jpeg";
		// draw test button
		StdDraw.picture(.5, .5, image, 0.05, 0.05);

		for (int i = 0; i < numButtons; i++) {
			// Generate random coordinates for the button
			double x = Math.random();
			double y = Math.random();

			// add the coordinates to arrays
			xCoordinates[i] = x;
			yCoordinates[i] = y;

			// Draw house button
			StdDraw.picture(x, y, image, 0.05, 0.05);

		}
		// debug test print
		StdOut.println("x coordinates:");
		StdOut.println("-------------");
		for (double xVar : xCoordinates) {
			StdOut.println(xVar);
		}
		StdOut.println("y coordinates:");
		StdOut.println("--------------");
		for (double yVar : yCoordinates) {
			StdOut.println(yVar);
		}

	}

	private static boolean isMouseOverButton(double mouseX, double mouseY, double x, double y, String image) {
		// Check if the mouse is over the button
		double buttonSize = 0.05; // Adjusted the buttonSize
		return mouseX >= x - buttonSize / 2 && mouseX <= x + buttonSize / 2 && mouseY >= y - buttonSize / 2
				&& mouseY <= y + buttonSize / 2;
	}

	private static void openPopupWindow() {
		// Implement the logic to open a pop-up window
		// For example, you can create another StdDraw window
		// and draw whatever content you want in that window
		StdDraw.setPenColor(StdDraw.BOOK_LIGHT_BLUE);
		StdDraw.filledRectangle(.75, 0.25, .25, 0.25);
		StdDraw.setPenColor(StdDraw.DARK_GRAY);
		StdDraw.text(0.75, 0.25, "Pop-up Window!");
		StdDraw.show();
		
	}
}
