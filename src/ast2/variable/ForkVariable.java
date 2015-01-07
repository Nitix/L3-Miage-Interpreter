package ast2.variable;

import ast2.Fork;
import command.IncorrectMethodCallException;

public class ForkVariable extends Variable{

	private String forkName;
	
	private Fork fork;
	
	public ForkVariable(String name) {
		super(name);
	}
	
	public ForkVariable(Fork fork, String forkname, String name) {
		super(name);
		this.fork = fork;
		this.forkName = forkname;
	}
	
	@Override
	public Variable copy() {
		return new ForkVariable(fork, forkName, getName());
	}

	@Override
	public Variable setFork(Fork fork, String forkName) {
		this.fork = fork;
		this.forkName = forkName;
		return this;
	}

	@Override
	public Fork getFork() throws IncorrectMethodCallException {
		return fork;
	}

	@Override
	public String getForkName() throws IncorrectMethodCallException {
		return forkName;
	}

	@Override
	public boolean isFork() {
		return true;
	}

	@Override
	public Variable setBooleanValue(boolean booleanValue) {
		return new BooleanVariable(booleanValue, getName());
	}
	
	@Override
	public Variable setIntValue(int intValue) {
		return new IntVariable(intValue, getName());
	}
}
