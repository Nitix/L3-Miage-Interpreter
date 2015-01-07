package command;

import java.util.LinkedList;

import environment.Data;
import environment.Node;
import environment.variable.BooleanVariable;
import environment.variable.Variable;
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
		if (command1.getVariable(data).isBooleanValue()) {
			if (!command2.getVariable(data).isBooleanValue()) {
				throw new IncorrectConversionException(this.getLine(),
						this.getCommand());
			}
			var = new BooleanVariable(command1.getVariable(data)
					.getBooleanValue() == command2.getVariable(data)
					.getBooleanValue(), getId());
		} else {
			if (command1.getVariable(data).isIntValue()) {
				if (!command2.getVariable(data).isIntValue()) {
					throw new IncorrectConversionException(this.getLine(),
							this.getCommand());
				}
				var = new BooleanVariable(command1.getVariable(data)
						.getIntValue() == command2.getVariable(data)
						.getIntValue(), getId());
			} else {
				throw new IncorrectConversionException(this.getLine(),
						this.getCommand());
			}
		}

		data.addVariable(this.getId(), var);
	}

	@Override
	public boolean hasValue(Data data) {
		return true;
	}

	public Variable getVariable(Data data) throws InexistentVariableException {
		return data.getVariable(getId(), getLine());
	}
}
