package command;

import exception.IncorrectConversionException;
import AST.Data;
import AST.Node;

public class IntegerType extends Command {

	@Override
	public String toString() {
		return "IntegerType [value=" + value + "]";
	}

	private int value;

	public IntegerType(int value, int line) {
		super(line, ""+value);
		this.value = value;
	}

	public IntegerType(String value, int line) {
		super(line, value);
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
