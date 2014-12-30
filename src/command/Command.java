package command;

import java.util.UUID;

import parser.IncorrectConversionException;
import AST.Data;
import AST.Fork;
import AST.InexistantVariableException;
import AST.Node;
import AST.VariableAlreadyExistException;

public abstract class Command {

	private String uuid;

	public Command() {
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
		throw new IncorrectMethodCallException();
	}

	public void partialExecute(Node node, Data data)
			throws VariableAlreadyExistException, IncorrectConversionException,
			IncorrectMethodCallException, InexistantVariableException,
			VariableNotDeclaredException {
		throw new IncorrectMethodCallException();
	}

	public boolean hasValue(Data data) throws InexistantVariableException {
		return false;
	}

	public boolean hasIntValue(Data data) throws InexistantVariableException {
		return false;
	}

	public int getIntValue(Data data) throws IncorrectMethodCallException,
			InexistantVariableException {
		throw new IncorrectMethodCallException();
	}

	public boolean hasBooleanValue(Data data)
			throws InexistantVariableException {
		return false;
	}

	public boolean getBooleanValue(Data data)
			throws IncorrectMethodCallException, InexistantVariableException {
		throw new IncorrectMethodCallException();

	}

	public boolean isFork(Data data) throws InexistantVariableException {
		return false;
	}

	public String getForkName(Data data) throws IncorrectMethodCallException,
			InexistantVariableException {
		throw new IncorrectMethodCallException();
	}

	public Fork getFork(Data data) throws IncorrectMethodCallException,
	InexistantVariableException {
		throw new IncorrectMethodCallException();
	}
	
	public void removeVariable(Data data) {
		data.removeVariable(uuid);
	}
}
