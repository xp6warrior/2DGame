package entity;

import handler.CollisionHandler;
import handler.KeyHandler;
import handler.MapHandler;
import main.GamePanel;
import main.Utility;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.Objects;

public class Player extends Entity {
    private final GamePanel gp;
    private final KeyHandler keyH;
    private final MapHandler mapH;
    private final CollisionHandler collisionH;

    public boolean goldKey;
    public boolean bronzeKey;

    public Player(GamePanel gp) {
        this.gp = gp;
        keyH = gp.keyH;
        mapH = gp.mapH;
        collisionH = gp.collisionH;

        setDefaultValues();
        getPlayerImage();
    }
    private void setDefaultValues() {
        worldX = gp.tileSize * mapH.startColumn;
        worldY = gp.tileSize * mapH.startRow;
        screenX = gp.windowX / 2 - (gp.tileSize / 2);
        screenY = gp.windowY / 2 - (gp.tileSize / 2);

        speed = 5;
        direction = "down";

        tileSolidAreaX = 16;
        tileSolidAreaY = 32;

        objectSolidArea.x = tileSolidAreaX;
        objectSolidArea.y = tileSolidAreaY;
        objectSolidArea.width = 32;
        objectSolidArea.height = 32;

        spriteNumber = 1;
        spriteCounter = 0;

        goldKey = false;
        bronzeKey = false;
    }
    private void getPlayerImage() {
        try {
            Utility util = new Utility(gp);

            up1 = util.scaleImage(ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/player/up1.png"))), gp.tileSize, gp.tileSize);
            up2 = util.scaleImage(ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/player/up2.png"))), gp.tileSize, gp.tileSize);
            down1 = util.scaleImage(ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/player/down1.png"))), gp.tileSize, gp.tileSize);
            down2 = util.scaleImage(ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/player/down2.png"))), gp.tileSize, gp.tileSize);
            left1 = util.scaleImage(ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/player/left1.png"))), gp.tileSize, gp.tileSize);
            left2 = util.scaleImage(ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/player/left2.png"))), gp.tileSize, gp.tileSize);
            right1 = util.scaleImage(ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/player/right1.png"))), gp.tileSize, gp.tileSize);
            right2 = util.scaleImage(ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/player/right2.png"))), gp.tileSize, gp.tileSize);

        } catch (IOException e) {e.printStackTrace();}
    }


    private void PickUpObject(int index) {
        if (index != 999) {

            switch (gp.objHandler.objects[index].name) {
                case "GoldKey":
                    gp.objHandler.objects[index] = null;
                    goldKey = true;
                    gp.ui.showMessage("Found a Golden Key!");
                    gp.soundEffects.setFile(1);
                    gp.soundEffects.play();

                    break;
                case "GoldDoor":
                    if (goldKey) {
                        gp.objHandler.objects[index] = null;
                        goldKey = false;
                        gp.ui.showMessage("Door Unlocked!");
                        gp.soundEffects.setFile(2);
                        gp.soundEffects.play();
                    }

                    break;
                case "BronzeKey":
                    gp.objHandler.objects[index] = null;
                    bronzeKey = true;
                    gp.ui.showMessage("Found a Bronze Key!");
                    gp.soundEffects.setFile(1);
                    gp.soundEffects.play();

                    break;
                case "BronzeDoor":
                    if (bronzeKey) {
                        gp.objHandler.objects[index] = null;
                        bronzeKey = false;
                        gp.ui.showMessage("Door Unlocked!");
                        gp.soundEffects.setFile(2);
                        gp.soundEffects.play();
                    }

                    break;
                case "Sign":
                    gp.ui.showMessage("Welcome to Tanzania!");
            }

        }
    }

    public void update() {

        if (keyH.up || keyH.down || keyH.left || keyH.right) {

            // Step 1: Find direction
            if (keyH.up) {
                direction = "up";
            } else if (keyH.down) {
                direction = "down";
            } else if (keyH.left) {
                direction = "left";
            } else {
                direction = "right";
            }

            // Step 2: Check for collision
            collisionOn = false;
            collisionH.tileCollision(this);
            int objectIndex = collisionH.objectCollision(this);
            PickUpObject(objectIndex);

            // Step 3: Move player position
            if (!collisionOn) {
                switch (direction) {
                    case "up": worldY -= speed; break;
                    case "down": worldY += speed; break;
                    case "left": worldX -= speed; break;
                    case "right": worldX += speed; break;
                }
            }

            // Step 4: Animation
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
