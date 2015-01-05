package command;

import AST.Data;
import AST.Fork;
import AST.Node;
import AST.Variable;
import exception.InterpreterException;

public class WaitCommand extends Command {

	private String forkName;

	public WaitCommand(String forkName, int line) {
		super(line, "Wait()");
		this.forkName = forkName;
	}

	@Override
	public void execute(Node node, Data data)
			throws InterpreterException, InterruptedException{
		Variable forkVar = data.getVariable(this.forkName, this.getLine());
		if(!forkVar.isFork()){
			throw new IncorrectMethodCallException(this.getIntValue(data), "wait()", "Can not call wait on non fork variable");
		}
		Fork fork = forkVar.getFork();
		if(fork == null){
			throw new IncorrectMethodCallException(this.getIntValue(data), "wait()", "Fork variable is empty");
		}
		fork.join();
		Variable var = new Variable();

		if(fork.hasException()){
			var.setIntValue(1);
		}else{
			var.setIntValue(0);
		}
		data.addVariable(getUuid(), var);
	}

	@Override
	public boolean hasValue(Data data) throws InexistantVariableException {
		return true;
	}

	@Override
	public boolean hasIntValue(Data data) throws InexistantVariableException {
		return true;
	}

	@Override
	public int getIntValue(Data data) throws IncorrectMethodCallException,
			InexistantVariableException {
		return data.getVariable(getUuid(), getLine()).getIntValue();
	}
	

}
