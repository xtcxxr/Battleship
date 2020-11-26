package battleship;

import java.util.Scanner;

/**
 * Main class for a human vs computer version Battleship.
 * Create a single instance of Ocean. Gets user input (row and column)
 * for interacting with and playing against the computer
 * @author xiranxu and hkim8
 */
public class BattleshipGame {
	
	private static Scanner scanner;

	public static void main(String[] args) {
		 
		System.out.println("Welcome to Battleship game!");
    	System.out.println("You'll play against the computer.");
    	System.out.println("Computer will place the ships. Try to sink them!");
    	Ocean ocean = new Ocean();
    	scanner = new Scanner(System.in);
		ocean.placeAllShipsRandomly();
    	
		while(true) {
    		ocean.print();
    		System.out.println("Enter row and column below, seperate with ','");
    		System.out.println("Enter row, column:");
        	String input = scanner.nextLine();
        	String[] rowAndColumn = input.split(",");
       
        	if (rowAndColumn.length != 2) {
        		System.out.print("Invalid input. Please enter row, column");
        	} else if ((Integer.parseInt(rowAndColumn[0]) > 9) || (Integer.parseInt(rowAndColumn[0]) < 0)
        		|| (Integer.parseInt(rowAndColumn[1]) > 9) || Integer.parseInt(rowAndColumn[1]) < 0){
        		System.out.print("Invalid input. Please enter row, column");
        	} else {
        		if(ocean.shootAt(Integer.parseInt(rowAndColumn[0]), Integer.parseInt(rowAndColumn[1])) == true) {
                	System.out.println("hit");
            		} else {
            			System.out.println("miss");
                		}
        		
        	}

        	if (ocean.isGameOver() == true) {
        		System.out.println("Game is over!");
        		System.out.println("Total shots fired: " + ocean.getShotsFired());
        		break;
        		}
        	}
    	}
    	
    }
    		

