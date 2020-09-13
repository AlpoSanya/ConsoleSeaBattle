package ships;

import main.Player;
import maps.PlacementMap;
import maps.ShootingMap;

import java.util.Scanner;

public class Ship {
	
	private static Player[] players = new Player[2];
	
	private static int playersCounter = -1;
	
	private static int mainShipCounter = -1;
	
	private static boolean placeShip(int shipType,
			int shipNum, int cellNum,
			int xValue, int yValue) {
		
		if (PlacementMap.isMapBorder(xValue) ||
				PlacementMap.isMapBorder(yValue) ||
				PlacementMap.isShipPartHere(xValue, yValue) ||
				PlacementMap.isShipBorder(mainShipCounter, xValue, yValue))
			return true;
		
		final int COORDINATE_X = 0;
		final int COORDINATE_Y = 1;
		
		switch(shipType) {
		case 4:
			players[playersCounter].getLinkorCoordinates()[shipNum][cellNum]
					[COORDINATE_X] = xValue;
			players[playersCounter].getLinkorCoordinates()[shipNum][cellNum]
					[COORDINATE_Y] = yValue;
			
			break;
		case 3:
			players[playersCounter].getCracersCoordinates()[shipNum][cellNum]
					[COORDINATE_X] = xValue;
			players[playersCounter].getCracersCoordinates()[shipNum ][cellNum]
					[COORDINATE_Y] = yValue;
			
			break;
		case 2:
			players[playersCounter].getDestroyersCoordinates()[shipNum][cellNum]
					[COORDINATE_X] = xValue;
			players[playersCounter].getDestroyersCoordinates()[shipNum][cellNum]
					[COORDINATE_Y] = yValue;
			
			break;
		case 1:
			players[playersCounter].getBoatsCoordinates()[shipNum][cellNum]
					[COORDINATE_X] = xValue;
			players[playersCounter].getBoatsCoordinates()[shipNum][cellNum]
					[COORDINATE_Y] = yValue;
			
			break;
		}
		
		PlacementMap.placeCoordinate(players[playersCounter], mainShipCounter, xValue, yValue);
		
		return false;
	}
	
	public static void placeShips(Player newPlayer) {
		players[++playersCounter] = newPlayer;
		mainShipCounter = -1;
		
		final int SHIP_TYPES_COUNT = 4;
		
		for (int i = 0; i < SHIP_TYPES_COUNT; ++i) {
			switch(i) {
			case 0:
				placeShipsLoop(4);
				break;
			case 1:
				placeShipsLoop(3);
				break;
			case 2:
				placeShipsLoop(2);
				break;
			case 3:
				placeShipsLoop(1);
				break;
			}
		}
		
	}
	
	private static void placeShipsLoop(int shipType) {
		switch(shipType) {
		case 4:
			for (int shipsCounter = 0; 
					shipsCounter < players
					[playersCounter].getLinkorCoordinates().length; 
					++shipsCounter) {
				String mainCoordinate = enterMainCoordinate();
				int mainCoordinateValue = enterMainCoordinateValue();
				
				mainShipCounter++;
				
				for (int cellsCounter = 0;
						cellsCounter < players
						[playersCounter].getLinkorCoordinates()
						[shipsCounter].length;
						++cellsCounter) {
				
					enterCoordinates(shipType,
							shipsCounter, cellsCounter,
							mainCoordinate, mainCoordinateValue);
					
					if (cellsCounter + 1 == players
							[playersCounter].getLinkorCoordinates()
							[shipsCounter].length)
						PlacementMap.displayMap(players
								[playersCounter].getPlacementMap());
				}
				
			}
			break;
		case 3:
			for (int shipsCounter = 0; 
					shipsCounter < players
					[playersCounter].getCracersCoordinates().length; 
					++shipsCounter) {
				String mainCoordinate = enterMainCoordinate();
				int mainCoordinateValue = enterMainCoordinateValue();
				
				mainShipCounter++;
				
				for (int cellsCounter = 0;
						cellsCounter < players
						[playersCounter].getCracersCoordinates()
						[shipsCounter].length;
						++cellsCounter) {
					
					enterCoordinates(shipType,
							shipsCounter, cellsCounter,
							mainCoordinate, mainCoordinateValue);
					
					if (cellsCounter + 1 == players
							[playersCounter].getCracersCoordinates()
							[shipsCounter].length)
						PlacementMap.displayMap(players
								[playersCounter].getPlacementMap());
				}
				
			}
			break;
		case 2:
			for (int shipsCounter = 0; 
					shipsCounter < players
					[playersCounter].getDestroyersCoordinates().length; 
					++shipsCounter) {
				String mainCoordinate = enterMainCoordinate();
				int mainCoordinateValue = enterMainCoordinateValue();
				
				mainShipCounter++;
				
				for (int cellsCounter = 0;
						cellsCounter < players
						[playersCounter].getDestroyersCoordinates()
						[shipsCounter].length;
						++cellsCounter) {
					
					enterCoordinates(shipType,  
							shipsCounter, cellsCounter,
							mainCoordinate, mainCoordinateValue);
					
					if (cellsCounter + 1 == players
							[playersCounter].getDestroyersCoordinates()
							[shipsCounter].length)
						PlacementMap.displayMap(players
								[playersCounter].getPlacementMap());
				}
				
			}
			break;
		case 1:
			for (int shipsCounter = 0; 
					shipsCounter < players
					[playersCounter].getBoatsCoordinates().length; 
					++shipsCounter) {
				
				mainShipCounter++;
				
				for (int cellsCounter = 0;
						cellsCounter < players[playersCounter].getBoatsCoordinates()
						[shipsCounter].length;
						++cellsCounter) {
					enterCoordinates(shipType,
							shipsCounter, cellsCounter);
					
					if (cellsCounter + 1 == players
							[playersCounter].getBoatsCoordinates()
							[shipsCounter].length)
						PlacementMap.displayMap(players
								[playersCounter].getPlacementMap());
				}
				
			}
			break;
		}
	}
	
	private static int enterCoordinate() {
		Scanner in = new Scanner(System.in);
		
		int coordinate = 0;
		
		do {
			try {
				coordinate = in.nextInt() + 1;
				if (PlacementMap.isMapBorder(coordinate)) {
					System.out.println("Coordinate is out of map border! "
							+ "Please, try again.");
					continue;
				}
				
				return coordinate;
			}
			catch (Exception exception) {
				System.out.println("Error enter! Please, try again.");
				continue;
			}
		} while (true);
	}
	
	private static void enterCoordinates(int shipType, 
			int shipsCounter, int cellsCounter) {
		Scanner in = new Scanner(System.in);
		
		int xValue = 0;
		int yValue = 0;
		do {
			System.out.print("Enter a value of x coordinate: ");
			xValue = enterCoordinate();
			
			System.out.print("Enter a value of y coordinate: ");
			yValue = enterCoordinate();
			
			if (placeShip(shipType, 
					shipsCounter, cellsCounter, 
					xValue, yValue)) {
				System.out.println("Error enter. Please, try again!\n");
				continue;
			}
			break;
		} while (true);
	}
	
	private static void enterCoordinates(int shipType,
			int shipsCounter, int cellsCounter,
			String mainCoordinate, int mainCoordinateValue) {
		Scanner in = new Scanner(System.in);
		
		final int COORDINATE_X = 0;
		final int COORDINATE_Y = 1;
		
		int xValue = 0;
		int yValue = 0;
		do {
			if (mainCoordinate.equals("x")) {
				System.out.print("Enter a value of y coordinate: ");
				yValue = enterCoordinate();
				xValue = mainCoordinateValue;
			}
			else {
				System.out.print("Enter a value of x coordinate: ");
				xValue = enterCoordinate();
				yValue = mainCoordinateValue;
			}
			
			switch(shipType) {
			case 4:
				players[playersCounter].getLinkorCoordinates()
					[shipsCounter][cellsCounter][COORDINATE_X] = xValue;
				players[playersCounter].getLinkorCoordinates()
					[shipsCounter][cellsCounter][COORDINATE_Y] = yValue;
				
				if (PlacementMap.isShipUnitsNearby(players
						[playersCounter].getLinkorCoordinates(),
						shipsCounter, cellsCounter,
						mainCoordinate)) {
					System.out.println("\nUnits of current ship is not nearby."
							+ " Please, try again!");
					
					players[playersCounter].getLinkorCoordinates()
						[shipsCounter][cellsCounter][COORDINATE_X] = 0;
					players[playersCounter].getLinkorCoordinates()
						[shipsCounter][cellsCounter][COORDINATE_Y] = 0;
					continue;
				}
				break;
			case 3:
				players[playersCounter].getCracersCoordinates()
					[shipsCounter][cellsCounter][COORDINATE_X] = xValue;
				players[playersCounter].getCracersCoordinates()
					[shipsCounter][cellsCounter][COORDINATE_Y] = yValue;
				
				if (PlacementMap.isShipUnitsNearby(players
						[playersCounter].getCracersCoordinates(),
						shipsCounter, cellsCounter,
						mainCoordinate)) {
					System.out.println("\nUnits of current ship is not nearby. "
							+ "Please, try again!");
					players[playersCounter].getCracersCoordinates()
						[shipsCounter][cellsCounter][COORDINATE_X] = 0;
					players[playersCounter].getCracersCoordinates()
						[shipsCounter][cellsCounter][COORDINATE_Y] = 0;
					continue;
				}
				break;
			case 2:
				players[playersCounter].getDestroyersCoordinates()
					[shipsCounter][cellsCounter][COORDINATE_X] = xValue;
				players[playersCounter].getDestroyersCoordinates()
					[shipsCounter][cellsCounter][COORDINATE_Y] = yValue;
				
				if (PlacementMap.isShipUnitsNearby(players
						[playersCounter].getDestroyersCoordinates(),
						shipsCounter, cellsCounter,
						mainCoordinate)) {
					System.out.println("\nUnits of current ship is not nearby."
							+ " Please, try again!");
					players[playersCounter].getDestroyersCoordinates()
							[shipsCounter][cellsCounter][COORDINATE_X] = 0;
					players[playersCounter].getDestroyersCoordinates()
						[shipsCounter][cellsCounter][COORDINATE_Y] = 0;
					continue;
				}
				break;
			}
			
			if (placeShip(shipType, 
					shipsCounter, cellsCounter, 
					xValue, yValue)) {
				System.out.println("Error enter. Please, try again!\n");
				continue;
			}
			
				break;
			} while (true);
			
	}
	
/*
	Methods for enter a main coordinate
*/
	private static String enterMainCoordinate() {
		Scanner in = new Scanner(System.in);
		String mainCoordinate;

		do {
			System.out.print("Enter a main coordinate: ");
			mainCoordinate = in.nextLine().toLowerCase();
			if (!(mainCoordinate.equals("x") || mainCoordinate.equals("y"))) {
				System.out.println("Error enter. Please, try again!\n");
				continue;
			}
		
			break;
		} while (true);

		return mainCoordinate;
	}

	private static int enterMainCoordinateValue() {
		Scanner in = new Scanner(System.in);
		int mainCoordinateValue = 0;

		do {
			System.out.print("Enter a value of main coordinate: ");
			mainCoordinateValue = in.nextInt() + 1;
			if (!(mainCoordinateValue >= 0 && mainCoordinateValue <= 9)) {
				System.out.println("Error enter. Please, try again!");
				continue;
			}
		
			break;
		} while (true);

		return mainCoordinateValue;
	}
/***********************************************/

	public static void startSeaBattle() {
		players[playersCounter - 1].setBattleMap(players[playersCounter - 1].getPlacementMap());
		players[playersCounter].setBattleMap(players[playersCounter].getPlacementMap());
		
		ShootingMap.startSeaBattle(players[playersCounter - 1], players[playersCounter]);
	}

}
