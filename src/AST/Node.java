package AST;

import java.util.LinkedList;

import parser.IncorrectConversionException;
import command.Command;
import command.IncorrectMethodCallException;
import command.VariableNotDeclaredException;

public class Node {

	private LinkedList<Node> childs = new LinkedList<>();

	private Command command;

	private Node father;

	public Node(Command command, Node father) {
		this.command = command;
		this.father = father;
	}

	public void add(Node node) {
		this.childs.add(node);
	}

	public void remplace(Node nodeRemplaced, Node nodeRemplacer) {
		this.childs.set(this.childs.indexOf(nodeRemplaced), nodeRemplacer);
	}

	public Node getFather() {
		return father;
	}

	public void setFather(Node father) {
		this.father = father;
	}

	public Command getCommand() {
		return command;
	}

	public void recursivePrint(int index) {
		System.out.println(index + " : " + this.command + " : "
				+ this.childs.size());
		for (Node child : childs) {
			child.recursivePrint(index + 1);
		}
		if (index == 0)
			System.out.println("END of tree");
	}

	public LinkedList<Node> getChilds() {
		return childs;
	}

	public void execute(Data data) throws VariableAlreadyExistException,
			IncorrectConversionException, IncorrectMethodCallException,
			InexistantVariableException, VariableNotDeclaredException {
		this.command.execute(this, data);
	}

	public void removeVariable(Data data) {
		this.command.removeVariable(data);
	}

}
