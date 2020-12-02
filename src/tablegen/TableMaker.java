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
		Leg[] legs=new Leg[4];
		legs[0]=new Leg(overhang,overhang,height-Table.tableTopThickness);
		legs[1]=new Leg(overhang,width-overhang-Leg.width,height-Table.tableTopThickness);
		legs[2]=new Leg(length-overhang-Leg.length,overhang,height-Table.tableTopThickness);
		legs[3]=new Leg(length-overhang-Leg.length,width-overhang-Leg.width,height-Table.tableTopThickness);
		
		ArrayList<TableComponent> components = new ArrayList<TableComponent>();
		for(int i = 1; i<=lamp; i++)
		{
			components.add(new Lamp(i, i, i));
		}
		for(int i = 1; i<=receptacle; i++)
		{
			components.add(new Receptacle(i, i));
		}
		
		Slat[] slats=new Slat[] { new Slat(new Leg[] {legs[0],legs[1]}, legs[1].getX()-legs[0].getX()-Leg.length, 1+Leg.width, false),
		new Slat(new Leg[] {legs[0],legs[2]}, legs[1].getY()-legs[0].getY()-Leg.width, 1, true),
		new Slat(new Leg[] {legs[1],legs[3]}, legs[1].getY()-legs[0].getY()-Leg.width, 1, true)};
		
		return new Table(length, width, legs, slats, components);
	}
}
