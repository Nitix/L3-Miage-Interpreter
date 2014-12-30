package parser;

public class ParserException extends Exception {

	private int ligneNumber = 0;

	private String command = "";

	private String message = "";

	public ParserException() {

	}

	public ParserException(int line) {
		this.ligneNumber = line;
	}

	public int getLineNumber() {
		return ligneNumber;
	}
}
