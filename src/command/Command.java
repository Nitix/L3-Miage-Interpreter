package command;

import java.io.Serializable;
import java.util.UUID;

import exception.IncorrectConversionException;
import exception.InterpreterException;
import AST.Data;
import AST.Node;
import AST.variable.Variable;

public abstract class Command implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -4326199918997155358L;

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
			throws InterpreterException, InterruptedException;

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
			throws InterpreterException, InterruptedException {
		throw new IncorrectMethodCallException(line, command);
	}

	public void partialExecute(Node node, Data data)
			throws InterpreterException, InterruptedException {
		throw new IncorrectMethodCallException(line, command);
	}

	public boolean hasValue(Data data) throws InexistantVariableException {
		return false;
	}

	public Variable getVariable(Data data) throws IncorrectMethodCallException, InexistantVariableException{
		throw new IncorrectMethodCallException();
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
