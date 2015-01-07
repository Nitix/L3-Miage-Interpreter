package command;

import environment.Data;
import environment.Node;
import exception.InterpreterException;

/**
 * The Class DeclareCommand. Declare a new variable that can be used next
 */
public class DeclareCommand extends Command {

	/** The Constant serialVersionUID. */
	private static final long serialVersionUID = -8126510234681380206L;

	/** The name variable. */
	private String nameVariable;

	/**
	 * Instantiates a new declare command.
	 *
	 * @param name
	 *            the name
	 * @param line
	 *            the line
	 */
	public DeclareCommand(String name, int line) {
		super(line, "let " + name);
		this.nameVariable = name;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "DeclareCommand [nameVariable=" + nameVariable + "]";
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see command.Command#execute(ast.Node, ast.Data)
	 */
	@Override
	public void execute(Node node, Data data) throws InterpreterException,
			InterruptedException {
		data.declareVariable(nameVariable, this.getLine());
		node.getChilds().get(0).execute(data);
		node.getChilds().get(0).removeVariable(data);
		data.removeVariable(nameVariable);
	}
}
