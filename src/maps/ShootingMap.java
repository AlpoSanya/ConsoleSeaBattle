package maps;

import main.Player;

import java.util.Scanner;

public class ShootingMap extends Map {
	
	// TODO: разобраться с этими массивами и целочисленными переменными
	
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
						else if (map[i][j] == 'x')
							System.out.print("x ");
						else if (map[i][j] == 'X')
							System.out.print("O ");
						else
							System.out.print("# ");
					}
			}
		}
		System.out.println("\ny\n");
	}
	
	// TODO: проверить методы на работоспособность и добавить к ним комментарий-раздел
	
	private static void hitMap(char[][] battleMap, char[][] shootingMap,
			int xValue, int yValue) {
		battleMap[yValue][xValue] = 'x';
		shootingMap[yValue][xValue] = 'x';
	}
	
	private static void finalShip(char[][] placementMap, 
			char[][] battleMap, char[][] shootingMap,
			int newDestroyedShipNum) {
		
		for (int i = 0; i < placementMap.length; ++i) {
			for (int j = 0; j < placementMap[i].length; ++j) {
				if (placementMap[i][j] == newDestroyedShipNum + '0') {
					if (shootingMap[i - 1][j] != 'X') {
						battleMap[i - 1][j] = 'x';
						shootingMap[i - 1][j] = 'x';
					}
					if (shootingMap[i + 1][j] != 'X') {
						battleMap[i + 1][j] = 'x';
						shootingMap[i + 1][j] = 'x';
					}
					if (shootingMap[i][j - 1] != 'X') {
						battleMap[i][j - 1] = 'x';
						shootingMap[i][j - 1] = 'x';
					}
					if (shootingMap[i][j + 1] != 'X') {
						battleMap[i][j + 1] = 'x';
						shootingMap[i][j + 1] = 'x';
					}
					if (shootingMap[i - 1][j + 1] != 'X') {
						battleMap[i - 1][j + 1] = 'x';
						shootingMap[i - 1][j + 1] = 'x';
					}
					if (shootingMap[i + 1][j - 1] != 'X') {
						battleMap[i + 1][j - 1] = 'x';
						shootingMap[i + 1][j - 1] = 'x';
					}
					if (shootingMap[i + 1][j + 1] != 'X') {
						battleMap[i + 1][j + 1] = 'x';
						shootingMap[i + 1][j + 1] = 'x';
					}
					if (shootingMap[i - 1][j - 1] != 'X') {
						battleMap[i - 1][j - 1] = 'x';
						shootingMap[i - 1][j - 1] = 'x';
					}
				}	
			}
		}			
	}
	
	// TODO: доработать метод по такой логике, что один 1)человек попал 2)человек промазал 3) другой промазал
	private static boolean isAnyShipDestroyed(char[][] placementMap, 
			char[][] battleMap, char[][] shootingMap,
			boolean[] existingShips, int newDestroyedShipNum) {
		boolean[] newExistingShips = new boolean[10];
		for (int i = 0; i < battleMap.length; ++i)
			for (int j = 0; j < battleMap[i].length; ++j)
				if (battleMap[i][j] >= '0' && battleMap[i][j] <= '9') 
					newExistingShips[battleMap[i][j] - '0'] = true;
		
		for (int i = 0; i < existingShips.length; ++i)
			if (existingShips[i] != newExistingShips[i]) {
				newDestroyedShipNum = i;
				finalShip(placementMap, battleMap, shootingMap, newDestroyedShipNum);
				
				for (int k = 0; k < existingShips.length; ++k)
					existingShips[k] = newExistingShips[k];
				
				return true;
			}
		
		return false;
	}
	
	private static boolean isShipHit(char[][] placementMap,
			char[][] battleMap, char[][] shootingMap,
			int xValue, int yValue) {
		
		if (placementMap[yValue][xValue] >= '0'
				&& placementMap[yValue][xValue] <= '9') {
			battleMap[yValue][xValue] = 'X';
			shootingMap[yValue][xValue] = 'X';
			
			return true;
		}
		
		return false;
	}
	
	// TODO: проверить работу этого метода
	private static boolean isMapBorder(int coordinate) {
		if (coordinate < 0 || coordinate > 10)
			return true;
		
		return false;
	}
	
	private static boolean isHitMap(char[][] placementMap,
			char[][] battleMap, char[][] shootingMap,
			boolean[] existingShips, int newDestroyedShipNum,
			int xValue, int yValue) {

		if (isShipHit(placementMap, battleMap, shootingMap,
				xValue, yValue))  {
			System.out.println("You hit the ship!\n");
			
			if (isAnyShipDestroyed(placementMap, battleMap, shootingMap, 
					existingShips, newDestroyedShipNum))
				System.out.println("You destroyed the ship!\n");
			
			displayMap(shootingMap);
			
			return true;
		}
		else {
			hitMap(battleMap, shootingMap, 
					xValue, yValue);
			System.out.println("You miss!\n");
			
			return true;
		}
			
	}
	
	public static int enterCoordinate() {
		Scanner in = new Scanner(System.in);
		
		int coordinate = 0;
		do {
			try {
				coordinate = in.nextInt() + 1;
				if (isMapBorder(coordinate)) {
					System.out.println("Coordinate is out of map border! Please, try again.");
					continue;
				}
				
				return coordinate;
			}
			catch(Exception exception) {
				System.out.println("Error enter! Please, try again.");
				continue;
			}
		} while (true);
	}
	
	private static boolean isGameEnd(char[][] battleMap) {
		for (int i = 0; i < battleMap.length; ++i)
			for (int j = 0; j < battleMap[i].length; ++j) {
				if (battleMap[i][j] >= '0' && '9' <= battleMap[i][j])
					return false;
			}
		
		return true;
	}
	
	public static void startSeaBattle(Player player1, Player player2) {
		
		player1.tuneExistingShips();
		player2.tuneExistingShips();
		
		Player[] players = new Player[2];
		int xValue = 0;
		int yValue = 0;
		
		int moveOrder = (int)(1 + Math.random() * 2);
		switch(moveOrder) {
		case 1:
			players[0] = player1;
			players[1] = player2;
			break;
		case 2:
			players[0] = player2;
			players[1] = player1;
			break;
		}
		
		System.out.println("The battle has been starting!\n");
		
		for (int i = 0;;) {
			System.out.println(players[i].getName() + ", your turn!");
				
			System.out.println("Your ships:");
			displayMap(players[i].getBattleMap());
			System.out.println("Your shooting map:");
			displayMap(players[i].getShootingMap());
			
			do {
				System.out.print("Enter a value of x coordinate: ");
				xValue = enterCoordinate();
				System.out.print("Enter a value of y coordinate: ");
				yValue = enterCoordinate();
			
				if (i == 0) {
					if (isHitMap(players[i + 1].getPlacementMap(), 
							players[i + 1].getBattleMap(), players[i].getShootingMap(),
							players[i].getExistingShips(), players[i].getNewDestroyedShipNum(),
							xValue, yValue))  {
					
						if (players[i + 1].getBattleMap()[yValue][xValue] == 'X')
							continue;
						else 
							break;
					}
					else
						continue;
				}
				else if (i == 1) {
					if (isHitMap(players[i - 1].getPlacementMap(), 
							players[i - 1].getBattleMap(), players[i].getShootingMap(),
							players[i].getExistingShips(), players[i].getNewDestroyedShipNum(),
							xValue, yValue))  {
						if (players[i - 1].getBattleMap()[yValue][xValue] == 'X')
							continue;
						else 
							break;
					}
					else
						continue;
				}
			} while(true);
			
			// TODO: проверить эти методы на работоспособность
			if (isGameEnd(player2.getBattleMap())) {
				player2.win();
				player1.lose();
			}
			else if (isGameEnd(player1.getBattleMap())) {
				player1.win();
				player2.lose();
			}
			
			switch(i) {
			case 0:
				++i;
				break;
			case 1:
				--i;
				break;
			}
		}
	}
	
}
