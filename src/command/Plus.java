package command;

public class Plus implements Command {

	public static boolean isPlusCommand(String command){
		return command.equals("+");
	}
	
}
