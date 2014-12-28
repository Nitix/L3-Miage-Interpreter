package command;


public class IntegerVariable extends Variable {

	private int value;
	
	public IntegerVariable(int value){
		this.value = value;
	}

	public IntegerVariable(String value) {
		this.value = Integer.parseInt(value);
	}
}
