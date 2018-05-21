/**
 * 
 */
package it.unicam.cs.pa.battleship;

/**
 * @author loreti
 *
 */
public class ShipLocation {
	
	private final int x;
	private final int y;
	private final Direction dir;

	public ShipLocation( int x , int y , Direction dir ) {
		this.x = x;
		this.y = y;
		this.dir = dir;
	}
	
	public int getX() {
		return x;
	}

	public int getY() {
		return y;
	}

	public Direction getDirection() {
		return dir;
	}

}
