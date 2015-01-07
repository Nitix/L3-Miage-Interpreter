package command;

import environment.Data;
import environment.Node;
import environment.variable.Variable;
import exception.InterpreterException;

/**
 * The Class PrintCommand. Print a value Text, boolean, integer or fork name
 */
public class PrintCommand extends Command {

	/** The Constant serialVersionUID. */
	private static final long serialVersionUID = -3017889076037915767L;

	/** The is string. */
	private boolean isString;

	/**
	 * Instantiates a new prints the command.
	 *
	 * @param line
	 *            the line
	 * @param command
	 *            the command
	 * @param isString
	 *            the is string
	 */
	public PrintCommand(int line, String command, boolean isString) {
		super(line, command);
		this.isString = isString;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see command.Command#execute(ast.Node, ast.Data)
	 */
	@Override
	public void execute(Node node, Data data) throws InterpreterException,
			InterruptedException {
		if (this.isString) {
			System.out.println(this.getCommand().replaceAll("\"", ""));
		} else {
			Variable var = data.getVariable(this.getCommand(), this.getLine());
			if (var.isBooleanValue()) {
				System.out.println(var.getBooleanValue());
			} else if (var.isIntValue()) {
				System.out.println(var.getIntValue());
			} else if (var.isFork()) {
				System.out.println(var.getForkName());
			}
		}
	}

}
