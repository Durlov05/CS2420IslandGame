package socialIsland;

public class GameObjects {

	private String objectName;
	private double xCoordinate;
	private double yCoordinate;
	private int sourceVertex;
	private int destinationVertex;
	private int countofItem;
	
	public GameObjects(double xCoordinate, double yCoordinate, int destinationVertex, int countofItem) {
		this.xCoordinate = xCoordinate;
		this.yCoordinate = yCoordinate;
		this.destinationVertex = destinationVertex;
		this.countofItem = countofItem;
	}

	public double getxCoordinate() {
		return xCoordinate;
	}

	public void setxCoordinate(double xCoordinate) {
		this.xCoordinate = xCoordinate;
	}

	public double getyCoordinate() {
		return yCoordinate;
	}

	public void setyCoordinate(double yCoordinate) {
		this.yCoordinate = yCoordinate;
	}

	public int getDestinationVertex() {
		return destinationVertex;
	}

	public void setDestinationVertex(int destinationVertex) {
		this.destinationVertex = destinationVertex;
	}

	public int getCountofItem() {
		return countofItem;
	}

	public void setCountofItem(int countofItem) {
		this.countofItem = countofItem;
	}

	@Override
	public String toString() {
		return "GameObjects [xCoordinate=" + xCoordinate + ", yCoordinate=" + yCoordinate
				+ ", sourceVertex=" + sourceVertex + ", destinationVertex=" + destinationVertex + ", countofItem="
				+ countofItem + "]";
	}

	
	
}
