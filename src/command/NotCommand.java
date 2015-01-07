package command;

import java.util.LinkedList;

import exception.IncorrectConversionException;
import exception.InterpreterException;
import AST.Data;
import AST.Node;
import AST.variable.BooleanVariable;
import AST.variable.Variable;

public class NotCommand extends Command {

	/**
	 * 
	 */
	private static final long serialVersionUID = -1193426715782605872L;

	public NotCommand(int line) {
		super(line, "not");
	}

	@Override
	public String toString() {
		return "NotCommand []";
	}

	@Override
	public void execute(Node node, Data data)
			throws InterpreterException, InterruptedException {
		LinkedList<Node> childs = node.getChilds();
		Node node1 = childs.get(0);
		Command command1 = node1.getCommand();
		command1.execute(node1, data);

		if (!command1.getVariable(data).isBooleanValue()) {
			throw new IncorrectConversionException(this.getLine(), this.getCommand());
		}
		Variable var = new BooleanVariable(!command1.getVariable(data).getBooleanValue(), getUuid());

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
