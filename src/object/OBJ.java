package object;

import main.GamePanel;
import main.Utility;

import java.awt.*;
import java.awt.image.BufferedImage;

public class OBJ {
    public String name;
    public boolean collision = false;
    public Rectangle solidArea = new Rectangle();
    public BufferedImage image;
    public int worldX, worldY;

    protected Utility util;

    public OBJ(GamePanel gp, boolean inv) {
        if (inv) {
            solidArea.width = 100;
            solidArea.height = 100;
        } else {
            solidArea.width = gp.tileSize;
            solidArea.height = gp.tileSize;
        }

        util = new Utility(gp);
    }
}
