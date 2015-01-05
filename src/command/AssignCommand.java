package command;

import parser.IncorrectConversionException;
import AST.Data;
import AST.InexistantVariableException;
import AST.Node;
import AST.Variable;
import AST.VariableAlreadyExistException;

public class AssignCommand extends Command {

	private String nameVariable;

	public AssignCommand(String name) {
		this.nameVariable = name;
	}

	@Override
	public String toString() {
		return "AssignCommand [nameVariable=" + nameVariable + "]";
	}

	@Override
	public void execute(Node node, Data data)
			throws VariableAlreadyExistException, IncorrectConversionException,
			IncorrectMethodCallException, InexistantVariableException,
			VariableNotDeclaredException {
		Node value = node.getChilds().get(0);
		value.execute(data);
		Command valueCommand = value.getCommand();
		Variable var = new Variable();
		if(!valueCommand.hasValue(data))
			throw new IncorrectMethodCallException();
		if(valueCommand.hasIntValue(data)){
			var.setIntValue(valueCommand.getIntValue(data));
		}else if(valueCommand.hasBooleanValue(data)){
			var.setBooleanValue(valueCommand.getBooleanValue(data));
		}else if(valueCommand.isFork(data)){
			var.setFork(valueCommand.getFork(data), valueCommand.getForkName(data));
		}else{
			throw new IncorrectConversionException();
		}
		data.addVariable(nameVariable, var);
		value.removeVariable(data);
	}
	
	@Override 
	public void partialExecute(Node node, Data data) //Called from fork
			throws VariableAlreadyExistException, IncorrectConversionException,
			IncorrectMethodCallException, InexistantVariableException,
			VariableNotDeclaredException {
		Node value = node.getChilds().get(0);
		Command valueCommand = value.getCommand();
		Variable var = new Variable();
		if(!valueCommand.isFork(data)){
			var.setFork(valueCommand.getFork(data), valueCommand.getForkName(data));
		}
		value.removeVariable(data);

	}
	

}