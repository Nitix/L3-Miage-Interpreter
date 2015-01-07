package environment.variable;

import command.IncorrectMethodCallException;
import environment.Fork;

/**
 * The Class AliasVariable. Alias of an another variable, can modify it's proper
 * var, it the target has changed of type
 */
public class AliasVariable extends Variable {

	/** The var. */
	Variable var;

	/** The old. */
	Variable old;

	/**
	 * Instantiates a new alias variable.
	 *
	 * @param var
	 *            the var
	 * @param name
	 *            the name
	 */
	public AliasVariable(Variable var, String name) {
		super(name);
		this.var = var;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see ast.variable.Variable#copy()
	 */
	@Override
	public Variable copy() {
		return new AliasVariable(var, getName());
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see ast.variable.Variable#isAlias()
	 */
	@Override
	public boolean isAlias() {
		return true;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see ast.variable.Variable#setIntValue(int)
	 */
	@Override
	public Variable setIntValue(int intValue) {
		if (var.isAlias())
			return var.setIntValue(intValue);
		else
			var = var.setIntValue(intValue);
		return var;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see ast.variable.Variable#setBooleanValue(boolean)
	 */
	@Override
	public Variable setBooleanValue(boolean booleanValue) {
		if (var.isAlias())
			return var.setBooleanValue(booleanValue);
		else
			var = var.setBooleanValue(booleanValue);
		return var;

	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see ast.variable.Variable#setFork(ast.Fork, java.lang.String)
	 */
	@Override
	public Variable setFork(Fork fork, String forkName) {
		if (var.isAlias())
			return var.setFork(fork, forkName);
		else
			var = var.setFork(fork, forkName);
		return var;

	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see ast.variable.Variable#getIntValue()
	 */
	@Override
	public int getIntValue() throws IncorrectMethodCallException {
		return var.getIntValue();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see ast.variable.Variable#getFork()
	 */
	@Override
	public Fork getFork() throws IncorrectMethodCallException {
		return var.getFork();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see ast.variable.Variable#getForkName()
	 */
	@Override
	public String getForkName() throws IncorrectMethodCallException {
		return var.getForkName();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see ast.variable.Variable#getBooleanValue()
	 */
	@Override
	public boolean getBooleanValue() throws IncorrectMethodCallException {
		return var.getBooleanValue();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see ast.variable.Variable#isIntValue()
	 */
	@Override
	public boolean isIntValue() {
		return var.isIntValue();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see ast.variable.Variable#isBooleanValue()
	 */
	@Override
	public boolean isBooleanValue() {
		return var.isBooleanValue();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see ast.variable.Variable#isFork()
	 */
	@Override
	public boolean isFork() {
		return var.isFork();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see ast.variable.Variable#isEmpty()
	 */
	@Override
	public boolean isEmpty() {
		return var.isEmpty();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see ast.variable.Variable#getAlias()
	 */
	@Override
	public Variable getAlias() throws IncorrectMethodCallException {
		return var;
	}
}
