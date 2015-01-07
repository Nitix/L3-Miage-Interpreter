package parser;

/**
 * The Class SyntaxErrorException. A syntax error has occured
 */
public class SyntaxErrorException extends ParserException {

	/**
	 * Instantiates a new syntax error exception.
	 *
	 * @param line
	 *            the line
	 * @param command
	 *            the command
	 */
	public SyntaxErrorException(int line, String command) {
		super(line, command);
	}

	/**
	 * Instantiates a new syntax error exception.
	 *
	 * @param line
	 *            the line
	 * @param command
	 *            the command
	 * @param message
	 *            the message
	 */
	public SyntaxErrorException(int line, String command, String message) {
		super(line, command, message);
	}

	/** The Constant serialVersionUID. */
	private static final long serialVersionUID = 7313109686570940386L;

}
