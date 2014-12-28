package parser;

public class Utils {

	public static boolean isCommand(String command) {
		return Utils.isSystemCommand(command)
				|| Utils.isOperatorCommand(command);
	}

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
				|| command.equalsIgnoreCase("fork()")
				|| command.equalsIgnoreCase("wait")
				|| command.equalsIgnoreCase("return")
				|| command.equalsIgnoreCase(";")
				|| command.equalsIgnoreCase("exit");
	}

	public static boolean isOperatorCommand(String command) {
		return command.equalsIgnoreCase("-") || command.equalsIgnoreCase("+")
				|| command.equalsIgnoreCase("*")
				|| command.equalsIgnoreCase("/")
				|| command.equalsIgnoreCase("and")
				|| command.equalsIgnoreCase("or")
				|| command.equalsIgnoreCase("<")
				|| command.equalsIgnoreCase("=");
	}

	public static boolean isArithmeticOperatorCommand(String command) {
		return command.equalsIgnoreCase("-") || command.equalsIgnoreCase("+")
				|| command.equalsIgnoreCase("*")
				|| command.equalsIgnoreCase("/");
	}

	public static boolean isPrioArithmeticOperatorCommand(String command) {
		return command.equalsIgnoreCase("*") || command.equalsIgnoreCase("/");
	}

	public static boolean isComparatorOperatorCommand(String command) {
		return command.equalsIgnoreCase("<") || command.equalsIgnoreCase("=");
	}

	public static boolean isBooleanOperatorCommand(String command) {
		return command.equalsIgnoreCase("and")
				|| command.equalsIgnoreCase("or");
	}
}
