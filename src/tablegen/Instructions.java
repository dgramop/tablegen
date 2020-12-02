package tablegen;

public class Instructions {
private Table tableObject;
Instructions(Table t)
{
	tableObject = t;
}

public String writeInstructions() {
	String instruct = "";
	instruct+="Obtain 2x4 from "+tableObject.getWoodStore()+"\n";
	
	return instruct;
}
}
