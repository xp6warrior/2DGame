package object;

import main.Util;

import javax.imageio.ImageIO;
import java.io.IOException;
import java.util.Objects;

public class OBJ_BronzeKey extends OBJ {

    public OBJ_BronzeKey() {
        setup();
    }

    public OBJ_BronzeKey(int w, int h) {
        super(w, h);
        setup();
    }

    private void setup() {
        name = "BronzeKey";
        message1 = "Found a Bronze Key!";
        soundIndex = 1;
        messageTimerLength = 50;

        try {
            image = Util.scaleImage(ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/objects/bronzeKey.png"))), width, height);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
