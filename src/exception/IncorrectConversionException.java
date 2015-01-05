package exception;

public class IncorrectConversionException extends InterpreterException {

	/**
	 * 
	 */
	private static final long serialVersionUID = 56780275321844127L;

	public IncorrectConversionException(int line, String command, String message) {
		super(line, command, message);
	}

	public IncorrectConversionException(int line, String command) {
		super(line, command);
	}

}
