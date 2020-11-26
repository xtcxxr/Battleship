package battleship;

/**
 * This is a subclass of Ship class
 * create cruiser
 * @author xiranxu and hkim8
 *
 */
public class Cruiser extends Ship {
	static final int cruiserLength = 3;
	static final String shipType = "cruiser"; 

	/**
	 *creates cruiser with length 3
	 */
	public Cruiser() {
		super(cruiserLength);
		
	}
	
	/**
	 * return "cruiser"
	 * @return string value of cruiser
	 */
	@Override
	public String getShipType() {
		return shipType;
	}
}