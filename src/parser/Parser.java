package parser;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.PushbackReader;
import java.util.LinkedList;

import command.Command;
import command.Devided;
import command.Minus;
import command.NumberOperator;
import command.Plus;
import command.Times;
import command.type.BooleanType;
import command.type.IntegerType;
import models.AST;
import models.Data;
import models.Node;

public class Parser {

	private PushbackReader reader;
	
	private AST ast = new AST();
	
	private Data data = new Data();
		
	public Parser(File file) throws FileNotFoundException{
		this.reader = new PushbackReader(new FileReader(file));
	}
	
	
	public void parse(){
		
	}
	
	private String readCommand() throws IOException{
		int charac; 
		String command = "";

		boolean ok = false;

		while((charac = reader.read()) != -1 && !ok){
			switch (charac) {
			case ' ':
			case '	':
			case ';':
				reader.unread(charac);
				ok = true;
				break;
			default:
				command += (char) charac;
				break;
			}
		}
		return command;
	}
		
	private void readExpression(Node node) throws IOException, SyntaxErrorException, VariableNotDeclaredException {
		
		boolean needNextValue = true;	// Prochaine valeur attendu : valeur
		boolean isPrio = false; 		// Dernière opération * ou / donc priorité
		Node simpleOperatorNode = node; // Dernier node avec le quel on a fait +
		Node currentNode = node; 		// Dernier node sur lequel travailler
		String command;					// Commande en cours de trairement
		
		boolean isBooleanExpression = false;

		//TODO Première valeur
		
		//(2P) 2 fois le pere (pere du pere)
		//(C) Seulement au pere
		
		//Première opération : Ajouter une étape
		
		// x + x +(CP) x   
		// x + x *(CP) x   
		// x * x *(CP) x
		// x * x +(2P) x

		// * ou / Remplacer avec le pere seulement
		while(!isEndOfCommand()){
			this.ignoreSpace();
			command = readCommand();
			if(command.isEmpty() && needNextValue){ //Peut arriver s'il y a de l'espace après la variable et qu'on est à la fin de la commande
				throw new SyntaxErrorException();
			}else{
				if(!command.isEmpty()){		
					if(NumberOperator.isOperatorCommand(command)){
						if(needNextValue){
							throw new SyntaxErrorException();
						}else{
							if(Devided.isDividedCommand(command) || Times.isTimesCommand(command)){
								Command commandC;
								if(Times.isTimesCommand(command)){
									commandC = new Times();
								}else{
									commandC = new Devided();
								}
								if(!isPrio){
									simpleOperatorNode = currentNode.getFather();
									isPrio = true;
								}
								Node n = new Node( commandC, currentNode.getFather());
								
								currentNode.getFather().remplace(currentNode, n);
								currentNode.setFather(n);
								n.add(currentNode);
								currentNode = n;
								
								needNextValue = true;
							}else{
								Command commandC;
								if(Plus.isPlusCommand(command)){
									commandC = new Plus();
								}else{
									commandC = new Minus();
								}
								Node father;
								Node child;
								if(isPrio){
									father = simpleOperatorNode.getFather();
									child = simpleOperatorNode;

								}else{
									father = currentNode.getFather();
									child =  currentNode;
								}
								Node n = new Node( commandC, father);
								child.getFather().remplace(currentNode, n);
								child.setFather(n);
								n.add(currentNode);
								isPrio = false;
								needNextValue = true;
							}
						}
					}else{
						if(command.equals(Boolean.TRUE.toString()) || command.equals(Boolean.FALSE.toString()) ){
							Node n = new Node(new BooleanType(command), currentNode);
							currentNode.add(n);
							currentNode = n;
							isBooleanExpression = true;
						}else{
							if(isInteger(command)){
								Node n = new Node(new IntegerType(command), currentNode);
								currentNode.add(n);
								currentNode = n;
								isBooleanExpression = false;
								needNextValue = false;
							}else{
								if(!data.isDeclaredVariable(command))
									throw new VariableNotDeclaredException();
							}
						}
					}
				}
			}
		}
	}
	
	private void ignoreSpace() throws IOException{
		int charac; 
		boolean ok = false;

		while((charac = reader.read()) != -1 && !ok){
			switch (charac) {
			case ' ':
			case '	':
				break;
			default:
				reader.unread(charac);
				ok = true;
				break;
			}
		}
	}
	
	private boolean isEndOfCommand() throws IOException{
		int charac = reader.read();
		reader.unread(charac);
		return charac != ';'  || charac == -1;		
	}
	
	public static boolean isInteger(String str) {
		if (str == null) {
			return false;
		}
		int length = str.length();
		if (length == 0) {
			return false;
		}
		int i = 0;
		if (str.charAt(0) == '-') {
			if (length == 1) {
				return false;
			}
			i = 1;
		}
		for (; i < length; i++) {
			char c = str.charAt(i);
			if (c <= '/' || c >= ':') {
				return false;
			}
		}
		return true;
	}
	
}
	
