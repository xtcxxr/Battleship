package battleship;

/**
 * Ship is an abstract class
 * to create and operate different types of ships (including empty sea)
 * @author xiranxu and hkim8
 */

public abstract class Ship {
	private int bowRow;
	private int bowColumn;
	private int length;
	private boolean horizontal;
	private boolean[] hit;
	
	/**
	 * sets the length property of the particular ship
	 * initialize the hit array
	 * @param length the length of the particular ship
	 */
	public Ship(int length) {
		this.length = length;
		this.hit = new boolean[length];
	}
	
	/**
	 * return the type of ship as a String
	 * @return getShipType change the type of ship as a String
	 */
	public abstract String getShipType();
	
	/**
	 * based on the given row, column, and orientation, returns true if it is okay to put a ship of this length with its bow in this location
	 * false otherwise
	 * this ship must not overlap another ship, or touch another ship(vertically, horizontally, or diagonally)
	 * must not stick out beyond the array
	 * @param row Check if the ship is ok to be placed on this row
	 * @param column Check if the ship is ok to be placed on this column
	 * @param horizontal Check if the ship is ok to be placed horizontally
	 * @param ocean Check if the ship is ok to be placed on this ocean
	 * @return The boolean value if the ship is ok to be placed
	 */
	boolean okToPlaceShipAt(int row, int column, boolean horizontal, Ocean ocean) {
			
			if (horizontal == true) {
				for (int r = row - 1; r <= row + 1; r++) {
					for (int c = column - getLength(); c <= column + 1; c++) {
						try {
							if (r < 0 || c == -1) {
								continue;
							}
							else if (c < -1 || ocean.isOccupied(r, c) == true) {
								return false;
							}
						} catch (Exception e) {
							continue;
						}
					}
				}
			} else {
				for (int r = row - getLength(); r <= row + 1; r++) {
					for (int c = column - 1; c <= column + 1; c++) {
						try {
							if (r == -1 || c < 0) {
								continue;
							}
							else if (r < -1 || ocean.isOccupied(r, c)) {
								return false;
							}
						} catch (Exception e) {
							continue;
						}
					}
				}
			}
			return true;
		}
	
	
	/**
	 * ”Puts” the ship in the ocean
	 * This involves giving values to the bowRow, bowColumn, and horizontal instance variables in the ship
	 * it also involves putting a reference to the ship in each of 1 or more locations (up to 4) in the ships array in the Ocean object. 
	 * Note: This will be as many as eight identical references; you can’t refer to a ”part” of a ship, only to the whole ship
	 * @param row the row at which the ship will be placed
	 * @param column the column at which the ship will be placed
	 * @param horizontal if the ship will be placed horizontally or vertically
	 * @param ocean the ocean at which the ship will be placed
	 */
	void placeShipAt(int row, int column, boolean horizontal, Ocean ocean) {
		bowRow = row;
		bowColumn = column;
		this.horizontal = horizontal;
		if (this.horizontal == true) {
			for (int c = column - getLength() + 1; c <= column; c++){
				ocean.getShipArray()[row][c] = this;
			}
		} else {
			for (int r = row - getLength() + 1; r <= row; r++) {
				ocean.getShipArray()[r][column] = this;
			}
		}
	}
	
	/**
	 * If a part of the ship occupies the given row and column, and the ship hasn’t been sunk,
	 * mark that part of the ship as ”hit” (in the hit array, 0 indicates the bow) and return true, 
	 * otherwise return false
	 * @param row the row to shoot at
	 * @param column the column to shoot at
	 * @return true if the part of ship was hit
	 */
	boolean shootAt(int row, int column) {
		if (isSunk() == true) {
			return false;
			}
		
		if (horizontal == true) {
			for (int c = bowColumn - getLength() + 1; c <= bowColumn; c++) {
				if (c == column && bowRow == row) {
					hit[bowColumn - c] = true;
					return true;
				}
			}
		} else {
			for (int r = bowRow - getLength() + 1; r <= bowRow; r++) {
				if (r == row && bowColumn == column) {
					hit[bowRow - r] = true;
					return true;
			}
		}
	}
	return false;
}
	/**
	 * Determines whether the given location hit the ship
	 * @param row the row location
	 * @param column the column location
	 * @return false if the given location did not hit the ship
	 */
	boolean isHit(int row, int column) {
		if (horizontal == true) {
			for (int c = bowColumn - getLength() + 1; c <= bowColumn; c++) {
				if (c == column && bowRow == row) {
					return hit[bowColumn - c];
				}
			}
		} else {
			for (int r = bowRow - getLength() + 1; r <= bowRow; r++) {
				if (r == row && bowColumn == column) {
					return hit[bowRow - r];
			}
		}
	}
	return false;
	}
	
	/**
	 * Return true if every part of the ship has been hit, false otherwise.
	 * @return return true if every part of the ship has been hit
	 */
	boolean isSunk() {
		for (int counter = 0; counter < getLength(); counter++) {
			if (hit[counter] == false) {
				return false;
			}
		}
		return true;
	}
		
	/**
	 * Return "x" if the ship has been sunk,  "s" if it has not been sunk. 
	 * This method can be used to print out locations in the ocean that have been shot at; 
	 * it should not be used to print locations that have not been shot at.
	 * @return "x" if the ship has been sunk
	 */
	@Override
	public String toString() {
		if (isSunk() == true) {
			return "s";
		} else {
			return "x";
		}
		
	}
	//Getters and setters
	/**
	 * Returns the row corresponding to the position of the bow
	 * @return the bowRow
	 */
	public int getBowRow() {
		return bowRow;
	}

	/**
	 * Sets the value of bowRow
	 * @param bowRow the bowRow to set
	 */
	public void setBowRow(int bowRow) {
		this.bowRow = bowRow;
	}

	/**
	 * Returns the bow column location
	 * @return the bowColumn
	 */
	public int getBowColumn() {
		return bowColumn;
	}

	/**
	 * Sets the value of bowColumn
	 * @param bowColumn the bowColumn to set
	 */
	public void setBowColumn(int bowColumn) {
		this.bowColumn = bowColumn;
	}

	/**
	 * Returns the ship length
	 * @return the length
	 */
	public int getLength() {
		return length;
	}

	/**
	 * Sets the value of length
	 * @param length the length to set
	 */
	public void setLength(int length) {
		this.length = length;
	}

	/**
	 * Returns whether the ship is horizontal or not
	 * @return the horizontal
	 */
	public boolean isHorizontal() {
		return horizontal;
	}

	/**
	 * Sets the value of the instance variable horizontal
	 * @param horizontal the horizontal to set
	 */
	public void setHorizontal(boolean horizontal) {
		this.horizontal = horizontal;
	}

	/**
	 * Returns whether the location hit the ship 
	 * @return the hit
	 */
	public boolean[] getHit() {
		return hit;
	}

	/**
	 * Set the position of hit location
	 * @param position the given location
	 * @param isHit determines whether the given location hit the ship
	 */
	public void setHit(int position, boolean isHit) {
		this.hit[position] = isHit; 
	}	

}