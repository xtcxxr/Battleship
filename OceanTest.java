package battleship;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class OceanTest {

	@BeforeEach
	void setUp() throws Exception {
	}

	//test Ocean setup
	@Test
	void testOcean() {
		Ocean ocean = new Ocean();
		assertEquals(0, ocean.getShotsFired());
		assertEquals(0, ocean.getHitCount());
		assertEquals(0, ocean.getShipsSunk());
		
		for (int row = 0; row < 10; row ++) 
		{
			for (int column = 0; column < 10; column ++) 
			{
				assertEquals("empty", ocean.getShipArray()[row][column].getShipType());
			}
		}
	}

	@Test
	void testPalceAllShipsRandomly() {
		Ocean ocean = new Ocean();
		ocean.placeAllShipsRandomly();
		int shipCount = 0;
		for (int row = 0; row < 10; row ++) 
		{
			for (int column = 0; column < 10; column ++) 
			{
				if (ocean.getShipArray()[row][column].getShipType() != "empty") 
				{
					shipCount++;
				}
				
			}
		}
		//4 + 2*3 + 3*2 + 4*1
		assertEquals(20, shipCount);	
	}

	@Test
	void testIsOccupied() {
		Ocean ocean = new Ocean();
		Ship ship = new Battleship();
		for(int row = 0; row < 10; row++) 
		{
			for(int column = 0; column < 10; column ++)
			{
				assertFalse(ocean.isOccupied(row, column));
			}
		}
		ship.placeShipAt(4, 4, true, ocean);
		assertTrue(ocean.isOccupied(4, 4));
		assertTrue(ocean.isOccupied(4, 3));
		assertTrue(ocean.isOccupied(4, 2));
		assertTrue(ocean.isOccupied(4, 1));
	}
	
	@Test
	void testShootAt() {
		Ocean ocean = new Ocean();
		
		Battleship battleship = new Battleship();
		battleship.placeShipAt(4, 4, true, ocean);
		
		ocean.shootAt(4, 4);
		ocean.shootAt(4, 3);
		ocean.shootAt(0, 1);
		ocean.shootAt(3, 3);
		
		assertTrue(ocean.shootAt(4, 2));
		assertEquals(3, ocean.getHitCount());
		assertEquals(0, ocean.getShipsSunk());
		assertEquals(5, ocean.getShotsFired());
		
		//battleship sunk
		ocean.shootAt(4, 1);
		
		assertEquals(4, ocean.getHitCount());
		assertEquals(1, ocean.getShipsSunk());
		assertEquals(6, ocean.getShotsFired());
		
		ocean.shootAt(4, 4);
		ocean.shootAt(4, 3);
		
		assertEquals(4, ocean.getHitCount());
		assertEquals(1, ocean.getShipsSunk());
		assertEquals(8, ocean.getShotsFired());	
	}
	
	@Test
	void testGetShotsFired() {
		Ocean ocean = new Ocean();
		assertTrue(ocean.getShotsFired() == 0);
		
		ocean.shootAt(0, 0);
		ocean.shootAt(1, 2);
		ocean.shootAt(7, 9);
		
		assertTrue(ocean.getShotsFired() == 3);
	}
	
	@Test
	void testGetHitCount() {
		Ocean ocean = new Ocean();
		assertTrue(ocean.getHitCount() == 0);
		
		Destroyer destroyer1 = new Destroyer();
		Destroyer destroyer2 = new Destroyer();
		destroyer1.placeShipAt(1, 4, true, ocean);
		destroyer2.placeShipAt(5, 7, true, ocean);
		
		ocean.shootAt(5, 7);
		ocean.shootAt(5, 6);
		ocean.shootAt(2, 0);
		
		assertTrue(ocean.getHitCount() == 2);
		
		//hit count should remain same, since the battleship sunk
		ocean.shootAt(5, 7);
		assertTrue(ocean.getHitCount() == 2);
	}
	
	@Test
	void testGetShipsSunk() {
		Ocean ocean = new Ocean();
		assertTrue(ocean.getShipsSunk() == 0);
		
		Destroyer destroyer1 = new Destroyer();
		Destroyer destroyer2 = new Destroyer();
		destroyer1.placeShipAt(1, 4, true, ocean);
		destroyer2.placeShipAt(5, 7, true, ocean);
		
		ocean.shootAt(5, 7);
		ocean.shootAt(5, 6);
		assertTrue(ocean.getShipsSunk() == 1);
		
		ocean.shootAt(1, 4);
		assertTrue(ocean.getShipsSunk() == 1);
	}
	
	@Test
	void testIsGameOver() {
		Ocean ocean = new Ocean();
		ocean.placeAllShipsRandomly();
		
		for (int r = 0; r < 10; r++) {
			for (int c = 0; c < 10; c++) {
				ocean.shootAt(r, c);
			}
		}
		assertTrue(ocean.isGameOver());
	}
	
	
	@Test
	void testGetShipArray() {
		Ocean ocean = new Ocean();
		Ship [][] ships = ocean.getShipArray();
		
		Battleship battleship = new Battleship();
		battleship.placeShipAt(0, 4, true, ocean);
		
		 assertEquals(ships[0][3].getShipType(), "battleship");
		 assertEquals(ships[0][0].getShipType(), "empty");
	}
}

