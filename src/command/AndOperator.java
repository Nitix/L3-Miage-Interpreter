package command;

import java.util.LinkedList;

import environment.Data;
import environment.Node;
import environment.variable.BooleanVariable;
import environment.variable.Variable;
import exception.IncorrectConversionException;
import exception.InterpreterException;

/**
 * The Class AndOperator. Make and And operator between two operator, throws
 * exception if they are int
 */
public class AndOperator extends Command {

	/** The Constant serialVersionUID. */
	private static final long serialVersionUID = -3676786464624469922L;

	/**
	 * Instantiates a new and operator.
	 *
	 * @param line
	 *            the line
	 */
	public AndOperator(int line) {
		super(line, "and");
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "AndOperator []";
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
		Node node1 = children.get(0);
		Node node2 = children.get(1);
		Command command1 = node1.getCommand();
		Command command2 = node2.getCommand();
		command1.execute(node1, data);
		if (!command1.getVariable(data).isBooleanValue()) {
			throw new IncorrectConversionException(this.getLine(),
					this.getCommand());
		}
		command2.execute(node2, data);
		if (!command2.getVariable(data).isBooleanValue()) {
			throw new IncorrectConversionException(this.getLine(),
					this.getCommand());
		}
		Variable var = new BooleanVariable(command1.getVariable(data)
				.getBooleanValue()
				&& command2.getVariable(data).getBooleanValue(), getId());
		data.addVariable(this.getId(), var);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see command.Command#hasValue(ast.Data)
	 */
	@Override
	public boolean hasValue(Data data) {
		return true;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see command.Command#getVariable(ast.Data)
	 */
	public Variable getVariable(Data data) throws InexistentVariableException {
		return data.getVariable(getId(), getLine());
	}
}
