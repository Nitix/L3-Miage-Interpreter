package environment.variable;

import command.IncorrectMethodCallException;
import environment.Fork;

/**
 * The Class BooleanVariable. Contains a boolean value
 */
public class BooleanVariable extends Variable {

	/** The booleanvalue. */
	private boolean booleanvalue;

	/**
	 * Instantiates a new boolean variable.
	 *
	 * @param name
	 *            the name
	 */
	public BooleanVariable(String name) {
		super(name);
	}

	/**
	 * Instantiates a new boolean variable.
	 *
	 * @param boolvalue
	 *            the boolvalue
	 * @param name
	 *            the name
	 */
	public BooleanVariable(boolean boolvalue, String name) {
		super(name);
		this.booleanvalue = boolvalue;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see ast.variable.Variable#setBooleanValue(boolean)
	 */
	@Override
	public Variable setBooleanValue(boolean booleanValue) {
		this.booleanvalue = booleanValue;
		return this;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see ast.variable.Variable#getBooleanValue()
	 */
	@Override
	public boolean getBooleanValue() throws IncorrectMethodCallException {
		return booleanvalue;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see ast.variable.Variable#isBooleanValue()
	 */
	@Override
	public boolean isBooleanValue() {
		return true;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see ast.variable.Variable#copy()
	 */
	@Override
	public Variable copy() {
		return new BooleanVariable(booleanvalue, getName());
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see ast.variable.Variable#setIntValue(int)
	 */
	@Override
	public Variable setIntValue(int intValue) {
		return new IntVariable(intValue, getName());
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

}
