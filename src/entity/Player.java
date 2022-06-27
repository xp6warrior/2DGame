package entity;

import handler.GamePanel;
import handler.KeyHandler;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.Objects;

public class Player extends Entity {
    GamePanel gp;
    KeyHandler keyH;

    public Player(GamePanel gp) {
        this.gp = gp;
        keyH = gp.keyH;

        setDefaultValues();
        getPlayerImage();
    }
    private void setDefaultValues() {
        worldX = gp.tileSize * gp.map.startColumn;
        worldY = gp.tileSize * gp.map.startRow;
        screenX = gp.windowX / 2 - (gp.tileSize / 2);
        screenY = gp.windowY / 2 - (gp.tileSize / 2);

        speed = 5;
        direction = "down";

        solidArea = new Rectangle();
        solidArea.x = 16;
        solidArea.y = 32;
        solidArea.width = 32;
        solidArea.height = 32;

        spriteNumber = 1;
        spriteCounter = 0;
    }
    private void getPlayerImage() {
        try {
            up1 = ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/player/up1.png")));
            up2 = ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/player/up2.png")));
            down1 = ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/player/down1.png")));
            down2 = ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/player/down2.png")));
            left1 = ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/player/left1.png")));
            left2 = ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/player/left2.png")));
            right1 = ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/player/right1.png")));
            right2 = ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/player/right2.png")));

        } catch (IOException e) {e.printStackTrace();}
    }

    public void update() {
        if (keyH.up || keyH.down || keyH.left || keyH.right) {
            // Direction
            if (keyH.up) {
                direction = "up";
            } else if (keyH.down) {
                direction = "down";
            } else if (keyH.left) {
                direction = "left";
            } else {
                direction = "right";
            }

            // Collision
            collisionOn = false;
            gp.collisionH.checkCollision(this);

            // Movement
            if (!collisionOn) {
                switch (direction) {
                    case "up": worldY -= speed; break;
                    case "down": worldY += speed; break;
                    case "left": worldX -= speed; break;
                    case "right": worldX += speed; break;
                }
            }

            // Animation
            spriteCounter++;
            if (spriteCounter > 20 - speed) {

                if (spriteNumber == 1) {
                    spriteNumber = 2;
                } else if (spriteNumber == 2) {
                    spriteNumber = 1;
                }

                spriteCounter = 0;
            }
        }
    }

    public void paint(Graphics2D g2d) {
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

        g2d.drawImage(img, screenX, screenY, gp.tileSize, gp.tileSize, null);
    }
}
