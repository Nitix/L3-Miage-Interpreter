package command;

import environment.Data;
import environment.Node;
import environment.variable.BooleanVariable;
import environment.variable.ForkVariable;
import environment.variable.IntVariable;
import environment.variable.Variable;
import exception.IncorrectConversionException;
import exception.InterpreterException;

/**
 * The Class AssignCommand. Define a value to the name of the variable
 */
public class AssignCommand extends Command {

	/** The Constant serialVersionUID. */
	private static final long serialVersionUID = 390131133752547143L;

	/** The name variable. */
	private String nameVariable;

	/**
	 * Instantiates a new assign command.
	 *
	 * @param name
	 *            the name
	 * @param line
	 *            the line
	 */
	public AssignCommand(String name, int line) {
		super(line, ":=");
		this.nameVariable = name;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "AssignCommand [nameVariable=" + nameVariable + "]";
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see command.Command#execute(ast.Node, ast.Data)
	 */
	@Override
	public void execute(Node node, Data data) throws InterruptedException,
			InterpreterException {
		Node value = node.getChilds().get(0);
		value.execute(data);
		Command valueCommand = value.getCommand();
		Variable var = data.getVariable(nameVariable, getLine());

		if (!valueCommand.hasValue(data))
			throw new IncorrectMethodCallException(this.getLine(),
					this.getCommand());
		Variable child = valueCommand.getVariable(data);

		if (var.isAlias()) {
			var = data.getVariable(nameVariable, getLine());
			if (child.isIntValue()) {
				var = var.setIntValue(child.getIntValue());
			} else if (child.isBooleanValue()) {
				var = var.setBooleanValue(child.getBooleanValue());
			} else if (child.isFork()) {
				var = var.setFork(child.getFork(), child.getForkName());
			} else {
				throw new IncorrectConversionException(this.getLine(),
						this.getCommand());
			}

		} else {
			if (child.isIntValue()) {
				var = new IntVariable(child.getIntValue(), nameVariable);
			} else if (child.isBooleanValue()) {
				var = new BooleanVariable(child.getBooleanValue(), nameVariable);
			} else if (child.isFork()) {
				var = new ForkVariable(child.getFork(), child.getForkName(),
						nameVariable);
			} else {
				throw new IncorrectConversionException(this.getLine(),
						this.getCommand());
			}
		}
		data.addVariable(nameVariable, var);
		value.removeVariable(data);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see command.Command#partialExecute(ast.Node, ast.Data)
	 */
	@Override
	public void partialExecute(Node node, Data data)
			// Called from fork
			throws InterpreterException, IncorrectMethodCallException,
			InexistentVariableException, VariableNotDeclaredException,
			InterruptedException {
		Node value = node.getChilds().get(0);
		Command valueCommand = value.getCommand();
		if (!valueCommand.getVariable(data).isFork()) {
			throw new IncorrectMethodCallException(getLine(), getCommand());
		}
		Variable child = valueCommand.getVariable(data);
		Variable var = data.getVariable(nameVariable, getLine());
		if (child.isAlias()) {
			var = var.setFork(child.getFork(), child.getForkName());
		} else {
			var = new ForkVariable(child.getFork(), child.getForkName(),
					nameVariable);
		}
		data.addVariable(nameVariable, var);
		value.removeVariable(data);

	}

}
