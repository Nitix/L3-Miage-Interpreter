package command;

import java.util.LinkedList;

import environment.Data;
import environment.Node;
import environment.variable.IntVariable;
import environment.variable.Variable;
import exception.IncorrectConversionException;
import exception.InterpreterException;

/**
 * The Class Divided. Divided two children,
 */
public class Divided extends Command {

	/** The Constant serialVersionUID. */
	private static final long serialVersionUID = -2042457385400321447L;

	/**
	 * Instantiates a new divided.
	 *
	 * @param line
	 *            the line
	 */
	public Divided(int line) {
		super(line, "/");
	}

	/**
	 * Checks if it's a divided command.
	 *
	 * @param command
	 *            the command
	 * @return true, if it's a divided command
	 */
	public static boolean isDividedCommand(String command) {
		return command.equals("/");
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "Divided []";
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
		Node node1 = children.get(0);
		Node node2 = children.get(1);
		Command command1 = node1.getCommand();
		Command command2 = node2.getCommand();
		command1.execute(node1, data);
		if (!command1.getVariable(data).isIntValue()) {
			throw new IncorrectConversionException(this.getLine(),
					this.getCommand());
		}
		command2.execute(node2, data);
		if (!command2.getVariable(data).isIntValue()) {
			throw new IncorrectConversionException(this.getLine(),
					this.getCommand());
		}
		Variable var = new IntVariable(command1.getVariable(data).getIntValue()
				/ command2.getVariable(data).getIntValue(), getId());
		data.addVariable(this.getId(), var);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see command.Command#hasValue(ast.Data)
	 */
	@Override
	public boolean hasValue(Data data) {
		return true;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see command.Command#getVariable(ast.Data)
	 */
	public Variable getVariable(Data data) throws InexistentVariableException {
		return data.getVariable(getId(), getLine());
	}
}
