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
	public static Table makeTable(int height,int width, int length, int overhang, int lamp, int receptacle)
	{
		Leg[] leg=new Leg[4];
		leg[0]=new Leg(overhang,overhang,height-Table.tableTopThickness);
		leg[1]=new Leg(overhang,width-overhang-Leg.width,height-Table.tableTopThickness);
		leg[2]=new Leg(length-overhang-Leg.length,overhang,height-Table.tableTopThickness);
		leg[3]=new Leg(length-overhang-Leg.length,width-overhang-Leg.width,height-Table.tableTopThickness);
		ArrayList<TableComponent> components = new ArrayList<TableComponent>();
		for(int i = 1; i<=lamp; i++)
		{
			components.add(new Lamp(i, i, i));
		}
		for(int i = 1; i<=receptacle; i++)
		{
			components.add(new Receptacle(i, i));
		}
		return new Table(length, width, leg, components);
	}
}
