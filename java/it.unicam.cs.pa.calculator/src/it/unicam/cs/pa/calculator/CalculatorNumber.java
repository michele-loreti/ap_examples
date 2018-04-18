package it.unicam.cs.pa.calculator;

public class CalculatorNumber {
	
	private String value;
	private boolean commaFlag;
	
	public CalculatorNumber() {
		this(0);
	}
	
	public CalculatorNumber(int value) {
		super();
		this.commaFlag = false;
		this.value = ""+value;
	}

	public CalculatorNumber(double value) {
		super();
		
		if (Math.floor(value)==Math.ceil(value)) {
			this.commaFlag = false;
			this.value = ""+((int) value);
		} else {
			this.commaFlag = true;
			this.value = ""+value;
		}
	}
	
	public void addDigit(Digit d) {
		if (this.value.equals("0")) {
			this.value = d.value()+"";
		} else {
			this.value += d.value();
		}
		
	}

	public Number getNumber() {
		if (this.commaFlag) {
			return Double.parseDouble(this.value);
		} else {
			return Integer.parseInt(this.value);
		}
	}
	
	public double getValue() {
		return Double.parseDouble(this.value);
	}
	
	public void setCommaFlag() {
		if (!this.commaFlag) {
			this.commaFlag = true;
			this.value += ".";
		}
	}
	
	public static CalculatorNumber sum( CalculatorNumber v1 , CalculatorNumber v2 ) {
		return new CalculatorNumber(v1.getValue()+v2.getValue());
	}

	@Override
	public String toString() {
		return this.value;
	}
	
	
}
