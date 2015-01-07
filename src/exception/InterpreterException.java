package exception;

/**
 * The Class InterpreterException. Exception thrown when an error has occurred
 * with the parser or the execution
 */
public class InterpreterException extends Exception {

	/** The Constant serialVersionUID. */
	private static final long serialVersionUID = 681885188024846011L;

	/** The line number. */
	private int lineNumber = 0;

	/** The command. */
	private String command = "";

	/**
	 * Instantiates a new interpreter exception.
	 *
	 * @param line
	 *            the line
	 * @param command
	 *            the command
	 */
	public InterpreterException(int line, String command) {
		super();
		this.lineNumber = line;
		this.command = command;
	}

	/**
	 * Instantiates a new interpreter exception.
	 *
	 * @param line
	 *            the line
	 * @param command
	 *            the command
	 * @param message
	 *            the message
	 */
	public InterpreterException(int line, String command, String message) {
		super(message);
		this.lineNumber = line;
		this.command = command;
	}

	/**
	 * Gets the line number.
	 *
	 * @return the line number
	 */
	public int getLineNumber() {
		return lineNumber;
	}

	/**
	 * Gets the command.
	 *
	 * @return the command
	 */
	public String getCommand() {
		return command;
	}
}
