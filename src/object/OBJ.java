package object;

import main.Util;

import java.awt.*;
import java.awt.image.BufferedImage;

public abstract class OBJ {
    public String name;
    public boolean collision = false;
    public Rectangle solidArea = new Rectangle(0, 0, Util.tileSize, Util.tileSize);
    public BufferedImage image;

    public String message3, message2, message1;
    public int soundIndex, messageTimerLength, secondaryTimerLength;

    protected int worldX, worldY, width, height;

    public OBJ() {
        width = Util.tileSize;
        height = Util.tileSize;
    }

    public OBJ(int w, int h) {
        width = w;
        height = h;
    }
}
