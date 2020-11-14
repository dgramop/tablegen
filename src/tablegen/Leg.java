package tablegen;

public class Leg  {
private double x;
private double y;
private double height;
public static final double width = 1.5;
public static final double length = 3.5;

public Leg(double x, double y, double h)
{
	this.x = x;
	this.y = y;
	height = h;

	
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

public double getWidth() {
	return width;
}

public double getLength() {
	return length;
}

public double calculateCubeFoot() {
	return height*width*length;
}

public double calculatePrice() {
	return 0.0;
}

}
