/**
 * 
 */
package it.unicam.cs.pa.calculator;

/**
 * @author loreti
 *
 */
public class CalculatorParser {

	public static Command parse(char c) {
		if (('0'<=c)&&(c<='9')) {
			return Command.getDigitCommand(Digit.values()[c-'0']);
		} else {
			return parseOperation( c );
		}
	}
	
	private static Command parseOperation(char c) {
		switch (c) {
		case '+': return Command.getSumCommand();
		case '-': return Command.getDifCommand();
		case '*': return Command.getMulCommand();
		case '/': return Command.getDivCommand();
		case '=': return Command.getEqualCommand();
		case '.': return Command.getDotCommand();
		default:
			throw new IllegalArgumentException("Uknown symbol '"+c+"'!");
		}
	}

	public static Command[] parse(String program) {
		Command[] carray = new Command[program.length()];
		for( int i=0 ; i<program.length() ; i++ ) {
			carray[i] = parse( program.charAt(i) );
		}
		return carray;
	}

}
