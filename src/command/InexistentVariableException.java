package command;

/**
 * The Class InexistentVariableException. Exception thrown when he program try
 * to access to a inexistent variable
 */
public class InexistentVariableException extends CommandException {

	/** The Constant serialVersionUID. */
	private static final long serialVersionUID = 3212066130517005547L;

	/**
	 * Instantiates a new inexistent variable exception.
	 *
	 * @param line
	 *            the line
	 * @param command
	 *            the command
	 * @param message
	 *            the message
	 */
	public InexistentVariableException(int line, String command, String message) {
		super(line, command, message);
	}

	/**
	 * Instantiates a new inexistent variable exception.
	 *
	 * @param line
	 *            the line
	 * @param command
	 *            the command
	 */
	public InexistentVariableException(int line, String command) {
		super(line, command);
	}

}
