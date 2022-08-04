package object;

import main.Util;

import javax.imageio.ImageIO;
import java.io.IOException;
import java.util.Objects;

public class OBJ_GoldDoor extends OBJ {
    public OBJ_GoldDoor() {
        name = "goldDoor";
        collision = true;

        message3 = "Requires a Golden key!";
        message2 = "[SPACE] to Unlock";
        message1 = "Door Unlocked!";
        messageTimerLength = 40;

        soundIndex = 2;

        image = getObjectIcon(name, Util.tileSize, Util.tileSize);
    }
}
