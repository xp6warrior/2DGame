package tile;

import handler.MapHandler;
import main.GamePanel;

import java.awt.*;
import java.io.*;
import java.util.Objects;

public class TileManager {
    public final Tile[] tileSet;
    public final int[][] mapInfo;

    private final GamePanel gp;
    private final MapHandler mapH;

    public TileManager(GamePanel gp) {
        this.gp = gp;
        mapH = gp.mapH;

        // Step 1: Reads mapH information on the tileSet used and the size of the map
        mapInfo = new int[mapH.columns][mapH.rows];
        tileSet = loadTileSet(mapH.tileSetName).tileArray;

        loadMap(mapH.path);
    }
    private TILESET loadTileSet(String name) {
        TILESET tileset = null;
        
        switch (name) {
            case "alpha": tileset = new TILESET_Alpha(); break;
            case "kwas": tileset = new TILESET_Kwas(); break;
        }

        return tileset;
    }
    private void loadMap(String filePath) {
        try {
            // Step 2: Reads the map file, puts map information into a 2D array (mapTileArray)

            InputStream is = getClass().getResourceAsStream(filePath);
            BufferedReader br = new BufferedReader(new InputStreamReader(Objects.requireNonNull(is)));

            for (int row = 0; row < mapH.rows; row++) {

                String line = br.readLine();
                String[] indexes = line.split(" ");

                for (int column = 0; column < mapH.columns; column++) {
                    mapInfo[column][row] = Integer.parseInt(indexes[column]);
                }
            }

            br.close();
        } catch (IOException e) {e.printStackTrace();}
    }


    public void draw(Graphics2D g2d) {

        for (int row = 0; row < mapH.rows; row++) {

            for (int column = 0; column < mapH.columns; column++) {

                // Step 3: Checks every tile if it is in player's vision. If so, it draws it

                Tile tile = tileSet[mapInfo[column][row]];

                int worldX = column * gp.tileSize;
                int worldY = row * gp.tileSize;

                int screenX = worldX - gp.player.worldX + gp.player.screenX;
                int screenY = worldY - gp.player.worldY + gp.player.screenY;

                if (screenX > -gp.tileSize && screenX < gp.windowX
                    && screenY > -gp.tileSize && screenY < gp.windowY) {

                    g2d.drawImage(tile.image, screenX, screenY, gp.tileSize, gp.tileSize, null);
                }
            }
        }
    }
}
