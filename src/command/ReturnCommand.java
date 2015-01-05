package command;

import AST.Data;
import AST.Node;
import exception.InterpreterException;

public class ReturnCommand extends Command {

	public ReturnCommand(int line) {
		super(line, "return");
	}

	@Override
	public void execute(Node node, Data data)
			throws InterpreterException, InterruptedException {
		data.setTerminated(true);
	}

}
