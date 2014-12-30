package parser;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PushbackReader;

import command.*;
import AST.AST;
import AST.Node;

public class Parser {

	private PushbackReader reader;

	public AST ast = new AST(); // FIXME DEBUG ONLY
	private String unReadCommand = null;

	private boolean isLoop = false;
	private boolean isFinish = false;

	private int line = 1;

	public Parser() {
		this.reader = new PushbackReader(new InputStreamReader(System.in));
	}

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
		while (!this.isEndOfFile() && !this.isFinish) {
			this.readCommand(racine);
		}
	}

	private void readCommand(Node node) throws IOException,
			SyntaxErrorException, VariableNotDeclaredException,
			IncorrectConversionException, UnexceptedEndOfFileException {

		String command = this.readCommandName();
		if (command.equalsIgnoreCase("end")) { // Fin de boucle on retourne en
												// arrière
			if (!this.isLoop) {
				throw new SyntaxErrorException(this.line);
			} else {
				this.isLoop = false;
			}
		} else if (command.equalsIgnoreCase("if")) {
			this.readIfCommand(node);
		} else if (command.equalsIgnoreCase("while")) {
			this.readWhileCommand(node);
		} else if (command.equalsIgnoreCase("let")) {
			this.readLetcommand(node);
		} else if (command.equalsIgnoreCase("exit")) {
			this.isFinish = true;
		} else {
			if (Utils.isCommand(command))
				throw new SyntaxErrorException(this.line);
			this.readAffectionCommand(command, node);
		}
	}

	private void readAffectionCommand(String var, Node node)
			throws UnexceptedEndOfFileException, IOException,
			SyntaxErrorException, VariableNotDeclaredException,
			IncorrectConversionException {
		String command = this.readCommandName();
		Node assign = new Node(new AssignCommand(var), node);
		node.add(assign);
		if (!command.equalsIgnoreCase(":="))
			throw new SyntaxErrorException(this.line);

		command = this.readCommandName();
		if (command.equalsIgnoreCase("fork()")) {
			Node n = new Node(new ForkCommand(), assign);
			assign.add(n);
		} else {
			this.unReadCommand = command;
			this.readExpression(assign);
		}
		command = this.readCommandName();
		if (!command.equalsIgnoreCase(";"))
			throw new SyntaxErrorException(this.line);
	}

	private void readLetcommand(Node node) throws UnexceptedEndOfFileException,
			IOException, SyntaxErrorException, VariableNotDeclaredException,
			IncorrectConversionException {
		String var = this.readCommandName();
		if (Utils.isCommand(var))
			throw new SyntaxErrorException(this.line);
		String command = this.readCommandName();
		Node let;
		if (command.equalsIgnoreCase("in")) {
			let = new Node(new DeclareCommand(var), node);
			node.add(let);
		} else {
			if (!command.equalsIgnoreCase("be")) {
				throw new SyntaxErrorException(this.line);
			}
			String alias = this.readCommandName();
			if (Utils.isCommand(alias))
				throw new SyntaxErrorException(this.line);
			if (alias.equalsIgnoreCase("var"))
				throw new SyntaxErrorException(this.line);
			let = new Node(new AliasCommand(var, alias), node);
			node.add(let);
			command = this.readCommandName();
			if (!command.equalsIgnoreCase("in"))
				throw new SyntaxErrorException(this.line);
		}
		Node container = new Node(new Container(), let);
		let.add(container);
		command = this.readCommandName();
		while (!command.equalsIgnoreCase("end")) {
			this.unReadCommand = command;
			this.readCommand(container);
			command = this.readCommandName();
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
		boolean isUniqueChar = true;
		while (charac != -1 && !ok) {
			if (this.isEmptyChar(charac)) {
				reader.unread(charac);
				ok = true;
			} else {
				if (this.isUniqueCharCommand(charac)) {
					if (isUniqueChar) {
						command += (char) charac;
						ok = true;
					} else {
						reader.unread(charac);
						ok = true;
					}
				} else {
					command += (char) charac;
					charac = reader.read();
				}
				isUniqueChar = false;
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

		// Condition
		this.readExpression(ifnode); // Expression de la verification

		// Then
		String command = this.readCommandName();
		if (!command.equalsIgnoreCase("then")) { // La commande a execute
			throw new SyntaxErrorException(this.line);
		}
		Node thennode = new Node(new Container(), ifnode);
		ifnode.add(thennode);

		command = this.readCommandName();
		while (!command.equalsIgnoreCase("end")
				&& !command.equalsIgnoreCase("else")) {
			this.unReadCommand = command;
			this.readCommand(thennode);
			command = this.readCommandName();
		}

		// Else
		if (command.equalsIgnoreCase("else")) {
			Node elsenode = new Node(new Container(), ifnode);
			ifnode.add(elsenode);
			command = this.readCommandName();
			while (!command.equalsIgnoreCase("end")) {
				this.unReadCommand = command;
				this.readCommand(elsenode);
				command = this.readCommandName();
			}
		}
	}

	private void readWhileCommand(Node node) throws SyntaxErrorException,
			UnexceptedEndOfFileException, IOException,
			VariableNotDeclaredException, IncorrectConversionException {
		// Ajout de la commande dans l'arbre
		Node whilenode = new Node(new WhileCommand(), node);
		node.add(whilenode);

		// Condition
		this.readExpression(whilenode); // Expression de la verification

		// Then
		String command = this.readCommandName();
		if (!command.equalsIgnoreCase("do")) { // La commande a execute
			throw new SyntaxErrorException(this.line);
		}
		Node donode = new Node(new Container(), whilenode);
		whilenode.add(donode);
		command = this.readCommandName();
		while (!command.equalsIgnoreCase("end")) {
			this.unReadCommand = command;
			this.readCommand(donode);
			command = this.readCommandName();
		}
	}

	private void readExpression(Node node) throws IOException,
			SyntaxErrorException, VariableNotDeclaredException,
			IncorrectConversionException, UnexceptedEndOfFileException {

		ExpressionReader er = new ExpressionReader(node);
		er.parseExpression();
	}

	private void ignoreSpace() throws IOException {
		int charac = reader.read();
		boolean ok = false;

		while (charac != -1 && !ok) {
			if (this.isEmptyChar(charac)) {
				if (charac == '\n' || charac == '\r') {
					this.line++;
					int c = reader.read();
					if (charac == '\n' || charac == '\r') {
						charac = reader.read();
					} else {
						charac = c;
					}
				} else {
					charac = reader.read();
				}
			} else {
				reader.unread(charac);
				ok = true;
			}
		}
	}

	private boolean isEndOfFile() throws IOException {
		if (this.unReadCommand != null)
			return false;
		int charac = reader.read();
		reader.unread(charac);
		return charac == -1;
	}

	private boolean isEmptyChar(int c) {
		return c == '	' || c == ' ' || c == '\n' || c == '\r';
	}

	private boolean isUniqueCharCommand(int c) {
		return c == ';' || c == '*' || c == '/' || c == '+' || c == '-';
	}

	/*
	 * private boolean isNewLine(int c){ urn }
	 */
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

	private class ExpressionReader { // FIXME Prioity AND OR
		// Prochaine valeur attendu : valeur
		private boolean needNextValue = false;

		// Dernière opération * ou / donc priorité
		private boolean isPrio = false;

		// Dernier node avec le quel ona fait +
		private Node simpleOperatorNode = null;

		// Dernier node sur lequel travailler
		private Node currentNode = null;

		private Node topBooleanNode = null;

		private boolean needBooleanOperator = false;

		private boolean isBooleanExpression = false;

		private boolean needOperator = false;

		private boolean needBooleanExpression = false;

		private boolean nextValueIsTop = true;

		private boolean nextOperatorIsTop;

		private boolean nextOperatorIsBooleanTop;

		private boolean nextValueIsBooleanTop = true;

		private boolean isNotCommand = false;

		public ExpressionReader(Node node) {
			this.simpleOperatorNode = node;
			this.currentNode = node;
			this.topBooleanNode = node;
			this.nextOperatorIsBooleanTop = true;
		}

		public void parseExpression() throws UnexceptedEndOfFileException,
				IOException, VariableNotDeclaredException, SyntaxErrorException {
			this.readValue();
			while (!this.isExpressionEnded()) {
				if (this.needOperator) {
					this.readOperator();
				} else {
					this.readValue();
				}
			}
			if (this.needBooleanExpression || this.needNextValue)
				throw new SyntaxErrorException(line);
		}

		private void readOperator() throws UnexceptedEndOfFileException,
				IOException, SyntaxErrorException {
			String command = readCommandName();
			if (!Utils.isOperatorCommand(command))
				throw new SyntaxErrorException(line);
			if (this.needBooleanOperator
					&& !Utils.isBooleanOperatorCommand(command))
				throw new SyntaxErrorException(line);

			if (Utils.isBooleanOperatorCommand(command)) {
				if (!this.isBooleanExpression)
					throw new SyntaxErrorException(line);
				Node operator;
				if (command.equalsIgnoreCase("and")) {
					operator = new Node(new AndOperator(),
							this.topBooleanNode.getFather());

				} else {
					operator = new Node(new OrOperator(),
							this.topBooleanNode.getFather());

				}
				this.topBooleanNode.getFather().remplace(this.topBooleanNode,
						operator);
				operator.add(this.topBooleanNode);
				this.topBooleanNode.setFather(operator);
				this.topBooleanNode = operator;
				this.isBooleanExpression = false;
				this.needBooleanExpression = true;
				this.isPrio = false;
				this.simpleOperatorNode = operator;
				this.currentNode = operator;
				this.nextValueIsTop = true;

			} else if (Utils.isArithmeticOperatorCommand(command)) {
				if (Utils.isPrioArithmeticOperatorCommand(command)) {
					Command commandC;
					if (Times.isTimesCommand(command)) {
						commandC = new Times();
					} else {
						commandC = new Devided();
					}

					Node n = new Node(commandC, currentNode.getFather());

					currentNode.getFather().remplace(currentNode, n);
					currentNode.setFather(n);
					n.add(currentNode);
					if (!isPrio) {
						if (this.nextOperatorIsTop) {
							simpleOperatorNode = n;
						} else {
							simpleOperatorNode = currentNode.getFather();
						}
						isPrio = true;
					}

					currentNode = n;

				} else {
					Command commandC;
					if (Plus.isPlusCommand(command)) {
						commandC = new Plus();
					} else {
						commandC = new Minus();
					}
					Node father;
					Node child;
					father = simpleOperatorNode.getFather();
					child = simpleOperatorNode;
					Node n = new Node(commandC, father);
					child.getFather().remplace(simpleOperatorNode, n);
					child.setFather(n);
					n.add(simpleOperatorNode);
					isPrio = false;
					this.simpleOperatorNode = n;
					this.currentNode = n;
				}
			} else {
				Node operator;
				if (command.equalsIgnoreCase("<")) {
					operator = new Node(new LowerComparatorCommand(),
							this.simpleOperatorNode.getFather());
				} else {
					operator = new Node(new EqualComparatorCommand(),
							this.simpleOperatorNode.getFather());
				}
				this.simpleOperatorNode.getFather().remplace(
						this.simpleOperatorNode, operator);
				operator.add(simpleOperatorNode);
				this.simpleOperatorNode.setFather(operator);
				this.simpleOperatorNode = operator;
				this.currentNode = operator;
				this.nextValueIsTop = true;
				this.needBooleanExpression = false;
				this.isBooleanExpression = true;

				if (this.nextOperatorIsBooleanTop) {
					this.topBooleanNode = operator;
					this.nextOperatorIsBooleanTop = false;
				}
			}
			this.needOperator = false;
			this.needNextValue = true;
			this.nextOperatorIsTop = false;
		}

		private void readValue() throws UnexceptedEndOfFileException,
				IOException, VariableNotDeclaredException, SyntaxErrorException {
			String command = readCommandName();
			if (Utils.isSystemCommand(command))
				throw new SyntaxErrorException(line);

			Node value;
			// La valeur est un booléen
			if (command.equalsIgnoreCase("true")
					|| command.equalsIgnoreCase("false")) {
				value = new Node(new BooleanType(command), this.currentNode);
				this.currentNode.add(value);
				this.isBooleanExpression = true;
				this.needBooleanOperator = true;
				this.needBooleanExpression = false;
				this.isNotCommand = false;

				// La valeur est un entier
			} else if (isInteger(command)) {
				if (isNotCommand)
					throw new SyntaxErrorException(line);
				value = new Node(new IntegerType(command), this.currentNode);
				this.currentNode.add(value);
				this.needBooleanOperator = false;

			} else {
				if (command.equalsIgnoreCase("not")) {
					// Utilisation de variable, aucune erreur ne peut être
					// detecté
					// sans calcul.
					value = new Node(new NotCommand(), this.currentNode);
					this.currentNode.add(value);
					this.isBooleanExpression = true;
					this.needBooleanOperator = false;
					this.needBooleanExpression = false;
					if (this.nextValueIsBooleanTop) {
						this.topBooleanNode = value;
						this.nextValueIsBooleanTop = false;
					}
					this.isNotCommand = true;
				} else {
					// Utilisation de variable, aucune erreur ne peut être
					// detecté
					// sans calcul.
					value = new Node(new VariableCommand(command),
							this.currentNode);
					this.currentNode.add(value);
					this.isBooleanExpression = true;
					this.needBooleanOperator = false;
					this.needBooleanExpression = false;
					if (this.nextValueIsBooleanTop) {
						this.topBooleanNode = value;
						this.nextValueIsBooleanTop = false;
					}
					this.isNotCommand = false;
				}
			}
			this.needNextValue = false;
			this.needOperator = true;
			this.nextOperatorIsTop = true;
			if (this.nextValueIsTop) {
				this.simpleOperatorNode = value;
				this.nextValueIsTop = false;
			}
			this.currentNode = value;
			if (this.isNotCommand) {
				this.readValue();
			}
		}

		private boolean isExpressionEnded()
				throws UnexceptedEndOfFileException, IOException {
			String command = readCommandName();
			unReadCommand = command;
			return Utils.isSystemCommand(command);
		}
	}
}
