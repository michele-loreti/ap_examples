/**
 * 
 */
package it.unicam.cs.pa.calculator;

import java.util.function.BiFunction;
import java.util.function.Function;

/**
 * @author loreti
 *
 */
public class CalculatorState {

	private CalculatorNumber view = new CalculatorNumber();
	private boolean operationFlag = false; 
	private BiFunction<CalculatorNumber,CalculatorNumber,CalculatorNumber> operation = null;
	private CalculatorNumber registry = new CalculatorNumber();
	
	public Number getView() {
		return this.view.getNumber();
	}

	public void addDigit(Digit d) {
		if (this.operationFlag) {
			this.view = new CalculatorNumber();
			this.operationFlag = false;
		}
		this.view.addDigit(d);
	}

	public void setCommaFlag() {
		this.view.setCommaFlag();
	}
	
	public void applyFunction() {
		if (this.operation != null) {
			this.view = this.operation.apply(this.view,this.registry);
			this.operation = null;
		}
	}

	public void setOperation(BiFunction<CalculatorNumber, CalculatorNumber, CalculatorNumber> operation) {
		this.operationFlag = true;
		this.operation = operation;
		this.registry = this.view;
	}
	

}
