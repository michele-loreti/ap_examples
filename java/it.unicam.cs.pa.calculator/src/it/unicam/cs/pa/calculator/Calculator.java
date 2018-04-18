/**
 * 
 */
package it.unicam.cs.pa.calculator;

/**
 * @author loreti
 *
 */
public class Calculator {

	private CalculatorState state;

	public Calculator() {
		this.state = new CalculatorState();
	}
	
	public Number getView() {
		return this.state.getView();
	}
	
	public void execCommand( Command c ) {
		state = c.execute( state );
	}
	
	public void exec( Command[] program ) {
		for (Command c : program) {
			execCommand(c);
		}
	}
	
	public static void main(String[] argv) {
		Calculator c = new Calculator();
		c.exec(CalculatorParser.parse("1223+234-45+46="));
	}
}
