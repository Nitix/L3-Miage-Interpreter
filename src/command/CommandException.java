package command;

import exception.InterpreterException;

public class CommandException extends InterpreterException {

	/**
	 * 
	 */
	private static final long serialVersionUID = -6347426133044974110L;

	public CommandException(int line, String command) {
		super(line, command);
	}

	public CommandException(int line, String command, String message) {
		super(line, command, message);
	}

}
