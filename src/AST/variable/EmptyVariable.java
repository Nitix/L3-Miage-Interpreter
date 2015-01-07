package AST.variable;

import AST.Fork;

public class EmptyVariable extends Variable {

	public EmptyVariable(String name) {
		super(name);
	}

	@Override
	public Variable copy() {
		return new EmptyVariable(getName());
	}

	@Override
	public boolean isEmpty() {
		return true;
	}

	@Override
	public Variable setIntValue(int intValue) {
		return new IntVariable(intValue, getName());
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
