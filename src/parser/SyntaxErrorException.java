package parser;



public class SyntaxErrorException extends ParserException {

	public SyntaxErrorException(int line, String command) {
		super(line, command);
	}

	public SyntaxErrorException(int line, String command, String message) {
		super(line, command, message);
		// TODO Auto-generated constructor stub
	}

	/**
	 * 
	 */
	private static final long serialVersionUID = 7313109686570940386L;

}
