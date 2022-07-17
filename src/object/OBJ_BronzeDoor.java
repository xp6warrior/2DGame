package object;

import main.Util;

import javax.imageio.ImageIO;
import java.io.IOException;
import java.util.Objects;

public class OBJ_BronzeDoor extends OBJ {

    public OBJ_BronzeDoor() {
        setup();
    }

    public OBJ_BronzeDoor(int w, int h) {
        super(w, h);
        setup();
    }

    private void setup() {
        name = "BronzeDoor";
        collision = true;

        message3 = "Requires a Bronze key!";
        message2 = "[SPACE] to Unlock";
        message1 = "Door Unlocked!";

        soundIndex = 2;
        messageTimerLength = 10;
        secondaryTimerLength = 50;

        try {
            image = Util.scaleImage(ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/objects/bronzeDoor.png"))), width, height);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
