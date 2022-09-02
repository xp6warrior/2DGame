package tile;

import main.Util;

import javax.imageio.ImageIO;
import java.io.IOException;
import java.util.Objects;

public class SET_Alpha extends SET {

    public SET_Alpha() {
        path = "/tiles/tilesetAlpha/";
        initialise(2);

        try {
            Util.loadAnimatedTile(tileArray[5], path + "liquid1");
            tileArray[5].collision = true;

            tileArray[6].image = Util.scaleImage(ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream(path + "floor3.png"))), Util.tileSize, Util.tileSize);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
