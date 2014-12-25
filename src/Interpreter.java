import java.io.FileNotFoundException;
import java.io.IOException;

import parser.IncorrectConversionException;
import parser.Parser;
import parser.SyntaxErrorException;
import parser.UnexceptedEndOfFileException;
import parser.VariableNotDeclaredException;


public class Interpreter {

	public static void main(String[] args) {
		try {
			Parser p = new Parser("test.ji");
			p.parse();
			p.ast.getRacine().recursivePrint(0);
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SyntaxErrorException e) {
			// TODO Auto-generated catch block
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
