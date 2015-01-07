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
		Variable var = new ForkVariable();
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

	@Override
	public boolean isFork(Data data) throws InexistantVariableException {
		return true;
	}

	@Override
	public String getForkName(Data data) throws IncorrectMethodCallException,
			InexistantVariableException {
		return data.getVariable(this.getUuid(), this.getLine()).getForkName();
	}

	@Override
	public Fork getFork(Data data) throws IncorrectMethodCallException,
			InexistantVariableException {
		return data.getVariable(this.getUuid(), this.getLine()).getFork();
	}


	
}
