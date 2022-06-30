package object;

import javax.imageio.ImageIO;
import java.io.IOException;
import java.util.Objects;

public class OBJ_GoldKey extends OBJ {

    public OBJ_GoldKey() {
        name = "GoldKey";

        try {
            image = ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/objects/goldKey.png")));
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

}
