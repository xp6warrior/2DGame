package tile;

import main.Util;

import javax.imageio.ImageIO;
import java.io.IOException;
import java.util.Objects;

public class SET_Dungeon extends SET {

    public SET_Dungeon() {
        path = "/tiles/tilesetDungeon/";
        initialise(4);

        Util.loadAnimatedTile(tileArray[5], path + "liquid1");
        tileArray[5].collision = true;

        Util.loadAnimatedTile(tileArray[6], path + "wall2");
        tileArray[6].collision = true;

        try {
            tileArray[7].image = Util.scaleImage(ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream(path + "wall3.png"))), Util.tileSize, Util.tileSize);
            tileArray[7].collision = true;

            tileArray[8].image = Util.scaleImage(ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream(path + "wall4.png"))), Util.tileSize, Util.tileSize);
            tileArray[8].collision = true;
        } catch (IOException e) {
            e.printStackTrace();
        }

    }
}
