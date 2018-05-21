/**
 * 
 */
package it.unicam.cs.pa.battleship;

/**
 * @author loreti
 *
 */
public enum Direction {
	NORTH,
	SOUTH,
	EAST,
	WEST;
	
	public int getXStep() {
		if (this==NORTH) return -1;
		if (this==SOUTH) return +1;
		return 0;
	}

	public int getYStep() {
		if (this==EAST) return +1;
		if (this==WEST) return -1;
		return 0;
	}
	
	public static Direction parse(char c) {
		switch (c) {
		case 'N': return NORTH;
		case 'S': return SOUTH;
		case 'E': return EAST;
		case 'W': return WEST;
		default:
			throw new IllegalArgumentException("Invalid char '"+c+"' for direction!");
		}
	}
	
	public static boolean isADirection(char c) {
		return (c=='N')||(c=='S')||(c=='E')||(c=='W');
	}

	
}
