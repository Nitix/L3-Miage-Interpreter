package command;

import AST.Data;
import AST.Node;
import AST.Variable;
import exception.InterpreterException;

public class PrintCommand extends Command {
	
	private boolean isString;

	public PrintCommand(int line, String command, boolean isString) {
		super(line, command);
		this.isString = isString;
	}

	@Override
	public void execute(Node node, Data data) throws InterpreterException,
			InterruptedException {
		if(this.isString){
			System.out.println(this.getCommand().replaceAll("\"", ""));
		}else{
			Variable var = data.getVariable(this.getCommand(), this.getLine());
			if(var.isBooleanValue()){
				System.out.println(var.getBooleanValue());
			}else if(var.isIntValue()){
				System.out.println(var.getIntValue());
			}else if(var.isFork()){
				System.out.println(var.getForkName());
			}
		}
	}

}
