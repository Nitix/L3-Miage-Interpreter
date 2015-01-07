package command;

import ast2.Data;
import ast2.Node;
import ast2.variable.BooleanVariable;
import ast2.variable.Variable;
import exception.InterpreterException;

public class BooleanType extends Command {

	/**
	 * 
	 */
	private static final long serialVersionUID = 6271398765651851129L;
	private boolean value;

	public BooleanType(boolean value, int line) {
		super(line, Boolean.toString(value));
		this.value = value;
	}

	public BooleanType(String command, int line) {
		super(line, command);
		this.value = Boolean.parseBoolean(command);
	}

	@Override
	public void execute(Node node, Data data)
			throws InterpreterException, InexistantVariableException {
		BooleanVariable var = new BooleanVariable(value, getUuid());
		data.addVariable(getUuid(), var);
	}

	@Override
	public boolean hasValue(Data data) throws InexistantVariableException {
		return true;
	}	
	
	
	public Variable getVariable(Data data) throws InexistantVariableException{
		return data.getVariable(getUuid(), getLine());
	}
}
