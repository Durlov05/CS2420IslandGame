package socialIsland;

/**
 * The PointOnMap class represents a point on a map with coordinates (x, y).
 * 
 * @author Eliza Kitchens & Syed Mujibur Rahman (Mujib)
 */

public class PointOnMap {

	private double x;
	private double y;

	/**
	 * Constructs a PointOnMap object with the specified x and y coordinates.
	 * 
	 * @param x The x-coordinate of the point on the map
	 * @param y The y-coordinate of the point on the map
	 */

	public PointOnMap(double x, double y) {
		this.x = x;
		this.y = y;
	}

	public double getX() {
		return x;
	}

	public void setX(double x) {
		this.x = x;
	}

	public double getY() {
		return y;
	}

	public void setY(double y) {
		this.y = y;
	}

}
