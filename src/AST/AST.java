package AST;

import command.Root;
import exception.InterpreterException;

public class AST {

	public Node racine;

	public AST() {
		this.racine = new Node(new Root(), null);
	}

	public Node getRacine() {
		return this.racine;
	}

	public void execute() throws InterpreterException, InterruptedException {
		this.racine.execute(new Data());
	}
}
