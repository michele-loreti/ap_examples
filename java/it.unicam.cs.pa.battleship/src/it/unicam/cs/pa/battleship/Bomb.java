/**
 * 
 */
package it.unicam.cs.pa.battleship;

/**
 * @author loreti
 *
 */
public class Bomb {
	
	private final int x;
	private final int y;

	public Bomb( int x , int y ) {
		this.x = x;
		this.y = y;
	}

	public int getX() {
		return x;
	}

	public int getY() {
		return y;
	}
	
	public String toString() {
		return "<"+x+","+y+">";
	}

}
