package parser;

public class SyntaxErrorException extends ParserException {

	public SyntaxErrorException(int line) {
		super(line);
	}

	/**
	 * 
	 */
	private static final long serialVersionUID = 7313109686570940386L;

}
