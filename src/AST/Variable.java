package AST;

public class Variable {

	private int intValue;

	private boolean booleanValue;

	private Fork fork;
	
	private String forkName;

	private boolean isIntValue;

	private boolean isBooleanValue;

	private boolean isFork;

	private boolean isAlias;

	private Variable alias;

	private boolean isEmpty;

	public Variable() {
		this.isBooleanValue = false;
		this.isIntValue = false;
		this.isFork = false;
		this.isAlias = false;
		this.isEmpty = true;
	}

	private Variable(int intValue, boolean booleanValue, Fork fork, String forkName,
			boolean isIntValue, boolean isBooleanValue, boolean isFork,
			boolean isAlias, Variable alias) {
		super();
		this.intValue = intValue;
		this.booleanValue = booleanValue;
		this.fork = fork;
		this.forkName = forkName;
		this.isIntValue = isIntValue;
		this.isBooleanValue = isBooleanValue;
		this.isFork = isFork;
		this.isAlias = isAlias;
		this.alias = alias;
		this.isEmpty = false;
	}

	public void setIntValue(int intValue) {
		this.intValue = intValue;
		this.isBooleanValue = false;
		this.isIntValue = true;
		this.isFork = false;
		this.isAlias = false;
		this.isEmpty = false;
	}

	public void setBooleanValue(boolean booleanValue) {
		this.booleanValue = booleanValue;
		this.isBooleanValue = true;
		this.isIntValue = false;
		this.isFork = false;
		this.isAlias = false;
		this.isEmpty = false;
	}

	public void setFork(Fork fork, String forkName) {
		this.fork = fork;
		this.forkName = forkName;
		this.isBooleanValue = false;
		this.isIntValue = false;
		this.isFork = true;
		this.isAlias = false;
		this.isEmpty = false;
	}

	public void setAlias(Variable alias) {
		this.alias = alias;
		this.isBooleanValue = false;
		this.isIntValue = false;
		this.isFork = false;
		this.isAlias = true;
	}

	public Variable getAlias() {
		return alias;
	}

	public int getIntValue() {
		if (isAlias)
			return alias.getIntValue();
		else
			return intValue;
	}

	public Fork getFork() {
		if (isAlias)
			return alias.getFork();
		else
			return fork;
	}
	
	public String getForkName() {
		if (isAlias)
			return alias.getForkName();
		else
			return forkName;
	}

	public boolean getBooleanValue() {
		if (isAlias)
			return alias.getBooleanValue();
		else
			return booleanValue;
	}

	public boolean isIntValue() {
		if (isAlias)
			return alias.isIntValue();
		else
			return isIntValue;
	}

	public boolean isBooleanValue() {
		if (isAlias)
			return alias.isBooleanValue();
		else
			return isBooleanValue;
	}

	public boolean isFork() {
		if (isAlias)
			return alias.isFork();
		else
			return isFork;
	}

	public boolean isEmpty() {
		if (isAlias)
			return alias.isEmpty();
		else
			return isEmpty;
	}

	public boolean isAlias() {
		return isAlias;
	}

	public Variable copy() {
		return new Variable(intValue, booleanValue, fork, forkName, isIntValue,
				isBooleanValue, isFork, isAlias, alias);
	}
}
