package parser;

/**
 * The Class Utils. Contains useful informations about the program Can say the
 * type of a command
 */
public class Utils {

	/**
	 * Checks if it's a command.
	 *
	 * @param command
	 *            the command
	 * @return true, if it's a command
	 */
	public static boolean isCommand(String command) {
		return Utils.isSystemCommand(command)
				|| Utils.isOperatorCommand(command);
	}

	/**
	 * Checks if it's a system command.
	 *
	 * @param command
	 *            the command
	 * @return true, if it's a system command
	 */
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

	/**
	 * Checks if it's an operator command.
	 *
	 * @param command
	 *            the command
	 * @return true, if it's an operator command
	 */
	public static boolean isOperatorCommand(String command) {
		return command.equalsIgnoreCase("-") || command.equalsIgnoreCase("+")
				|| command.equalsIgnoreCase("*")
				|| command.equalsIgnoreCase("/")
				|| command.equalsIgnoreCase("and")
				|| command.equalsIgnoreCase("or")
				|| command.equalsIgnoreCase("<")
				|| command.equalsIgnoreCase("=");
	}

	/**
	 * Checks if it's an arithmetic operator command.
	 *
	 * @param command
	 *            the command
	 * @return true, if it's an arithmetic operator command
	 */
	public static boolean isArithmeticOperatorCommand(String command) {
		return command.equalsIgnoreCase("-") || command.equalsIgnoreCase("+")
				|| command.equalsIgnoreCase("*")
				|| command.equalsIgnoreCase("/");
	}

	/**
	 * Checks if it's a prio arithmetic operator command.
	 *
	 * @param command
	 *            the command
	 * @return true, if it's a prio arithmetic operator command
	 */
	public static boolean isPrioArithmeticOperatorCommand(String command) {
		return command.equalsIgnoreCase("*") || command.equalsIgnoreCase("/");
	}

	/**
	 * Checks if it's a comparator operator command.
	 *
	 * @param command
	 *            the command
	 * @return true, if it's a comparator operator command
	 */
	public static boolean isComparatorOperatorCommand(String command) {
		return command.equalsIgnoreCase("<") || command.equalsIgnoreCase("=");
	}

	/**
	 * Checks if it's a boolean operator command.
	 *
	 * @param command
	 *            the command
	 * @return true, if it's a boolean operator command
	 */
	public static boolean isBooleanOperatorCommand(String command) {
		return command.equalsIgnoreCase("and")
				|| command.equalsIgnoreCase("or");
	}
}
