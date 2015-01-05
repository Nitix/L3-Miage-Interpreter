package command;

import java.util.LinkedList;

import exception.IncorrectConversionException;
import AST.Data;
import AST.Node;
import AST.Variable;

public class EqualComparatorCommand extends Command {

	public EqualComparatorCommand(int line) {
		super(line, "=");
	}

	@Override
	public String toString() {
		return "EqualComparatorCommand []";
	}

	@Override
	public void execute(Node node, Data data)
			throws VariableAlreadyExistException, IncorrectConversionException,
			IncorrectMethodCallException, InexistantVariableException,
			VariableNotDeclaredException {
		LinkedList<Node> childs = node.getChilds();
		Node node1 = childs.get(0);
		Node node2 = childs.get(1);
		Command command1 = node1.getCommand();
		Command command2 = node2.getCommand();
		command1.execute(node1, data);
		command2.execute(node2, data);

		Variable var = new Variable();

		if (command1.hasBooleanValue(data)) {
			if (!command2.hasBooleanValue(data)) {
				throw new IncorrectConversionException(this.getLine(), this.getCommand());
			}
			var.setBooleanValue(command1.getBooleanValue(data) == command2
					.getBooleanValue(data));
		} else {
			if (command1.hasIntValue(data)) {
				if (!command2.hasIntValue(data)) {
					throw new IncorrectConversionException(this.getLine(), this.getCommand());
				}
				var.setBooleanValue(command1.getIntValue(data) == command2
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
