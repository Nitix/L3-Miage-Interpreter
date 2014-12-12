package command;

public class NumberOperator {	
	
	public static boolean isOperatorCommand(String command){
		return Minus.isMinusCommand(command) || Plus.isPlusCommand(command) || Times.isTimesCommand(command) || Devided.isDividedCommand(command);
	}
}
