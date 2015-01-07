package AST.variable;

public class EmptyVariable extends Variable {

	@Override
	public Variable copy() {
		return new EmptyVariable();
	}

	@Override
	public boolean isEmpty() {
		return true;
	}

}
