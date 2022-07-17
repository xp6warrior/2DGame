package object;

import main.Util;

import javax.imageio.ImageIO;
import java.io.IOException;
import java.util.Objects;

public class OBJ_Sign extends OBJ {

    public OBJ_Sign() {
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
            image = Util.scaleImage(ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/objects/sign.png"))), width, height);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
