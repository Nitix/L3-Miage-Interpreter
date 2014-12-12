package command.type;

import command.Command;

public class IntegerType implements Command {

	private int value;
	
	public IntegerType(int value){
		this.value = value;
	}

	public IntegerType(String value) {
		this.value = Integer.parseInt(value);
	}
}
