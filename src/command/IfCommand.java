package command;

import java.util.LinkedList;

import ast2.Data;
import ast2.Node;
import exception.IncorrectConversionException;
import exception.InterpreterException;

public class IfCommand extends Command {

	/**
	 * 
	 */
	private static final long serialVersionUID = -1401953064183147211L;

	public IfCommand(int line) {
		super(line, "if");
	}

	@Override
	public String toString() {
		return "IfCommand []";
	}

	@Override
	public void execute(Node node, Data data)
			throws InterruptedException, InterpreterException {
		LinkedList<Node> childs = node.getChilds();
		Command exp = childs.get(0).getCommand();
		exp.execute(childs.get(0), data);
		if (!exp.hasValue(data)) {
			throw new IncorrectConversionException(this.getLine(), this.getCommand());
		}
		if(exp.getVariable(data).isBooleanValue()){
			if (exp.getVariable(data).getBooleanValue()) {
				childs.get(1).execute(data);
				childs.get(1).removeVariable(data);
			} else {
				if (childs.size() == 3) {
					childs.get(2).execute(data);
					childs.get(2).removeVariable(data);
				}
			}
		}else if(exp.getVariable(data).isIntValue()){
			if (exp.getVariable(data).getIntValue() > 0) {
				childs.get(1).execute(data);
				childs.get(1).removeVariable(data);
			} else {
				if (childs.size() == 3) {
					childs.get(2).execute(data);
					childs.get(2).removeVariable(data);
				}
			}
		}else if(exp.getVariable(data).isFork()){
			if (exp.getVariable(data).getForkName() == null) {
				childs.get(1).execute(data);
				childs.get(1).removeVariable(data);
			} else {
				if (childs.size() == 3) {
					childs.get(2).execute(data);
					childs.get(2).removeVariable(data);
				}
			}
		}else{
			throw new IncorrectMethodCallException(this.getLine(), this.getCommand());
		}
		exp.removeVariable(data);
	}

}
