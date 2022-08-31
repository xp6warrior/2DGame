package tile;

import main.Util;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.Objects;

public abstract class SET {
    protected Tile[] tileArray;
    protected String path;

    protected void initialise(int extraTiles) {
        tileArray = new Tile[5 + extraTiles];

        for (int i = 0; i < 5 + extraTiles; i++) {
            tileArray[i] = new Tile();
        }

        try {
            tileArray[0].image = Util.scaleImage(ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream(path + "floor1.png"))), Util.tileSize, Util.tileSize);
            tileArray[1].image = Util.scaleImage(ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream(path + "floor2.png"))), Util.tileSize, Util.tileSize);
            tileArray[2].image = Util.scaleImage(ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream(path + "path1.png"))), Util.tileSize, Util.tileSize);
            tileArray[3].image = Util.scaleImage(ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream(path + "wall1.png"))), Util.tileSize, Util.tileSize);
            tileArray[3].collision = true;
            tileArray[4].image = Util.scaleImage(ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream(path + "obstacle1.png"))), Util.tileSize, Util.tileSize);
            tileArray[4].collision = true;

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
