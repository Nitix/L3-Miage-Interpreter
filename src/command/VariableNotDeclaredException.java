package command;

/**
 * The Class VariableNotDeclaredException. Exception thrown when the variable
 * does not exist but the programm want to acces to it
 */
public class VariableNotDeclaredException extends CommandException {

	/** The Constant serialVersionUID. */
	private static final long serialVersionUID = -3038195950320415832L;

	/**
	 * Instantiates a new variable not declared exception.
	 *
	 * @param line
	 *            the line
	 * @param command
	 *            the command
	 * @param message
	 *            the message
	 */
	public VariableNotDeclaredException(int line, String command, String message) {
		super(line, command, message);
	}

	/**
	 * Instantiates a new variable not declared exception.
	 *
	 * @param line
	 *            the line
	 * @param command
	 *            the command
	 */
	public VariableNotDeclaredException(int line, String command) {
		super(line, command);
	}

}
