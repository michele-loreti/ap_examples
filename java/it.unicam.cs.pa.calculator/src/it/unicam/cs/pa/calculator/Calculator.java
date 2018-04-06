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

	public Calculator() {}
	
	public Number getView() {
		return this.state.getView();
	}
	
	public void addDigit( Digit d ) {
		this.state.addDigit( d );
	}
	
	public void execCommand( Command c ) {
		state = c.execute( state );
	}
}
