package maps;

import main.Player;

public class PlacementMap extends Map {
	
/*
	Methods for work with map
*/
	public static void displayMap(char[][] map) {
		final int COORDINATES_COUNT = 10;
		
		System.out.print("   ");
		for (int i = 0; i < COORDINATES_COUNT; ++i) {
			System.out.printf("%d ", i);
		}
		System.out.print("x");
		
		for (int i = 0, k = 0; i < map.length; ++i) {
			if (i != 0 && i != map.length - 1 && i != map.length - 2)
				System.out.printf("\n%d. ", k++);
			
			for (int j = 0; j < map[i].length; ++j) {
				if (i != 0 && i != map.length - 1 && i != map.length - 2) 
					if (j != 0 
						&& j != map[i].length - 1 && j != map[i].length - 2) {
						if (map[i][j] >= '0' && map[i][j] <= '9')
							System.out.print("@ ");
						else
							System.out.print("# ");
					}
			}
		}
		System.out.println("\ny\n");
	}
	
	public static void placeCoordinate(Player player, int shipNum, 
			int xValue, int yValue) {
		switch(shipNum) {
		case 9:
			map[yValue][xValue] = '9';
			break;
		case 8:
			map[yValue][xValue] = '8';
			break;
		case 7:
			map[yValue][xValue] = '7';
			break;
		case 6:
			map[yValue][xValue] = '6';
			break;
		case 5:
			map[yValue][xValue] = '5';
			break;
		case 4:
			map[yValue][xValue] = '4';
			break;
		case 3:
			map[yValue][xValue] = '3';
			break;
		case 2:
			map[yValue][xValue] = '2';
			break;
		case 1:
			map[yValue][xValue] = '1';
			break;
		case 0:
			map[yValue][xValue] = '0';
			break;
		}
		
		player.setPlacementMap(map);
	}
/*********************************************************/
	
/*
	Methods for check truth of user's enters
*/
	
	// does part of another ship exist at this coordinate
	public static boolean isShipPartHere(int xValue, int yValue) {
		if (map[yValue][xValue] >= '0' && '9' <= map[yValue][xValue])
			return true;
		return false;
	}
	
	public static boolean isMapBorder(int coordinate) {
		if (coordinate < 0 || coordinate > 10)
			return true;
		
		return false;
	}
	
	public static boolean isShipBorder(int shipNum, int xValue, int yValue) {
		
		if (map[yValue - 1][xValue] != '0' + shipNum
				&& map[yValue - 1][xValue] != '#')
			return true;
		else if (map[yValue + 1][xValue] != '0' + shipNum
				&& map[yValue + 1][xValue] != '#')
			return true;
		else if (map[yValue][xValue - 1] != '0' + shipNum
				&& map[yValue][xValue - 1] != '#') 
			return true;
		else if (map[yValue][xValue + 1] != '0' + shipNum 
				&& map[yValue][xValue + 1] != '#')
			return true;
		else if (map[yValue - 1][xValue - 1] != '0' + shipNum 
				&& map[yValue - 1][xValue - 1] != '#')
			return true;
		else if (map[yValue + 1][xValue + 1] != '0' + shipNum
				&& map[yValue + 1][xValue + 1] != '#')
			return true;
		else if (map[yValue + 1][xValue - 1] != '0' + shipNum
				&& map[yValue + 1][xValue - 1] != '#')
			return true;
		else if (map[yValue - 1][xValue + 1] != '0' + shipNum
				&& map[yValue - 1][xValue + 1] != '#')
			return true;
	
		return false;
	}
	
	// TODO: пофиксить этот метод
	public static boolean isShipUnitsNearby(int[][][] shipCoordinates, 
			int shipNum, int cellNum,
			String mainCoordinate) {
		final int COORDINATE_X = 0;
		final int COORDINATE_Y = 1;
		
		int minCell = findMinCell(shipCoordinates, shipNum, cellNum, mainCoordinate);
		int maxCell = findMaxCell(shipCoordinates, shipNum, cellNum, mainCoordinate);
		if (mainCoordinate.equals("x")) {
			switch(cellNum) {
			case 0:
				return false;
			case 1:
				if (!(
						(shipCoordinates[shipNum][cellNum - 1][COORDINATE_Y] + 1 
						== shipCoordinates[shipNum][cellNum][COORDINATE_Y])
						|| (shipCoordinates[shipNum][cellNum - 1][COORDINATE_Y] - 1
						== shipCoordinates[shipNum][cellNum][COORDINATE_Y])
					)) 
					return true;
				break;
			case 2:
			case 3:
				if (!(
						(shipCoordinates[shipNum][maxCell][COORDINATE_Y] + 1 
						== shipCoordinates[shipNum][cellNum][COORDINATE_Y])
						|| (shipCoordinates[shipNum][minCell][COORDINATE_Y] - 1
						== shipCoordinates[shipNum][cellNum][COORDINATE_Y])
					)) 
					return true;
				break;
			}
		}
		else {
			switch(cellNum) {
			case 0:
				return false;
			case 1:
				if (!(
						(shipCoordinates[shipNum][cellNum - 1][COORDINATE_X] + 1 
						== shipCoordinates[shipNum][cellNum][COORDINATE_X])
						|| (shipCoordinates[shipNum][cellNum - 1][COORDINATE_X] - 1
						== shipCoordinates[shipNum][cellNum][COORDINATE_X])
					)) 
					return true;
				break;
			case 2:
			case 3:
				if (!(
						(shipCoordinates[shipNum][maxCell][COORDINATE_X] + 1 
						== shipCoordinates[shipNum][cellNum][COORDINATE_X])
						|| (shipCoordinates[shipNum][minCell][COORDINATE_X] - 1
						== shipCoordinates[shipNum][cellNum][COORDINATE_X])
					))
					return true;
				break;
			}
		}
		
		return false;
	}
	
	// Find cell that have the smallest not main coordinate
	private static int findMinCell(int[][][] shipCoordinates,
			int shipNum, int currentCell,
			String mainCoordinate) {
		final int MAIN_COORDINATE;
		int minCell = 0;
		int minCoordinate = 11;
		
		if (mainCoordinate.equals("x"))
			MAIN_COORDINATE = 1;
		else
			MAIN_COORDINATE = 0;
			
		for (int i = 0; i < currentCell; ++i)
			if (shipCoordinates[shipNum][i][MAIN_COORDINATE] < minCoordinate) {
				minCell = i;
				minCoordinate = shipCoordinates[shipNum][i][MAIN_COORDINATE];
			}
		
		return minCell;
	}
	
	// Find cell that have the biggest not main coordinate
	private static int findMaxCell(int[][][] shipCoordinates,
			int shipNum, int currentCell,
			String mainCoordinate) {
		final int MAIN_COORDINATE;
		int maxCell = 0;
		int maxCoordinate = 0;
		
		if (mainCoordinate.equals("x"))
			MAIN_COORDINATE = 1;
		else
			MAIN_COORDINATE = 0;
			
		for (int i = 0; i < currentCell; ++i)
			if (shipCoordinates[shipNum][i][MAIN_COORDINATE] > maxCoordinate) {
				maxCell = i;
				maxCoordinate = shipCoordinates[shipNum][i][MAIN_COORDINATE];
			}
		
		return maxCell;
	}
/**********************************************************/
	
}
