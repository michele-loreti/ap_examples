/**
 * 
 */
package it.unicam.cs.pa.calculator;

import java.util.function.BiFunction;

/**
 * @author loreti
 *
 */
@FunctionalInterface
public interface Command {
	
	public static final BiFunction<Integer,Integer,Integer> PLUS = (x,y) -> x+y;
	public static final BiFunction<Integer,Integer,Integer> MINUS = (x,y) -> x-y;
	public static final BiFunction<Integer,Integer,Integer> MUL = (x,y) -> x*y;

	CalculatorState execute(CalculatorState state);

	static Command getDigitCommand( Digit d ) {
		return new DigitCommand( d );
	}
	
	static Command getDotCommand() {
		return new DotCommand();
	}
	
	static Command getEqualCommand() {
		return new EqualCommand();
	}
	
	static Command getOperationCommand(BiFunction<CalculatorNumber, CalculatorNumber, CalculatorNumber> operation) {
		return new OperationCommand( operation );
	}
}
