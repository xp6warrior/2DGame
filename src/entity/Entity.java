package entity;

import handler.CollisionHandler;
import main.Util;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.Objects;
import java.util.Random;

public class Entity {
    public int worldX, worldY;
    public int screenX, screenY;
    public int speed;
    public String direction;
    public Rectangle solidArea = new Rectangle();
    public boolean collisionOn;

    protected BufferedImage up1, up2, down1, down2, left1, left2, right1, right2;
    protected int spriteNumber;
    protected int spriteCounter;
    protected int defaultSpeed;
    protected CollisionHandler collisionH;

    private final Random AI = new Random();
    private int randomInt;
    private int AiTimer = 0;

    protected void setDefaultValues(int defaultSpeed) {
        this.defaultSpeed = defaultSpeed;
        speed = defaultSpeed;
        direction = "down";

        solidArea.x = 0;
        solidArea.y = 0;
        solidArea.width = Util.tileSize;
        solidArea.height = Util.tileSize;

        spriteNumber = 1;
        spriteCounter = 0;
    }
    protected void getPlayerImage(String entityName) {
        try {
            up1 = Util.scaleImage(ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/entities/"+entityName+"/up1.png"))), Util.tileSize, Util.tileSize);
            up2 = Util.scaleImage(ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/entities/"+entityName+"/up2.png"))), Util.tileSize, Util.tileSize);
            down1 = Util.scaleImage(ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/entities/"+entityName+"/down1.png"))), Util.tileSize, Util.tileSize);
            down2 = Util.scaleImage(ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/entities/"+entityName+"/down2.png"))), Util.tileSize, Util.tileSize);
            left1 = Util.scaleImage(ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/entities/"+entityName+"/left1.png"))), Util.tileSize, Util.tileSize);
            left2 = Util.scaleImage(ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/entities/"+entityName+"/left2.png"))), Util.tileSize, Util.tileSize);
            right1 = Util.scaleImage(ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/entities/"+entityName+"/right1.png"))), Util.tileSize, Util.tileSize);
            right2 = Util.scaleImage(ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/entities/"+entityName+"/right2.png"))), Util.tileSize, Util.tileSize);

        } catch (IOException e) {e.printStackTrace();}
    }


    public void update() {
        collisionOn = false;

        // Step 1: Find direction
        if (AiTimer == 0) {
            AiTimer = 1;
        } else if (AiTimer > 40) {
            AiTimer = 0;
            randomInt = AI.nextInt(4);
        } else if (AiTimer > 0) {
            AiTimer++;
        }

        switch (randomInt) {
            case 0: direction = "up"; break;
            case 1: direction = "down"; break;
            case 2: direction = "left"; break;
            case 3: direction = "right"; break;
        }

        // Step 2: Check for tile collision
        int remainingDistance = collisionH.tileCollision(this);
        if (remainingDistance != 0) {
            speed = remainingDistance;
        }


        // Step 5: Move player position
        if (!collisionOn) {
            switch (direction) {
                case "up": worldY -= speed; break;
                case "down": worldY += speed; break;
                case "left": worldX -= speed; break;
                case "right": worldX += speed; break;
            }
        }


        // Step 6: Animation
        spriteCounter++;
        if (spriteCounter > 20 - speed) {

            if (spriteNumber == 1) {
                spriteNumber = 2;

            } else if (spriteNumber == 2) {
                spriteNumber = 1;
            }

            spriteCounter = 0;
        }

        speed = defaultSpeed;
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
