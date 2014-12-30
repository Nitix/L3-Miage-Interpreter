package command;

import java.util.UUID;

import parser.IncorrectConversionException;
import AST.Data;
import AST.Fork;
import AST.InexistantVariableException;
import AST.Node;
import AST.Variable;
import AST.VariableAlreadyExistException;

public class ForkCommand extends Command {	

	@Override
	public String toString() {
		return "ForkCommand []";
	}

	@Override
	public void execute(Node node, Data data)
			throws VariableAlreadyExistException, IncorrectConversionException,
			IncorrectMethodCallException, InexistantVariableException,
			VariableNotDeclaredException {
		Variable var = new Variable();
		var.setFork(null, null);
		data.addVariable(getUuid(), var);
		Data duplicate = data.deepClone();
		Fork fork = new Fork(node.getFather(), duplicate);
		var.setFork(fork, UUID.randomUUID().toString());
	}

	@Override
	public boolean hasValue(Data data) throws InexistantVariableException {
		return true;
	}

	@Override
	public boolean isFork(Data data) throws InexistantVariableException {
		return true;
	}

	@Override
	public String getForkName(Data data) throws IncorrectMethodCallException,
			InexistantVariableException {
		return data.getVariable(getUuid()).getForkName();
	}

	@Override
	public Fork getFork(Data data) throws IncorrectMethodCallException,
			InexistantVariableException {
		return data.getVariable(getUuid()).getFork();
	}


	
}
