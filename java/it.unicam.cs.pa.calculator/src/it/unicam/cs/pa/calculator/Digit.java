package it.unicam.cs.pa.calculator;

public enum Digit {
	ZERO (0),
	ONE (1),
	TWO (2),
	THREE (3),
	FOUR (4),
	FIVE (5),
	SIX (6),
	SEVEN (7),
	EIGHT (8),
	NINE (9)
	;
	
	Digit( int value ) {
		this.value = value;
	}
	
	private int value;

	/**
	 * Returns the digit associated with an element in the enum.
	 * 
	 * @return an integer value in the interval [0,9].
	 */
	public int valueOf() {
		return this.value;
	}

}
