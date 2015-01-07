package command;

import exception.InterpreterException;
import AST.Data;
import AST.Node;

public class DeclareCommand extends Command {

	/**
	 * 
	 */
	private static final long serialVersionUID = -8126510234681380206L;
	private String nameVariable;

	public DeclareCommand(String name, int line) {
		super(line, "let " + name);
		this.nameVariable = name;
	}

	@Override
	public String toString() {
		return "DeclareCommand [nameVariable=" + nameVariable + "]";
	}

	@Override
	public void execute(Node node, Data data)
			throws InterpreterException, InterruptedException {
		data.declareVariable(nameVariable,this.getLine());
		node.getChilds().get(0).execute(data);
		node.getChilds().get(0).removeVariable(data);
		data.removeVariable(nameVariable);
	}
}
