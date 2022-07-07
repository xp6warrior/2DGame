package object;

import main.GamePanel;

import javax.imageio.ImageIO;
import java.io.IOException;
import java.util.Objects;

public class OBJ_BronzeKey extends OBJ {

    public OBJ_BronzeKey(GamePanel gp, boolean inv) {
        super(gp, inv);

        name = "BronzeKey";

        try {
            image = util.scaleImage(ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/objects/bronzeKey.png"))), solidArea.width, solidArea.height);
        } catch (IOException e) {
            e.printStackTrace();
        }

    }
}
