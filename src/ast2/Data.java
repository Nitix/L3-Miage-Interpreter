package ast2;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Set;

import ast2.variable.EmptyVariable;
import ast2.variable.Variable;
import command.InexistantVariableException;
import command.VariableAlreadyExistException;

public class Data implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -6729835640691409304L;
	private HashMap<String, Variable> variables = new HashMap<>();
	private boolean isTerminated = false;

	public boolean isDeclaredVariable(String command) {
		return this.variables.containsKey(command);
	}

	public void declareVariable(String nameVariable, int line)
			throws VariableAlreadyExistException {
		if (this.variables.containsKey(nameVariable))
			throw new VariableAlreadyExistException(line, nameVariable);
		Variable var = new EmptyVariable(nameVariable);
		this.addVariable(nameVariable, var);
	}

	public void removeVariable(String nameVariable) {
		this.variables.remove(nameVariable);
	}

	public void addVariable(String name, Variable var) {
		this.variables.put(name, var);
	}

	public Data deepClone() {
		Data data = new Data();
		Set<String> keys = variables.keySet();
		for (String key : keys) {
			data.addVariable(key, variables.get(key).copy());
		}
		return data;
	}

	public Variable getVariable(String nameVariable, int line)
			throws InexistantVariableException {
		if (!this.variables.containsKey(nameVariable))
			throw new InexistantVariableException(line, nameVariable);
		return this.variables.get(nameVariable);
	}

	public boolean isTerminated() {
		return isTerminated;
	}

	public void setTerminated(boolean terminated) {
		this.isTerminated = terminated;
	}

	public void remplaceVariable(Variable newVar) {
		this.variables.put(newVar.getName(), newVar);
	}

}
