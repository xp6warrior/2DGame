package entity;

import java.awt.*;
import java.awt.image.BufferedImage;

public class Entity {
    public int worldX, worldY;
    public int screenX, screenY;
    public int speed;
    public String direction;

    public Rectangle objectSolidArea = new Rectangle();
    public int tileSolidAreaX, tileSolidAreaY;
    public boolean collisionOn;

    protected BufferedImage up1, up2, down1, down2, left1, left2, right1, right2;
    protected int spriteNumber;
    protected int spriteCounter;
    protected int defaultSpeed;
}
