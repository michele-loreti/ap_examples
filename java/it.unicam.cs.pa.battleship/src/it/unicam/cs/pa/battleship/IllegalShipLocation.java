/**
 * 
 */
package it.unicam.cs.pa.battleship;

/**
 * @author loreti
 *
 */
public class IllegalShipLocation extends Exception {

	private final Ship ship;
	private final ShipLocation loc;

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public IllegalShipLocation(Ship ship, ShipLocation loc) {
		super("A ship of size "+ship.getSize()+" cannot be placed at "+loc+"!");
		this.ship = ship;
		this.loc = loc;
	}

	public Ship getShip() {
		return ship;
	}

	public ShipLocation getLoc() {
		return loc;
	}

}
