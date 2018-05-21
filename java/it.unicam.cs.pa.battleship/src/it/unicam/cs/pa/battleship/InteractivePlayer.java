/**
 * 
 */
package it.unicam.cs.pa.battleship;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.PrintStream;
import java.util.HashSet;
import java.util.Random;
import java.util.Set;
import java.util.function.Function;
import java.util.function.Predicate;

/**
 * @author loreti
 *
 */
public class InteractivePlayer implements Player {
	
	private Random random;
	private String name;
	private int myId;
	private BattleField myField;
	private CellStatus[][] otherField;
	private int size;
	private BufferedReader in;
	private PrintStream out;

	public InteractivePlayer(String name) {
		this(name,System.in,System.out);
	}
	
	public InteractivePlayer(String name, InputStream in, PrintStream out) {
		this.name = name;
		this.random = new Random();
		this.in = new BufferedReader(new InputStreamReader(in));
		this.out = out;
	}

	private boolean isValidIndex( String txt ) {
		try {
			int v = Integer.parseUnsignedInt(txt);
			return (v<this.size);
		} catch (NumberFormatException e) {
			return false;
		}
	}
	
	/* (non-Javadoc)
	 * @see it.unicam.cs.pa.battleship.Player#arrange(it.unicam.cs.pa.battleship.Ship)
	 */
	@Override
	public ShipLocation arrange(Ship ship) {
		while (true) {
			Utils.printField(this.myField);
			print("Place a ship of size "+ship.getSize());
			int x = doInput(String.format("Insert row (a value from 0 to %d): ",(size-1)), this::isValidIndex, Integer::parseUnsignedInt);
			int y = doInput(String.format("Insert column (a value from 0 to %d): ",(size-1)), this::isValidIndex, Integer::parseUnsignedInt);
			Direction d = doInput("Insert direction (N,S,E or W): ",
				s -> s.length()==1&&Direction.isADirection(s.charAt(0)),
				s -> Direction.parse(s.charAt(0))
			);
			if (this.myField.isValidAt(ship, x, y, d)) {
				ShipLocation loc = new ShipLocation(x,y,d);
				this.myField.addShip(ship, loc);
				return loc;
			} else {
				print("Invalid location!");
			}
		}
	}
	
	/* (non-Javadoc)
	 * @see it.unicam.cs.pa.battleship.Player#winForError(java.lang.Throwable)
	 */
	@Override
	public void winForError(Throwable e) {
		print("The other player has made an error! You have won!");
	}

	/* (non-Javadoc)
	 * @see it.unicam.cs.pa.battleship.Player#loseForError(java.lang.Throwable)
	 */
	@Override
	public void loseForError(Throwable e) {
		print("Noooo... Great mistake... You have lost! "+e.getMessage());
	}

	/* (non-Javadoc)
	 * @see it.unicam.cs.pa.battleship.Player#startFighting()
	 */
	@Override
	public void startFighting() {
		this.print("Figt start! This is your field: ");
		Utils.printField(this.myField);
	}

	/* (non-Javadoc)
	 * @see it.unicam.cs.pa.battleship.Player#bomb()
	 */
	@Override
	public Bomb bomb() {
		print("Oppenent's field: ");
		Utils.printField((i,j)->(this.otherField[i][j]==null?CellStatus.EMPTY:otherField[i][j]),this.size);
		print("Launch a bomb!");
		int x = doInput(String.format("Insert row (a value from 0 to %d): ",(size-1)), this::isValidIndex, Integer::parseUnsignedInt);
		int y = doInput(String.format("Insert column (a value from 0 to %d): ",(size-1)), this::isValidIndex, Integer::parseUnsignedInt);
		return new Bomb(x,y);
	}

	/* (non-Javadoc)
	 * @see it.unicam.cs.pa.battleship.Player#bombResult(it.unicam.cs.pa.battleship.Bomb, it.unicam.cs.pa.battleship.CellStatus)
	 */
	@Override
	public void bombResult(Bomb b, CellStatus s) {
		print("The result of the bomb at "+b+" was "+s);
		this.otherField[b.getX()][b.getY()]=s;
		print("Oppenent's field: ");
		Utils.printField((i,j)->(this.otherField[i][j]==null?CellStatus.EMPTY:otherField[i][j]),this.size);
	}

	/* (non-Javadoc)
	 * @see it.unicam.cs.pa.battleship.Player#bomb(it.unicam.cs.pa.battleship.Bomb)
	 */
	@Override
	public void bomb(Bomb b) {
		print("A bomb fallen at "+b);
		this.myField.launch(b);
		print("My field: ");
		Utils.printField(this.myField);
	}

	/* (non-Javadoc)
	 * @see it.unicam.cs.pa.battleship.Player#youWin()
	 */
	@Override
	public void youWin() {
		print("Incredible! You have won!");
	}

	/* (non-Javadoc)
	 * @see it.unicam.cs.pa.battleship.Player#youLose()
	 */
	@Override
	public void youLose() {
		print("Gosh... You have lost!");
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
		System.out.println(this.name+"> "+string);
	}
	

	private <T> T doInput( String message , Predicate<String> condition , Function<String,T> readFun ) {
		while (true) {
			System.out.println(message);
			String line;
			try {
				line = this.in.readLine();
			} catch (IOException e) {
				throw new InternalException(e);
			}
			if (!condition.test(line)) {
				System.out.println("Input Error!");
			} else {
				return readFun.apply(line);
			}
		}
	}


}
