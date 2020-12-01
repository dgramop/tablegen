package tablegen;

public class Receptacle implements TableComponent{
	private double x;
	private double y;


	public Receptacle(double x, double y) {

		this.x = x;
		this.y = y;
	

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
		case Lowes:
			price = 0.58;
			//https://www.lowes.com/pd/Eaton-White-15-Amp-Duplex-Outlet-Residential-Outlet/1098367
			price+= 0.35;
			//https://www.lowes.com/pd/Eaton-1-Gang-White-Single-Duplex-Standard-Wall-Plate/1001456742
			price+=0.32;
			//https://www.lowes.com/pd/CARLON-1-Gang-Blue-Plastic-New-Work-Standard-Switch-Outlet-Wall-Electrical-Box/1000108303
			break;
		}
		return price;
	}
}
