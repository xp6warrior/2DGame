package tile;

import handler.GamePanel;

import javax.imageio.ImageIO;
import java.awt.*;
import java.io.*;
import java.util.Objects;

public class TileManager {
    GamePanel gp;
    Tile[] tilesetAlpha;
    int[][] mapTileArray;

    public TileManager(GamePanel gp) {
        this.gp = gp;

        mapTileArray = new int[gp.columns][gp.rows];
        tilesetAlpha = new Tile[4];

        loadTiles();
        loadMap("/maps/mapAlpha.txt");
    }
    private void loadTiles() {
        try {
            for (int i=0; i < tilesetAlpha.length; i++) {
                tilesetAlpha[i] = new Tile();
            }
            tilesetAlpha[0].image = ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/tiles/tilesetAlpha/grass.png")));
            tilesetAlpha[1].image = ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/tiles/tilesetAlpha/wall.png")));
            tilesetAlpha[2].image = ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/tiles/tilesetAlpha/water.png")));
            tilesetAlpha[3].image = ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/tiles/tilesetAlpha/path.png")));

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    private void loadMap(String filePath) {
        try {
            InputStream is = getClass().getResourceAsStream(filePath);
            BufferedReader br = new BufferedReader(new InputStreamReader(Objects.requireNonNull(is)));

            for (int row=0; row < gp.rows; row++) {
                String line = br.readLine();
                String[] indexes = line.split(" ");

                for (int column=0; column < gp.columns; column++) {
                    int index = Integer.parseInt(indexes[column]);
                    mapTileArray[column][row] = index;
                }
            }

            br.close();

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void draw(Graphics2D g2d) {
        int x = 0;
        int y = 0;

        for (int row=0; row < gp.rows; row++) {

            for (int column=0; column < gp.columns; column++) {
                Tile tile = tilesetAlpha[mapTileArray[column][row]];
                g2d.drawImage(tile.image, x, y, gp.tileSize, gp.tileSize, null);

                x += gp.tileSize;
            }

            x = 0;
            y += gp.tileSize;
        }
    }
}
