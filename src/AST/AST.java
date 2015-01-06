package AST;

import java.io.Serializable;

import command.Root;
import exception.InterpreterException;

public class AST implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = -7878415460834312828L;
		
	private int line;
	
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

	public void setLine(int line){
		this.line = line;
	}
	
	public int getLine() {
		return line;
	}
}
