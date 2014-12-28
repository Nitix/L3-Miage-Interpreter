package command;

public class DeclareCommand implements Command {

	private String nameVariable;
	
	public DeclareCommand(String name) {
		this.nameVariable = name;
	}

	@Override
	public String toString() {
		return "DeclareCommand [nameVariable=" + nameVariable + "]";
	}
 
}
