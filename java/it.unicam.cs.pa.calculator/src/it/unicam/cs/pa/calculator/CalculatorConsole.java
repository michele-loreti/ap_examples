/**
 * 
 */
package it.unicam.cs.pa.calculator;

import java.io.BufferedInputStream;

import javax.swing.JOptionPane;

/**
 * @author loreti
 *
 */
public class CalculatorConsole {
	
	private final Calculator c = new Calculator();
	
	public void run() {
		boolean flag = true;
		showInfo();
		while (flag) {
			String program = JOptionPane.showInputDialog("Digit program:");
			if ((program != null)&&(!program.isEmpty())) {
				exec( CalculatorParser.SIMPLE_PARSER.parse(program) );
			} else {
				flag = false;
			}
		}
	}

	private void exec(Command[] parse) {
		for( int i=0 ; i<parse.length ; i++ ) {
			c.execCommand(parse[i]);
			showInfo();
		}
	}

	private void showInfo() {
		System.out.println( "[ "+c.getView()+" ]");
	}
	
	public static void main(String[] argv) {
		CalculatorConsole console = new CalculatorConsole();
		console.run();
		System.out.println("Bye!");
	}

}
