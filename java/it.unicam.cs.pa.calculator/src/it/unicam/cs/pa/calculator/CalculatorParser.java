/**
 * 
 */
package it.unicam.cs.pa.calculator;

import java.util.HashMap;
import java.util.Map;

/**
 * @author loreti
 *
 */
public class CalculatorParser {
	
	
	private static CalculatorParser simpleParserInstance = null;
	
	public static CalculatorParser simpleParser() {
		if (simpleParserInstance == null) {
			simpleParserInstance = new CalculatorParser(getSimpleParserMap());
		}
		return simpleParserInstance;
	}

	private static Map<Character, Command> getSimpleParserMap() {
		HashMap<Character, Command> toReturn = new HashMap<>();
		toReturn.put('0', Command.getDigitCommand(Digit.ZERO));		
		toReturn.put('1', Command.getDigitCommand(Digit.ONE));
		toReturn.put('+', Command.getSumCommand());
//		case '-': return Command.getDifCommand();
//		case '*': return Command.getMulCommand();
//		case '/': return Command.getDivCommand();
//		case '=': return Command.getEqualCommand();
//		case '.': return Command.getDotCommand();
		return toReturn;
	}

	private Map<Character, Command> commandSet;

	public CalculatorParser(Map<Character,Command> commandSet) {
		this.commandSet = commandSet;
	}
	
	public Command parse( Character c ) {
		Command cmd = this.commandSet.get(c);
		if (cmd == null) {
			throw new IllegalArgumentException("Uknown symbol '"+c+"'!");			
		}
		return cmd;
	}
	
	public Command[] parse(String program) {
		Command[] carray = new Command[program.length()];
		for (int i = 0; i < program.length(); i++) {
			carray[i] = parse(program.charAt(i));
		}
		return carray;
	}

//	public static Command parse(char c) {
//		if (('0'<=c)&&(c<='9')) {
//			return Command.getDigitCommand(Digit.values()[c-'0']);
//		} else {
//			return parseOperation( c );
//		}
//	}
//	
//	private static Command parseOperation(char c) {
//		switch (c) {
//		case '+': return Command.getSumCommand();
//		case '-': return Command.getDifCommand();
//		case '*': return Command.getMulCommand();
//		case '/': return Command.getDivCommand();
//		case '=': return Command.getEqualCommand();
//		case '.': return Command.getDotCommand();
//		default:
//			throw new IllegalArgumentException("Uknown symbol '"+c+"'!");
//		}
//	}
//
//	public static Command[] parse(String program) {
//		Command[] carray = new Command[program.length()];
//		for( int i=0 ; i<program.length() ; i++ ) {
//			carray[i] = parse( program.charAt(i) );
//		}
//		return carray;
//	}

}
