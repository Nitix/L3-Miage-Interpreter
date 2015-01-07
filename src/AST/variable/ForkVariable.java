package AST.variable;

import command.IncorrectMethodCallException;

import AST.Fork;

public class ForkVariable extends Variable{

	private String forkName;
	
	private Fork fork;
	
	public ForkVariable() {
	}
	
	public ForkVariable(Fork fork, String forkname) {
		this.fork = fork;
		this.forkName = forkname;
	}
	
	@Override
	public Variable copy() {
		return new ForkVariable(fork, forkName);
	}

	@Override
	public void setFork(Fork fork, String forkName)
			throws IncorrectMethodCallException {
		this.fork = fork;
		this.forkName = forkName;
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

}
