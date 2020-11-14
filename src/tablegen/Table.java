package tablegen;

import java.util.ArrayList;

public class Table {
private double tableTopLength;
private double tableTopWidth;
private double tableOverhang;
private final double tableTopThickness = 1.0;

Leg[] tableLegs = new Leg[4];
ArrayList<TableComponent> tableComponents = new ArrayList<>();
Slat[] tableSlats = new Slat[3];

public double calculatePrice() {
return 0.0;	
}

public double getTableTopLength() {
	return tableTopLength;
}

public void setTableTopLength(double tableTopLength) {
	this.tableTopLength = tableTopLength;
}

public double getTableTopWidth() {
	return tableTopWidth;
}

public void setTableTopWidth(double tableTopWidth) {
	this.tableTopWidth = tableTopWidth;
}

public double getTableOverhang() {
	return tableOverhang;
}

public void setTableOverhang(double tableOverhang) {
	this.tableOverhang = tableOverhang;
}

public double getTableTopThickness() {
	return tableTopThickness;
}


}
