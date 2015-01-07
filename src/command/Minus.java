package command;

import java.util.LinkedList;

import ast.Data;
import ast.Node;
import ast.variable.IntVariable;
import ast.variable.Variable;
import exception.IncorrectConversionException;
import exception.InterpreterException;

public class Minus extends Command {

	/**
	 * 
	 */
	private static final long serialVersionUID = 4758786039549709765L;

	public Minus(int line) {
		super(line, "-");
	}

	public static boolean isMinusCommand(String command) {
		return command.equals("-");
	}

	@Override
	public String toString() {
		return "Minus []";
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
		if (!command1.getVariable(data).isIntValue()) {
			throw new IncorrectConversionException(this.getLine(), this.getCommand());
		}
		command2.execute(node2, data);
		if (!command2.getVariable(data).isIntValue()) {
			throw new IncorrectConversionException(this.getLine(), this.getCommand());
		}
		Variable var = new IntVariable(command1.getVariable(data).getIntValue() + command2.getVariable(data).getIntValue(), getUuid());
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
