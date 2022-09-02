package object;

import main.Util;

import javax.imageio.ImageIO;
import java.io.IOException;
import java.util.Objects;

public class OBJ_BronzeKey extends OBJ {
    public OBJ_BronzeKey() {
        name = "bronzeKey";
        variant = "normal";
        message1 = "Found a Bronze Key!";
        messageTimerLength = 50;

        soundIndex = 1;

        image = getObjectIcon(name, Util.tileSize, Util.tileSize, variant);
    }
}
