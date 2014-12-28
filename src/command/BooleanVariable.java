package command;


public class BooleanVariable extends Variable {

	private boolean value;
	
	public BooleanVariable(boolean value){
		this.value = value;
	}

	public BooleanVariable(String command) {
		this.value = Boolean.parseBoolean(command);
	}
}
