package entity;

import handler.CollisionHandler;
import main.Util;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.Objects;
import java.util.Random;

public abstract class Entity {
    public int worldX, worldY;
    public int screenX, screenY;
    public int speed;
    public String direction;
    public Rectangle solidArea = new Rectangle();
    public boolean collisionOn;

    public BufferedImage up1, up2, down1, down2, left1, left2, right1, right2;
    protected int spriteNumber;
    protected int spriteCounter;
    protected CollisionHandler collisionH;
    protected int solidAreaXOffset;
    protected int solidAreaYOffset;

    protected void setDefaultValues() {
        speed = 2;
        direction = "down";

        solidAreaXOffset = Util.scale;
        solidAreaYOffset = Util.scale;
        solidArea.width = Util.tileSize - (solidAreaXOffset * 2);
        solidArea.height = Util.tileSize - solidAreaYOffset;

        spriteNumber = 1;
        spriteCounter = 0;
    }
    protected void getPlayerImage(String path) {
        try {
            up1 = Util.scaleImage(ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/entities/"+path+"/up1.png"))), Util.tileSize, Util.tileSize);
            up2 = Util.scaleImage(ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/entities/"+path+"/up2.png"))), Util.tileSize, Util.tileSize);
            down1 = Util.scaleImage(ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/entities/"+path+"/down1.png"))), Util.tileSize, Util.tileSize);
            down2 = Util.scaleImage(ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/entities/"+path+"/down2.png"))), Util.tileSize, Util.tileSize);
            left1 = Util.scaleImage(ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/entities/"+path+"/left1.png"))), Util.tileSize, Util.tileSize);
            left2 = Util.scaleImage(ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/entities/"+path+"/left2.png"))), Util.tileSize, Util.tileSize);
            right1 = Util.scaleImage(ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/entities/"+path+"/right1.png"))), Util.tileSize, Util.tileSize);
            right2 = Util.scaleImage(ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/entities/"+path+"/right2.png"))), Util.tileSize, Util.tileSize);

        } catch (IOException e) {e.printStackTrace();}
    }

    public void draw(Graphics2D g2d) {
        BufferedImage img = null;
        switch (direction) {
            case "up":
                if (spriteNumber == 1) {
                    img = up1;
                } else {
                    img = up2;
                }
                break;
            case "down":
                if (spriteNumber == 1) {
                    img = down1;
                } else {
                    img = down2;
                }
                break;
            case "left":
                if (spriteNumber == 1) {
                    img = left1;
                } else {
                    img = left2;
                }
                break;
            case "right":
                if (spriteNumber == 1) {
                    img = right1;
                } else {
                    img = right2;
                }
                break;
        }
        g2d.drawImage(img, screenX, screenY, null);
    }
}
