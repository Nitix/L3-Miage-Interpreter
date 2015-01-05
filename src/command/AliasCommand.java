package command;

import exception.IncorrectConversionException;
import AST.Data;
import AST.Node;
import AST.Variable;

public class AliasCommand extends Command {

	private String ori;
	private String alias;

	public AliasCommand(String ori, String alias, int line) {
		super(line, "let " + ori + " be " + alias);
		this.ori = ori;
		this.alias = alias;
	}

	@Override
	public String toString() {
		return "AliasCommand [var=" + ori + ", alias=" + alias + "]";
	}

	@Override
	public void execute(Node node, Data data)
			throws VariableAlreadyExistException, IncorrectConversionException,
			IncorrectMethodCallException, InexistantVariableException,
			VariableNotDeclaredException {
		if (!data.isDeclaredVariable(ori))
			throw new InexistantVariableException(this.getLine(), this.getCommand());
		Variable var = new Variable();
		var.setAlias(data.getVariable(ori, this.getLine()));
		data.addVariable(alias, var);
		node.getChilds().get(0).execute(data);
		node.getChilds().get(0).removeVariable(data);
		data.removeVariable(alias);
	}

}
