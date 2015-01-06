package parser;

import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PushbackReader;

import command.*;
import exception.IncorrectConversionException;
import AST.AST;
import AST.Node;

public class Parser {

	private PushbackReader reader;

	private AST ast = new AST(); 
	private String unReadCommand = null;

	private boolean isLoop = false;
	private boolean isFinish = false;
	
	private boolean isComplete = false;
	
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
	
	public AST getAST(){
		ast.setLine(line);
		return this.ast;
	}
	
	public boolean isComplete() {
		return isComplete;
	}
	
	public void close() throws IOException{
		this.reader.close();
	}
	
	
	public void parse(String string) throws IOException, SyntaxErrorException,
		VariableNotDeclaredException, IncorrectConversionException {
		this.reader.close();
		this.reader = new PushbackReader(new InputStreamReader(new ByteArrayInputStream(string.getBytes())));
		try{
			parse();
		}catch(UnexceptedEndOfFileException e){
		}
	}

	public void parse() throws IOException, SyntaxErrorException,
			VariableNotDeclaredException, IncorrectConversionException,
			UnexceptedEndOfFileException {
		Node racine = this.ast.getRacine();
		while (!this.isEndOfFile() && !this.isFinish) {
			isComplete = false;
			this.readCommand(racine);
		}
		isComplete = true;
	}

	private void readCommand(Node node) throws IOException,
			SyntaxErrorException, VariableNotDeclaredException,
			IncorrectConversionException, UnexceptedEndOfFileException {

		String command = this.readCommandName();
		if (command.equalsIgnoreCase("end")) { // Fin de boucle on retourne en
												// arrière
			if (!this.isLoop) {
				throw new SyntaxErrorException(this.line, command);
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
		} else if (command.equalsIgnoreCase("print")) {
			this.readPrintCommand(node);
		} else if (command.equalsIgnoreCase("return")){
			node.add(new Node(new ReturnCommand(line), node));
		} else {
			if (Utils.isCommand(command))
				throw new SyntaxErrorException(this.line, command);
			this.readAffectionCommand(command, node);
		}
	}

	private void readPrintCommand(Node node) throws UnexceptedEndOfFileException, IOException, SyntaxErrorException {
		String command = readCommandName();

		if(Utils.isCommand(command)){
			throw new SyntaxErrorException(line, command, "Text or Variable or Value expected");
		}
		node.add(new Node(new PrintCommand(line, command, command.startsWith("\"")), node));
		command = this.readCommandName();
		if (!command.equalsIgnoreCase(";"))
			throw new SyntaxErrorException(this.line, command, "; expected");
	}

	private void readAffectionCommand(String var, Node node)
			throws UnexceptedEndOfFileException, IOException,
			SyntaxErrorException, VariableNotDeclaredException,
			IncorrectConversionException {
		String command = this.readCommandName();
		Node assign = new Node(new AssignCommand(var, line), node);
		node.add(assign);
		if (!command.equalsIgnoreCase(":="))
			throw new SyntaxErrorException(this.line, command, ":= expected");

		command = this.readCommandName();
		if (command.equalsIgnoreCase("fork()")) {
			assign.add(new Node(new ForkCommand(line), assign));
		} else if(command.equalsIgnoreCase("wait(")){
			command = this.readCommandName();
			if(Utils.isCommand(command))
				throw new SyntaxErrorException(line, command, "Fork variable expected");
			assign.add(new Node(new WaitCommand(command, line), assign));
			command = this.readCommandName();
			if(!command.equals(")"))
				throw new SyntaxErrorException(line, command, ") expected");
		} else {
			this.unReadCommand = command;
			this.readExpression(assign);
		}
		command = this.readCommandName();
		if (!command.equalsIgnoreCase(";"))
			throw new SyntaxErrorException(this.line, command, "; expected");
	}

	private void readLetcommand(Node node) throws UnexceptedEndOfFileException,
			IOException, SyntaxErrorException, VariableNotDeclaredException,
			IncorrectConversionException {
		String var = this.readCommandName();
		if (Utils.isCommand(var))
			throw new SyntaxErrorException(this.line, var);
		String command = this.readCommandName();
		Node let;
		if (command.equalsIgnoreCase("in")) {
			let = new Node(new DeclareCommand(var, line), node);
			node.add(let);
		} else {
			if (!command.equalsIgnoreCase("be")) {
				throw new SyntaxErrorException(this.line, command);
			}
			String alias = this.readCommandName();
			if (Utils.isCommand(alias))
				throw new SyntaxErrorException(this.line, command);
			if (alias.equalsIgnoreCase("var"))
				throw new SyntaxErrorException(this.line, command);
			let = new Node(new AliasCommand(var, alias, line), node);
			node.add(let);
			command = this.readCommandName();
			if (!command.equalsIgnoreCase("in"))
				throw new SyntaxErrorException(this.line, command);
		}
		Node container = new Node(new Container(line, command), let);
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
			throw new UnexceptedEndOfFileException(line, command);

		if(charac == '"'){
			boolean ignoreChar = true;
			while (charac != -1) {
				if(charac == '\\')
					ignoreChar = true;
				if(charac == '"'){
					if(ignoreChar){
						ignoreChar = false;
					}else{
						command += (char) charac;
						break;
					}
				}
				command += (char) charac;
				charac = reader.read();
			}
			if(charac == -1)
				throw new UnexceptedEndOfFileException(line, command);
		}else{
			boolean ok = false;
			boolean isUniqueChar = true;
			boolean isparenthesis = false;
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
						if(isparenthesis && charac != ')'){
							reader.unread(charac);
							ok = true;
						}else{
							if(charac == '('){
								isparenthesis = true;
								command += (char) charac;
								charac = reader.read();
							}else if(charac == ')'){
								if(!isparenthesis){
									if(isUniqueChar){
										command += (char) charac;
									}else{
										reader.unread(charac);
									}
									ok = true;
								}else{
									command += (char) charac;
									ok = true;
								}
							}else{
								command += (char) charac;
								charac = reader.read();
							}
						}
					}
					isUniqueChar = false;
				}
			}
		}
		return command;

	}

	private void readIfCommand(Node node) throws IOException,
			SyntaxErrorException, VariableNotDeclaredException,
			IncorrectConversionException, UnexceptedEndOfFileException {

		// Ajout de la commande dans l'arbre
		Node ifnode = new Node(new IfCommand(line), node);
		node.add(ifnode);

		// Condition
		this.readExpression(ifnode); // Expression de la verification

		// Then
		String command = this.readCommandName();
		if (!command.equalsIgnoreCase("then")) { // La commande a execute
			throw new SyntaxErrorException(this.line, command, "then expected");
		}
		Node thennode = new Node(new Container(line, command), ifnode);
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
			Node elsenode = new Node(new Container(line, command), ifnode);
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
		Node whilenode = new Node(new WhileCommand(line), node);
		node.add(whilenode);

		// Condition
		this.readExpression(whilenode); // Expression de la verification

		// Then
		String command = this.readCommandName();
		if (!command.equalsIgnoreCase("do")) { // La commande a execute
			throw new SyntaxErrorException(this.line, command, "do expected");
		}
		Node donode = new Node(new Container(line, command), whilenode);
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
				throw new SyntaxErrorException(line, "Expression", "End of expression needed");
		}

		private void readOperator() throws UnexceptedEndOfFileException,
				IOException, SyntaxErrorException {
			String command = readCommandName();
			if (!Utils.isOperatorCommand(command))
				throw new SyntaxErrorException(line, command, "Operator expected");
			if (this.needBooleanOperator
					&& !Utils.isBooleanOperatorCommand(command))
				throw new SyntaxErrorException(line, command, "Boolean expected");

			if (Utils.isBooleanOperatorCommand(command)) {
				if (!this.isBooleanExpression)
					throw new SyntaxErrorException(line, command, "Can not use int with boolean expression");
				Node operator;
				if (command.equalsIgnoreCase("and")) {
					operator = new Node(new AndOperator(line),
							this.topBooleanNode.getFather());

				} else {
					operator = new Node(new OrOperator(line),
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
						commandC = new Times(line);
					} else {
						commandC = new Devided(line);
					}

					Node n = new Node(commandC, currentNode.getFather());

					currentNode.getFather().remplace(currentNode, n);
					currentNode.setFather(n);
					n.add(currentNode);
					if (!isPrio) {
						isPrio = true;
						if (this.nextValueIsTop) {
							simpleOperatorNode = n;
						}
					}
					currentNode = n;

				} else {
					Command commandC;
					if (Plus.isPlusCommand(command)) {
						commandC = new Plus(line);
					} else {
						commandC = new Minus(line);
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
					this.nextValueIsTop = false;
				}
			} else {
				Node operator;
				if (command.equalsIgnoreCase("<")) {
					operator = new Node(new LowerComparatorCommand(line),
							this.simpleOperatorNode.getFather());
				} else {
					operator = new Node(new EqualComparatorCommand(line),
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
		}

		private void readValue() throws UnexceptedEndOfFileException,
				IOException, VariableNotDeclaredException, SyntaxErrorException {
			String command = readCommandName();
			if (Utils.isSystemCommand(command))
				throw new SyntaxErrorException(line, command, "Value expected");

			Node value;
			// La valeur est un booléen
			if (command.equalsIgnoreCase("true")
					|| command.equalsIgnoreCase("false")) {
				value = new Node(new BooleanType(command, line), this.currentNode);
				this.currentNode.add(value);
				this.isBooleanExpression = true;
				this.needBooleanOperator = true;
				this.needBooleanExpression = false;
				this.isNotCommand = false;

				// La valeur est un entier
			} else if (isInteger(command)) {
				if (isNotCommand)
					throw new SyntaxErrorException(line, command, "Can not convert an int to boolean");
				value = new Node(new IntegerType(command, line), this.currentNode);
				this.currentNode.add(value);
				this.needBooleanOperator = false;

			} else {
				if (command.equalsIgnoreCase("not")) {
					// Utilisation de variable, aucune erreur ne peut être
					// detecté
					// sans calcul.
					value = new Node(new NotCommand(line), this.currentNode);
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
					value = new Node(new VariableCommand(command, line),
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

	public void setAST(AST ast) {
		this.ast = ast;
		this.line = ast.getLine();
	}
}
