package command;

import parser.IncorrectConversionException;
import AST.Data;
import AST.Fork;
import AST.InexistantVariableException;
import AST.Node;
import AST.VariableAlreadyExistException;

public class VariableCommand extends Command {

	private String name;

	@Override
	public String toString() {
		return "Variable [name=" + name + "]";
	}

	public VariableCommand() {

	}

	public VariableCommand(String command) {
		this.name = command;
	}

	@Override
	public void execute(Node node, Data data)
			throws VariableAlreadyExistException, IncorrectConversionException,
			IncorrectMethodCallException, InexistantVariableException,
			VariableNotDeclaredException {
		if (!data.isDeclaredVariable(name)) {
			throw new VariableNotDeclaredException();
		}
	}

	public boolean hasValue(Data data) {
		return true;
	}

	public boolean hasIntValue(Data data) throws InexistantVariableException {
		return data.getVariable(name).isIntValue();
	}

	public int getIntValue(Data data) throws IncorrectMethodCallException,
			InexistantVariableException {
		return data.getVariable(name).getIntValue();
	}

	public boolean hasBooleanValue(Data data)
			throws InexistantVariableException {
		return data.getVariable(name).isBooleanValue();
	}

	public boolean getBooleanValue(Data data)
			throws IncorrectMethodCallException, InexistantVariableException {
		return data.getVariable(name).getBooleanValue();
	}

	public boolean isFork(Data data) throws InexistantVariableException {
		return data.getVariable(name).isFork();
	}

	public String getForkName(Data data) throws IncorrectMethodCallException,
			InexistantVariableException {
		return data.getVariable(name).getForkName();
	}
	
	public Fork getFork(Data data) throws IncorrectMethodCallException,
	InexistantVariableException {
		return data.getVariable(name).getFork();
	}
}
