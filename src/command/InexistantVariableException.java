package command;

public class InexistantVariableException extends CommandException {

	/**
	 * 
	 */
	private static final long serialVersionUID = 3212066130517005547L;

	public InexistantVariableException(int line, String command, String message) {
		super(line, command, message);
		// TODO Auto-generated constructor stub
	}

	public InexistantVariableException(int line, String command) {
		super(line, command);
		// TODO Auto-generated constructor stub
	}

}
