package AST;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Set;

import command.InexistantVariableException;
import command.VariableAlreadyExistException;

public class Data implements Serializable {

	private HashMap<String, Variable> variables = new HashMap<>();

	public boolean isDeclaredVariable(String command) {
		return this.variables.containsKey(command);
	}

	public void declareVariable(String nameVariable, int line)
			throws VariableAlreadyExistException {
		if (this.variables.containsKey(nameVariable))
			throw new VariableAlreadyExistException(line, nameVariable);
		Variable var = new Variable();
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

}
