package command;

public class VariableAlreadyExistException extends CommandException {

	/**
	 * 
	 */
	private static final long serialVersionUID = 5744956203912576361L;

	public VariableAlreadyExistException(int line, String command) {
		super(line, command);
		// TODO Auto-generated constructor stub
	}

	public VariableAlreadyExistException(int line, String command,
			String message) {
		super(line, command, message);
		// TODO Auto-generated constructor stub
	}

}
