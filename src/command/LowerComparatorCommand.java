package command;

import java.util.LinkedList;

import environment.Data;
import environment.Node;
import environment.variable.BooleanVariable;
import environment.variable.Variable;
import exception.IncorrectConversionException;
import exception.InterpreterException;

/**
 * The Class LowerComparatorCommand. Compare two expression
 */
public class LowerComparatorCommand extends Command {

	/** The Constant serialVersionUID. */
	private static final long serialVersionUID = -8312204629331907085L;

	/**
	 * Instantiates a new lower comparator command.
	 *
	 * @param line
	 *            the line
	 */
	public LowerComparatorCommand(int line) {
		super(line, "<");
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "LowerComparatorCommand []";
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
		command2.execute(node2, data);

		Variable var;

		if (command1.getVariable(data).isIntValue()) {
			if (!command2.getVariable(data).isIntValue()) {
				throw new IncorrectConversionException(this.getLine(),
						this.getCommand(),
						"Can not compare int with boolean or fork");
			}
			var = new BooleanVariable(
					command1.getVariable(data).getIntValue() < command2
							.getVariable(data).getIntValue(), getId());
		} else {
			throw new IncorrectConversionException(this.getLine(),
					this.getCommand(), "Must compare int");
		}

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
