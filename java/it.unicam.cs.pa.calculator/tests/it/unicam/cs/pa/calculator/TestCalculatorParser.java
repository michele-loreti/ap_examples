package it.unicam.cs.pa.calculator;

import static org.junit.Assert.*;

import org.junit.Test;

public class TestCalculatorParser {

	@Test
	public void testDigitCommandZero() {
		Command c = CalculatorParser.parse('0');
		assertNotNull(c);
		assertTrue( c instanceof DigitCommand );
		assertEquals( Digit.ZERO , ((DigitCommand) c).getDigit() );
	}

	@Test
	public void testDigitCommandOne() {
		Command c = CalculatorParser.parse('1');
		assertNotNull(c);
		assertTrue( c instanceof DigitCommand );
		assertEquals( Digit.ONE , ((DigitCommand) c).getDigit() );
	}

}
