package command;

import java.util.LinkedList;

import environment.Data;
import environment.Node;
import exception.InterpreterException;

/**
 * This class contains only other commands They are usefull for : <br>
 * <ul>
 * <li>If command : One for then, and another for else branch</li>
 * <li>while command : for the loop</li>
 * </ul>
 * .
 */
public class Container extends Command {

	/** The Constant serialVersionUID. */
	private static final long serialVersionUID = 509430560902825564L;

	/**
	 * Instantiates a new container.
	 *
	 * @param line
	 *            the line
	 * @param command
	 *            the command
	 */
	public Container(int line, String command) {
		super(line, command);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "Container []";
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see command.Command#execute(ast.Node, ast.Data)
	 */
	@Override
	public void execute(Node node, Data data) throws InterpreterException,
			InterruptedException {
		LinkedList<Node> children = node.getChilds();
		for (Node child : children) {
			if (data.isTerminated())
				break;
			child.execute(data);
			child.removeVariable(data);
		}
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see command.Command#executeAtChilds(ast.Node, ast.Data, ast.Node)
	 */
	@Override
	public void executeAtChilds(Node node, Data data, Node child)
			throws InterpreterException, InterruptedException {
		LinkedList<Node> children = node.getChilds();
		int index = children.indexOf(child);
		child.getCommand().partialExecute(child, data);
		for (int i = index + 1; i < children.size(); i++) {
			if (data.isTerminated())
				break;
			child = children.get(i);
			child.execute(data);
			child.removeVariable(data);
		}
	}
}
