package command;

import environment.Data;
import environment.Fork;
import environment.Node;
import environment.variable.IntVariable;
import environment.variable.Variable;
import exception.InterpreterException;

/**
 * The Class WaitCommand. Wait a fork child to terminate Return a value of the
 * status of the child -1 if it there is an error 0 if it's OK
 */
public class WaitCommand extends Command {

	/** The Constant serialVersionUID. */
	private static final long serialVersionUID = -553468386406316823L;

	/** The fork name. */
	private String forkName;

	/**
	 * Instantiates a new wait command.
	 *
	 * @param forkName
	 *            the fork name
	 * @param line
	 *            the line
	 */
	public WaitCommand(String forkName, int line) {
		super(line, "Wait()");
		this.forkName = forkName;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see command.Command#execute(ast.Node, ast.Data)
	 */
	@Override
	public void execute(Node node, Data data) throws InterpreterException,
			InterruptedException {
		Variable forkVar = data.getVariable(this.forkName, this.getLine());
		if (!forkVar.isFork()) {
			throw new IncorrectMethodCallException(this.getLine(), "wait()",
					"Can not call wait on non fork variable");
		}
		Fork fork = forkVar.getFork();
		if (fork == null) {
			throw new IncorrectMethodCallException(this.getLine(), "wait()",
					"Fork variable is empty");
		}
		fork.join();
		Variable var = new IntVariable(getId());

		if (fork.hasException()) {
			var.setIntValue(-1);
		} else {
			var.setIntValue(0);
		}
		data.addVariable(getId(), var);
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
