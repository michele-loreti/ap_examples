/**
 * 
 */
package it.unicam.cs.pa.battleship;

/**
 * @author loreti
 *
 */
public interface Player {

	ShipLocation arrange(Ship ship);

	void init(int pid, int size);

	void winForError(Throwable e);

	void loseForError(Throwable e);

	void startFighting();

	Bomb bomb();

	void bombResult(Bomb b, CellStatus s);

	void bomb(Bomb b);

	void youWin();

	void youLose();

}
