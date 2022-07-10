package object;

import main.GamePanel;
import main.Utility;

import javax.imageio.ImageIO;
import java.io.IOException;
import java.util.Objects;

public class OBJ_Sign extends OBJ {

    public OBJ_Sign(GamePanel gp) {
        super(gp);
        setup();
    }

    public OBJ_Sign(int w, int h) {
        super(w, h);
        setup();
    }

    private void setup() {
        name = "Sign";
        collision = true;

        message1 = "Welcome to Tanzania!";
        message2 = "[SPACE] to read";

        messageTimerLength = 10;
        secondaryTimerLength = 80;
        soundIndex = 3;

        try {
            image = Utility.scaleImage(ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/objects/sign.png"))), solidArea.width, solidArea.height);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
