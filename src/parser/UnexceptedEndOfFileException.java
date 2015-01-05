package parser;


public class UnexceptedEndOfFileException extends ParserException {

	public UnexceptedEndOfFileException(int line, String command, String message) {
		super(line, command, message);
		// TODO Auto-generated constructor stub
	}

	public UnexceptedEndOfFileException(int line, String command) {
		super(line, command);
		// TODO Auto-generated constructor stub
	}

}
