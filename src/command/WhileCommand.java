package command;

import java.util.LinkedList;

import environment.Data;
import environment.Node;
import exception.IncorrectConversionException;
import exception.InterpreterException;

/**
 * The Class WhileCommand. Do the command that contains the command until the
 * verification is false
 */
public class WhileCommand extends Command {

	/** The Constant serialVersionUID. */
	private static final long serialVersionUID = 9207629406031221041L;

	/**
	 * Instantiates a new while command.
	 *
	 * @param line
	 *            the line
	 */
	public WhileCommand(int line) {
		super(line, "while");
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "WhileCommand []";
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see command.Command#execute(ast.Node, ast.Data)
	 */
	@Override
	public void execute(Node node, Data data) throws InterpreterException,
			InterruptedException {
		LinkedList<Node> children = node.getChilds();
		Command exp = children.get(0).getCommand();
		exp.execute(children.get(0), data);
		if (!exp.getVariable(data).isBooleanValue()) {
			throw new IncorrectConversionException(this.getLine(),
					this.getCommand());
		}
		while (exp.getVariable(data).getBooleanValue()) {
			exp.removeVariable(data);
			children.get(1).execute(data);
			children.get(1).removeVariable(data);
			exp.execute(children.get(0), data);
			if (!exp.getVariable(data).isBooleanValue()) {
				throw new IncorrectConversionException(this.getLine(),
						this.getCommand());
			}
		}
	}
}
