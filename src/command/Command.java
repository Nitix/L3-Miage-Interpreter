package command;

import java.io.Serializable;
import java.util.UUID;

import environment.Data;
import environment.Node;
import environment.variable.Variable;
import exception.IncorrectConversionException;
import exception.InterpreterException;

/**
 * The Class Command. Abstract class the define the command can do a method
 */
public abstract class Command implements Serializable {

	/** The Constant serialVersionUID. */
	private static final long serialVersionUID = -4326199918997155358L;

	private static int nextID = 0;

	/** The id f the command. */
	private String id;

	/** The line. */
	private int line;

	/** The command. */
	private String command;

	/**
	 * Instantiates a new command.
	 *
	 * @param line
	 *            the line
	 * @param command
	 *            the command
	 */
	public Command(int line, String command) {
		this.line = line;
		this.command = command;
		this.id = "command-" + nextID;
		nextID++;
	}

	/**
	 * Gets the id.
	 *
	 * @return the id
	 */
	public String getId() {
		return id;
	}

	/**
	 * Execute.
	 *
	 * @param node
	 *            the node attached to the command
	 * @param data
	 *            the environment of the command
	 * @throws InterpreterException
	 *             the interpreter exception
	 * @throws InterruptedException
	 *             the interrupted exception
	 */
	public abstract void execute(Node node, Data data)
			throws InterpreterException, InterruptedException;

	/**
	 * Execute the command but do a partial execute on the first child.
	 *
	 * @param node
	 *            the node attached to the command
	 * @param data
	 *            the environment of the command
	 * @param child
	 *            the child to execute from
	 * @throws InterpreterException
	 *             the interpreter exception
	 * @throws InterruptedException
	 *             the interrupted exception
	 */
	public void executeAtChilds(Node node, Data data, Node child)
			throws InterpreterException, InterruptedException {
		throw new IncorrectMethodCallException(line, command);
	}

	/**
	 * Partial execute. Does not execute children
	 *
	 * @param node
	 *            the node attached to the command
	 * @param data
	 *            the environment of the command
	 * @throws InterpreterException
	 *             the interpreter exception
	 * @throws InterruptedException
	 *             the interrupted exception
	 */
	public void partialExecute(Node node, Data data)
			throws InterpreterException, InterruptedException {
		throw new IncorrectMethodCallException(line, command);
	}

	/**
	 * Checks for value.
	 *
	 * @param data
	 *            the data
	 * @return true, if successful
	 * @throws InexistentVariableException
	 *             the inexistent variable exception
	 */
	public boolean hasValue(Data data) throws InexistentVariableException {
		return false;
	}

	/**
	 * Gets the variable.
	 *
	 * @param data
	 *            the data
	 * @return the variable
	 * @throws IncorrectMethodCallException
	 *             the incorrect method call exception
	 * @throws InexistentVariableException
	 *             the inexistent variable exception
	 */
	public Variable getVariable(Data data) throws IncorrectMethodCallException,
			InexistentVariableException {
		throw new IncorrectMethodCallException();
	}

	/**
	 * Gets the line of the command.
	 *
	 * @return the line
	 */
	public int getLine() {
		return line;
	}

	/**
	 * Gets the command.
	 *
	 * @return the command
	 */
	public String getCommand() {
		return command;
	}

	/**
	 * Removes the variable.
	 *
	 * @param data
	 *            the data
	 */
	public void removeVariable(Data data) {
		data.removeVariable(id);
	}
}
