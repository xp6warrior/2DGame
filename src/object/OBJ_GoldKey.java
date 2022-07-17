package object;

import main.Util;

import javax.imageio.ImageIO;
import java.io.IOException;
import java.util.Objects;

public class OBJ_GoldKey extends OBJ {

    public OBJ_GoldKey() {
        setup();
    }

    public OBJ_GoldKey(int w, int h) {
        super(w, h);
        setup();
    }

    private void setup() {
        name = "GoldKey";
        message1 = "Found a Golden Key!";
        soundIndex = 1;
        messageTimerLength = 50;

        try {
            image = Util.scaleImage(ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/objects/goldKey.png"))), width, height);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
