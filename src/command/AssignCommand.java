package command;

import exception.IncorrectConversionException;
import exception.InterpreterException;
import AST.Data;
import AST.Node;
import AST.variable.BooleanVariable;
import AST.variable.ForkVariable;
import AST.variable.IntVariable;
import AST.variable.Variable;

public class AssignCommand extends Command {

	/**
	 * 
	 */
	private static final long serialVersionUID = 390131133752547143L;
	private String nameVariable;

	public AssignCommand(String name, int line) {
		super(line, ":=");
		this.nameVariable = name;
	}

	@Override
	public String toString() {
		return "AssignCommand [nameVariable=" + nameVariable + "]";
	}

	@Override
	public void execute(Node node, Data data)
			throws InterruptedException, InterpreterException {
		Node value = node.getChilds().get(0);
		value.execute(data);
		Command valueCommand = value.getCommand();
		Variable var;
		if(!valueCommand.hasValue(data))
			throw new IncorrectMethodCallException(this.getLine(), this.getCommand());
		if(valueCommand.hasIntValue(data)){
			var = new IntVariable(valueCommand.getIntValue(data));
		}else if(valueCommand.hasBooleanValue(data)){
			var = new BooleanVariable(valueCommand.getBooleanValue(data));
		}else if(valueCommand.isFork(data)){
			var = new ForkVariable(valueCommand.getFork(data), valueCommand.getForkName(data));
		}else{
			throw new IncorrectConversionException(this.getLine(), this.getCommand());
		}
		data.addVariable(nameVariable, var);
		value.removeVariable(data);
	}
	
	@Override 
	public void partialExecute(Node node, Data data) //Called from fork
			throws InterpreterException,
			IncorrectMethodCallException, InexistantVariableException,
			VariableNotDeclaredException, InterruptedException {
		Node value = node.getChilds().get(0);
		Command valueCommand = value.getCommand();
		if(!valueCommand.isFork(data)){
			throw new IncorrectMethodCallException(getLine(), getCommand());
		}
		Variable var = new ForkVariable(valueCommand.getFork(data), valueCommand.getForkName(data));
		data.addVariable(nameVariable, var);
		value.removeVariable(data);

	}
	

}
