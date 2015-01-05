package command;

public class InexistantVariableException extends CommandException {

	public InexistantVariableException(int line, String command, String message) {
		super(line, command, message);
		// TODO Auto-generated constructor stub
	}

	public InexistantVariableException(int line, String command) {
		super(line, command);
		// TODO Auto-generated constructor stub
	}

}
