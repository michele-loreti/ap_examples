package it.unicam.cs.pa.calculator;

import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.function.Predicate;

import org.junit.Test;

public class TestCalculatorState {

	
	private void execute( CalculatorState cs , Command[] commands , ArrayList<Predicate<CalculatorState>> predicates ) {
		CalculatorState current = cs;
		for( int i=0 ; i<commands.length ; i++ ) {
			current = commands[i].execute(current);
			assertTrue( "Command "+i, predicates.get(i).test(current));
		}
	}
	
	private ArrayList<Predicate<CalculatorState>> getPredicates( Predicate<CalculatorState> ... predicates ) {
		ArrayList<Predicate<CalculatorState>> toReturn = new ArrayList<>();
		for( int i=0 ; i<predicates.length ; i++ ) {
			toReturn.add(predicates[i]);
		}		
		return toReturn;
	}
	
	@Test
	public void testNumberInteger() {
		CalculatorState cs = new CalculatorState();
		cs.addDigit(Digit.ONE);
		assertEquals(1, cs.getView());
	}

	@Test
	public void testCreateAddAndGetView() {
		CalculatorState cs = new CalculatorState(new CalculatorNumber(20.2),new CalculatorNumber());
		cs.addDigit(Digit.THREE);
		assertEquals(20.23, cs.getView());
	}
	
	@Test
	public void testCommaFlag() {
		CalculatorState cs = new CalculatorState(23);
		cs.setCommaFlag();
		assertTrue(cs.getView() instanceof Double);
	}
	
	@Test
	public void testSumOperation( ) {
		CalculatorState cs = new CalculatorState(12);
		Command[] commands = { 
				Command.getOperationCommand(Command.SUM) , 
				Command.getDigitCommand(Digit.ONE) ,
				Command.getEqualCommand()
		};
		@SuppressWarnings("unchecked")
		ArrayList<Predicate<CalculatorState>> predicates = getPredicates(
				s -> s.getView().equals(12)&&s.getRegistry().equals(12) ,
				s -> s.getView().equals(1) ,
				s -> s.getView().equals(13)
		);
		execute( cs, commands, predicates);
	}

	@Test
	public void testMulOperation( ) {
		CalculatorState cs = new CalculatorState(12);
		cs.setOperation(Command.MUL);
		assertEquals(12, cs.getView());
		assertEquals(12, cs.getRegistry());		
		cs.addDigit(Digit.FIVE);
		assertEquals(5,cs.getView());
		cs.applyOperation();
		assertEquals(60, cs.getView());
	}

	@Test
	public void testDivOperation( ) {
		CalculatorState cs = new CalculatorState(12);
		cs.setOperation(Command.DIV);
		assertEquals(12, cs.getView());
		assertEquals(12, cs.getRegistry());		
		cs.addDigit(Digit.SIX);
		assertEquals(6,cs.getView());
		cs.applyOperation();
		assertEquals(2, cs.getView());
	}
	
	@Test
	public void testMultipleOperations( ) {
		CalculatorState cs = new CalculatorState(0);
		Command[] commands = { 
				Command.getDigitCommand(Digit.TWO) ,
				Command.getDigitCommand(Digit.THREE) ,
				Command.getOperationCommand(Command.SUM) , 
				Command.getDigitCommand(Digit.SIX) ,
				Command.getOperationCommand(Command.SUM) 
		};
		@SuppressWarnings("unchecked")
		ArrayList<Predicate<CalculatorState>> predicates = getPredicates(
				s -> s.getView().equals(2) ,
				s -> s.getView().equals(23) ,
				s -> s.getView().equals(23) ,
				s -> s.getView().equals(6) ,
				s -> s.getView().equals(29)
		);
		execute( cs, commands, predicates);
	}
	
}
