package object;

import main.GamePanel;

import javax.imageio.ImageIO;
import java.io.IOException;
import java.util.Objects;

public class OBJ_Sign extends OBJ {

    public OBJ_Sign(GamePanel gp, boolean inv) {
        super(gp, inv);

        name = "Sign";
        collision = true;

        try {
            image = util.scaleImage(ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/objects/sign.png"))), solidArea.width, solidArea.height);
        } catch (IOException e) {
            e.printStackTrace();
        }

    }
}
