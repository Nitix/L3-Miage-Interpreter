package command;

public class Devided implements Command {

	public static boolean isDividedCommand(String command){
		return command.equals("/");
	}

	@Override
	public String toString() {
		return "Devided []";
	}
}
