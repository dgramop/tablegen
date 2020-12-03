package tablegen;

public enum ApplianceStore {
Ikea, HomeDepot, Lowes;

public  double getWoodPrice() {
	double price = 0.0;
	switch(this) {
	case HomeDepot:
			price = 4.98;
		//https://www.homedepot.com/p/2-in-x-4-in-x-92-625-in-Premium-Kiln-Dried-Whitewood-Stud-569062/302778078
		break;
	case Ikea:
		break;
	case Lowes:
			price = 6.62;
		//https://www.lowes.com/pd/2-in-x-4-in-x-10-ft-Whitewood-Lumber-Common-2-in-x-4-in-x-10-ft-Actual/1001134500
		break;
	}
	return price;
}
}
