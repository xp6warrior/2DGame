package object;

import main.Util;

import javax.imageio.ImageIO;
import java.io.IOException;
import java.util.Objects;

public class OBJ_BronzeDoor extends OBJ {
    public OBJ_BronzeDoor() {
        name = "bronzeDoor";
        collision = true;

        message3 = "Requires a Bronze key!";
        message2 = "[SPACE] to Unlock";
        message1 = "Door Unlocked!";
        messageTimerLength = 40;

        soundIndex = 2;

        image = getObjectIcon(name, Util.tileSize, Util.tileSize);
    }
}
