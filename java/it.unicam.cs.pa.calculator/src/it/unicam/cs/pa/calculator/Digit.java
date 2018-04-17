package it.unicam.cs.pa.calculator;

public enum Digit {
	ZERO,
	ONE,
	TWO,
	THREE,
	FOUR,
	FIVE,
	SIX,
	SEVEN,
	EIGHT,
	NINE
	;
	
	/**
	 * Returns the digit associated with an element in the enum.
	 * 
	 * @return an integer value in the interval [0,9].
	 */
	public int value() {
		return this.ordinal();
	}

}
