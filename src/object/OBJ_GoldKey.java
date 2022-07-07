package object;

import main.GamePanel;

import javax.imageio.ImageIO;
import java.io.IOException;
import java.util.Objects;

public class OBJ_GoldKey extends OBJ {

    public OBJ_GoldKey(GamePanel gp, boolean inv) {
        super(gp, inv);

        name = "GoldKey";

        try {
            image = util.scaleImage(ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/objects/goldKey.png"))), solidArea.width, solidArea.height);
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

}
