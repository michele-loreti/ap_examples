package it.unicam.cs.pa.calculator;

public class DotCommand implements Command {

	@Override
	public CalculatorState execute(CalculatorState state) {
		state.setCommaFlag();
		return state;
	}

}
