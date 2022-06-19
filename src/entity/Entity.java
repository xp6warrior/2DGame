package entity;

import java.awt.image.BufferedImage;

public class Entity {
    public int worldX, worldY;
    public int screenX, screenY;
    protected int speed;

    protected String direction;
    protected BufferedImage up1, up2, down1, down2, left1, left2, right1, right2;

    protected int spriteNumber;
    protected int spriteCounter;
}
