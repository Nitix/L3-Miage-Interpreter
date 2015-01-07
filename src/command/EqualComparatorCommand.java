package command;

import java.util.LinkedList;

import exception.IncorrectConversionException;
import exception.InterpreterException;
import AST.Data;
import AST.Node;
import AST.variable.BooleanVariable;
import AST.variable.Variable;

public class EqualComparatorCommand extends Command {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1095959937114205310L;

	public EqualComparatorCommand(int line) {
		super(line, "=");
	}

	@Override
	public String toString() {
		return "EqualComparatorCommand []";
	}

	@Override
	public void execute(Node node, Data data)
			throws InterpreterException, InterruptedException {
		LinkedList<Node> childs = node.getChilds();
		Node node1 = childs.get(0);
		Node node2 = childs.get(1);
		Command command1 = node1.getCommand();
		Command command2 = node2.getCommand();
		command1.execute(node1, data);
		command2.execute(node2, data);

		Variable var;
		if (command1.hasBooleanValue(data)) {
			if (!command2.hasBooleanValue(data)) {
				throw new IncorrectConversionException(this.getLine(), this.getCommand());
			}
			var = new BooleanVariable(command1.getBooleanValue(data) == command2
					.getBooleanValue(data));
		} else {
			if (command1.hasIntValue(data)) {
				if (!command2.hasIntValue(data)) {
					throw new IncorrectConversionException(this.getLine(), this.getCommand());
				}
				var = new BooleanVariable(command1.getIntValue(data) == command2
						.getIntValue(data));
			} else {
				throw new IncorrectConversionException(this.getLine(), this.getCommand());
			}
		}

		data.addVariable(this.getUuid(), var);
	}

	@Override
	public boolean hasValue(Data data) {
		return true;
	}

	@Override
	public boolean hasBooleanValue(Data data) {
		return true;
	}

	@Override
	public boolean getBooleanValue(Data data)
			throws IncorrectMethodCallException, InexistantVariableException {
		return data.getVariable(this.getUuid(), this.getLine()).getBooleanValue();
	}
}
