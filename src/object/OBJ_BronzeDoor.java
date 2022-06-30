package object;

import javax.imageio.ImageIO;
import java.io.IOException;
import java.util.Objects;

public class OBJ_BronzeDoor extends OBJ {

    public OBJ_BronzeDoor() {
        name = "BronzeDoor";
        collision = true;

        try {
            image = ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/objects/bronzeDoor.png")));
        } catch (IOException e) {
            e.printStackTrace();
        }

    }
}
