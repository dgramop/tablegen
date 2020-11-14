package tablegen;

import java.util.ArrayList;

public class Table {
private double tableTopLength;
private double tableTopWidth;

public static final double tableTopThickness = 1.0;

Leg[] tableLegs = new Leg[4];
ArrayList<TableComponent> tableComponents = new ArrayList<>();
Slat[] tableSlats = new Slat[3];

/**
 * 
 * @param tableTopLength the length of the table top
 * @param tableTopWidth the width of the table top
 * @param tableOverhang
 * @param tableLegs
 * @param tableComponents
 */
public Table(double tableTopLength, double tableTopWidth, Leg[] tableLegs,
		ArrayList<TableComponent> tableComponents) {
	super();
	this.tableTopLength = tableTopLength;
	this.tableTopWidth = tableTopWidth;
	
	this.tableLegs = tableLegs;
	this.tableComponents = tableComponents;
	this.tableSlats = null;//TODO: Katie compute slats
}

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


public double getTableTopThickness() {
	return tableTopThickness;
}


}
