package object;

import javax.imageio.ImageIO;
import java.io.IOException;
import java.util.Objects;

public class OBJ_GoldDoor extends OBJ {

    public OBJ_GoldDoor() {
        name = "GoldDoor";
        collision = true;

        try {
            image = ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/objects/goldDoor.png")));
        } catch (IOException e) {
            e.printStackTrace();
        }

    }
}
