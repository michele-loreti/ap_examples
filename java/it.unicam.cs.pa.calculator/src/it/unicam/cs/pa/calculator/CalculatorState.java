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
	
	
	public CalculatorState(CalculatorNumber view, boolean operationFlag,
			BiFunction<CalculatorNumber, CalculatorNumber, CalculatorNumber> operation, CalculatorNumber registry) {
		super();
		this.view = view;
		this.operationFlag = operationFlag;
		this.operation = operation;
		this.registry = registry;
	}
	
	public CalculatorState(CalculatorNumber view, CalculatorNumber registry) {
		this(view,false,null,registry);
	}
	
	public CalculatorState() {
		this(new CalculatorNumber(),new CalculatorNumber());
	}
	

	public CalculatorState(double d) {
		this( new CalculatorNumber(d),new CalculatorNumber());
	}

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
	
	public void setOperation(BiFunction<CalculatorNumber, CalculatorNumber, CalculatorNumber> operation) {
		this.operationFlag = true;
		this.applyOperation();
		this.operation = operation;
		this.registry = this.view;
	}
	
	public void applyOperation() {
		if (this.operation != null) {
			this.view = this.operation.apply(this.registry,this.view);
			this.operation = null;
		}
	}

	public Number getRegistry() {
		return this.registry.getNumber();
	}


}
