package battleship;

import java.util.Random;

/**
 * public ocean class
 * @author xiranxu and hkim8
 */
public class Ocean
{
	private Ship[][] ships = new Ship[10][10];
	private int shotsFired;
	private int hitCount;
	private int shipsSunk;
	
	/**
	 * creates an ”empty” ocean (fills the ships array with a bunch of EmptySea instances).
	 * initializes any game variables, such as how many shots have been fired.
	 */
	public Ocean() {
		
		for (int row = 0; row < 10; row++) 
		{
			for(int column = 0; column < 10; column++)
			{
				EmptySea emptySea = new EmptySea();
				emptySea.placeShipAt(row, column, true, this);
			}	
		}
		shotsFired = 0;
		hitCount = 0;
		shipsSunk = 0;
	}	

	/**
	 * Place all randomly on the (initially empty) ocean. 
	 * Place larger ships before smaller ones. 
	 */
	void placeAllShipsRandomly() {
		
		Random random = new Random();
		
		Ship[] ships = new Ship[10];
		for (int i = 0; i < 10; i++){
			if (i == 0){
				ships[i] = new Battleship();
			}
			else if (i > 0 && i < 3){
				ships[i] = new Cruiser();
			}
			else if (i > 2 && i < 6){
				ships[i] = new Destroyer();
			}
			else if (i > 5 && i < 10){
				ships[i] = new Submarine(); 
			}
		}
		
		for (Ship ship : ships){
			while (true)
			{
				int row = random.nextInt(10);
				int column = random.nextInt(10);
				boolean horizontal = random.nextBoolean();
				if (ship.okToPlaceShipAt(row, column, horizontal, this) == true) 
				{
					ship.placeShipAt(row, column, horizontal, this);
					break;
				}
			}
		}
	}
	/**
	 * This method returns true if the given location contains a ship, false if it does not
	 * @param row the row location
	 * @param column the column location
	 * @return true if the given location contains a ship
	 */
	boolean isOccupied(int row, int column) {
		if (ships[row][column].getShipType() != "empty") {
			return true;
		}
		return false;
	}
	
	/**
	 * Returns true if the given location contains a ”real” ship, still afloat, (not an EmptySea), 
	 * false if it does not. 
	 * updates the number of shots that have been fired, and the number of hits. 
	 * Note: If a location contains a ”real” ship, shootAt should return true every time the user shoots at that same location. 
	 * Once a ship has been ”sunk”, additional shots at its location should return false.
	 * @param row the row location
	 * @param column the column location
	 * @return true if the given location contains a real ship
	 */
	boolean shootAt(int row, int column){
		shotsFired ++;
			if(ships[row][column].shootAt(row, column) == true){
				//if(ships[row][column].isSunk() == true)
			{
					hitCount ++;
					if (ships[row][column].isSunk() == true) {
						shipsSunk ++;
						System.out.println("You just sunk a: " + ships[row][column].getShipType());
					}
					return true;
				}
			}
			return false;
	}
	
	/**
	 * Returns the number of shots fired (in this game).
	 * @return the number of shots fired
	 */
	int getShotsFired(){
		return shotsFired;
	}
	
	/**
	 * Returns the number of hits recorded (in this game). 
	 * All hits are counted, not just the first time a given square is hit.
	 * @return hitCount the number of hits recorded 
	 */
	int getHitCount() {
		return hitCount;
	}
	
	/**
	 * Returns the number of ships sunk (in this game).
	 * @return shipSunk the number of ships suck
	 */
	int getShipsSunk(){
		return shipsSunk;
	}
	
	/**
	 * This method returns true if all ships have been sunk, otherwise false.
	 * @return true if all ships have been sunk
	 */
	boolean isGameOver(){
		return shipsSunk == 10;
	}
	
	/**
	 * Returns the 10 x 10 array of ships. 
	 * The methods in the Ship class that take an Ocean parameter really need to be able to look at the contents of this array; 
	 * the placeShipAt method even needs to modify it
	 * @return ships the 10 x 10 array of ships.
	 */
	Ship[][] getShipArray(){
		return ships;
	}
	/**
	 * Prints the ocean
	 */
	void print() {
		System.out.println(toString());
	}
	
	/**
	 * Creates the string sea. 
	 * To aid the user, 
	 * row numbers should be displayed along the left edge of the array, and 
	 * column numbers should be displayed along the top. 
	 * Numbers should be 0 to 9, not 1 to 10.
	 * The top left corner square should be 0, 0.
	 * Use "s" to indicate a location that you have fired upon and hit a (real) ship, 
	 * "-" to indicate a location that you have fired upon and found nothing there, 
	 * "x" to indicate a location containing a sunken ship,
	 * and "." to indicate a location that you have never fired upon.
	 * @return sea.toString
	 */
	public String toString() {
		StringBuilder sea = new StringBuilder();
		sea.append(" ");
		for (int row = 0; row < 10; row ++) {
			sea.append(String.format("%3d", row));
		}
		sea.append("\n");
		
		for (int row = 0; row < 10; row ++) {
			sea.append(row + "  ");
			for (int column = 0; column < 10; column ++) {
				if (!ships[row][column].isHit(row, column)) {
					sea.append(".");
				} else {
					sea.append(ships[row][column].toString());
				}
				
				sea.append("  ");
			}
			sea.append("\n");
		}
		return sea.toString();
	}
	
	
}
