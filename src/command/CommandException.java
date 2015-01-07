package command;

import exception.InterpreterException;

/**
 * The Class CommandException. Exception thrown when there is an error when
 * executing the program
 */
public class CommandException extends InterpreterException {

	/** The Constant serialVersionUID. */
	private static final long serialVersionUID = -6347426133044974110L;

	/**
	 * Instantiates a new command exception.
	 *
	 * @param line
	 *            the line
	 * @param command
	 *            the command
	 */
	public CommandException(int line, String command) {
		super(line, command);
	}

	/**
	 * Instantiates a new command exception.
	 *
	 * @param line
	 *            the line
	 * @param command
	 *            the command
	 * @param message
	 *            the message
	 */
	public CommandException(int line, String command, String message) {
		super(line, command, message);
	}

}
