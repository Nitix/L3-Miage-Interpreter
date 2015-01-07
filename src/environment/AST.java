package environment;

import java.io.Serializable;

import command.Root;
import exception.InterpreterException;

/**
 * The Class AST. Contain the root of AST, an AST is full of nodes
 */
public class AST implements Serializable {

	/** The Constant serialVersionUID. */
	private static final long serialVersionUID = -7878415460834312828L;

	/** The line. */
	private int line;

	/** The root of the ast. */
	public Node root;

	/**
	 * Instantiates a new ast.
	 */
	public AST() {
		this.root = new Node(new Root(), null);
	}

	/**
	 * Gets the racine.
	 *
	 * @return the racine
	 */
	public Node getRoot() {
		return this.root;
	}

	/**
	 * Execute.
	 *
	 * @throws InterpreterException
	 *             the interpreter exception
	 * @throws InterruptedException
	 *             the interrupted exception
	 */
	public void execute() throws InterpreterException, InterruptedException {
		this.root.execute(new Data());
	}

	/**
	 * Sets the line.
	 *
	 * @param line
	 *            the new line
	 */
	public void setLine(int line) {
		this.line = line;
	}

	/**
	 * Gets the line.
	 *
	 * @return the line
	 */
	public int getLine() {
		return line;
	}
}
