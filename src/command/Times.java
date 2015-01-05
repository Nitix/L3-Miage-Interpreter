package command;

import java.util.LinkedList;

import exception.IncorrectConversionException;
import exception.InterpreterException;
import AST.Data;
import AST.Node;
import AST.Variable;

public class Times extends Command {

	public Times(int line) {
		super(line, "*");
	}

	public static boolean isTimesCommand(String command) {
		return command.equals("*");
	}

	@Override
	public String toString() {
		return "Times []";
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
		if (!command1.hasIntValue(data)) {
			throw new IncorrectConversionException(this.getLine(), this.getCommand());
		}
		command2.execute(node2, data);
		if (!command2.hasIntValue(data)) {
			throw new IncorrectConversionException(this.getLine(), this.getCommand());
		}
		Variable var = new Variable();
		var.setIntValue(command1.getIntValue(data) * command2.getIntValue(data));
		data.addVariable(this.getUuid(), var);
	}

	@Override
	public boolean hasValue(Data data) {
		return true;
	}

	@Override
	public boolean hasIntValue(Data data) {
		return true;
	}

	@Override
	public int getIntValue(Data data) throws IncorrectMethodCallException,
			InexistantVariableException {
		return data.getVariable(this.getUuid(), this.getLine()).getIntValue();
	}

}
