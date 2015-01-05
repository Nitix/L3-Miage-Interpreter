package parser;

import exception.InterpreterException;

public class ParserException extends InterpreterException {

	/**
	 * 
	 */
	private static final long serialVersionUID = -2770726136437562124L;

	public ParserException(int line, String command, String message) {
		super(line, command, message);
		// TODO Auto-generated constructor stub
	}

	public ParserException(int line, String command) {
		super(line, command);
		// TODO Auto-generated constructor stub
	}

}
