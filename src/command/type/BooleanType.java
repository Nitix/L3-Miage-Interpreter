package command.type;

import command.Command;

public class BooleanType implements Command {

	private boolean value;
	
	public BooleanType(boolean value){
		this.value = value;
	}

	public BooleanType(String command) {
		this.value = Boolean.parseBoolean(command);
	}
}
