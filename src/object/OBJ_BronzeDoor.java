package object;

import main.GamePanel;

import javax.imageio.ImageIO;
import java.io.IOException;
import java.util.Objects;

public class OBJ_BronzeDoor extends OBJ {

    public OBJ_BronzeDoor(GamePanel gp, boolean inv) {
        super(gp, inv);

        name = "BronzeDoor";
        collision = true;

        try {
            image = util.scaleImage(ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/objects/bronzeDoor.png"))), solidArea.width, solidArea.height);
        } catch (IOException e) {
            e.printStackTrace();
        }

    }
}
