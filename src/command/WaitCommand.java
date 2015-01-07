package command;

import ast2.Data;
import ast2.Fork;
import ast2.Node;
import ast2.variable.IntVariable;
import ast2.variable.Variable;
import exception.InterpreterException;

public class WaitCommand extends Command {

	/**
	 * 
	 */
	private static final long serialVersionUID = -553468386406316823L;
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
			throw new IncorrectMethodCallException(this.getLine(), "wait()", "Can not call wait on non fork variable");
		}
		Fork fork = forkVar.getFork();
		if(fork == null){
			throw new IncorrectMethodCallException(this.getLine(), "wait()", "Fork variable is empty");
		}
		fork.join();
		Variable var = new IntVariable(getUuid());

		if(fork.hasException()){
			var.setIntValue(-1);
		}else{
			var.setIntValue(0);
		}
		data.addVariable(getUuid(), var);
	}

	@Override
	public boolean hasValue(Data data) throws InexistantVariableException {
		return true;
	}
	
	public Variable getVariable(Data data) throws InexistantVariableException{
		return data.getVariable(getUuid(), getLine());
	}
}
