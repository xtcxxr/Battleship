package battleship;
/**
 * This is a subclass of Ship class
 * create submarine
 * @author xiranxu and hkim8
 *
 */
public class Submarine extends Ship {
	static final int submarineLength = 1;
	static final String shipType = "submarine"; 

	/**
	 *creates submarine with length 1
	 */
	public Submarine() {
		super(submarineLength);
	}
	
	/**
	 * return "submarine"
	 * @return string value of submarine
	 */
	@Override
	public String getShipType() {
		return shipType;
	}
}
