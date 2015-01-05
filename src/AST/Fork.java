package AST;

import parser.IncorrectConversionException;

import command.IncorrectMethodCallException;
import command.VariableNotDeclaredException;

public class Fork extends Thread{
	
	private Node node;
	private Data data;
	
	public Fork(Node node, Data data) {
		super();
		this.node = node;
		this.data = data;
	}

	public void run(){
		try {
			node.getFather().getCommand().executeAtChilds(node.getFather(), data, node);
		} catch (IncorrectConversionException | VariableNotDeclaredException
				| VariableAlreadyExistException | IncorrectMethodCallException
				| InexistantVariableException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}