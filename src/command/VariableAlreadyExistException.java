package command;

/**
 * The Class VariableAlreadyExistException. Exception thrown when the variable
 * already exist and the program want to redeclare this variable
 */
public class VariableAlreadyExistException extends CommandException {

	/** The Constant serialVersionUID. */
	private static final long serialVersionUID = 5744956203912576361L;

	/**
	 * Instantiates a new variable already exist exception.
	 *
	 * @param line
	 *            the line
	 * @param command
	 *            the command
	 */
	public VariableAlreadyExistException(int line, String command) {
		super(line, command);
	}

	/**
	 * Instantiates a new variable already exist exception.
	 *
	 * @param line
	 *            the line
	 * @param command
	 *            the command
	 * @param message
	 *            the message
	 */
	public VariableAlreadyExistException(int line, String command,
			String message) {
		super(line, command, message);
	}

}
