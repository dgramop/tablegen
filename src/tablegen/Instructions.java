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
	"cut four pieces of 2x4 wood to specified leg length (" +tableObject.tableLegs[0].getHeight()+")\n"+
	"cut three pieces of wood to specified slats length ("+tableObject.getTableSlats()[0].getLength()+")\n"+ 
	"cut Melamine White Panel to specified dimensions ("+tableObject.getTableTopLength()+","+tableObject.getTableTopWidth()+")\n"+
	"screw slats onto legs at specified height"+
	"screw legs onto table top with specified overhang on the outside \n";
	
	return instruct;
}
}
