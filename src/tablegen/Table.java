package tablegen;

import java.util.ArrayList;

public class Table {
	private double tableTopLength;
	private double tableTopWidth;
	private ApplianceStore woodStore;
	private ApplianceStore lampStore;

	public static final double tableTopThickness = 0.8;

	Leg[] tableLegs = new Leg[4];
	ArrayList<TableComponent> tableComponents = new ArrayList<>();
	Slat[] tableSlats = new Slat[3];

	/**
	 * 
	 * @param tableTopLength	the length of the table top
	 * @param tableTopWidth  	the width of the table top
	 * @param tableOverhang		
	 * @param tableLegs
	 * @param tableComponents
	 */
	public Table(double tableTopLength, double tableTopWidth, Leg[] tableLegs, Slat[] tableSlats,
			ArrayList<TableComponent> tableComponents) {
		super();
		this.tableTopLength = tableTopLength;
		this.tableTopWidth = tableTopWidth;
		this.tableLegs = tableLegs;
		this.tableComponents = tableComponents;
		this.tableSlats = tableSlats;
	}

	public double calculatePrice(ApplianceStore woodandmore, ApplianceStore lamp) {
		woodStore = woodandmore;
		lampStore = lamp;
		double price = 0.0;
		for (TableComponent t : tableComponents) {
			if (t instanceof Lamp) {
				price += t.getPrice(lamp);
			} else {
				price += t.getPrice(woodandmore);
			}
		}
		for (Leg l : tableLegs) {
			price += l.getPrice(woodandmore);
		}
		switch (woodandmore) {
		case HomeDepot:
			price += 28.97;
			// https://www.homedepot.com/p/Veranda-Melamine-White-Panel-Common-3-4-in-x-4-ft-x-8-ft-Actual-750-in-x-49-in-x-97-in-461877/100070209?source=shoppingads&locale=en-US&mtc=Shopping-VF-F_D21-G-D21-21_5_BOARDS-Generic-NA-Feed-LIA-NA-NA-BOARDS&cm_mmc=Shopping-VF-F_D21-G-D21-21_5_BOARDS-Generic-NA-Feed-LIA-NA-NA-BOARDS-71700000053671446-58700005130561935-92700045041683771&gclid=CjwKCAiA8Jf-BRB-EiwAWDtEGm5IRdf93R8YBibgutIgg5r3G6RaNEwPU38rLNYXQjbpVySBk4keyhoCvm8QAvD_BwE&gclsrc=aw.ds
			break;
		case Lowes:
			price += 28.97;
			// https://www.lowes.com/pd/Melamine-Board-Actual-0-75-in-x-49-in-x-8-08-ft/3605066
			break;
		case Ikea:
			break;
		}
		return price;

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


	public String getWoodStore() {
		if(woodStore == ApplianceStore.HomeDepot)
		{
			return "HomeDepot";
		}
		else {
			return "Lowes";
		}
	}


	public String getLampStore() {
		if(lampStore == ApplianceStore.HomeDepot)
		{
			return "HomeDepot";
		}
		else if (lampStore==ApplianceStore.Lowes){
			return "Lowes";
		}
		else {
			return "Ikea";
		}
	}

	


	/**
	 * @return the tableSlats
	 */
	public Slat[] getTableSlats() {
		return tableSlats;
	}

	/**
	 * @param tableSlats the tableSlats to set
	 */
	public void setTableSlats(Slat[] tableSlats) {
		this.tableSlats = tableSlats;
	}


}
