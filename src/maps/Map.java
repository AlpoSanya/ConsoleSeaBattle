package maps;

public abstract class Map {
	
	// more length need for correctly work of method PlacemantMap.isShipBorder()
	static char[][] map = new char[13][13];
	
	public static void updateMap() {
		for (int i = 0; i < map.length; ++i)
			for (int j = 0; j < map[i].length; ++j) 
				map[i][j] = '#';
	}
	
	public static void updateMap(char[][] map) {
		for (int i = 0; i < map.length; ++i)
			for (int j = 0; j < map[i].length; ++j) 
				map[i][j] = '#';
	}
	
}
