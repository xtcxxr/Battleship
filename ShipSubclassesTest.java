package battleship;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

class ShipSubclassesTest {

	@Test
	void testGetShipTypeBattleship() {
		Ship ship = new Battleship();
		assertEquals("battleship", ship.getShipType());
		assertEquals(4, ship.getLength());
	}
	
	@Test
	void testGetShipTypeCruiser() {
		Ship ship = new Cruiser();
		assertEquals("cruiser", ship.getShipType());
		assertEquals(3, ship.getLength());
	}
	
	@Test
	void testGetShipTypeDestroyer() {
		Ship ship = new Destroyer();
		assertEquals("destroyer", ship.getShipType());
		assertEquals(2, ship.getLength());
	}
	
	@Test
	void testGetShipTypeSubmarine() {
		Ship ship = new Submarine();
		assertEquals("submarine", ship.getShipType());
		assertEquals(1, ship.getLength());
	}
	
	@Test
	void testGetShipTypeEmptySea() {
		Ship ship = new EmptySea();
		assertEquals("empty", ship.getShipType());
		assertEquals(1, ship.getLength());
	}
	

	
}
