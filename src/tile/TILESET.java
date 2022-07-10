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

    protected void initialiseTiles(int length) {
        tileArray = new Tile[length];

        for (int i = 0; i < length; i++) {
            tileArray[i] = new Tile();
        }
    }
    protected void loadBasicTiles(GamePanel gp) {
        try {
            tileArray[0].image = Utility.scaleImage(ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream(path + "grass.png"))), gp.tileSize, gp.tileSize);

            tileArray[1].image = Utility.scaleImage(ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream(path + "wall.png"))), gp.tileSize, gp.tileSize);
            tileArray[1].collision = true;

            tileArray[2].image = Utility.scaleImage(ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream(path + "water.png"))), gp.tileSize, gp.tileSize);
            tileArray[2].collision = true;
            tileArray[2].animationFrames = new BufferedImage[3];
                tileArray[2].animationFrames[0] = Utility.scaleImage(ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream(path + "water2.png"))), gp.tileSize, gp.tileSize);
                tileArray[2].animationFrames[1] = Utility.scaleImage(ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream(path + "water3.png"))), gp.tileSize, gp.tileSize);
                tileArray[2].animationFrames[2] = Utility.scaleImage(ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream(path + "water4.png"))), gp.tileSize, gp.tileSize);

            tileArray[3].image = Utility.scaleImage(ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream(path + "path.png"))), gp.tileSize, gp.tileSize);

            tileArray[4].image = Utility.scaleImage(ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream(path + "dirt.png"))), gp.tileSize, gp.tileSize);

            tileArray[5].image = Utility.scaleImage(ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream(path + "sand.png"))), gp.tileSize, gp.tileSize);

            tileArray[6].image = Utility.scaleImage(ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream(path + "tree.png"))), gp.tileSize, gp.tileSize);
            tileArray[6].collision = true;

        } catch (IOException e) {e.printStackTrace();}
    }
}
