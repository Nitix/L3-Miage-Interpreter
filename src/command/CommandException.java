package command;

import exception.InterpreterException;

public class CommandException extends InterpreterException {

	public CommandException(int line, String command) {
		super(line, command);
	}

	public CommandException(int line, String command, String message) {
		super(line, command, message);
	}

}
