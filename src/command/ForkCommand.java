package command;

import java.util.UUID;

import ast.Data;
import ast.Fork;
import ast.Node;
import ast.variable.ForkVariable;
import ast.variable.Variable;
import exception.InterpreterException;

public class ForkCommand extends Command {	

	/**
	 * 
	 */
	private static final long serialVersionUID = -8011490572047535187L;

	public ForkCommand(int line) {
		super(line, "fork()");
	}

	@Override
	public String toString() {
		return "ForkCommand []";
	}

	@Override
	public void execute(Node node, Data data)
			throws InterpreterException, InterruptedException {
		Variable var = new ForkVariable(getUuid());
		data.addVariable(getUuid(), var);
		Data duplicate = data.deepClone();
		Fork fork = new Fork(node.getFather(), duplicate);
		fork.start();
		var.setFork(fork, UUID.randomUUID().toString());
	}

	@Override
	public boolean hasValue(Data data) throws InexistantVariableException {
		return true;
	}
	
	public Variable getVariable(Data data) throws InexistantVariableException{
		return data.getVariable(getUuid(), getLine());
	}
}
