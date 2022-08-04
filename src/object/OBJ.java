package object;

import main.Util;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.Objects;

public abstract class OBJ {
    public String name;
    public boolean collision = false;
    public Rectangle solidArea = new Rectangle(0, 0, Util.tileSize, Util.tileSize);
    public BufferedImage image;

    public String message3, message2, message1;
    public int soundIndex, messageTimerLength;
    public boolean stationaryDebuff = false;

    protected int worldX, worldY;

    public static BufferedImage getObjectIcon(String name, int width, int height) {
        try {
            return Util.scaleImage(ImageIO.read(Objects.requireNonNull(OBJ.class.getResourceAsStream("/objects/"+name+".png"))), width, height);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }
}
