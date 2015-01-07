package environment;

import exception.InterpreterException;

/**
 * The Class Fork. Create a fork for the program Catch exception that can be re
 * thrown or checked by the father of the fork
 */
public class Fork extends Thread {

	/** The node. */
	private Node node;

	/** The data. */
	private Data data;

	/** The exception. */
	private InterpreterException exception;

	/**
	 * Instantiates a new fork.
	 *
	 * @param node
	 *            the node
	 * @param data
	 *            the data
	 */
	public Fork(Node node, Data data) {
		super();
		this.node = node;
		this.data = data;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.lang.Thread#run()
	 */
	public void run() {
		try {
			node.getFather().getCommand()
					.executeAtChilds(node.getFather(), data, node);
		} catch (InterpreterException | InterruptedException e) {
			this.exception = (InterpreterException) e;
			e.printStackTrace();
		}
	}

	/**
	 * Checks for exception.
	 *
	 * @return true, if successful
	 */
	public boolean hasException() {
		return this.exception != null;
	}

	/**
	 * Gets the exception.
	 *
	 * @return the exception
	 */
	public InterpreterException getException() {
		return exception;
	}

}