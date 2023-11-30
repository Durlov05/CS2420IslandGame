package socialIsland;

/**
 * The Character class represents a character in a social island context,
 * defining its features such as name, image, coordinates, and assigned vertex
 * within the island. This class provides methods to access and modify these
 * features as needed.
 * 
 * @author Eliza Kitchens & Syed Mujibur Rahman (Mujib)
 */

public class Character {

	private String name;
	private String image;
	private double xCoordinate;
	private double yCoordinate;
	private int vertex;

	/**
	 * Constructs a Character object with the specified features.
	 * 
	 * @param name        The name of the character
	 * @param image       The image representing the character
	 * @param xCoordinate The X-coordinate position of the character
	 * @param yCoordinate The Y-coordinate position of the character
	 * @param vertex      The vertex assigned to the character within the social
	 *                    island
	 */

	public Character(String name, String image, double xCoordinate, double yCoordinate, int vertex) {

		this.name = name;
		this.image = image;
		this.xCoordinate = xCoordinate;
		this.yCoordinate = yCoordinate;
		this.vertex = vertex;
	}

	// Accessor methods
	public String getName() {
		return name;
	}

	public String getImage() {
		return image;
	}

	public double getxCoordinate() {
		return xCoordinate;
	}

	public double getyCoordinate() {
		return yCoordinate;
	}

	public int getVertex() {
		return vertex;
	}

	public void setName(String name) {
		this.name = name;
	}

	public void setImage(String image) {
		this.image = image;
	}

	public void setxCoordinate(double xCoordinate) {
		this.xCoordinate = xCoordinate;
	}

	public void setyCoordinate(double yCoordinate) {
		this.yCoordinate = yCoordinate;
	}

	public void setVertex(int vertex) {
		this.vertex = vertex;
	}

	/**
	 * Returns a string representation of the Character object, displaying its
	 * features.
	 * 
	 * @return A string representing the character's details (name, image,
	 *         coordinates, and vertex)
	 */
	@Override
	public String toString() {
		return "Character [name=" + name + ", image=" + image + ", xCoordinate=" + xCoordinate + ", yCoordinate="
				+ yCoordinate + ", vertex=" + vertex + "]";
	}

}
