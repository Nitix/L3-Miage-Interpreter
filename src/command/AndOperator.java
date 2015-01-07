package command;

import java.util.LinkedList;

import ast.Data;
import ast.Node;
import ast.variable.BooleanVariable;
import ast.variable.Variable;
import exception.IncorrectConversionException;
import exception.InterpreterException;

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
		if (!command1.getVariable(data).isBooleanValue()) {
			throw new IncorrectConversionException(this.getLine(), this.getCommand());
		}
		command2.execute(node2, data);
		if (!command2.getVariable(data).isBooleanValue()) {
			throw new IncorrectConversionException(this.getLine(), this.getCommand());
		}
		Variable var = new BooleanVariable(command1.getVariable(data).getBooleanValue()
				&& command2.getVariable(data).getBooleanValue(), getUuid());
		data.addVariable(this.getUuid(), var);
	}

	@Override
	public boolean hasValue(Data data) {
		return true;
	}
	
	public Variable getVariable(Data data) throws InexistantVariableException{
		return data.getVariable(getUuid(), getLine());
	}
}
