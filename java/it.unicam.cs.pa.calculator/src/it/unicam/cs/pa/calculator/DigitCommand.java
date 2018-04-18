package it.unicam.cs.pa.calculator;

public class DigitCommand implements Command {

	private final Digit d;

	public DigitCommand(Digit d) {
		this.d = d;
	}

	@Override
	public CalculatorState execute(CalculatorState state) {
		state.addDigit(this.d);
		return state;
	}

	public Digit getDigit() {
		return d;
	}
}
