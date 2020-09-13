package main;

import ships.Ship;

import maps.PlacementMap;
import maps.ShootingMap;

public class Player {
	private static int playersID;
	
	private String name;
	private int playerID;
	
	private char[][] placementMap = new char[13][13];
	private char[][] shootingMap = new char[13][13];
	private char[][] battleMap = new char[13][13]; 
	
	private int[][][] linkorCoordinates = new int[1][4][2]; 
	private int[][][] cracersCoordinates = new int[2][3][2];
	private int[][][] destroyersCoordinates = new int[3][2][2];
	private int[][][] boatsCoordinates = new int[4][1][2];
	
	private boolean[] existingShips = new boolean[10];
	private int newDestroyedShipNum;
	
	public Player(String name) {
		this.name = name;
		
		this.playerID = playersID;
	}
	
	public int getPlayerID() {
		return this.playerID;
	}

	public String getName() {
		return this.name;
	}
	
	public void win() {
		System.out.println("Congratulations to " + this.getName() + ", you won!");
	}
	
	public void lose() {
		System.out.println(this.getName() + ", don't be upset, next time be lucky!");
	}
	
	public boolean[] getExistingShips() {
		return existingShips;
	}
	
	public void tuneExistingShips() {
		for (int i = 0; i < existingShips.length; ++i)
			existingShips[i] = true;
	}
	
	public int getNewDestroyedShipNum() {
		return newDestroyedShipNum;
	}
	
	// placementMap methods
	public char[][] getPlacementMap() {
		return placementMap;
	}
	
	public void setPlacementMap(char[][] newPlacementMap) {
		for (int i = 0; i < this.placementMap.length; ++i)
			for (int j = 0; j < this.placementMap[i].length; ++j)
				this.placementMap[i][j] = newPlacementMap[i][j];
	}
	
	public void startPlacemantShips() {
		System.out.println("Ok, " + this.getName() + ", you can start "
				+ "to place your ships!");
		PlacementMap.displayMap(this.placementMap);
		Ship.placeShips(this);
	}
	
	public void updatePlayerPlacementMap() {
		PlacementMap.updateMap(placementMap);
	}
	
	// shootingMap methods
	public char[][] getShootingMap() {
		return shootingMap;
	}
	
	public void setShootingMap(char[][] newShootingMap) {
		for (int i = 0; i < this.shootingMap.length; ++i)
			for (int j = 0; j < this.shootingMap[i][j]; ++j)
				this.shootingMap[i][j] = newShootingMap[i][j];
	}
	
	public void updatePlayerShootingMap() {
		ShootingMap.updateMap(shootingMap);
	}
	
	// battleMap methods
	public char[][] getBattleMap() {
		return this.battleMap;
	}
	
	public void setBattleMap(char[][] newBattleMap) {
		for (int i = 0; i < this.battleMap.length; ++i)
			for (int j = 0; j < this.battleMap[i].length; ++j)
				this.battleMap[i][j] = newBattleMap[i][j];
	}
	
	public int[][][] getLinkorCoordinates() {
		return linkorCoordinates;
	}
	
	public int[][][] getCracersCoordinates() {
		return cracersCoordinates;
	}
	
	public int[][][] getDestroyersCoordinates() {
		return destroyersCoordinates;
	}
	
	public int[][][] getBoatsCoordinates() {
		return boatsCoordinates;
	}
	
}
