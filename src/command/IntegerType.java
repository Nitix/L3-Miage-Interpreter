package command;

import exception.InterpreterException;
import AST.Data;
import AST.Node;

public class IntegerType extends Command {

	/**
	 * 
	 */
	private static final long serialVersionUID = -39506532777110614L;

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
			throws InterpreterException, InterruptedException {
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
