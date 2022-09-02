package handler;

public enum MapHandler {
    mapAlpha("/maps/mapAlpha.txt", 16, 12, 7, 6, "dungeon", -1),
    mapIsland("/maps/mapIsland.txt", 48, 42, 9, 22, "alpha", 0),
    testMap("/maps/testMap.txt", 16, 12, 0, 0, "dungeon", -1),
    titleMap("/maps/titleMap.txt", 21, 13, 6, 10, "alpha", -1),
    mapDungeon("/maps/mapDungeon2.txt", 50, 50, 7, 12, "dungeon", -1);

    public String path;
    public int rows;
    public int columns;
    public int startRow;
    public int startColumn;
    public String tileSetName;
    public int musicID;

    MapHandler(String path, int columns, int rows, int startRow, int startColumn, String tileSetName, int musicID) {
        this.path = path;
        this.rows = rows;
        this.columns = columns;
        this.startRow = startRow;
        this.startColumn = startColumn;
        this.tileSetName = tileSetName;
        this.musicID = musicID;
    }
}
