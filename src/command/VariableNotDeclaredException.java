package command;


public class VariableNotDeclaredException extends CommandException {

	public VariableNotDeclaredException(int line, String command, String message) {
		super(line, command, message);
		// TODO Auto-generated constructor stub
	}

	public VariableNotDeclaredException(int line, String command) {
		super(line, command);
		// TODO Auto-generated constructor stub
	}

}
