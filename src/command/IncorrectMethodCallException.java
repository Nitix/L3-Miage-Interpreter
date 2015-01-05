package command;

public class IncorrectMethodCallException extends CommandException {

	public IncorrectMethodCallException(int line, String command) {
		super(line, command);
		// TODO Auto-generated constructor stub
	}

	public IncorrectMethodCallException(int line, String command,
			String message) {
		super(line, command, message);
		// TODO Auto-generated constructor stub
	}

}
