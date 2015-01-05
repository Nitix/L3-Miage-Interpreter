package command;

import java.util.UUID;

import exception.IncorrectConversionException;
import AST.Data;
import AST.Fork;
import AST.Node;

public abstract class Command {

	private String uuid;
	
	private int line;
	
	private String command;

	public Command(int line, String command) {
		this.line = line;
		this.command = command;
		this.uuid = UUID.randomUUID().toString();
	}

	public String getUuid() {
		return uuid;
	}

	public abstract void execute(Node node, Data data)
			throws VariableAlreadyExistException, IncorrectConversionException,
			IncorrectMethodCallException, InexistantVariableException,
			VariableNotDeclaredException;

	/**
	 * Execute the command but do a partial execute on the first child
	 * 
	 * @param node
	 * @param data
	 * @param child
	 * @throws VariableAlreadyExistException
	 * @throws IncorrectConversionException
	 * @throws IncorrectMethodCallException
	 * @throws InexistantVariableException
	 * @throws VariableNotDeclaredException
	 */
	public void executeAtChilds(Node node, Data data, Node child)
			throws VariableAlreadyExistException, IncorrectConversionException,
			IncorrectMethodCallException, InexistantVariableException,
			VariableNotDeclaredException {
		throw new IncorrectMethodCallException(line, command);
	}

	public void partialExecute(Node node, Data data)
			throws VariableAlreadyExistException, IncorrectConversionException,
			IncorrectMethodCallException, InexistantVariableException,
			VariableNotDeclaredException {
		throw new IncorrectMethodCallException(line, command);
	}

	public boolean hasValue(Data data) throws InexistantVariableException {
		return false;
	}

	public boolean hasIntValue(Data data) throws InexistantVariableException {
		return false;
	}

	public int getIntValue(Data data) throws IncorrectMethodCallException,
			InexistantVariableException {
		throw new IncorrectMethodCallException(line, command);
	}

	public boolean hasBooleanValue(Data data)
			throws InexistantVariableException {
		return false;
	}

	public boolean getBooleanValue(Data data)
			throws IncorrectMethodCallException, InexistantVariableException {
		throw new IncorrectMethodCallException(line, command);

	}

	public boolean isFork(Data data) throws InexistantVariableException {
		return false;
	}

	public String getForkName(Data data) throws IncorrectMethodCallException,
			InexistantVariableException {
		throw new IncorrectMethodCallException(line, command);
	}

	public Fork getFork(Data data) throws IncorrectMethodCallException,
	InexistantVariableException {
		throw new IncorrectMethodCallException(line, command);
	}
	
	public int getLine() {
		return line;
	}

	public String getCommand() {
		return command;
	}

	public void removeVariable(Data data) {
		data.removeVariable(uuid);
	}
}
