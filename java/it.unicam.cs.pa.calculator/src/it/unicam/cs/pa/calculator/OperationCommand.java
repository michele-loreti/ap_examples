package it.unicam.cs.pa.calculator;

import java.util.function.BiFunction;

public class OperationCommand implements Command {

	private BiFunction<CalculatorNumber, CalculatorNumber, CalculatorNumber> operation;

	public OperationCommand(BiFunction<CalculatorNumber, CalculatorNumber, CalculatorNumber> operation) {
		this.operation = operation;
	}

	@Override
	public CalculatorState execute(CalculatorState state) {
		state.setOperation( this.operation );
		return state;
	}

}
