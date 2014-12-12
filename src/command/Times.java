package command;

public class Times implements Command {
	
	public static boolean isTimesCommand(String command){
		return command.equals("*");
	}
}
