package command;

import parser.IncorrectConversionException;
import AST.Data;
import AST.InexistantVariableException;
import AST.Node;
import AST.VariableAlreadyExistException;

public class IntegerType extends Command {

	@Override
	public String toString() {
		return "IntegerType [value=" + value + "]";
	}

	private int value;

	public IntegerType(int value) {
		this.value = value;
	}

	public IntegerType(String value) {
		this.value = Integer.parseInt(value);
	}

	@Override
	public void execute(Node node, Data data)
			throws VariableAlreadyExistException, IncorrectConversionException,
			IncorrectMethodCallException, InexistantVariableException,
			VariableNotDeclaredException {
		//DO nothing
	}

	@Override
	public boolean hasValue(Data data) throws InexistantVariableException {
		return true;
	}

	@Override
	public boolean hasIntValue(Data data) throws InexistantVariableException {
		return true;
	}

	@Override
	public int getIntValue(Data data) throws IncorrectMethodCallException,
			InexistantVariableException {
		return value;
	}
	
	
	
}
