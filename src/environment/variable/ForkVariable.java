package environment.variable;

import command.IncorrectMethodCallException;
import environment.Fork;

/**
 * The Class ForkVariable. Contains informations about a fork
 */
public class ForkVariable extends Variable {

	/** The fork name. */
	private String forkName;

	/** The fork. */
	private Fork fork;

	/**
	 * Instantiates a new fork variable.
	 *
	 * @param name
	 *            the name
	 */
	public ForkVariable(String name) {
		super(name);
	}

	/**
	 * Instantiates a new fork variable.
	 *
	 * @param fork
	 *            the fork
	 * @param forkname
	 *            the forkname
	 * @param name
	 *            the name
	 */
	public ForkVariable(Fork fork, String forkname, String name) {
		super(name);
		this.fork = fork;
		this.forkName = forkname;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see ast.variable.Variable#copy()
	 */
	@Override
	public Variable copy() {
		return new ForkVariable(fork, forkName, getName());
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see ast.variable.Variable#setFork(ast.Fork, java.lang.String)
	 */
	@Override
	public Variable setFork(Fork fork, String forkName) {
		this.fork = fork;
		this.forkName = forkName;
		return this;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see ast.variable.Variable#getFork()
	 */
	@Override
	public Fork getFork() throws IncorrectMethodCallException {
		return fork;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see ast.variable.Variable#getForkName()
	 */
	@Override
	public String getForkName() throws IncorrectMethodCallException {
		return forkName;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see ast.variable.Variable#isFork()
	 */
	@Override
	public boolean isFork() {
		return true;
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

	/*
	 * (non-Javadoc)
	 * 
	 * @see ast.variable.Variable#setIntValue(int)
	 */
	@Override
	public Variable setIntValue(int intValue) {
		return new IntVariable(intValue, getName());
	}
}
