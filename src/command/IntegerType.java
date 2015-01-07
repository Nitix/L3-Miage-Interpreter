package command;

import environment.Data;
import environment.Node;
import environment.variable.IntVariable;
import environment.variable.Variable;
import exception.InterpreterException;

/**
 * The Class IntegerType. Simple command to return direct type of value
 */
public class IntegerType extends Command {

	/** The Constant serialVersionUID. */
	private static final long serialVersionUID = -39506532777110614L;

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "IntegerType [value=" + value + "]";
	}

	/** The value. */
	private int value;

	/**
	 * Instantiates a new integer type.
	 *
	 * @param value
	 *            the value
	 * @param line
	 *            the line
	 */
	public IntegerType(int value, int line) {
		super(line, "" + value);
		this.value = value;
	}

	/**
	 * Instantiates a new integer type.
	 *
	 * @param value
	 *            the value
	 * @param line
	 *            the line
	 */
	public IntegerType(String value, int line) {
		super(line, value);
		this.value = Integer.parseInt(value);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see command.Command#execute(ast.Node, ast.Data)
	 */
	@Override
	public void execute(Node node, Data data) throws InterpreterException,
			InterruptedException {
		IntVariable var = new IntVariable(value, getId());
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
