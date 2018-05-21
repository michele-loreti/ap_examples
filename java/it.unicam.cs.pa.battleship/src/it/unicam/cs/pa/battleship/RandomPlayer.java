/**
 * 
 */
package it.unicam.cs.pa.battleship;

import java.util.Random;

/**
 * @author loreti
 *
 */
public class RandomPlayer implements Player {
	
	private Random random;
	private String name;
	private int myId;
	private BattleField myField;
	private CellStatus[][] otherField;
	private int size;
	private boolean echo;

	public RandomPlayer(String name) {
		this(name,true);
	}

	public RandomPlayer(String name, boolean echo) {
		this.name = name;
		this.random = new Random();
		this.echo = echo;
	}

	/* (non-Javadoc)
	 * @see it.unicam.cs.pa.battleship.Player#arrange(it.unicam.cs.pa.battleship.Ship)
	 */
	@Override
	public ShipLocation arrange(Ship ship) {
		ShipLocation loc = selectLocation( ship );
		this.myField.addShip(ship, loc);
		return loc;
	}

	private ShipLocation selectLocation(Ship ship) {
		Direction[] directions = Direction.values();
		while (true) {
			int x = this.random.nextInt(size);
			int y = this.random.nextInt(size);
			int start = this.random.nextInt(directions.length);
			for( int i=0 ; i<directions.length ; i++ ) {
				int dir = (start+i)%directions.length;
				if (this.myField.isValidAt(ship, x, y, directions[dir])) {
					return new ShipLocation(x, y, directions[dir]);
				}
			}
		}
	}

	/* (non-Javadoc)
	 * @see it.unicam.cs.pa.battleship.Player#winForError(java.lang.Throwable)
	 */
	@Override
	public void winForError(Throwable e) {
		print("I have won! You are a newbie!");
	}

	/* (non-Javadoc)
	 * @see it.unicam.cs.pa.battleship.Player#loseForError(java.lang.Throwable)
	 */
	@Override
	public void loseForError(Throwable e) {
		print("Noooo... Great mistake... I have lost!");
	}

	/* (non-Javadoc)
	 * @see it.unicam.cs.pa.battleship.Player#startFighting()
	 */
	@Override
	public void startFighting() {
		this.print("I am ready!");
		if (this.echo) {
			this.print("My field is: ");
			Utils.printField(this.myField);
		}
	}

	/* (non-Javadoc)
	 * @see it.unicam.cs.pa.battleship.Player#bomb()
	 */
	@Override
	public Bomb bomb() {
		return new Bomb(random.nextInt(this.size),random.nextInt(this.size));
	}

	/* (non-Javadoc)
	 * @see it.unicam.cs.pa.battleship.Player#bombResult(it.unicam.cs.pa.battleship.Bomb, it.unicam.cs.pa.battleship.CellStatus)
	 */
	@Override
	public void bombResult(Bomb b, CellStatus s) {
		print("The result of the bomb at "+b+" was "+s);
		this.otherField[b.getX()][b.getY()]=s;
		if (this.echo) {
			print("My field: ");
			Utils.printField(this.myField);
			print("Oppenent's field: ");
			Utils.printField((i,j)->(this.otherField[i][j]==null?CellStatus.EMPTY:otherField[i][j]),this.size);
		}
	}

	/* (non-Javadoc)
	 * @see it.unicam.cs.pa.battleship.Player#bomb(it.unicam.cs.pa.battleship.Bomb)
	 */
	@Override
	public void bomb(Bomb b) {
		print("A bomb fallen at "+b);
		this.myField.launch(b);
	}

	/* (non-Javadoc)
	 * @see it.unicam.cs.pa.battleship.Player#youWin()
	 */
	@Override
	public void youWin() {
		print("Incredible! I have won!");
	}

	/* (non-Javadoc)
	 * @see it.unicam.cs.pa.battleship.Player#youLose()
	 */
	@Override
	public void youLose() {
		print("Gosh... I have lost!");
	}

	@Override
	public void init(int pid, int size) {
		this.myId = pid;
		this.myField = new BattleField(size);
		this.otherField = new CellStatus[size][size];
		this.size = size;
		print("I have index "+pid+" and the field size is "+size+"!");
	}

	private void print(String string) {
		if (echo) {
			System.out.println(this.name+"> "+string);
		}
	}

}
