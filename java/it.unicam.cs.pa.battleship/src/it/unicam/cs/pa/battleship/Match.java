/**
 * 
 */
package it.unicam.cs.pa.battleship;

import java.util.List;

/**
 * @author loreti
 *
 */
public class Match {
	
	private static final int PLAYER1 = 0;
	private static final int PLAYER2 = 1;
	private final Player[] players;
	private final BattleField[] fields;
	private MatchStatus status = MatchStatus.ARRANGE;
	private int currentPlayer = PLAYER1;
	private final ShipFactory factory;
	private int size;

	public Match(Player p1, Player p2, ShipFactory factory ) {
		this(p1,p2,BattleField.DEFAULT_SIZE,factory);
	}
	
	public Match(Player p1, Player p2, int size, ShipFactory factory ) {
		this.players = new Player[] { p1 , p2 };
		this.fields = new BattleField[] { new BattleField(size), new BattleField(size) };
		this.factory = factory; 
		this.size = size;
	}
	
	public MatchStatus getStatus() {
		return this.status;
	}
	
	public void play() {
		if (!init(PLAYER1)) {
			return ;
		}
		if (!init(PLAYER2)) {
			return ;
		}
		if (!arrangeShips(PLAYER1)) {
			return ;
		} 
		if (!arrangeShips(PLAYER2)) {
			return ;
		}
		this.setStatus(MatchStatus.FIGHTING);
		fight();
	}

	private boolean init(int pid) {
		try {
			this.players[pid].init( pid , size );
			return true;
		} catch (Throwable e) {
			this.winForError(otherPlayer(pid), e);
			return false;
		}
	}

	private boolean arrangeShips(int pid) {
		try {
			doArrangeShips(pid);
			return true;
		} catch (Exception e) {
			e.printStackTrace();
			winForError( otherPlayer(pid) , e );
			return false;
		}
	}

	private static int otherPlayer(int pid) {
		return (pid+1)%2;
	}

	private void winForError(int pid, Throwable e) {
		this.players[pid].winForError(e);
		this.players[otherPlayer(pid)].loseForError(e);
	}

	private void win(int pid) {
		this.players[pid].youWin();
		this.players[otherPlayer(pid)].youLose();
	}


	private void fight() {
		this.players[PLAYER1].startFighting();
		this.players[PLAYER2].startFighting();
		while (doAction());
	}

	private boolean doAction() {
		try {
			Bomb b = this.players[this.currentPlayer].bomb();
			CellStatus s = this.fields[otherPlayer(this.currentPlayer)].launch(b);
			this.players[otherPlayer(this.currentPlayer)].bomb( b );
			this.players[this.currentPlayer].bombResult(b,s);
			if (this.fields[otherPlayer(this.currentPlayer)].isClear()) {
				win( this.currentPlayer );
				return false;
			}
		} catch (Throwable e) {
			winForError(otherPlayer(this.currentPlayer), e);
			return false;
		}
		this.currentPlayer = otherPlayer(this.currentPlayer);
		return true;
	}
	
	private void setStatus(MatchStatus status) {
		this.status = status;
	}

	private void doArrangeShips(int pid) throws IllegalShipLocation {
		Ship[] ships = this.factory.getShips( this.size );
		for( int i=0 ; i<ships.length ; i++ ) {
			ShipLocation loc = this.players[pid].arrange(ships[i]);
			if (!this.fields[pid].addShip(ships[i], loc)) {
				throw new IllegalShipLocation(ships[i],loc);
			}
		}
	}
	
}
