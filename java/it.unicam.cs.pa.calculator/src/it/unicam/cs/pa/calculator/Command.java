/**
 * 
 */
package it.unicam.cs.pa.calculator;

import java.util.TreeSet;
import java.util.function.BiFunction;

/**
 * @author loreti
 *
 */
@FunctionalInterface
public interface Command {
	
	public static final BiFunction<CalculatorNumber,CalculatorNumber,CalculatorNumber> SUM = (x,y) -> new CalculatorNumber(x.getValue()+y.getValue());
	public static final BiFunction<CalculatorNumber,CalculatorNumber,CalculatorNumber> DIF = (x,y) -> new CalculatorNumber(x.getValue()-y.getValue());
	public static final BiFunction<CalculatorNumber,CalculatorNumber,CalculatorNumber> MUL = (x,y) -> new CalculatorNumber(x.getValue()*y.getValue());
	public static final BiFunction<CalculatorNumber,CalculatorNumber,CalculatorNumber> DIV = (x,y) -> new CalculatorNumber(x.getValue()/y.getValue());

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

	static Command getSumCommand() {
		return getOperationCommand(Command.SUM);
	}

	static Command getDifCommand() {
		return getOperationCommand(Command.DIF);
	}

	static Command getMulCommand() {
		return getOperationCommand(Command.MUL);
	}

	static Command getDivCommand() {
		return getOperationCommand(Command.DIV);
	}
}
