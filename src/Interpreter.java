import java.io.FileNotFoundException;
import java.io.IOException;

import command.IncorrectMethodCallException;
import command.InexistantVariableException;
import command.VariableAlreadyExistException;
import command.VariableNotDeclaredException;
import exception.IncorrectConversionException;
import parser.Parser;
import parser.ParserException;
import parser.SyntaxErrorException;
import parser.UnexceptedEndOfFileException;

public class Interpreter {

	public static void main(String[] args) {
		try {
			Parser p = new Parser("test.ji");
			p.parse();
			try {
				p.ast.execute();
			} catch (VariableAlreadyExistException
					| IncorrectMethodCallException
					| InexistantVariableException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} catch (SyntaxErrorException e) {
			System.out.println("Syntax Error : Ligne " + e.getLineNumber() + ", command : " + e.getCommand());
			e.printStackTrace();
		} catch (VariableNotDeclaredException e) {
			System.out.println("Variable is not declared : Ligne " + e.getLineNumber() + ", command : " + e.getCommand());
			e.printStackTrace();
		} catch (IncorrectConversionException e) {
			System.out.println("Incorrect conversion exception : Ligne " + e.getLineNumber() + ", command : " + e.getCommand());
			e.printStackTrace();
		} catch (UnexceptedEndOfFileException e) {
			System.out.println("End of file unexpected : Ligne " + e.getLineNumber() + ", command : " + e.getCommand());
			e.printStackTrace();
		}
	}

}
