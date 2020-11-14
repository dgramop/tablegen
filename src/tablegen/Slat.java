package tablegen;

public class Slat {

	private double x;
	private double y;
	private double height;

	private double length;

	public Slat(double x, double y, double h, double l) {
		this.x = x;
		this.y = y;
		height = h;

		length = l;
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

	public double getHeight() {
		return height;
	}

	public void setHeight(double height) {
		this.height = height;
	}

	public double getLength() {
		return length;
	}

	public void setLength(double length) {
		this.length = length;
	}

	public double calculateFoot() {
		return height * length;
	}

	public double calculatePrice() {
		return 0.0;
	}

}
