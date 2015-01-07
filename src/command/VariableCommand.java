package command;

import environment.Data;
import environment.Node;
import environment.variable.Variable;
import exception.InterpreterException;

/**
 * The Class VariableCommand. Give the name of the variable to access at
 */
public class VariableCommand extends Command {

	/** The Constant serialVersionUID. */
	private static final long serialVersionUID = 1141243422421491031L;

	/** The name. */
	private String name;

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "Variable [name=" + name + "]";
	}

	/**
	 * Instantiates a new variable command.
	 *
	 * @param command
	 *            the command
	 * @param line
	 *            the line
	 */
	public VariableCommand(String command, int line) {
		super(line, command);
		this.name = command;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see command.Command#execute(ast.Node, ast.Data)
	 */
	@Override
	public void execute(Node node, Data data) throws InterpreterException,
			InterruptedException {
		if (!data.isDeclaredVariable(name)) {
			throw new VariableNotDeclaredException(this.getLine(),
					this.getCommand());
		}
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see command.Command#hasValue(ast.Data)
	 */
	public boolean hasValue(Data data) {
		return true;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see command.Command#getVariable(ast.Data)
	 */
	public Variable getVariable(Data data) throws InexistentVariableException {
		return data.getVariable(name, getLine());
	}
}
