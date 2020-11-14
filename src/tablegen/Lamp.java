package tablegen;

public class Lamp {

	private double x;
	private double y;
	private double width;
	// lamp type enum

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

	public double getPrice(ApplianceStore store) {
		double price = 0;
		switch (store) {
		case Ikea:
			price = 12.99;
			// https://www.ikea.com/us/en/p/tertial-work-lamp-with-led-bulb-dark-gray-00424985/
			break;
		case HomeDepot:
			price = 17.59;
			// https://www.homedepot.com/pep/Globe-Electric-32-in-Multi-Joint-Metal-Clamp-Black-Desk-Lamp-56963/205139331?source=shoppingads&locale=en-US&mtc=Shopping-B-F_D27L-G-D27L-27_16_INTERIOR_LIGHTING-NA-NA-Feed-PLA-NA-NA-INTERIOR_LIGHTING_GeneralInteriorLighting&cm_mmc=Shopping-B-F_D27L-G-D27L-27_16_INTERIOR_LIGHTING-NA-NA-Feed-PLA-NA-NA-INTERIOR_LIGHTING_GeneralInteriorLighting-71700000038836110-58700004241533705-92700052328522866&gclid=Cj0KCQiAnb79BRDgARIsAOVbhRqDDrtYvvqCGkmI1p5lgi4x38vwS8Lk2p67W1iLeY2wAQ740RhXdJsaAulEEALw_wcB&gclsrc=aw.ds
			break;
		case Target:
			price = -1;
			break;
		}
		return price;

	}
}
