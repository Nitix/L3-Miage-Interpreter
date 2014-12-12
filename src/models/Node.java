package models;

import java.util.LinkedList;

import command.Command;

public class Node {

	private LinkedList<Node> childs = new LinkedList<>();
	
	private Command command;
	
	private Node father;
	
	public Node(Command command, Node father){
		this.command = command;
	}
	
	public void add(Node node){
		this.childs.add(node);
	}
	
	public void remplace(Node nodeRemplaced, Node nodeRemplacer){
		this.childs.set(this.childs.indexOf(nodeRemplaced), nodeRemplacer);
	}

	public Node getFather() {
		return father;
	}

	public void setFather(Node father) {
		this.father = father;
	}

	public Command getCommand() {
		return command;
	}
	
	
}
