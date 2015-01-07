package AST.variable;

import AST.Fork;
import command.IncorrectMethodCallException;


public class BooleanVariable extends Variable {

	private boolean booleanvalue;
	
	public BooleanVariable(String name) {
		super(name);
	}

	public BooleanVariable(boolean boolvalue, String name) {
		super(name);
		this.booleanvalue = boolvalue;
	}
	
	@Override
	public Variable setBooleanValue(boolean booleanValue) {
		this.booleanvalue = booleanValue;
		return this;
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
		return new BooleanVariable(booleanvalue, getName());
	}

	@Override
	public Variable setIntValue(int intValue) {
		return new IntVariable(intValue, getName());
	}

	@Override
	public Variable setFork(Fork fork, String forkName) {
		return new ForkVariable(fork, forkName, getName());
	}

}
