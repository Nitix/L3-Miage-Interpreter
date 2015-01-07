package command;

import java.util.LinkedList;

import exception.IncorrectConversionException;
import exception.InterpreterException;
import AST.Data;
import AST.Node;

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
		if (!exp.hasBooleanValue(data)) {
			throw new IncorrectConversionException(this.getLine(), this.getCommand());
		}
		while (exp.getBooleanValue(data)) {
			exp.removeVariable(data);
			childs.get(1).execute(data);
			childs.get(1).removeVariable(data);
			exp.execute(childs.get(0), data);
			if (!exp.hasBooleanValue(data)) {
				throw new IncorrectConversionException(this.getLine(), this.getCommand());
			}
		}
	}
}
