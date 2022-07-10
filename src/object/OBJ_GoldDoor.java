package object;

import main.GamePanel;
import main.Utility;

import javax.imageio.ImageIO;
import java.io.IOException;
import java.util.Objects;

public class OBJ_GoldDoor extends OBJ {

    public OBJ_GoldDoor(GamePanel gp) {
        super(gp);
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
        message2 = "[SPACE] to unlock";
        message1 = "Door Unlocked!";

        soundIndex = 2;
        messageTimerLength = 10;

        try {
            image = Utility.scaleImage(ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/objects/goldDoor.png"))), solidArea.width, solidArea.height);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
