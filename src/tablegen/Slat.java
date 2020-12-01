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

	public double getPrice(ApplianceStore store) {
		double price = 0.0;
		switch(store) {
		case HomeDepot:
			if(length<=8/2) {
				price = 4.98/2.0;
			}
			else {
				price = 4.98;
			}
			//https://www.homedepot.com/p/2-in-x-4-in-x-92-625-in-Premium-Kiln-Dried-Whitewood-Stud-569062/302778078
			break;
		case Ikea:
			break;
		case Lowes:
			if(length<=10/2) {
				price = 6.62/2.0;
			}
			else {
				price = 6.62;
			}
			//https://www.lowes.com/pd/2-in-x-4-in-x-10-ft-Whitewood-Lumber-Common-2-in-x-4-in-x-10-ft-Actual/1001134500
			break;
		}
		return price;
	}

}
