package tile;

import main.GamePanel;
import main.Utility;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.Objects;

public class TILESET {
    protected Tile[] tileArray;
    protected String path;
    protected Utility util;

    protected void initialiseTiles(int length) {
        tileArray = new Tile[length];

        for (int i = 0; i < length; i++) {
            tileArray[i] = new Tile();
        }
    }
    protected void loadBasicTiles(GamePanel gp) {
        try {
            util = new Utility(gp);

            tileArray[0].image = util.scaleImage(ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream(path + "grass.png"))), gp.tileSize, gp.tileSize);

            tileArray[1].image = util.scaleImage(ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream(path + "wall.png"))), gp.tileSize, gp.tileSize);
            tileArray[1].collision = true;

            tileArray[2].image = util.scaleImage(ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream(path + "water.png"))), gp.tileSize, gp.tileSize);
            tileArray[2].collision = true;
            tileArray[2].animationFrames = new BufferedImage[3];
                tileArray[2].animationFrames[0] = util.scaleImage(ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream(path + "water2.png"))), gp.tileSize, gp.tileSize);
                tileArray[2].animationFrames[1] = util.scaleImage(ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream(path + "water3.png"))), gp.tileSize, gp.tileSize);
                tileArray[2].animationFrames[2] = util.scaleImage(ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream(path + "water4.png"))), gp.tileSize, gp.tileSize);

            tileArray[3].image = util.scaleImage(ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream(path + "path.png"))), gp.tileSize, gp.tileSize);

            tileArray[4].image = util.scaleImage(ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream(path + "dirt.png"))), gp.tileSize, gp.tileSize);

            tileArray[5].image = util.scaleImage(ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream(path + "sand.png"))), gp.tileSize, gp.tileSize);

            tileArray[6].image = util.scaleImage(ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream(path + "tree.png"))), gp.tileSize, gp.tileSize);
            tileArray[6].collision = true;

        } catch (IOException e) {e.printStackTrace();}
    }
}
