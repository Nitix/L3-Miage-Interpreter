package command;

public class Utils {

	public static boolean isSystemCommand(String command) {
		return command.equalsIgnoreCase("if")
				|| command.equalsIgnoreCase("then")
				|| command.equalsIgnoreCase("else")
				|| command.equalsIgnoreCase("while")
				|| command.equalsIgnoreCase("do")
				|| command.equalsIgnoreCase("end")
				|| command.equalsIgnoreCase("let")
				|| command.equalsIgnoreCase("be")
				|| command.equalsIgnoreCase("in")
				|| command.equalsIgnoreCase("fork")
				|| command.equalsIgnoreCase("wait")
				|| command.equalsIgnoreCase("return");
	}

	public static boolean isOperatorCommand(String command) {
		return Minus.isMinusCommand(command) || Plus.isPlusCommand(command)
				|| Times.isTimesCommand(command)
				|| Devided.isDividedCommand(command);
	}
}
