package tablegen;

public class Switch implements TableComponent{
	private double x;
	private double y;
	private boolean circuitBreaker;


	public Switch(double x, double y, boolean circuitBreaker) {
		this.x = x;
		this.y = y;
		this.setCircuitBreaker(circuitBreaker);
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

	public boolean isCircuitBreaker() {
		return circuitBreaker;
	}

	public void setCircuitBreaker(boolean circuitBreaker) {
		this.circuitBreaker = circuitBreaker;
	}

	public double getPrice(ApplianceStore store) {
		double price = 0.0;
		switch(store) {
		case Ikea:
			break;
		case HomeDepot:
			price = 0.68;
			//https://www.homedepot.com/p/Leviton-15-Amp-Single-Pole-Toggle-Light-Switch-White-R52-01451-02W/100026991
			price+=0.57;
			//https://www.homedepot.com/p/Leviton-1-Gang-Midway-Toggle-Nylon-Wall-Plate-White-R52-00PJ1-00W/202059761
			break;
		case Lowes:
			price = 0.69;
			//https://www.lowes.com/pd/Legrand-Single-Pole-White-Compatible-with-LED-Framed-Toggle-Light-Switch/3236020
			price+=0.57;
			//https://www.lowes.com/pd/Pass-Seymour-Legrand-1-Gang-White-Single-Toggle-Midsize-Wall-Plate/3236765
			break;
		}
		return price;
	}
}
