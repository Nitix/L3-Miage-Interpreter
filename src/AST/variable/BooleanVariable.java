package AST.variable;

import command.IncorrectMethodCallException;


public class BooleanVariable extends Variable {

	private boolean booleanvalue;
	
	public BooleanVariable() {
		// TODO Auto-generated constructor stub
	}

	public BooleanVariable(boolean boolvalue) {
		this.booleanvalue = boolvalue;
	}
	
	@Override
	public void setBooleanValue(boolean booleanValue)
			throws IncorrectMethodCallException {
		this.booleanvalue = booleanValue;
	}

	@Override
	public boolean getBooleanValue() throws IncorrectMethodCallException {
		return booleanvalue;
	}

	@Override
	public boolean isBooleanValue() {
		return true;
	}

	@Override
	public Variable copy() {
		return new BooleanVariable(booleanvalue);
	}

}
