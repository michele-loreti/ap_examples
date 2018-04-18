/**
 * 
 */
package it.unicam.cs.pa.calculator;

/**
 * @author loreti
 *
 */
public class Main {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		CalculatorNumber cn = new CalculatorNumber();
		System.out.println( "N: "+cn );
		cn.addDigit(Digit.ZERO);
		System.out.println( "N: "+cn );
		cn.addDigit(Digit.ZERO);
		System.out.println( "N: "+cn );
		cn.addDigit(Digit.ZERO);
		System.out.println( "N: "+cn );
		cn.setCommaFlag();
		cn.addDigit(Digit.ZERO);
		System.out.println( "N: "+cn );
		cn.addDigit(Digit.ZERO);
		System.out.println( "N: "+cn );
		cn.addDigit(Digit.ZERO);
		System.out.println( "N: "+cn );
	}

}
