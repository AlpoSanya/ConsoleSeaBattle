package main;

import java.util.Scanner;

import maps.PlacementMap;
import ships.Ship;

public class Main {

	public static void main(String[] args) {
		
		Scanner in = new Scanner(System.in);
		Player player1;
		Player player2;
		
// Registration of players
		PlacementMap.updateMap();
		System.out.println("Hello, players! Let's start to registering you!");
		
		System.out.print("Player 1, please, enter your name: ");
		player1 = new Player(in.nextLine());
		
		System.out.print("Player 2, please, enter your name: ");
		player2 = new Player(in.nextLine());

// Placement of ships
		player1.updatePlayerPlacementMap();
		player1.startPlacemantShips();
		
		PlacementMap.updateMap();
		
		player2.updatePlayerPlacementMap();
		player2.startPlacemantShips();
		
// Battle of ships
		Ship.startSeaBattle();
	}

}
