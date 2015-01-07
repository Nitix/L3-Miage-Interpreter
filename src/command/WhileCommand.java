package command;

import java.util.LinkedList;

import ast.Data;
import ast.Node;
import exception.IncorrectConversionException;
import exception.InterpreterException;

public class WhileCommand extends Command {

	/**
	 * 
	 */
	private static final long serialVersionUID = 9207629406031221041L;

	public WhileCommand(int line) {
		super(line, "while");
	}

	@Override
	public String toString() {
		return "WhileCommand []";
	}

	@Override
	public void execute(Node node, Data data)
			throws InterpreterException, InterruptedException {
		LinkedList<Node> childs = node.getChilds();
		Command exp = childs.get(0).getCommand();
		exp.execute(childs.get(0), data);
		if (!exp.getVariable(data).isBooleanValue()) {
			throw new IncorrectConversionException(this.getLine(), this.getCommand());
		}
		while (exp.getVariable(data).getBooleanValue()) {
			exp.removeVariable(data);
			childs.get(1).execute(data);
			childs.get(1).removeVariable(data);
			exp.execute(childs.get(0), data);
			if (!exp.getVariable(data).isBooleanValue()) {
				throw new IncorrectConversionException(this.getLine(), this.getCommand());
			}
		}
	}
}
