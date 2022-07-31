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

    protected BufferedImage up1, up2, down1, down2, left1, left2, right1, right2;
    protected int spriteNumber;
    protected int spriteCounter;
    protected CollisionHandler collisionH;
    protected int solidAreaXOffset;
    protected int solidAreaYOffset;

    private final Random AI = new Random();
    private int randomDirection = -1;
    private int AiTimer = 0;

    protected void setDefaultValues() {
        speed = 1;
        direction = "down";

        solidAreaXOffset = Util.scale;
        solidAreaYOffset = Util.scale;
        solidArea.width = Util.tileSize - (solidAreaXOffset * 2);
        solidArea.height = Util.tileSize - solidAreaYOffset;

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
        // Step 1: Setting values
        collisionOn = false;
        solidArea.x = worldX;
        solidArea.y = worldY;

        // Step 2: Find AI direction
        if (AiTimer == 0) { // Start (waits 200 frames)
            AiTimer = 1;
        } else if (AiTimer == 200) { // Moves (50 frames)
            randomDirection = AI.nextInt(3);
        } else if (AiTimer > 250) { // Stops and resets
            AiTimer = 0;
            randomDirection = -1;
        }
        if (AiTimer > 0) { // Counter
            AiTimer++;
        }
        switch (randomDirection) {
            case 0: direction = "up"; break;
            case 1: direction = "down"; break;
            case 2: direction = "left"; break;
            case 3: direction = "right"; break;
        }

        // Step 3: Collision
        collisionH.tileCollision(this);
        collisionH.npcPlayerCollision(this);

        // Step 4: Move player position
        if (!collisionOn && randomDirection != -1) {
            switch (direction) {
                case "up": worldY -= speed; break;
                case "down": worldY += speed; break;
                case "left": worldX -= speed; break;
                case "right": worldX += speed; break;
            }

            // Step 5: Animation
            if (spriteCounter > 20 - speed) {
                if (spriteNumber == 1) {
                    spriteNumber = 2;

                } else if (spriteNumber == 2) {
                    spriteNumber = 1;
                }
                spriteCounter = 0;
            }
            spriteCounter++;
        } else {
            spriteNumber = 2;
        }


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
