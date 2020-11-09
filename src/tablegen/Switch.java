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

	public double calculatePrice() {
		return 0.0;
	}
}
