package command;

import ast2.Data;
import ast2.Fork;
import ast2.Node;
import ast2.variable.Variable;
import exception.InterpreterException;

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

	public Variable getVariable(Data data) throws InexistantVariableException{
		return data.getVariable(name, getLine());
	}
}
