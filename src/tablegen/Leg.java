package tablegen;

public class Leg implements TableComponent {
private double x;
private double y;
private double height;
private double width;
private double length;

public Leg(double x, double y, double h, double w, double l)
{
	this.x = x;
	this.y = y;
	height = h;
	width = w;
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

public double getWidth() {
	return width;
}

public void setWidth(double width) {
	this.width = width;
}

public double getLength() {
	return length;
}

public void setLength(double length) {
	this.length = length;
}

public double calculateCubeFoot() {
	return height*width*length;
}

public double calculatePrice() {
	return 0.0;
}

}
