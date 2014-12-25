package command;

public class Minus implements Command {

	public static boolean isMinusCommand(String command){
		return command.equals("-");
	}

	@Override
	public String toString() {
		return "Minus []";
	}
}
