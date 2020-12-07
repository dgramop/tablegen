package tablegen;

import java.util.ArrayList;

public class Table {
	private double tableTopLength;
	private double tableTopWidth;
	private ApplianceStore woodStore;
	private ApplianceStore lampStore;
	private int numReceptacles;
	private int numLamps;

	public static final double tableTopThickness = 0.8;

	Leg[] tableLegs = new Leg[4];
	ArrayList<TableComponent> tableComponents = new ArrayList<>();
	Slat[] tableSlats = new Slat[3];

	/**
	 * 
	 * @param tableTopLength  the length of the table top
	 * @param tableTopWidth   the width of the table top
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

	/**
	 * 
	 * @param woodandmore store used for wood and electric
	 * @param lamp        store used for lamp
	 * @return estimated price
	 */
	public double calculatePrice(ApplianceStore woodandmore, ApplianceStore lamp) {
		woodStore = woodandmore;
		lampStore = lamp;
		double price = 0.0;
		for (TableComponent t : tableComponents) {
			if (t instanceof Lamp) {
				price += t.getPrice(lamp);
				numLamps++;
			} else {
				if (t instanceof Receptacle) {
					numReceptacles++;
				}

				price += t.getPrice(woodandmore);
			}
		}

		// wood comes in 2inx4inx8foot, and it's not practical to lengthen the wood
		// Calculating total number of wood segments to purchase for slats and legs
		int totalSegments = 0;
		double lengthLeft = 8 * 12;
		for (Leg l : tableLegs) {
			lengthLeft -= l.getHeight();
			if (lengthLeft <= 0) {
				lengthLeft += 8 * 12;
				totalSegments++;
			}
		}

		for (Slat l : tableSlats) {
			lengthLeft -= l.getHeight();
			if (lengthLeft <= 0) {
				lengthLeft += 8 * 12;
				totalSegments++;
			}
		}

		price += woodandmore.getWoodPrice() * totalSegments + (lengthLeft != 8 * 12 ? 1 : 0);

		// price of the table top for both stores
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

	public int getNumReceptacles() {
		return numReceptacles;
	}

	public int getNumLamps() {
		return numLamps;
	}

	/**
	 * 
	 * @return string of WoodStore
	 */
	public String getWoodStore() {
		if (woodStore == ApplianceStore.HomeDepot) {
			return "HomeDepot";
		} else {
			return "Lowes";
		}
	}

	/**
	 * 
	 * @return Enum value of WoodStore
	 */
	public ApplianceStore getWoodStoreEnum() {
		return woodStore;
	}

	/**
	 * 
	 * @return Enum value for LampStore
	 */
	public ApplianceStore getLampStoreEnum() {
		return lampStore;
	}

	/**
	 * 
	 * @return string value for LampStore
	 */
	public String getLampStore() {
		if (lampStore == ApplianceStore.HomeDepot) {
			return "HomeDepot";
		} else if (lampStore == ApplianceStore.Lowes) {
			return "Lowes";
		} else {
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

	/**
	 * 
	 * @return String of picture file name
	 */
	public String getLampImage(ApplianceStore s) {
		switch (s) {
		case Ikea:
			return "ikealamp.jpg";
		// https://www.ikea.com/us/en/p/tertial-work-lamp-with-led-bulb-dark-gray-00424985/

		case HomeDepot:
			return "homedepot.jpg";
		// https://www.homedepot.com/pep/Globe-Electric-32-in-Multi-Joint-Metal-Clamp-Black-Desk-Lamp-56963/205139331?source=shoppingads&locale=en-US&mtc=Shopping-B-F_D27L-G-D27L-27_16_INTERIOR_LIGHTING-NA-NA-Feed-PLA-NA-NA-INTERIOR_LIGHTING_GeneralInteriorLighting&cm_mmc=Shopping-B-F_D27L-G-D27L-27_16_INTERIOR_LIGHTING-NA-NA-Feed-PLA-NA-NA-INTERIOR_LIGHTING_GeneralInteriorLighting-71700000038836110-58700004241533705-92700052328522866&gclid=Cj0KCQiAnb79BRDgARIsAOVbhRqDDrtYvvqCGkmI1p5lgi4x38vwS8Lk2p67W1iLeY2wAQ740RhXdJsaAulEEALw_wcB&gclsrc=aw.ds

		case Lowes:
			return "loweslamp.jpg";
		// https://www.lowes.com/pd/Globe-Electric-Architect-Lamp-31-5-in-Adjustable-Black-Clip-Desk-Lamp-with-Metal-Shade/1002981060

		default:
			return null;
		}
	}

	/**
	 * 
	 * @return String of picture file name
	 */
	public String getLegImage(ApplianceStore s) {
		switch (s) {
		case Ikea:
			return null;
		// https://www.ikea.com/us/en/p/tertial-work-lamp-with-led-bulb-dark-gray-00424985/

		case HomeDepot:
			return "homedepotleg.jpg";
		// https://www.homedepot.com/pep/Globe-Electric-32-in-Multi-Joint-Metal-Clamp-Black-Desk-Lamp-56963/205139331?source=shoppingads&locale=en-US&mtc=Shopping-B-F_D27L-G-D27L-27_16_INTERIOR_LIGHTING-NA-NA-Feed-PLA-NA-NA-INTERIOR_LIGHTING_GeneralInteriorLighting&cm_mmc=Shopping-B-F_D27L-G-D27L-27_16_INTERIOR_LIGHTING-NA-NA-Feed-PLA-NA-NA-INTERIOR_LIGHTING_GeneralInteriorLighting-71700000038836110-58700004241533705-92700052328522866&gclid=Cj0KCQiAnb79BRDgARIsAOVbhRqDDrtYvvqCGkmI1p5lgi4x38vwS8Lk2p67W1iLeY2wAQ740RhXdJsaAulEEALw_wcB&gclsrc=aw.ds

		case Lowes:
			return "lowesleg.jpg";
		// https://www.lowes.com/pd/Globe-Electric-Architect-Lamp-31-5-in-Adjustable-Black-Clip-Desk-Lamp-with-Metal-Shade/1002981060

		default:
			return null;
		}
	}

	/**
	 * 
	 * @return String of picture file name
	 */
	public String getReceptImage(ApplianceStore s) {
		switch (s) {
		case Ikea:
			return null;
		// https://www.ikea.com/us/en/p/tertial-work-lamp-with-led-bulb-dark-gray-00424985/

		case HomeDepot:
			return "homedepotplug.jpg";
		// https://www.homedepot.com/pep/Globe-Electric-32-in-Multi-Joint-Metal-Clamp-Black-Desk-Lamp-56963/205139331?source=shoppingads&locale=en-US&mtc=Shopping-B-F_D27L-G-D27L-27_16_INTERIOR_LIGHTING-NA-NA-Feed-PLA-NA-NA-INTERIOR_LIGHTING_GeneralInteriorLighting&cm_mmc=Shopping-B-F_D27L-G-D27L-27_16_INTERIOR_LIGHTING-NA-NA-Feed-PLA-NA-NA-INTERIOR_LIGHTING_GeneralInteriorLighting-71700000038836110-58700004241533705-92700052328522866&gclid=Cj0KCQiAnb79BRDgARIsAOVbhRqDDrtYvvqCGkmI1p5lgi4x38vwS8Lk2p67W1iLeY2wAQ740RhXdJsaAulEEALw_wcB&gclsrc=aw.ds

		case Lowes:
			return "lowesplug.jpg";
		// https://www.lowes.com/pd/Globe-Electric-Architect-Lamp-31-5-in-Adjustable-Black-Clip-Desk-Lamp-with-Metal-Shade/1002981060

		default:
			return null;
		}
	}

	/**
	 * 
	 * @return String of picture file name
	 */
	public String getSwitchImage(ApplianceStore s) {
		switch (s) {
		case Ikea:
			return null;
		// https://www.ikea.com/us/en/p/tertial-work-lamp-with-led-bulb-dark-gray-00424985/

		case HomeDepot:
			return "hdswitch.jpg";
		// https://www.homedepot.com/pep/Globe-Electric-32-in-Multi-Joint-Metal-Clamp-Black-Desk-Lamp-56963/205139331?source=shoppingads&locale=en-US&mtc=Shopping-B-F_D27L-G-D27L-27_16_INTERIOR_LIGHTING-NA-NA-Feed-PLA-NA-NA-INTERIOR_LIGHTING_GeneralInteriorLighting&cm_mmc=Shopping-B-F_D27L-G-D27L-27_16_INTERIOR_LIGHTING-NA-NA-Feed-PLA-NA-NA-INTERIOR_LIGHTING_GeneralInteriorLighting-71700000038836110-58700004241533705-92700052328522866&gclid=Cj0KCQiAnb79BRDgARIsAOVbhRqDDrtYvvqCGkmI1p5lgi4x38vwS8Lk2p67W1iLeY2wAQ740RhXdJsaAulEEALw_wcB&gclsrc=aw.ds

		case Lowes:
			return "lowesswitch.jpg";
		// https://www.lowes.com/pd/Globe-Electric-Architect-Lamp-31-5-in-Adjustable-Black-Clip-Desk-Lamp-with-Metal-Shade/1002981060

		default:
			return null;
		}
	}

	/**
	 * 
	 * @return String of picture file name
	 */
	public String getTopImage(ApplianceStore s) {
		switch (s) {
		case HomeDepot:
			return "hdtop.jpg";
		// https://www.homedepot.com/p/Veranda-Melamine-White-Panel-Common-3-4-in-x-4-ft-x-8-ft-Actual-750-in-x-49-in-x-97-in-461877/100070209?source=shoppingads&locale=en-US&mtc=Shopping-VF-F_D21-G-D21-21_5_BOARDS-Generic-NA-Feed-LIA-NA-NA-BOARDS&cm_mmc=Shopping-VF-F_D21-G-D21-21_5_BOARDS-Generic-NA-Feed-LIA-NA-NA-BOARDS-71700000053671446-58700005130561935-92700045041683771&gclid=CjwKCAiA8Jf-BRB-EiwAWDtEGm5IRdf93R8YBibgutIgg5r3G6RaNEwPU38rLNYXQjbpVySBk4keyhoCvm8QAvD_BwE&gclsrc=aw.ds

		case Lowes:
			return "lowestop.jpg";
		// https://www.lowes.com/pd/Melamine-Board-Actual-0-75-in-x-49-in-x-8-08-ft/3605066

		case Ikea:
			return null;

		default:
			return null;
		}
	}
}
