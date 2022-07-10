package object;

import main.GamePanel;
import main.Utility;

import javax.imageio.ImageIO;
import java.io.IOException;
import java.util.Objects;

public class OBJ_GoldKey extends OBJ {

    public OBJ_GoldKey(GamePanel gp) {
        super(gp);
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
            image = Utility.scaleImage(ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/objects/goldKey.png"))), solidArea.width, solidArea.height);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
