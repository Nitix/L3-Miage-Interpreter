package command;

import exception.InterpreterException;
import AST.Data;
import AST.Node;
import AST.variable.AliasVariable;
import AST.variable.Variable;

public class AliasCommand extends Command {

	/**
	 * 
	 */
	private static final long serialVersionUID = 374046769029502531L;
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
			throws InterpreterException, InterruptedException {
		if (!data.isDeclaredVariable(ori))
			throw new InexistantVariableException(this.getLine(), this.getCommand());
		Variable var = new AliasVariable(data.getVariable(ori, this.getLine()), getUuid());
		data.addVariable(alias, var);
		node.getChilds().get(0).execute(data);
		node.getChilds().get(0).removeVariable(data);
		data.removeVariable(alias);
	}

}
