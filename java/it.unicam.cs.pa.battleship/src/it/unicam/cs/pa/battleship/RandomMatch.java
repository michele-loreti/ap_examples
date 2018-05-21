/**
 * 
 */
package it.unicam.cs.pa.battleship;

/**
 * @author loreti
 *
 */
public class RandomMatch {

	/**
	 * @param args
	 */
	public static void main(String[] argv) {
		Player p1 = new RandomPlayer("Bill");
		Player p2 = new RandomPlayer("Ben");
		Match m = new Match(p1, p2, Utils::defaultShips);
		m.play();
	}

}
