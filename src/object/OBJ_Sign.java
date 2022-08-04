package object;

import main.Util;

import javax.imageio.ImageIO;
import java.io.IOException;
import java.util.Objects;

public class OBJ_Sign extends OBJ {
    public static String[] writings = {"Welcome to Tanzania!", "Roy's House"};

    public OBJ_Sign() {
        name = "sign";
        collision = true;

        message2 = "[SPACE] to read";
        messageTimerLength = 80;

        soundIndex = 3;

        image = getObjectIcon(name, Util.tileSize, Util.tileSize);
    }
}
