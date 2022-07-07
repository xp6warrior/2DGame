package object;

import main.GamePanel;

import javax.imageio.ImageIO;
import java.io.IOException;
import java.util.Objects;

public class OBJ_GoldDoor extends OBJ {

    public OBJ_GoldDoor(GamePanel gp, boolean inv) {
        super(gp, inv);

        name = "GoldDoor";
        collision = true;

        try {
            image = util.scaleImage(ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/objects/goldDoor.png"))), solidArea.width, solidArea.height);
        } catch (IOException e) {
            e.printStackTrace();
        }

    }
}
