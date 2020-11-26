package battleship;
/**
 * This is a subclass of Ship class
 * create emptysea
 * @author xiranxu and hkim8
 *
 */
public class EmptySea extends Ship {
	static final int emptySeaLength = 1;
	static final String shipType = "empty";

	/**
	 *creates emptysea with length 1
	 */
	public EmptySea() {
		super(emptySeaLength);
	}
	
	/**
	 *return false to indicate that nothing was hit
	 *@param row the location of row
	 *@param column the location of column
	 *@return false 
	 */
	boolean shootAt(int row, int column) {
		this.setHit(0, true);
		return false;
	}
	
	/**
	 *Return false to indicate that you didn't sink anything
	 *@return false
	 */
	@Override
	boolean isSunk() {
		return false;
	}
	
	/**
	 *Return "-" String to use in the Ocean's print method
	 *@return "-"
	 */
	@Override
	public String toString() {
		return "-";
	}
	/**
	 * return "empty"
	 * @return string empty
	 */
	@Override
	public String getShipType() {
		return shipType;
	}
	

}