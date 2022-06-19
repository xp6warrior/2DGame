package handler;

public enum MapHandler {
    mapAlpha("/maps/mapAlpha.txt", 16, 12, 7, 6),
    mapIsland("/maps/mapIsland.txt", 46, 42, 9, 20),
    testMap("/maps/testMap.txt", 16, 12, 0, 0);

    public String path;
    public int rows;
    public int columns;
    public int startRow;
    public int startColumn;

    MapHandler(String path, int columns, int rows, int startRow, int startColumn) {
        this.path = path;
        this.rows = rows;
        this.columns = columns;
        this.startRow = startRow;
        this.startColumn = startColumn;
    }
}
