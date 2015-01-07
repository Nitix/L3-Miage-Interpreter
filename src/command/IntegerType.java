package command;

import exception.InterpreterException;
import AST.Data;
import AST.Node;
import AST.variable.IntVariable;
import AST.variable.Variable;

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
		IntVariable var = new IntVariable(value, getUuid());
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
