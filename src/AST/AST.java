package AST;

import parser.IncorrectConversionException;
import command.IncorrectMethodCallException;
import command.Root;
import command.VariableNotDeclaredException;

public class AST {

	public Node racine;

	public AST() {
		this.racine = new Node(new Root(), null);
	}

	public Node getRacine() {
		return this.racine;
	}

	public void execute() throws IncorrectConversionException, VariableNotDeclaredException, VariableAlreadyExistException, IncorrectMethodCallException, InexistantVariableException {
		this.racine.execute(new Data());
	}
}
