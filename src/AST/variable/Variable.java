package AST.variable;

import command.IncorrectMethodCallException;
import AST.Fork;

public abstract class Variable {

	private String name;
	
	public Variable(String name){
		this.name = name;
	}
	
	public abstract Variable setIntValue(int intValue);

	public abstract Variable setBooleanValue(boolean booleanValue);
	
	public abstract Variable setFork(Fork fork, String forkName);

	public  int getIntValue() throws IncorrectMethodCallException{
		throw new IncorrectMethodCallException(-1, name);
	}

	public Fork getFork()  throws IncorrectMethodCallException{
		throw new IncorrectMethodCallException(-1, name);
	}
	
	public String getForkName()  throws IncorrectMethodCallException{
		throw new IncorrectMethodCallException(-1, name);
	}

	public boolean getBooleanValue()  throws IncorrectMethodCallException {
		throw new IncorrectMethodCallException(-1, name);
	}

	public Variable getAlias()  throws IncorrectMethodCallException {
		throw new IncorrectMethodCallException(-1, name);
	}

	public boolean isIntValue() {
		return false;
	}

	public boolean isBooleanValue() {
		return false;
	}

	public boolean isFork() {
		return false;
	}

	public boolean isEmpty() {
		return false;
	}

	public boolean isAlias() {
		return false;
	}

	public abstract Variable copy();

	public String getName() {
		return this.name;
	}
}
