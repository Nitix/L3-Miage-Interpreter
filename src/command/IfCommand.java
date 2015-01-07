package command;

import java.util.LinkedList;

import environment.Data;
import environment.Node;
import exception.IncorrectConversionException;
import exception.InterpreterException;

/**
 * The Class IfCommand.
 */
public class IfCommand extends Command {

	/** The Constant serialVersionUID. */
	private static final long serialVersionUID = -1401953064183147211L;

	/**
	 * Instantiates a new if command.
	 *
	 * @param line
	 *            the line
	 */
	public IfCommand(int line) {
		super(line, "if");
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "IfCommand []";
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see command.Command#execute(ast.Node, ast.Data)
	 */
	@Override
	public void execute(Node node, Data data) throws InterruptedException,
			InterpreterException {
		LinkedList<Node> children = node.getChilds();
		Command exp = children.get(0).getCommand();
		exp.execute(children.get(0), data);
		if (!exp.hasValue(data)) {
			throw new IncorrectConversionException(this.getLine(),
					this.getCommand());
		}
		if (exp.getVariable(data).isBooleanValue()) {
			if (exp.getVariable(data).getBooleanValue()) {
				children.get(1).execute(data);
				children.get(1).removeVariable(data);
			} else {
				if (children.size() == 3) {
					children.get(2).execute(data);
					children.get(2).removeVariable(data);
				}
			}
		} else if (exp.getVariable(data).isIntValue()) {
			if (exp.getVariable(data).getIntValue() > 0) {
				children.get(1).execute(data);
				children.get(1).removeVariable(data);
			} else {
				if (children.size() == 3) {
					children.get(2).execute(data);
					children.get(2).removeVariable(data);
				}
			}
		} else if (exp.getVariable(data).isFork()) {
			if (exp.getVariable(data).getForkName() == null) {
				children.get(1).execute(data);
				children.get(1).removeVariable(data);
			} else {
				if (children.size() == 3) {
					children.get(2).execute(data);
					children.get(2).removeVariable(data);
				}
			}
		} else {
			throw new IncorrectMethodCallException(this.getLine(),
					this.getCommand());
		}
		exp.removeVariable(data);
	}

}
