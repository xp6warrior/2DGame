package tile;

import main.Util;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.Objects;

public class TSET {
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
            tileArray[0].image = Util.scaleImage(ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream(path + "grass.png"))), Util.tileSize, Util.tileSize);

            tileArray[1].image = Util.scaleImage(ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream(path + "wall.png"))), Util.tileSize, Util.tileSize);
            tileArray[1].collision = true;

            tileArray[2].image = Util.scaleImage(ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream(path + "water.png"))), Util.tileSize, Util.tileSize);
            tileArray[2].collision = true;
            tileArray[2].animationFrames = new BufferedImage[3];
                tileArray[2].animationFrames[0] = Util.scaleImage(ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream(path + "water2.png"))), Util.tileSize, Util.tileSize);
                tileArray[2].animationFrames[1] = Util.scaleImage(ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream(path + "water3.png"))), Util.tileSize, Util.tileSize);
                tileArray[2].animationFrames[2] = Util.scaleImage(ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream(path + "water4.png"))), Util.tileSize, Util.tileSize);

            tileArray[3].image = Util.scaleImage(ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream(path + "path.png"))), Util.tileSize, Util.tileSize);

            tileArray[4].image = Util.scaleImage(ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream(path + "dirt.png"))), Util.tileSize, Util.tileSize);

            tileArray[5].image = Util.scaleImage(ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream(path + "sand.png"))), Util.tileSize, Util.tileSize);

            tileArray[6].image = Util.scaleImage(ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream(path + "tree.png"))), Util.tileSize, Util.tileSize);
            tileArray[6].collision = true;

        } catch (IOException e) {e.printStackTrace();}
    }
}
