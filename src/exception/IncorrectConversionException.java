package exception;

/**
 * The Class IncorrectConversionException. Exception thrown when an incorrect
 * conversion has occurred
 */
public class IncorrectConversionException extends InterpreterException {

	/** The Constant serialVersionUID. */
	private static final long serialVersionUID = 56780275321844127L;

	/**
	 * Instantiates a new incorrect conversion exception.
	 *
	 * @param line
	 *            the line
	 * @param command
	 *            the command
	 * @param message
	 *            the message
	 */
	public IncorrectConversionException(int line, String command, String message) {
		super(line, command, message);
	}

	/**
	 * Instantiates a new incorrect conversion exception.
	 *
	 * @param line
	 *            the line
	 * @param command
	 *            the command
	 */
	public IncorrectConversionException(int line, String command) {
		super(line, command);
	}

}
