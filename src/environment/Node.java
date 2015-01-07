package environment;

import java.io.Serializable;
import java.util.LinkedList;

import command.Command;
import exception.InterpreterException;

/**
 * The Class Node. Contains a list of children of the node Has a command which
 * is attached to the node
 */
public class Node implements Serializable {

	/** The Constant serialVersionUID. */
	private static final long serialVersionUID = 2772430925810320091L;

	/** The children. */
	private LinkedList<Node> children = new LinkedList<>();

	/** The command. */
	private Command command;

	/** The father. */
	private Node father;

	/**
	 * Instantiates a new node.
	 *
	 * @param command
	 *            the command
	 * @param father
	 *            the father
	 */
	public Node(Command command, Node father) {
		this.command = command;
		this.father = father;
	}

	/**
	 * Adds the node.
	 *
	 * @param node
	 *            the node
	 */
	public void add(Node node) {
		this.children.add(node);
	}

	/**
	 * Remplace.
	 *
	 * @param nodeRemplaced
	 *            the node remplaced
	 * @param nodeRemplacer
	 *            the node remplacer
	 */
	public void remplace(Node nodeRemplaced, Node nodeRemplacer) {
		this.children.set(this.children.indexOf(nodeRemplaced), nodeRemplacer);
	}

	/**
	 * Gets the father.
	 *
	 * @return the father
	 */
	public Node getFather() {
		return father;
	}

	/**
	 * Sets the father.
	 *
	 * @param father
	 *            the new father
	 */
	public void setFather(Node father) {
		this.father = father;
	}

	/**
	 * Gets the command.
	 *
	 * @return the command
	 */
	public Command getCommand() {
		return command;
	}

	/**
	 * Recursive print.
	 *
	 * @param index
	 *            the index
	 */
	public void recursivePrint(int index) {
		System.out.println(index + " : " + this.command + " : "
				+ this.children.size());
		for (Node child : children) {
			child.recursivePrint(index + 1);
		}
		if (index == 0)
			System.out.println("END of tree");
	}

	/**
	 * Gets the children.
	 *
	 * @return the children
	 */
	public LinkedList<Node> getChilds() {
		return children;
	}

	/**
	 * Execute.
	 *
	 * @param data
	 *            the data
	 * @throws InterpreterException
	 *             the interpreter exception
	 * @throws InterruptedException
	 *             the interrupted exception
	 */
	public void execute(Data data) throws InterpreterException,
			InterruptedException {
		this.command.execute(this, data);
	}

	/**
	 * Removes the variable.
	 *
	 * @param data
	 *            the data
	 */
	public void removeVariable(Data data) {
		this.command.removeVariable(data);
	}

}
