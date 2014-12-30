import java.io.FileNotFoundException;
import java.io.IOException;

import AST.InexistantVariableException;
import AST.VariableAlreadyExistException;
import command.IncorrectMethodCallException;
import command.VariableNotDeclaredException;
import parser.IncorrectConversionException;
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
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SyntaxErrorException e) {
			// TODO Auto-generated catch block
			System.out.println("Syntax Error : Ligne " + e.getLineNumber());
			e.printStackTrace();
		} catch (VariableNotDeclaredException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IncorrectConversionException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (UnexceptedEndOfFileException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
