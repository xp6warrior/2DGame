package object;

import main.GamePanel;
import main.Utility;

import javax.imageio.ImageIO;
import java.io.IOException;
import java.util.Objects;

public class OBJ_Stairs extends OBJ {

    public OBJ_Stairs(GamePanel gp) {
        super(gp);
        setup();
    }

    public OBJ_Stairs(int w, int h) {
        super(w, h);
        setup();
    }

    private void setup() {
        name = "Stairs";

        message1 = "";
        message2 = "Roy's basement...";

        messageTimerLength = 10;

        try {
            image = Utility.scaleImage(ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/objects/stairs.png"))), solidArea.width, solidArea.height);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
