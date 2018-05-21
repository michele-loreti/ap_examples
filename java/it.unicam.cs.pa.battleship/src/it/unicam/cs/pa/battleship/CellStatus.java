/**
 * 
 */
package it.unicam.cs.pa.battleship;

/**
 * @author loreti
 *
 */
public enum CellStatus {
	EMPTY,
	FULL,
	MISS,
	HIT,
	SANK;

	@Override
	public String toString() {
		switch ( this ) {
		case EMPTY: return " ";
		case MISS: return "@";
		case FULL: return "O";
		case HIT: return "*";
		case SANK: return "#";
		}
		return "";
	}
	
	
}
