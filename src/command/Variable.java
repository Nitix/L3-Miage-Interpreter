package command;

public class Variable implements Command {
    
    private String name;
    
    @Override
	public String toString() {
		return "Variable [name=" + name + "]";
	}

	public Variable(){
        
    }

    public Variable(String command) {
        this.name = command;
    }

}
