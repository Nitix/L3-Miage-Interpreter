package command;

import environment.Data;
import environment.Node;
import environment.variable.BooleanVariable;
import environment.variable.Variable;
import exception.InterpreterException;

/**
 * The Class BooleanType. Simple command to return direct type of value
 */
public class BooleanType extends Command {

	/** The Constant serialVersionUID. */
	private static final long serialVersionUID = 6271398765651851129L;

	/** The value. */
	private boolean value;

	/**
	 * Instantiates a new boolean type.
	 *
	 * @param value
	 *            the value
	 * @param line
	 *            the line
	 */
	public BooleanType(boolean value, int line) {
		super(line, Boolean.toString(value));
		this.value = value;
	}

	/**
	 * Instantiates a new boolean type.
	 *
	 * @param command
	 *            the command
	 * @param line
	 *            the line
	 */
	public BooleanType(String command, int line) {
		super(line, command);
		this.value = Boolean.parseBoolean(command);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see command.Command#execute(ast.Node, ast.Data)
	 */
	@Override
	public void execute(Node node, Data data) throws InterpreterException,
			InexistentVariableException {
		BooleanVariable var = new BooleanVariable(value, getId());
		data.addVariable(getId(), var);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see command.Command#hasValue(ast.Data)
	 */
	@Override
	public boolean hasValue(Data data) throws InexistentVariableException {
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
