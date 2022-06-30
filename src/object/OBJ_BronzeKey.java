package object;

import javax.imageio.ImageIO;
import java.io.IOException;
import java.util.Objects;

public class OBJ_BronzeKey extends OBJ {

    public OBJ_BronzeKey() {
        name = "BronzeKey";

        try {
            image = ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/objects/bronzeKey.png")));
        } catch (IOException e) {
            e.printStackTrace();
        }

    }
}
