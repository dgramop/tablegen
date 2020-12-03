package tablegen;

public class Instructions {
private Table tableObject;
Instructions(Table t)
{
	tableObject = t;
}

public String writeInstructions() {
	String instruct = "";
	instruct+="Obtain 2x4 from "+tableObject.getWoodStore()+"\n"+
	"Cut four pieces of 2x4 wood to specified leg length (" +tableObject.tableLegs[0].getHeight()+"in.).\n"+
	"Cut three pieces of wood to specified slats length ("+tableObject.getTableSlats()[0].getLength()+"in.).\n"+ 
	"Cut Melamine White Panel to specified dimensions ("+tableObject.getTableTopLength()+"in., "+tableObject.getTableTopWidth()+"in.).\n"+
	"Screw slats onto legs at specified height.\n"+
	"Screw legs onto table top with specified overhang on the outside. \n"+
	"Obtain all the pieces for "+tableObject.getNumReceptacles()+" receptacle(s) from "+ tableObject.getWoodStore()+".\n"+
	"Purchase "+tableObject.getNumLamps()+" lamp(s) from "+tableObject.getLampStore()+".\n";
	
	return instruct;
}
}
