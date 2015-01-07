package parser;

/**
 * The Class UnexpectedEndOfFileException. Exception thrown when the parser need
 * command but the file has ended
 */
public class UnexpectedEndOfFileException extends ParserException {

	/** The Constant serialVersionUID. */
	private static final long serialVersionUID = -3441504978847948647L;

	/**
	 * Instantiates a new unexpected end of file exception.
	 *
	 * @param line
	 *            the line
	 * @param command
	 *            the command
	 * @param message
	 *            the message
	 */
	public UnexpectedEndOfFileException(int line, String command, String message) {
		super(line, command, message);
	}

	/**
	 * Instantiates a new unexpected end of file exception.
	 *
	 * @param line
	 *            the line
	 * @param command
	 *            the command
	 */
	public UnexpectedEndOfFileException(int line, String command) {
		super(line, command);
	}

}
