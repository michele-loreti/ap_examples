/**
 * 
 */
package it.unicam.cs.pa.battleship;

/**
 * @author loreti
 *
 */
@FunctionalInterface
public interface ShipFactory {

	public Ship[] getShips( int size );
	
}
