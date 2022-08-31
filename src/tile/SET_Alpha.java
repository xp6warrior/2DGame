package tile;

import main.Util;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.Objects;

public class SET_Alpha extends SET {

    public SET_Alpha() {
        path = "/tiles/tilesetAlpha/";
        initialise(2);

        try {
            tileArray[5].image = Util.scaleImage(ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream(path + "liquid1.png"))), Util.tileSize, Util.tileSize);
            tileArray[5].collision = true;
            tileArray[5].animationFrames = new BufferedImage[3];
            tileArray[5].animationFrames[0] = Util.scaleImage(ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream(path + "liquid1_2.png"))), Util.tileSize, Util.tileSize);
            tileArray[5].animationFrames[1] = Util.scaleImage(ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream(path + "liquid_3.png"))), Util.tileSize, Util.tileSize);
            tileArray[5].animationFrames[2] = Util.scaleImage(ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream(path + "liquid1_4.png"))), Util.tileSize, Util.tileSize);

            tileArray[6].image = Util.scaleImage(ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream(path + "floor3.png"))), Util.tileSize, Util.tileSize);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
