package tablegen;

import java.util.ArrayList;

public class TableMaker {
	
	/**
	 * 
	 * @param height of the table from bottom of let to top of tabletop in inches
	 * @param width of the tabletop in inches
	 * @param length of the tabletop in inches
	 * @param overhang the height
	 * @return
	 */
	public static Table makeTable(int height,int width, int length, int overhang)
	{
		Leg[] leg=new Leg[4];
		leg[0]=new Leg(overhang,overhang,height-Table.tableTopThickness);
		leg[1]=new Leg(overhang,width-overhang-Leg.width,height-Table.tableTopThickness);
		leg[2]=new Leg(length-overhang-Leg.length,overhang,height-Table.tableTopThickness);
		leg[3]=new Leg(length-overhang-Leg.length,width-overhang-Leg.width,height-Table.tableTopThickness);
		return new Table(length, width, leg, new ArrayList<TableComponent>());
	}
}
