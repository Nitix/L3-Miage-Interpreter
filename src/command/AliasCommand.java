package command;

import environment.Data;
import environment.Node;
import environment.variable.AliasVariable;
import environment.variable.Variable;
import exception.InterpreterException;

/**
 * The Class AliasCommand. Create an alias of a variable, when the variable is
 * modified, the original value is modified as well
 */
public class AliasCommand extends Command {

	/** The Constant serialVersionUID. */
	private static final long serialVersionUID = 374046769029502531L;

	/** The ori. */
	private String ori;

	/** The alias. */
	private String alias;

	/**
	 * Instantiates a new alias command.
	 *
	 * @param ori
	 *            the ori
	 * @param alias
	 *            the alias
	 * @param line
	 *            the line
	 */
	public AliasCommand(String ori, String alias, int line) {
		super(line, "let " + ori + " be " + alias);
		this.ori = ori;
		this.alias = alias;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "AliasCommand [var=" + ori + ", alias=" + alias + "]";
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see command.Command#execute(ast.Node, ast.Data)
	 */
	@Override
	public void execute(Node node, Data data) throws InterpreterException,
			InterruptedException {
		if (!data.isDeclaredVariable(ori))
			throw new InexistentVariableException(this.getLine(),
					this.getCommand());
		Variable var = new AliasVariable(data.getVariable(ori, this.getLine()),
				getId());
		data.addVariable(alias, var);
		node.getChilds().get(0).execute(data);
		node.getChilds().get(0).removeVariable(data);
		data.removeVariable(alias);
	}

}
