/**
 * 
 */
package it.unicam.cs.pa.battleship;

import java.util.LinkedList;
import java.util.List;
import java.util.function.BiFunction;

/**
 * @author loreti
 *
 */
public class BattleField {
	
	public static final int DEFAULT_SIZE = 10;
	private final Cell[][] field;
	private int size;
	private int ships;
	private int sankShips;
	
	public BattleField( int size ) {
		this.size = size;
		this.field = new Cell[size][size];
		fill();
	}
	
	public BattleField() {
		this( DEFAULT_SIZE );
	}

	private void fill() {
		for( int i=0 ; i<this.size ; i++ ) {
			for( int j=0 ; j<this.size ; j++ ) {
				this.field[i][j] = new Cell();
			}
		}
	}

	public CellStatus getStatus( int i , int j ) {
		return this.field[i][j].getStatus();
	}
	
	public CellStatus hit( int i , int j ) {
		if (this.field[i][j].isTargeted()) {
			return getStatus(i,j);
		}
		CellStatus status = this.field[i][j].hit();
		if (status == CellStatus.SANK) {
			this.sankShips++;
		}
		return status;
	}
	
	public boolean isInBound( int i , int j ) {
		if ((i<0)||(i>=this.size)) return false;
		if ((j<0)||(j>=this.size)) return false;
		return true;
	}
	
	private List<Cell> getCells( int x , int y , Direction  dir , int n ) {
		LinkedList<Cell> toReturn = new LinkedList<>();
		for( int i=0 ; i<n ; i++ ) {
			if (!isInBound(x, y)) {
				return null;
			}
			toReturn.add(this.field[x][y]);
			x += dir.getXStep();
			y += dir.getYStep();
		}
		return toReturn;
	}
	
	public boolean isValidAt( Ship ship , int x , int y , Direction  dir ) {
		return isValidAt(this.getCells(x, y, dir, ship.getSize()));
	}
	
	private boolean isValidAt( List<Cell> involvedCells ) {
		if (involvedCells != null) {
			return involvedCells.stream().allMatch(Cell::isEmpty);//c -> c.isEmpty();
		}
		return false;
	}
	
	public boolean addShip( Ship ship , int x , int y , Direction dir ) {
		List<Cell> involvedCells = this.getCells(x, y, dir, ship.getSize());
		if (isValidAt(involvedCells)) {
			involvedCells.forEach(c -> c.setShip(ship));
			this.ships++;
		}
		return true;
	}
	
	public boolean isClear() {
		return this.ships==this.sankShips;
	}
	
	public int getShips() {
		return this.ships;
	}
	
	public int getSankShips() {
		return this.sankShips;
	}

	public boolean addShip(Ship ship, ShipLocation loc) {
		return addShip(ship, loc.getX(), loc.getY(), loc.getDirection());
	}

	public CellStatus launch(Bomb b) {
		return hit( b.getX() , b.getY() );
	}
	
	public BiFunction<Integer,Integer,CellStatus> getView() {
		return (x,y) -> {
			if (!isInBound(x, y)) {
				return null;
			}
			return getStatus(x, y);
		};
	}
		
	public int getSize() {
		return this.size;
	}

}
