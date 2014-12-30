package AST;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Set;

public class Data implements Serializable {

	private HashMap<String, Variable> variables = new HashMap<>();

	public boolean isDeclaredVariable(String command) {
		return this.variables.containsKey(command);
	}

	public void declareVariable(String nameVariable)
			throws VariableAlreadyExistException {
		if (this.variables.containsKey(nameVariable))
			throw new VariableAlreadyExistException();
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

	public Variable getVariable(String nameVariable)
			throws InexistantVariableException {
		if (!this.variables.containsKey(nameVariable))
			throw new InexistantVariableException();
		return this.variables.get(nameVariable);
	}

}
