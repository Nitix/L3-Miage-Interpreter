package command;

import java.util.LinkedList;

import ast.Data;
import ast.Node;
import exception.InterpreterException;

/**
 * This class contains only orther commands They are usefull for : <br>
 * <ul>
 * <li>If command : One for then, and another for else branch</li>
 * <li>while command : for the loop</li>
 * </ul>
 */
public class Container extends Command {

	/**
	 * 
	 */
	private static final long serialVersionUID = 509430560902825564L;

	public Container(int line, String command) {
		super(line, command);
	}

	@Override
	public String toString() {
		return "Container []";
	}

	@Override
	public void execute(Node node, Data data)
			throws InterpreterException, InterruptedException {
		LinkedList<Node> childs = node.getChilds();
		for (Node child : childs) {
			if(data.isTerminated())
				break;
			child.execute(data);
			child.removeVariable(data);
		}
	}
	
	@Override
	public void executeAtChilds(Node node, Data data, Node child)
			throws InterpreterException, InterruptedException {
		LinkedList<Node> childs = node.getChilds();
		int index = childs.indexOf(child);
		child.getCommand().partialExecute(child, data);
		for(int i = index+1; i < childs.size(); i++){
			if(data.isTerminated())
				break;
			child = childs.get(i);
			child.execute(data);
			child.removeVariable(data);
		}
	}
}
