package object;

import main.Util;

import javax.imageio.ImageIO;
import java.io.IOException;
import java.util.Objects;

public class OBJ_Stairs extends OBJ {

    public OBJ_Stairs() {
        setup();
    }

    public OBJ_Stairs(int w, int h) {
        super(w, h);
        setup();
    }

    private void setup() {
        name = "Stairs";

        message1 = "";
        message2 = "Roy's Basement...";

        soundIndex = 2;
        messageTimerLength = 10;
        secondaryTimerLength = 0;

        try {
            image = Util.scaleImage(ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/objects/stairs.png"))), width, height);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
