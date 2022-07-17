package object;

import main.Util;

import javax.imageio.ImageIO;
import java.io.IOException;
import java.util.Objects;

public class OBJ_GoldDoor extends OBJ {

    public OBJ_GoldDoor() {
        setup();
    }

    public OBJ_GoldDoor(int w, int h) {
        super(w, h);
        setup();
    }

    private void setup() {
        name = "GoldDoor";
        collision = true;

        message3 = "Requires a Golden key!";
        message2 = "[SPACE] to Unlock";
        message1 = "Door Unlocked!";

        soundIndex = 2;
        messageTimerLength = 10;
        secondaryTimerLength = 50;

        try {
            image = Util.scaleImage(ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/objects/goldDoor.png"))), width, height);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
