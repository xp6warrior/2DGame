package entity;

import java.awt.*;
import java.awt.image.BufferedImage;

public class Entity {
    public int worldX, worldY;
    public int screenX, screenY;
    public int speed;
    public String direction;

    public Rectangle solidArea;
    public int defaultSolidAreaX, defaultSolidAreaY;
    public boolean collisionOn;

    protected BufferedImage up1, up2, down1, down2, left1, left2, right1, right2;
    protected int spriteNumber;
    protected int spriteCounter;
}
