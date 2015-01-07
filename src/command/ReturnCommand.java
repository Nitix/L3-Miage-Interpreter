package command;

import ast2.Data;
import ast2.Node;
import exception.InterpreterException;

public class ReturnCommand extends Command {

	/**
	 * 
	 */
	private static final long serialVersionUID = -5070762742763862974L;

	public ReturnCommand(int line) {
		super(line, "return");
	}

	@Override
	public void execute(Node node, Data data)
			throws InterpreterException, InterruptedException {
		data.setTerminated(true);
	}

}
