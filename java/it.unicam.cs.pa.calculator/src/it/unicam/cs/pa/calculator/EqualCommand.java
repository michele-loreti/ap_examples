package it.unicam.cs.pa.calculator;

public class EqualCommand implements Command {

	@Override
	public CalculatorState execute(CalculatorState state) {
		state.applyFunction();
		return state;
	}

}
