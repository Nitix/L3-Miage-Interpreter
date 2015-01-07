package environment.variable;

import environment.Fork;

/**
 * The Class EmptyVariable. An empty variable, used to declare a new variable
 */
public class EmptyVariable extends Variable {

	/**
	 * Instantiates a new empty variable.
	 *
	 * @param name
	 *            the name
	 */
	public EmptyVariable(String name) {
		super(name);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see ast.variable.Variable#copy()
	 */
	@Override
	public Variable copy() {
		return new EmptyVariable(getName());
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see ast.variable.Variable#isEmpty()
	 */
	@Override
	public boolean isEmpty() {
		return true;
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
