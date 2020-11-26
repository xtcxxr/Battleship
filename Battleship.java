package battleship;
/**
 * This is a subclass of Ship class
 * create battleship
 * @author xiranxu and hkim8
 *
 */
public class Battleship extends Ship {
	static final int battleshipLength = 4;
	static final String shipType = "battleship"; 

	/**
	 *creates battleship with length 4
	 */
	public Battleship() {
		super(battleshipLength);		
	}
	
	/**
	 * return "battleship"
	 * @return string value of battleship
	 */
	@Override
	public String getShipType() {
		return shipType;
	}

}
