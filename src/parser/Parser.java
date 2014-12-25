package parser;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.PushbackReader;

import command.*;
import command.type.BooleanType;
import command.type.IntegerType;
import AST.AST;
import AST.Data;
import AST.Node;

public class Parser {

	private PushbackReader reader;

	public AST ast = new AST(); //FIXME DEBUG ONLY

	private Data data = new Data();

	private String unReadCommand = null;
	private boolean isLoop = false;

	public Parser(String file) throws FileNotFoundException {
		this.reader = new PushbackReader(new FileReader(file));
	}

	public Parser(File file) throws FileNotFoundException {
		this.reader = new PushbackReader(new FileReader(file));
	}

	public void parse() throws IOException, SyntaxErrorException,
			VariableNotDeclaredException, IncorrectConversionException,
			UnexceptedEndOfFileException {
		Node racine = this.ast.getRacine();
		while (!this.isEndOfFile()) {
			this.readCommand(racine);
			if(!this.isEndOfCommand())
				throw new SyntaxErrorException();
			else
				this.reader.read();
		}
	}

	private void readCommand(Node node) throws IOException,
			SyntaxErrorException, VariableNotDeclaredException,
			IncorrectConversionException, UnexceptedEndOfFileException {

		String command = null;
		command = this.readCommandName();
		if (command.equalsIgnoreCase("end")) { // Fin de boucle on retourne en
												// arrière
			if (!this.isLoop) {
				throw new SyntaxErrorException();
			} else {
				this.isLoop = false;
			}
		} else if (command.equalsIgnoreCase("if")) {
			this.readIfCommand(node);
		} else if (command.equalsIgnoreCase("while")) {
			// Ajout de la commande dans l'arbre
			Node whilenode = new Node(new WhileCommand(), node); 
			node.add(whilenode);
			this.isLoop = true;
			while (this.isLoop) {
				this.readCommand(whilenode);
			}
		} else if (command.equalsIgnoreCase("let")) {

		}
	}

	private String readCommandName() throws IOException,
			UnexceptedEndOfFileException {
		if (this.unReadCommand != null) {
			String cmd = this.unReadCommand;
			this.unReadCommand = null;
			return cmd;
		}
		this.ignoreSpace();
		int charac = reader.read();
		String command = "";
		if (charac == -1)
			throw new UnexceptedEndOfFileException();

		boolean ok = false;
		while (charac != -1 && !ok) {
			if(this.isEmptyChar(charac) || this.isEndCommandChar(charac)){
				reader.unread(charac);
				ok = true;
			}else{
				command += (char) charac;
				charac = reader.read();
			}
		}
		return command;
	}

	private void readIfCommand(Node node) throws IOException,
			SyntaxErrorException, VariableNotDeclaredException,
			IncorrectConversionException, UnexceptedEndOfFileException {
		
		// Ajout de la commande dans l'arbre
		Node ifnode = new Node(new IfCommand(), node);
		node.add(ifnode);

		this.readExpression(ifnode); // Expression de la verification

		String command = this.readCommandName();
		if (!command.equalsIgnoreCase("then")) { // La commande a execute
			throw new SyntaxErrorException();
		}
		this.readCommand(ifnode);

		command = this.readCommandName();
		if (command.equalsIgnoreCase("else")) { // Sinon
			this.readCommand(ifnode);
		} else {
			this.unReadCommand = command;
		}
	}

	private void readExpression(Node node) throws IOException,
			SyntaxErrorException, VariableNotDeclaredException,
			IncorrectConversionException, UnexceptedEndOfFileException {

		boolean needNextValue = false; // Prochaine valeur attendu : valeur
		boolean isPrio = false; // Dernière opération * ou / donc priorité
		Node simpleOperatorNode = node; // Dernier node avec le quel on a fait +
		Node currentNode = node; // Dernier node sur lequel travailler
		String command; // Commande en cours de trairement

		boolean isBooleanExpression = false;

		boolean finish = false;

		while (!isEndOfCommand() && !finish) {
			command = readCommandName();

			// Peut arriver s'il y a de l'espace après la
			// variable et qu'on est à la fin de la commande
			if (command.isEmpty() && needNextValue)
				throw new SyntaxErrorException();
			if (Utils.isSystemCommand(command) && needNextValue)
				throw new SyntaxErrorException();

			if (!command.isEmpty()) {
				if (!Utils.isSystemCommand(command)) {
					if (Utils.isOperatorCommand(command)) {
						if (needNextValue || isBooleanExpression) {
							throw new SyntaxErrorException();
						} else {
							if (Devided.isDividedCommand(command)
									|| Times.isTimesCommand(command)) {
								Command commandC;
								if (Times.isTimesCommand(command)) {
									commandC = new Times();
								} else {
									commandC = new Devided();
								}
								if (!isPrio) {
									simpleOperatorNode = currentNode
											.getFather();
									isPrio = true;
								}
								Node n = new Node(commandC,
										currentNode.getFather());

								currentNode.getFather()
										.remplace(currentNode, n);
								currentNode.setFather(n);
								n.add(currentNode);
								currentNode = n;

								needNextValue = true;
							} else {
								Command commandC;
								if (Plus.isPlusCommand(command)) {
									commandC = new Plus();
								} else {
									commandC = new Minus();
								}
								Node father;
								Node child;
								if (isPrio) {
									father = simpleOperatorNode.getFather();
									child = simpleOperatorNode;

								} else {
									father = currentNode.getFather();
									child = currentNode;
								}
								Node n = new Node(commandC, father);
								child.getFather().remplace(currentNode, n);
								child.setFather(n);
								n.add(currentNode);
								isPrio = false;
								needNextValue = true;
							}
						}
					} else {
						if (command.equalsIgnoreCase("true")
								|| command.equalsIgnoreCase("false")) {
							if (needNextValue)
								throw new IncorrectConversionException();
							Node n = new Node(new BooleanType(command),
									currentNode);
							currentNode.add(n);
							currentNode = n;
							isBooleanExpression = true;
						} else {
							if (isInteger(command)) {
								Node n = new Node(new IntegerType(command),
										currentNode);
								currentNode.add(n);
								currentNode = n;
								isBooleanExpression = false;
								needNextValue = false;
							} else {
								if (!data.isDeclaredVariable(command))
									throw new VariableNotDeclaredException();
							}
						}

					}
				} else {
					this.unReadCommand = command;
					finish = true;
				}
			}
		}
	}

	private void ignoreSpace() throws IOException {
		int charac = reader.read();
		boolean ok = false;

		while (charac != -1 && !ok) {
			if(this.isEmptyChar(charac)){
				charac = reader.read();
			}else{
				reader.unread(charac);
				ok = true;
			}
		}
	}

	private boolean isEndOfCommand() throws IOException {
		int charac = reader.read();
		reader.unread(charac);
		return charac == ';' || charac == -1;
	}

	private boolean isEndOfFile() throws IOException {
		int charac = reader.read();
		reader.unread(charac);
		return charac == -1;
	}

	private boolean isEmptyChar(int c) {
		return c == '	' || c == ' '  || c == '\n' || c == '\r';
	}
	
	private boolean isEndCommandChar(int charac) {
		return charac == ';';
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
