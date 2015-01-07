package command;

import java.util.LinkedList;

import exception.IncorrectConversionException;
import exception.InterpreterException;
import AST.Data;
import AST.Node;
import AST.variable.BooleanVariable;
import AST.variable.Variable;

public class AndOperator extends Command {

	/**
	 * 
	 */
	private static final long serialVersionUID = -3676786464624469922L;

	public AndOperator(int line) {
		super(line, "and");
	}

	@Override
	public String toString() {
		return "AndOperator []";
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
		if (!command1.hasBooleanValue(data)) {
			throw new IncorrectConversionException(this.getLine(), this.getCommand());
		}
		command2.execute(node2, data);
		if (!command2.hasBooleanValue(data)) {
			throw new IncorrectConversionException(this.getLine(), this.getCommand());
		}
		Variable var = new BooleanVariable();
		var.setBooleanValue(command1.getBooleanValue(data)
				&& command2.getBooleanValue(data));
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
