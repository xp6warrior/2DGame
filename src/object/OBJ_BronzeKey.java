package object;

import main.GamePanel;
import main.Utility;

import javax.imageio.ImageIO;
import java.io.IOException;
import java.util.Objects;

public class OBJ_BronzeKey extends OBJ {

    public OBJ_BronzeKey(GamePanel gp) {
        super(gp);
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
            image = Utility.scaleImage(ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/objects/bronzeKey.png"))), solidArea.width, solidArea.height);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
