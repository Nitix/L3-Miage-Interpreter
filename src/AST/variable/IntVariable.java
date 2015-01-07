package AST.variable;

import AST.Fork;
import command.IncorrectMethodCallException;


public class IntVariable extends Variable{

	private int value;

	public IntVariable(String name){
		super(name);
	}
	
	public IntVariable(int value, String name) {
		super(name);
		this.value  = value;
	}

	
	@Override
	public Variable setIntValue(int intValue) {
		this.value = intValue;
		return this;
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
		return new IntVariable(this.value, getName());
	}

	@Override
	public Variable setFork(Fork fork, String forkName) {
		return new ForkVariable(fork, forkName, getName());
	}

	@Override
	public Variable setBooleanValue(boolean booleanValue) {
		return new BooleanVariable(booleanValue, getName());
	}
	
}
