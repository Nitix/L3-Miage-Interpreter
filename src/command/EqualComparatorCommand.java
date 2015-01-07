package command;

import java.util.LinkedList;

import ast2.Data;
import ast2.Node;
import ast2.variable.BooleanVariable;
import ast2.variable.Variable;
import exception.IncorrectConversionException;
import exception.InterpreterException;

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
		if (command1.getVariable(data).isBooleanValue()) {
			if (!command2.getVariable(data).isBooleanValue()) {
				throw new IncorrectConversionException(this.getLine(), this.getCommand());
			}
			var = new BooleanVariable(command1.getVariable(data).getBooleanValue() == command2
					.getVariable(data).getBooleanValue(), getUuid());
		} else {
			if (command1.getVariable(data).isIntValue()) {
				if (!command2.getVariable(data).isIntValue()) {
					throw new IncorrectConversionException(this.getLine(), this.getCommand());
				}
				var = new BooleanVariable(command1.getVariable(data).getIntValue() == command2
						.getVariable(data).getIntValue(), getUuid());
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
	
	public Variable getVariable(Data data) throws InexistantVariableException{
		return data.getVariable(getUuid(), getLine());
	}
}
