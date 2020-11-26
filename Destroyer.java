package battleship;
/**
 * This is a subclass of Ship class
 * create destroyer
 * @author xiranxu and hkim8
 *
 */
public class Destroyer extends Ship {
	static final int destroyerLength = 2;
	static final String shipType = "destroyer"; 
	
	/**
	 *creates destroyer with length 2
	 */
	public Destroyer() {
		super(destroyerLength);
	}
	
	/**
	 * return "destroyer"
	 * @return string value of destroyer
	 */
	@Override
	public String getShipType() {
		return shipType;
	}
}