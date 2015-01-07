package command;

import environment.Data;
import environment.Node;
import exception.InterpreterException;

/**
 * The Class ReturnCommand. Stop the execution of the child
 */
public class ReturnCommand extends Command {

	/** The Constant serialVersionUID. */
	private static final long serialVersionUID = -5070762742763862974L;

	/**
	 * Instantiates a new return command.
	 *
	 * @param line
	 *            the line
	 */
	public ReturnCommand(int line) {
		super(line, "return");
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see command.Command#execute(ast.Node, ast.Data)
	 */
	@Override
	public void execute(Node node, Data data) throws InterpreterException,
			InterruptedException {
		data.setTerminated(true);
	}

}
