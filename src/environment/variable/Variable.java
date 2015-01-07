package environment.variable;

import command.IncorrectMethodCallException;
import environment.Fork;

/**
 * The Class Variable. Abstract class for type of variable
 */
public abstract class Variable {

	/** The name. */
	private String name;

	/**
	 * Instantiates a new variable.
	 *
	 * @param name
	 *            the name
	 */
	public Variable(String name) {
		this.name = name;
	}

	/**
	 * Sets the int value.
	 *
	 * @param intValue
	 *            the int value
	 * @return the variable
	 */
	public abstract Variable setIntValue(int intValue);

	/**
	 * Sets the boolean value.
	 *
	 * @param booleanValue
	 *            the boolean value
	 * @return the variable
	 */
	public abstract Variable setBooleanValue(boolean booleanValue);

	/**
	 * Sets the fork.
	 *
	 * @param fork
	 *            the fork
	 * @param forkName
	 *            the fork name
	 * @return the variable
	 */
	public abstract Variable setFork(Fork fork, String forkName);

	/**
	 * Gets the int value.
	 *
	 * @return the int value
	 * @throws IncorrectMethodCallException
	 *             the incorrect method call exception
	 */
	public int getIntValue() throws IncorrectMethodCallException {
		throw new IncorrectMethodCallException(-1, name);
	}

	/**
	 * Gets the fork.
	 *
	 * @return the fork
	 * @throws IncorrectMethodCallException
	 *             the incorrect method call exception
	 */
	public Fork getFork() throws IncorrectMethodCallException {
		throw new IncorrectMethodCallException(-1, name);
	}

	/**
	 * Gets the fork name.
	 *
	 * @return the fork name
	 * @throws IncorrectMethodCallException
	 *             the incorrect method call exception
	 */
	public String getForkName() throws IncorrectMethodCallException {
		throw new IncorrectMethodCallException(-1, name);
	}

	/**
	 * Gets the boolean value.
	 *
	 * @return the boolean value
	 * @throws IncorrectMethodCallException
	 *             the incorrect method call exception
	 */
	public boolean getBooleanValue() throws IncorrectMethodCallException {
		throw new IncorrectMethodCallException(-1, name);
	}

	/**
	 * Gets the alias.
	 *
	 * @return the alias
	 * @throws IncorrectMethodCallException
	 *             the incorrect method call exception
	 */
	public Variable getAlias() throws IncorrectMethodCallException {
		throw new IncorrectMethodCallException(-1, name);
	}

	/**
	 * Checks if it's an int value.
	 *
	 * @return true, if it's an int value
	 */
	public boolean isIntValue() {
		return false;
	}

	/**
	 * Checks if it's a boolean value.
	 *
	 * @return true, if it's a boolean value
	 */
	public boolean isBooleanValue() {
		return false;
	}

	/**
	 * Checks if it's a fork.
	 *
	 * @return true, if it's a fork
	 */
	public boolean isFork() {
		return false;
	}

	/**
	 * Checks if it's an empty variable.
	 *
	 * @return true, if it's an empty variable
	 */
	public boolean isEmpty() {
		return false;
	}

	/**
	 * Checks if it's an alias.
	 *
	 * @return true, if it's an alias
	 */
	public boolean isAlias() {
		return false;
	}

	/**
	 * Copy the variable to a new one.
	 *
	 * @return the variable
	 */
	public abstract Variable copy();

	/**
	 * Gets the name of the variable.
	 *
	 * @return the name
	 */
	public String getName() {
		return this.name;
	}
}
