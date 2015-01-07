package command;

import exception.InterpreterException;
import AST.Data;
import AST.Fork;
import AST.Node;

public class VariableCommand extends Command {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1141243422421491031L;
	private String name;

	@Override
	public String toString() {
		return "Variable [name=" + name + "]";
	}

	public VariableCommand(String command, int line) {
		super(line, command);
		this.name = command;
	}

	@Override
	public void execute(Node node, Data data)
			throws InterpreterException, InterruptedException {
		if (!data.isDeclaredVariable(name)) {
			throw new VariableNotDeclaredException(this.getLine(), this.getCommand());
		}
	}

	public boolean hasValue(Data data) {
		return true;
	}

	public boolean hasIntValue(Data data) throws InexistantVariableException {
		return data.getVariable(name, this.getLine()).isIntValue();
	}

	public int getIntValue(Data data) throws IncorrectMethodCallException,
			InexistantVariableException {
		return data.getVariable(name, this.getLine()).getIntValue();
	}

	public boolean hasBooleanValue(Data data)
			throws InexistantVariableException {
		return data.getVariable(name, this.getLine()).isBooleanValue();
	}

	public boolean getBooleanValue(Data data)
			throws IncorrectMethodCallException, InexistantVariableException {
		return data.getVariable(name, this.getLine()).getBooleanValue();
	}

	public boolean isFork(Data data) throws InexistantVariableException {
		return data.getVariable(name, this.getLine()).isFork();
	}

	public String getForkName(Data data) throws IncorrectMethodCallException,
			InexistantVariableException {
		return data.getVariable(name, this.getLine()).getForkName();
	}
	
	public Fork getFork(Data data) throws IncorrectMethodCallException,
	InexistantVariableException {
		return data.getVariable(name, this.getLine()).getFork();
	}
}
