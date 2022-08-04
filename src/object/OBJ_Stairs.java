package object;

import main.Util;

import javax.imageio.ImageIO;
import java.io.IOException;
import java.util.Objects;

public class OBJ_Stairs extends OBJ {
    public OBJ_Stairs() {
        name = "stairs";

        message2 = "Roy's Basement...";
        message1 = "";
        messageTimerLength = 0;

        soundIndex = 2;

        image = getObjectIcon(name, Util.tileSize, Util.tileSize);
    }
}
