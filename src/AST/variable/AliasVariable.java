package AST.variable;

import AST.Fork;
import command.IncorrectMethodCallException;

public class AliasVariable extends Variable{
	
	Variable var;
	
	public AliasVariable(){
		
	}
	
	public AliasVariable(Variable var) {
		this.var = var;
	}

	@Override
	public Variable copy() {
		return new AliasVariable(var);
	}

	@Override
	public void setAlias(Variable alias) throws IncorrectMethodCallException {
		this.var = alias;
	}

	@Override
	public Variable getAlias() throws IncorrectMethodCallException {
		return var;
	}

	@Override
	public boolean isAlias() {
		return true;
	}

	@Override
	public void setIntValue(int intValue) throws IncorrectMethodCallException {
		var.setIntValue(intValue);
	}

	@Override
	public void setBooleanValue(boolean booleanValue)
			throws IncorrectMethodCallException {
		var.setBooleanValue(booleanValue);
	}

	@Override
	public void setFork(Fork fork, String forkName)
			throws IncorrectMethodCallException {
		var.setFork(fork, forkName);
	}

	@Override
	public int getIntValue() throws IncorrectMethodCallException {
		return var.getIntValue();
	}

	@Override
	public Fork getFork() throws IncorrectMethodCallException {
		return var.getFork();
	}

	@Override
	public String getForkName() throws IncorrectMethodCallException {
		return var.getForkName();
	}

	@Override
	public boolean getBooleanValue() throws IncorrectMethodCallException {
		return var.getBooleanValue();
	}

	@Override
	public boolean isIntValue() {
		return var.isIntValue();
	}

	@Override
	public boolean isBooleanValue() {
		return var.isBooleanValue();
	}

	@Override
	public boolean isFork() {
		return var.isFork();
	}

	@Override
	public boolean isEmpty() {
		return var.isEmpty();
	}
}
