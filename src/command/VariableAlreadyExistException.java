package command;

public class VariableAlreadyExistException extends CommandException {

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
