package ast.variable;

import ast.Fork;
import command.IncorrectMethodCallException;

public class AliasVariable extends Variable{
	
	Variable var;
	Variable old;
	
	public AliasVariable(Variable var, String name) {
		super(name);
		this.var = var;
	}

	@Override
	public Variable copy() {
		return new AliasVariable(var, getName());
	}

	@Override
	public boolean isAlias() {
		return true;
	}

	@Override
	public Variable setIntValue(int intValue) {
		if(var.isAlias())
			return var.setIntValue(intValue);
		else
			var = var.setIntValue(intValue);
		return var;
	}

	@Override
	public Variable setBooleanValue(boolean booleanValue){
		if(var.isAlias())
			return var.setBooleanValue(booleanValue);
		else 
			var = var.setBooleanValue(booleanValue);
		return var;

	}

	@Override
	public Variable setFork(Fork fork, String forkName) {
		if(var.isAlias())
			return var.setFork(fork, forkName);
		else
			var = var.setFork(fork, forkName);
		return var;

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

	@Override
	public Variable getAlias() throws IncorrectMethodCallException {
		return var;
	}
}
