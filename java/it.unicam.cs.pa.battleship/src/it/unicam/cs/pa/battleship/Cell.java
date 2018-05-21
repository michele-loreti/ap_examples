/**
 * 
 */
package it.unicam.cs.pa.battleship;

/**
 * @author loreti
 *
 */
public class Cell {
	
	private Ship ship;
	private boolean targeted = false;
	
	public Cell() {
	}

	public CellStatus getStatus() {
		if (!this.targeted) {
			return (this.ship == null?CellStatus.EMPTY:CellStatus.FULL);
		}
		if (ship == null) {
			return CellStatus.MISS;
		}
		return (this.ship.isSank()?CellStatus.SANK:CellStatus.HIT);
	}

	public CellStatus hit() {
		if (this.targeted) {
			return this.getStatus();
		} 
		this.targeted = true;
		if (this.ship != null) {
			return (this.ship.hit()?CellStatus.SANK:CellStatus.HIT);
		} else {
			return CellStatus.MISS;
		}
	}
	
	public boolean setShip( Ship ship ) {
		if (this.ship != null) {
			return false;
		} else {
			this.ship = ship;
			return true;
		}
	}
	
	public Ship getShip( ) {
		return this.ship;
	}
	
	public boolean isEmpty() {
		return this.ship == null;
	}

	public boolean isTargeted() {
		return this.targeted;
	}

}
