package tile;

import javax.imageio.ImageIO;
import java.io.IOException;
import java.util.Objects;

public class TILESET {
    protected Tile[] tileArray;
    protected String path;

    protected void initialiseTiles(int length) {
        tileArray = new Tile[length];

        for (int i = 0; i < length; i++) {
            tileArray[i] = new Tile();
        }
    }
    protected void loadBasicTiles() {
        try {
            tileArray[0].image = ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream(path + "grass.png")));
            tileArray[1].image = ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream(path + "wall.png")));
            tileArray[1].collision = true;
            tileArray[2].image = ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream(path + "water.png")));
            tileArray[2].collision = true;
            tileArray[3].image = ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream(path + "path.png")));
            tileArray[4].image = ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream(path + "dirt.png")));
            tileArray[5].image = ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream(path + "sand.png")));
            tileArray[6].image = ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream(path + "tree.png")));
            tileArray[6].collision = true;

        } catch (IOException e) {e.printStackTrace();}
    }
}
