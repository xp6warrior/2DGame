package object;

import main.Util;

import javax.imageio.ImageIO;
import java.io.IOException;
import java.util.Objects;

public class OBJ_GoldKey extends OBJ {
    public OBJ_GoldKey() {
        name = "goldKey";
        message1 = "Found a Golden Key!";
        messageTimerLength = 50;

        soundIndex = 1;

        image = getObjectIcon(name, Util.tileSize, Util.tileSize);
    }
}
