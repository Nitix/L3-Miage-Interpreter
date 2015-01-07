package AST.variable;

import command.IncorrectMethodCallException;


public class IntVariable extends Variable{

	private int value;

	public IntVariable(){
		
	}
	
	public IntVariable(int value) {
		this.value  = value;
	}

	
	@Override
	public void setIntValue(int intValue) throws IncorrectMethodCallException {
		this.value = intValue;
	}

	@Override
	public boolean isIntValue() {
		return true;
	}

	@Override
	public int getIntValue() throws IncorrectMethodCallException {
		return value;
	}

	@Override
	public Variable copy() {
		return new IntVariable(this.value);
	}

}
