package tablegen;

public class Receptacle {
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

	public double getPrice(ApplianceStore store) {
		double price = 0.0;
		switch(store) {
		case HomeDepot:
			price = 1.04;
			//https://www.homedepot.com/p/Leviton-15-Amp-Tamper-Resistant-Duplex-Outlet-White-10-Pack-M22-T5320-WMP/100684043
			price+=0.21;
			//https://www.homedepot.com/p/Leviton-1-Gang-White-Duplex-Outlet-Wall-Plate-10-Pack-M24-88003-WMP/100357040
			price+=0.43;
			//https://www.homedepot.com/p/Carlon-1-Gang-20-cu-in-New-Work-PVC-Electrical-Outlet-Box-B120A-UPC/205319652
			break;
		case Ikea:
			price = -1;
			break;
		case Target:
			price = -1;
			break;
		}
		return price;
	}
}
