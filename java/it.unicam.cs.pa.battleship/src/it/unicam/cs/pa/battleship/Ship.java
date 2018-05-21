/**
 * 
 */
package it.unicam.cs.pa.battleship;

/**
 * @author loreti
 *
 */
public class Ship {
	
	private final int id;
	private int hitCount;
	private final int size;
	
	public Ship( int id , int size ) {
		this.id = id;
		this.size = size; 
	}
	
	public int getId() {
		return this.id;
	}
	
	public int getSize() {
		return this.size;
	}
	
	public boolean hit() {
		if (this.hitCount<this.size) {
			this.hitCount++;
		}
		return this.isSank();
	}

	public boolean isSank() {
		return this.hitCount==this.size;
	}

}
