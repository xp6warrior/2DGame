package object;

import main.GamePanel;
import main.Utility;

import javax.imageio.ImageIO;
import java.io.IOException;
import java.util.Objects;

public class OBJ_BronzeDoor extends OBJ {

    public OBJ_BronzeDoor(GamePanel gp) {
        super(gp);
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
        message2 = "[SPACE] to unlock";
        message1 = "Door Unlocked!";

        soundIndex = 2;
        messageTimerLength = 10;

        try {
            image = Utility.scaleImage(ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/objects/bronzeDoor.png"))), solidArea.width, solidArea.height);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
