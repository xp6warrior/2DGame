package handler;

public enum MapHandler {
    mapAlpha("/maps/mapAlpha.txt", 16, 12, 7, 6, "alpha", 0),
    mapIsland("/maps/mapIsland.txt", 46, 42, 9, 20, "alpha", 0),
    testMap("/maps/testMap.txt", 16, 12, 0, 0, "alpha", 0);

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
