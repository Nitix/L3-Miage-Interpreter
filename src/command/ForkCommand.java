package command;

import java.util.UUID;

import environment.Data;
import environment.Fork;
import environment.Node;
import environment.variable.ForkVariable;
import environment.variable.Variable;
import exception.InterpreterException;

/**
 * The Class ForkCommand. Create a fork and redirect the fork to the correct
 * command The fork will have his proper environment
 */
public class ForkCommand extends Command {

	/** The Constant serialVersionUID. */
	private static final long serialVersionUID = -8011490572047535187L;

	/**
	 * Instantiates a new fork command.
	 *
	 * @param line
	 *            the line
	 */
	public ForkCommand(int line) {
		super(line, "fork()");
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "ForkCommand []";
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see command.Command#execute(ast.Node, ast.Data)
	 */
	@Override
	public void execute(Node node, Data data) throws InterpreterException,
			InterruptedException {
		Variable var = new ForkVariable(getId());
		data.addVariable(getId(), var);
		Data duplicate = data.deepClone();
		Fork fork = new Fork(node.getFather(), duplicate);
		fork.start();
		var.setFork(fork, UUID.randomUUID().toString());
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see command.Command#hasValue(ast.Data)
	 */
	@Override
	public boolean hasValue(Data data) throws InexistentVariableException {
		return true;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see command.Command#getVariable(ast.Data)
	 */
	public Variable getVariable(Data data) throws InexistentVariableException {
		return data.getVariable(getId(), getLine());
	}
}
