/**
 * 
 */
package it.unicam.cs.pa.battleship;

import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.InputStream;
import java.io.PrintStream;
import java.io.PrintWriter;
import java.util.LinkedList;
import java.util.List;
import java.util.function.BiFunction;

/**
 * @author loreti
 *
 */
public class Utils {
	
	private static final int[] DEFAULT_SHIPS = new int[] { 4 , 3 , 2 , 1 }; 
	private static int shipCounter = 0;

	public static Ship[] defaultShips( int size ) {
		return getShips(DEFAULT_SHIPS);
	}
	
	public static Ship[] getShips( int[] data ) {
		List<Ship> ships = new LinkedList<>();
		for( int i=0 ; i<data.length ; i++ ) {
			for ( int k=0 ; k<data[i] ; k++ ) {
				ships.add(new Ship(shipCounter++,i+1));				
			}
		}
		return ships.toArray(new Ship[ships.size()]);
	}
	
	public static void printField(BattleField field ) {
		printField( System.out, field.getView() , field.getSize() );
	}

	public static void printField(PrintStream writer ,  BattleField field ) {
		printField( writer, field.getView() , field.getSize() );
	}

	public static void printField(BiFunction<Integer, Integer, CellStatus> view, int size) {
		printField(System.out,view,size);
	}

	public static void printField(PrintStream writer , BiFunction<Integer, Integer, CellStatus> view, int size) {
		for( int i=0; i<size ; i++ ) {
			printRowDelimiter(writer,size);
			printRow(writer,view,i,size);
		}
		printRowDelimiter(writer,size);
	}
	
	private static void printRowDelimiter( PrintStream writer , int size ) {
		writer.print("    ");
		for( int i=0 ; i<size ; i++ ) {
			writer.print("+---");
		}
		writer.println("+");
	}
	
	private static void printRow(PrintStream writer , BiFunction<Integer, Integer, CellStatus> view, int row , int size) {
		writer.print(String.format("%4d", row ));
		for( int i=0 ; i<size ; i++ ) {
			writer.print("| "+view.apply(row, i)+" ");
		}
		writer.println("|");
	}
	
}
