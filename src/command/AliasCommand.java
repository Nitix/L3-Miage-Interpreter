package command;

public class AliasCommand implements Command {

	private String var;
	private String alias;

	public AliasCommand(String var, String alias) {
		this.var = var;
		this.alias = alias;
	}

}
