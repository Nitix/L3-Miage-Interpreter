package exception;

import parser.ParserException;


public class IncorrectConversionException extends InterpreterException {

	public IncorrectConversionException(int line, String command, String message) {
		super(line, command, message);
		// TODO Auto-generated constructor stub
	}

	public IncorrectConversionException(int line, String command) {
		super(line, command);
		// TODO Auto-generated constructor stub
	}

}
