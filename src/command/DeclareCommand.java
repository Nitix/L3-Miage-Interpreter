package command;

import parser.IncorrectConversionException;
import AST.Data;
import AST.InexistantVariableException;
import AST.Node;
import AST.VariableAlreadyExistException;

public class DeclareCommand extends Command {

	private String nameVariable;

	public DeclareCommand(String name) {
		this.nameVariable = name;
	}

	@Override
	public String toString() {
		return "DeclareCommand [nameVariable=" + nameVariable + "]";
	}

	@Override
	public void execute(Node node, Data data)
			throws VariableAlreadyExistException, IncorrectConversionException,
			IncorrectMethodCallException, InexistantVariableException,
			VariableNotDeclaredException {
		data.declareVariable(nameVariable);
		node.getChilds().get(0).execute(data);
		node.getChilds().get(0).removeVariable(data);
		data.removeVariable(nameVariable);
	}
}
