package it.unicam.cs.pa.calculator;

public class CalculatorNumber {

	private int value;
	private int digitsAfterComma;
	private boolean commaFlag;
	
	public CalculatorNumber() {
		this(0, 0);
	}

	public CalculatorNumber(int value, int digitsAfterComma) {
		super();
		this.commaFlag = false;
		this.value = value;
		this.digitsAfterComma = digitsAfterComma;
	}

	
	public void addDigit(Digit d) {
		int v = d.valueOf();
		assert((v>=0)&&(v<=9));
		this.value = this.value*10 + v;
		if (this.commaFlag) {
			this.digitsAfterComma += 1; 
		}
	}

	public Number getNumber() {
		if (this.commaFlag) {
			return this.value*Math.pow(10,-digitsAfterComma);
		} else {
			return this.value;
		}
	}

	public void setCommaFlag() {
		this.commaFlag = true;
	}
	
	
}
