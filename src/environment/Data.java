package environment;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Set;

import command.InexistentVariableException;
import command.VariableAlreadyExistException;
import environment.variable.EmptyVariable;
import environment.variable.Variable;

/**
 * The Class Data. Contains the variables of the environments Indicate if the
 * program is stopped or not via return
 */
public class Data implements Serializable {

	/** The Constant serialVersionUID. */
	private static final long serialVersionUID = -6729835640691409304L;

	/** The variables. */
	private HashMap<String, Variable> variables = new HashMap<>();

	/** The is terminated. */
	private boolean isTerminated = false;

	/**
	 * Checks if it's a declared variable.
	 *
	 * @param command
	 *            the command
	 * @return true, if it's a declared variable
	 */
	public boolean isDeclaredVariable(String command) {
		return this.variables.containsKey(command);
	}

	/**
	 * Declare variable.
	 *
	 * @param nameVariable
	 *            the name variable
	 * @param line
	 *            the line
	 * @throws VariableAlreadyExistException
	 *             the variable already exist exception
	 */
	public void declareVariable(String nameVariable, int line)
			throws VariableAlreadyExistException {
		if (this.variables.containsKey(nameVariable))
			throw new VariableAlreadyExistException(line, nameVariable);
		Variable var = new EmptyVariable(nameVariable);
		this.addVariable(nameVariable, var);
	}

	/**
	 * Removes the variable.
	 *
	 * @param nameVariable
	 *            the name variable
	 */
	public void removeVariable(String nameVariable) {
		this.variables.remove(nameVariable);
	}

	/**
	 * Adds the variable. If the variable was already added, the old value is
	 * replaced.
	 *
	 * @param name
	 *            the name
	 * @param var
	 *            the var
	 */
	public void addVariable(String name, Variable var) {
		this.variables.put(name, var);
	}

	/**
	 * Deep clone.
	 *
	 * @return the data
	 */
	public Data deepClone() {
		Data data = new Data();
		Set<String> keys = variables.keySet();
		for (String key : keys) {
			data.addVariable(key, variables.get(key).copy());
		}
		return data;
	}

	/**
	 * Gets the variable.
	 *
	 * @param nameVariable
	 *            the name variable
	 * @param line
	 *            the line
	 * @return the variable
	 * @throws InexistentVariableException
	 *             the inexistent variable exception
	 */
	public Variable getVariable(String nameVariable, int line)
			throws InexistentVariableException {
		if (!this.variables.containsKey(nameVariable))
			throw new InexistentVariableException(line, nameVariable);
		return this.variables.get(nameVariable);
	}

	/**
	 * Checks if it's a terminated.
	 *
	 * @return true, if it's a terminated
	 */
	public boolean isTerminated() {
		return isTerminated;
	}

	/**
	 * Sets the terminated.
	 *
	 * @param terminated
	 *            the new terminated
	 */
	public void setTerminated(boolean terminated) {
		this.isTerminated = terminated;
	}

	/**
	 * Remplace variable.
	 *
	 * @param newVar
	 *            the new var
	 */
	public void remplaceVariable(Variable newVar) {
		this.variables.put(newVar.getName(), newVar);
	}

}
