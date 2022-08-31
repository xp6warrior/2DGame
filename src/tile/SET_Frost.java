package tile;

import main.Util;

import javax.imageio.ImageIO;
import java.io.IOException;
import java.util.Objects;

public class SET_Frost extends SET {

    public SET_Frost() {
        path = "/tiles/tilesetFrost/";
        initialise(2);

        try {
            tileArray[5].image = Util.scaleImage(ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream(path + "liquid1.png"))), Util.tileSize, Util.tileSize);
            tileArray[5].collision = true;

            tileArray[6].image = Util.scaleImage(ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream(path + "floor3.png"))), Util.tileSize, Util.tileSize);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
