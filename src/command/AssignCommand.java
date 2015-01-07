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
		Variable var = data.getVariable(nameVariable, getLine());

		if(!valueCommand.hasValue(data))
			throw new IncorrectMethodCallException(this.getLine(), this.getCommand());
		Variable child = valueCommand.getVariable(data);
		
		if(var.isAlias()){
			var = data.getVariable(nameVariable, getLine());
			if(child.isIntValue()){
				var = var.setIntValue(child.getIntValue());
			}else if(child.isBooleanValue()){
				var = var.setBooleanValue(child.getBooleanValue());
			}else if(child.isFork()){
				var = var.setFork(child.getFork(), child.getForkName());
			}else{
				throw new IncorrectConversionException(this.getLine(), this.getCommand());
			}

		}else{
			if(child.isIntValue()){
				var = new IntVariable(child.getIntValue(), nameVariable);
			}else if(child.isBooleanValue()){
				var = new BooleanVariable(child.getBooleanValue(), nameVariable);
			}else if(child.isFork()){
				var = new ForkVariable(child.getFork(), child.getForkName(), nameVariable);
			}else{
				throw new IncorrectConversionException(this.getLine(), this.getCommand());
			}
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
		if(!valueCommand.getVariable(data).isFork()){
			throw new IncorrectMethodCallException(getLine(), getCommand());
		}
		Variable child = valueCommand.getVariable(data);
		Variable var = data.getVariable(nameVariable, getLine());
		if(child.isAlias()){
			var = var.setFork(child.getFork(), child.getForkName());
		}else{
			var = new ForkVariable(child.getFork(), child.getForkName(), nameVariable);
		}
		data.addVariable(nameVariable, var);
		value.removeVariable(data);

	}
	

}
