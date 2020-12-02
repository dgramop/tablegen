package tablegen;

public class Slat {

	private double height;

	private double length;
	
	private boolean thick = false;

	/**
	 * Creates a slat
	 * @param legs - The two legs that this slat joins
	 * @param h - Height that the slat sits at
	 * @param l - Length of the slat, from tip to tip
	 * @param isThick - if this two-by-four's 3.5 inch side should face ups
	 */
	public Slat(Leg[] legs, double h, double l,boolean isThick) {
		height = h;

		length = l;
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
	
	public boolean isThick()
	{
		return thick;
	}

}
