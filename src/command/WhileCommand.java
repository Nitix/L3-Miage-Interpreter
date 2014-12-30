package command;

import java.util.LinkedList;

import parser.IncorrectConversionException;
import AST.Data;
import AST.InexistantVariableException;
import AST.Node;
import AST.VariableAlreadyExistException;

public class WhileCommand extends Command {

	@Override
	public String toString() {
		return "WhileCommand []";
	}

	@Override
	public void execute(Node node, Data data)
			throws VariableAlreadyExistException, IncorrectConversionException,
			IncorrectMethodCallException, InexistantVariableException,
			VariableNotDeclaredException {
		LinkedList<Node> childs = node.getChilds();
		Command exp = childs.get(0).getCommand();
		exp.execute(childs.get(0), data);
		if (!exp.hasBooleanValue(data)) {
			throw new IncorrectConversionException();
		}
		while (exp.getBooleanValue(data)) {
			exp.removeVariable(data);
			childs.get(1).execute(data);
			childs.get(1).removeVariable(data);
			exp.execute(childs.get(0), data);
			if (!exp.hasBooleanValue(data)) {
				throw new IncorrectConversionException();
			}
		}
	}
}
