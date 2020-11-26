package battleship;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

class ShipTest {

	@Test
	public void testGetShipTypeBattleship() {
		Ship ship = new Battleship();
		assertEquals("battleship", ship.getShipType());
		assertEquals(4, ship.getLength());
	}
	
	@Test
	public void testGetShipTypeCruiser() {
		Ship ship = new Cruiser();
		assertEquals("cruiser", ship.getShipType());
		assertEquals(3, ship.getLength());
	}
	
	@Test
	public void testGetShipTypeDestroyer() {
		Ship ship = new Destroyer();
		assertEquals("destroyer", ship.getShipType());
		assertEquals(2, ship.getLength());
	}
	
	@Test
	public void testGetShipTypeSubmarine() {
		Ship ship = new Submarine();
		assertEquals("submarine", ship.getShipType());
		assertEquals(1, ship.getLength());
	}
	
	@Test
	public void testOkToPlaceShipAt() {
		Ocean ocean = new Ocean();
		Ship ship = new Battleship();
		assertFalse(ship.okToPlaceShipAt(0, 2, true, ocean));
		assertTrue(ship.okToPlaceShipAt(0, 10, true, ocean));
		assertFalse(ship.okToPlaceShipAt(2, 5, false, ocean));
		assertTrue(ship.okToPlaceShipAt(6, 10, false, ocean));
	}
	
	@Test
	public void testOkToPlaceShipAtAdjacent() {
		Ocean ocean = new Ocean();
		Ship ship1 = new Battleship();
		Ship ship2 = new Battleship();
		Ship ship3 = new Submarine();
		assertTrue(ship1.okToPlaceShipAt(0, 5, true, ocean));
		ship1.placeShipAt(0, 5, true, ocean);
		assertFalse(ship2.okToPlaceShipAt(0, 4, true, ocean));
		assertTrue(ship2.okToPlaceShipAt(3, 5, true, ocean));
		assertFalse(ship3.okToPlaceShipAt(1, 1, true, ocean));	
	}
	
	@Test
	public void testPlaceShipAt() {
		Ocean ocean = new Ocean();
		Ship [][] ships = ocean.getShipArray();
		Ship ship = new Battleship();
		Ship ship1 = new Submarine();
		ship.placeShipAt(4, 3, true, ocean);
		ship1.placeShipAt(0, 0, true, ocean);
		assertEquals(ships[4][3].getShipType(), "battleship");
		assertEquals(ships[4][2].getShipType(), "battleship");
		assertEquals(ships[4][1].getShipType(), "battleship");
		assertEquals(ships[4][0].getShipType(), "battleship");
		assertEquals(ships[4][4].getShipType(), "empty");
		assertEquals(ships[0][0].getShipType(), "submarine");
		}
	
	@Test
	public void testShootAt() {
		Ocean ocean = new Ocean();
		Ship ship = new Battleship();
		ship.placeShipAt(4, 3, true, ocean);
		assertTrue(ship.shootAt(4, 3));
		assertFalse(ship.shootAt(4, 4));
	}
	
	@Test
	public void testIsSunk() {
		Ocean ocean = new Ocean();
		Battleship ship = new Battleship();
		ship.placeShipAt(3, 0, false, ocean);
		for (int r = 0; r <= 3; r ++) {
			ocean.shootAt(r, 0);
		}		
		assertTrue(ship.isSunk());
	}
	
	
	@Test
	public void testToString() {
		Ocean ocean = new Ocean();
		Battleship ship = new Battleship();
		ship.placeShipAt(0, 4, true, ocean);
		for (int c = 4; c >= 0; c--) {
			ocean.shootAt(0, c);
		}
		
		assertEquals("s", ship.toString());
	}
	
}
