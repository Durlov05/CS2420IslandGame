package socialIsland;

/**
 * This Character method declares the Character features and 
 * has many functions to set and obtain those features as needed.
 * 
 * @author Eliza Kitchens & Syed Mujibur Rahman (Mujib)
 */

public class Character {
	//Fields
	private String name;
	private String image;
	private double xCoordinate;
	private double yCoordinate;
	private int vertex;
	
	
	public Character(String name, String image, double xCoordinate, double yCoordinate, int vertex) {

		this.name = name;
		this.image = image;
		this.xCoordinate = xCoordinate;
		this.yCoordinate = yCoordinate;
		this.vertex = vertex;
	}


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


	@Override
	public String toString() {
		return "Character [name=" + name + ", image=" + image + ", xCoordinate=" + xCoordinate + ", yCoordinate="
				+ yCoordinate + ", vertex=" + vertex + "]";
	}
	
	
	
	
	

}
