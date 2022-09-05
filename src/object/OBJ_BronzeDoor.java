package object;

import main.Util;

public class OBJ_BronzeDoor extends OBJ {
    public OBJ_BronzeDoor(String variant) {
        name = "bronzeDoor";
        this.variant = variant;
        collision = true;

        message3 = "Requires a Bronze key!";
        message2 = "[SPACE] to Unlock";
        message1 = "Door Unlocked!";
        messageTimerLength = 40;

        soundIndex = 2;

        image = getObjectIcon(name, Util.tileSize, Util.tileSize, variant);
    }
}
