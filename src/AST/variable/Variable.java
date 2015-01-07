package AST.variable;

import command.IncorrectMethodCallException;

import AST.Fork;

public abstract class Variable {

	public void setIntValue(int intValue)  throws IncorrectMethodCallException{
		throw new IncorrectMethodCallException();
	}

	public void setBooleanValue(boolean booleanValue)  throws IncorrectMethodCallException{
		throw new IncorrectMethodCallException();
	}
	public void setFork(Fork fork, String forkName) throws IncorrectMethodCallException{
		throw new IncorrectMethodCallException();
	}

	public void setAlias(Variable alias)  throws IncorrectMethodCallException{
		throw new IncorrectMethodCallException();
	}

	public Variable getAlias() throws IncorrectMethodCallException{
		throw new IncorrectMethodCallException();
	}

	public  int getIntValue()  throws IncorrectMethodCallException{
		throw new IncorrectMethodCallException();

	}

	public Fork getFork()  throws IncorrectMethodCallException{
		throw new IncorrectMethodCallException();
	}
	
	public String getForkName()  throws IncorrectMethodCallException{
		throw new IncorrectMethodCallException();
	}

	public boolean getBooleanValue()  throws IncorrectMethodCallException {
		throw new IncorrectMethodCallException();
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
}
