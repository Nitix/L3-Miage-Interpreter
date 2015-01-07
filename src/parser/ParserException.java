package parser;

import exception.InterpreterException;

/**
 * The Class ParserException. Exception thrown when an error occurs when parsing
 * the program
 */
public class ParserException extends InterpreterException {

	/** The Constant serialVersionUID. */
	private static final long serialVersionUID = -2770726136437562124L;

	/**
	 * Instantiates a new parser exception.
	 *
	 * @param line
	 *            the line
	 * @param command
	 *            the command
	 * @param message
	 *            the message
	 */
	public ParserException(int line, String command, String message) {
		super(line, command, message);
	}

	/**
	 * Instantiates a new parser exception.
	 *
	 * @param line
	 *            the line
	 * @param command
	 *            the command
	 */
	public ParserException(int line, String command) {
		super(line, command);
	}

}
