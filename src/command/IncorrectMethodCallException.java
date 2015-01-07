package command;

public class IncorrectMethodCallException extends CommandException {

	/**
	 * 
	 */
	private static final long serialVersionUID = 7479028456669159340L;

	public IncorrectMethodCallException(int line, String command) {
		super(line, command);
		// TODO Auto-generated constructor stub
	}

	public IncorrectMethodCallException(int line, String command,
			String message) {
		super(line, command, message);
		// TODO Auto-generated constructor stub
	}

	public IncorrectMethodCallException() {
		super(-1, "");
	}
}
