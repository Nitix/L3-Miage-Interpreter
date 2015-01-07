package environment.variable;

import command.IncorrectMethodCallException;
import environment.Fork;

/**
 * The Class IntVariable. Contains a integer value
 */
public class IntVariable extends Variable {

	/** The value. */
	private int value;

	/**
	 * Instantiates a new int variable.
	 *
	 * @param name
	 *            the name
	 */
	public IntVariable(String name) {
		super(name);
	}

	/**
	 * Instantiates a new int variable.
	 *
	 * @param value
	 *            the value
	 * @param name
	 *            the name
	 */
	public IntVariable(int value, String name) {
		super(name);
		this.value = value;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see ast.variable.Variable#setIntValue(int)
	 */
	@Override
	public Variable setIntValue(int intValue) {
		this.value = intValue;
		return this;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see ast.variable.Variable#isIntValue()
	 */
	@Override
	public boolean isIntValue() {
		return true;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see ast.variable.Variable#getIntValue()
	 */
	@Override
	public int getIntValue() throws IncorrectMethodCallException {
		return value;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see ast.variable.Variable#copy()
	 */
	@Override
	public Variable copy() {
		return new IntVariable(this.value, getName());
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see ast.variable.Variable#setFork(ast.Fork, java.lang.String)
	 */
	@Override
	public Variable setFork(Fork fork, String forkName) {
		return new ForkVariable(fork, forkName, getName());
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see ast.variable.Variable#setBooleanValue(boolean)
	 */
	@Override
	public Variable setBooleanValue(boolean booleanValue) {
		return new BooleanVariable(booleanValue, getName());
	}

}
