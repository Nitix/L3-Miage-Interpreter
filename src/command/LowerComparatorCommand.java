package command;

import java.util.LinkedList;

import exception.IncorrectConversionException;
import exception.InterpreterException;
import AST.Data;
import AST.Node;
import AST.variable.BooleanVariable;
import AST.variable.Variable;

public class LowerComparatorCommand extends Command {

	/**
	 * 
	 */
	private static final long serialVersionUID = -8312204629331907085L;

	public LowerComparatorCommand(int line) {
		super(line, "<");
	}

	@Override
	public String toString() {
		return "LowerComparatorCommand []";
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
		
		if (command1.hasIntValue(data)) {
			if (!command2.hasIntValue(data)) {
				throw new IncorrectConversionException(this.getLine(), this.getCommand(), "Can not compare int with boolean or fork");
			}
			var = new BooleanVariable(command1.getIntValue(data) < command2
					.getIntValue(data));
		} else {
			throw new IncorrectConversionException(this.getLine(), this.getCommand(), "Must compare int");
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
