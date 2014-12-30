package command;

import parser.IncorrectConversionException;
import AST.Data;
import AST.InexistantVariableException;
import AST.Node;
import AST.VariableAlreadyExistException;

public class BooleanType extends Command {

	private boolean value;

	public BooleanType(boolean value) {
		this.value = value;
	}

	public BooleanType(String command) {
		this.value = Boolean.parseBoolean(command);
	}

	@Override
	public void execute(Node node, Data data)
			throws VariableAlreadyExistException, IncorrectConversionException,
			IncorrectMethodCallException, InexistantVariableException {
		//Do Nothing
	}

	@Override
	public boolean hasValue(Data data) throws InexistantVariableException {
		return true;
	}

	@Override
	public boolean hasBooleanValue(Data data)
			throws InexistantVariableException {
		return false;
	}

	@Override
	public boolean getBooleanValue(Data data)
			throws IncorrectMethodCallException, InexistantVariableException {
		return  value;
	}
	
	
}
