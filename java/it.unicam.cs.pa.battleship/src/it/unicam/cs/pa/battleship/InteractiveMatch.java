/**
 * 
 */
package it.unicam.cs.pa.battleship;

/**
 * @author loreti
 *
 */
public class InteractiveMatch {

	/**
	 * @param args
	 */
	public static void main(String[] argv) {
		Player p1 = new InteractivePlayer("Bill");
		Player p2 = new RandomPlayer("Ben",false);
		Match m = new Match(p1, p2, Utils::defaultShips);
		m.play();
	}

}
