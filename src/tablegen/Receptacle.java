package tablegen;

public class Receptacle implements TableComponent{
	private double x;
	private double y;
	// type receptacle
	private int numReceptacles;

	public Receptacle(double x, double y, int numReceptacles) {
		this.x = x;
		this.y = y;
		this.numReceptacles = numReceptacles;

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

	public int getNumReceptacles() {
		return numReceptacles;
	}

	public void setNumReceptacles(int numReceptacles) {
		this.numReceptacles = numReceptacles;
	}

	public double calculatePrice() {
		return 0.0;
	}
}
