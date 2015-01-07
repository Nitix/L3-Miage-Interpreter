package ast2;

import exception.InterpreterException;

public class Fork extends Thread{
	
	private Node node;
	private Data data;
	
	private InterpreterException exception;
	
	public Fork(Node node, Data data) {
		super();
		this.node = node;
		this.data = data;
	}

	public void run(){
		try {
			node.getFather().getCommand().executeAtChilds(node.getFather(), data, node);
		} catch (InterpreterException | InterruptedException e) {
			this.exception = (InterpreterException) e;
			e.printStackTrace();
		}
	}
	
	public boolean hasException(){
		return this.exception != null;
	}
	
	public InterpreterException getException(){
		return exception;
	}

}