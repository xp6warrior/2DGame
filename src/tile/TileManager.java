package tile;

import handler.MapHandler;
import main.GamePanel;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.Objects;

public class TileManager {
    public Tile[] tileSet;
    public int[][] mapInfo;

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

        // Step 2: Gets the map's tileset
        switch (name) {
            case "alpha": tileset = new TILESET_Alpha(gp); break;
            case "frost": tileset = new TILESET_Frost(gp); break;
        }

        return tileset;
    }

    private void loadMap(String filePath) {
        try {
            // Step 3: Reads the map file, puts map information into a 2D array (mapTileArray)
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

    int animationCounter = 0;

    public void draw(Graphics2D g2d) {

        for (int row = 0; row < mapH.rows; row++) {

            for (int column = 0; column < mapH.columns; column++) {

                // Step 4: Checks every tile if it is in player's vision. If so, it draws it

                Tile tile = tileSet[mapInfo[column][row]];
                BufferedImage img = tile.image;

                int worldX = column * gp.tileSize;
                int worldY = row * gp.tileSize;

                int screenX = worldX - gp.player.worldX + gp.player.screenX;
                int screenY = worldY - gp.player.worldY + gp.player.screenY;

                if (screenX > -gp.tileSize && screenX < gp.windowX
                    && screenY > -gp.tileSize && screenY < gp.windowY) {

                    if (tile.animationFrames != null) {

                        if (animationCounter > 40 && animationCounter <= 80) {
                            img = tile.animationFrames[0];
                        }
                        if (animationCounter > 80 && animationCounter <= 120) {
                            img = tile.animationFrames[1];
                        }
                        if (animationCounter > 120 && animationCounter <= 160) {
                            img = tile.animationFrames[2];
                        }
                        if (animationCounter > 160) {
                            animationCounter = 0;
                        }

                    }

                    g2d.drawImage(img, screenX, screenY, null);
                }
            }
        }

        animationCounter++;
    }
}
