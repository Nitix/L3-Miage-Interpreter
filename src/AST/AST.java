package AST;

import command.Root;

public class AST {

	public Node racine;
	
	public AST(){
		this.racine = new Node(new Root(), null);
	}

	public Node getRacine(){
		return this.racine;
	}
}
