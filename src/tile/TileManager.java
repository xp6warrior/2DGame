package tile;

import main.GamePanel;

import javax.imageio.ImageIO;
import java.awt.*;
import java.io.*;
import java.util.Objects;

public class TileManager {

    public Tile[] tilesetAlpha;
    public int[][] mapTileArray;
    GamePanel gp;

    public TileManager(GamePanel gp) {
        this.gp = gp;

        mapTileArray = new int[gp.map.columns][gp.map.rows];
        tilesetAlpha = new Tile[7];

        loadTiles();
        loadMap(gp.map.path);
    }
    private void loadTiles() { // Loads the tileset
        try {

            for (int i = 0; i < tilesetAlpha.length; i++) {
                tilesetAlpha[i] = new Tile();
            }

            tilesetAlpha[0].image = ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream( "/tiles/grass.png")));
            tilesetAlpha[1].image = ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/tiles/wall.png")));
            tilesetAlpha[1].collision = true;
            tilesetAlpha[2].image = ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/tiles/water.png")));
            tilesetAlpha[2].collision = true;
            tilesetAlpha[3].image = ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/tiles/path.png")));
            tilesetAlpha[4].image = ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/tiles/dirt.png")));
            tilesetAlpha[5].image = ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/tiles/sand.png")));
            tilesetAlpha[6].image = ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/tiles/tree.png")));
            tilesetAlpha[6].collision = true;

        } catch (IOException e) {e.printStackTrace();}
    }
    private void loadMap(String filePath) { // Loads the map
        try {

            InputStream is = getClass().getResourceAsStream(filePath);
            BufferedReader br = new BufferedReader(new InputStreamReader(Objects.requireNonNull(is)));

            for (int row = 0; row < gp.map.rows; row++) {

                String line = br.readLine();
                String[] indexes = line.split(" ");

                for (int column=0; column < gp.map.columns; column++) {
                    mapTileArray[column][row] = Integer.parseInt(indexes[column]);
                }
            }

            br.close();
        } catch (IOException e) {e.printStackTrace();}
    }

    public void draw(Graphics2D g2d) {

        for (int row = 0; row < gp.map.rows; row++) {

            for (int column = 0; column < gp.map.columns; column++) {
                Tile tile = tilesetAlpha[mapTileArray[column][row]];

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
