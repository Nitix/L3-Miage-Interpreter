package command;

import exception.InterpreterException;
import AST.Data;
import AST.Node;

public class BooleanType extends Command {

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
		//Do Nothing
	}

	@Override
	public boolean hasValue(Data data) throws InexistantVariableException {
		return true;
	}

	@Override
	public boolean hasBooleanValue(Data data)
			throws InexistantVariableException {
		return true;
	}

	@Override
	public boolean getBooleanValue(Data data)
			throws IncorrectMethodCallException, InexistantVariableException {
		return  value;
	}
	
	
}
