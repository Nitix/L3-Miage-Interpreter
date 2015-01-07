package command;

import java.util.UUID;

import exception.InterpreterException;
import AST.Data;
import AST.Fork;
import AST.Node;
import AST.variable.ForkVariable;
import AST.variable.Variable;

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
