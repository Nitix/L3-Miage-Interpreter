package exception;

public class InterpreterException extends Exception{

	private int lineNumber = 0;

	private String command = "";

	public InterpreterException(int line, String command) {
		super();
		this.lineNumber = line;
		this.command = command;
	}
	
	public InterpreterException(int line, String command, String message) {
		super(message);
		this.lineNumber = line;
		this.command = command;
	}


	public int getLineNumber() {
		return lineNumber;
	}

	public String getCommand() {
		return command;
	}
}
