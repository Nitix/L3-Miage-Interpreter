package command;

/**
 * The Class IncorrectMethodCallException. Exception thrown when a program try
 * to call incorrectly a method
 */
public class IncorrectMethodCallException extends CommandException {

	/** The Constant serialVersionUID. */
	private static final long serialVersionUID = 7479028456669159340L;

	/**
	 * Instantiates a new incorrect method call exception.
	 *
	 * @param line
	 *            the line
	 * @param command
	 *            the command
	 */
	public IncorrectMethodCallException(int line, String command) {
		super(line, command);
	}

	/**
	 * Instantiates a new incorrect method call exception.
	 *
	 * @param line
	 *            the line
	 * @param command
	 *            the command
	 * @param message
	 *            the message
	 */
	public IncorrectMethodCallException(int line, String command, String message) {
		super(line, command, message);
	}

	/**
	 * Instantiates a new incorrect method call exception.
	 */
	public IncorrectMethodCallException() {
		super(-1, "");
	}
}
