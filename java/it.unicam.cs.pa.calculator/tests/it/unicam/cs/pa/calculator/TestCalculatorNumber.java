package it.unicam.cs.pa.calculator;

import static org.junit.Assert.*;

import org.junit.Test;

public class TestCalculatorNumber {

	@Test
	public void testCreate() {
		CalculatorNumber cn = new CalculatorNumber();
		assertEquals("0", cn.toString());
	}

	@Test
	public void testAddZero() {
		CalculatorNumber cn = new CalculatorNumber();
		cn.addDigit(Digit.ZERO);
		assertEquals("0", cn.toString());
	}

	@Test
	public void testAddComma() {
		CalculatorNumber cn = new CalculatorNumber();
		cn.setCommaFlag();
		assertEquals("0.", cn.toString());
	}

	@Test
	public void testAddZeroAfterComma() {
		CalculatorNumber cn = new CalculatorNumber();
		cn.setCommaFlag();
		cn.addDigit(Digit.ZERO);
		cn.addDigit(Digit.ZERO);
		assertEquals("0.00", cn.toString());
	}
	
	@Test
	public void testCreatedValue() {
		CalculatorNumber cn = new CalculatorNumber(2.0);
		assertEquals("2",cn.toString());
	}

	@Test
	public void testGetValueInteger() {
		CalculatorNumber cn = new CalculatorNumber(2.0);
		cn.addDigit(Digit.ZERO);
		assertEquals(20.0,cn.getValue(),0.0);
	}

	@Test
	public void testGetValueDouble() {
		CalculatorNumber cn = new CalculatorNumber(2.2);
		cn.addDigit(Digit.ZERO);
		assertEquals("2.20",cn.toString());
		assertEquals(2.2,cn.getValue(),0.0);
	}
}
