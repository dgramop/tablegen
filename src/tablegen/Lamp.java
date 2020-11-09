package tablegen;

public class Lamp implements TableComponent{

	private double x;
	private double y;
	private double width;
	//lamp type enum

	public Lamp(double x, double y, double w) {
		this.x = x;
		this.y = y;
		width = w;

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

	public double getWidth() {
		return width;
	}

	public void setWidth(double width) {
		this.width = width;
	}

	public double calculatePrice() {
		return 0.0;
	}

}
