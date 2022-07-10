package object;

import main.GamePanel;

import java.awt.*;
import java.awt.image.BufferedImage;

public class OBJ {
    public String name;
    public boolean collision = false;
    public Rectangle solidArea = new Rectangle();
    public BufferedImage image;
    public int worldX, worldY;

    public String message3;
    public String message2;
    public String message1;

    public int soundIndex;
    public int messageTimerLength;
    public int secondaryTimerLength;

    public OBJ(GamePanel gp) {
        solidArea.width = gp.tileSize;
        solidArea.height = gp.tileSize;
    }

    public OBJ(int w, int h) {
        solidArea.width = w;
        solidArea.height = h;
    }
}
