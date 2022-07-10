package entity;

import handler.CollisionHandler;
import handler.KeyHandler;
import handler.MapHandler;
import main.GamePanel;
import main.Utility;
import object.OBJ;

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

    public int interactMsgTimer = 0;
    private boolean timerOn = false;

    public Player(GamePanel gp) {
        this.gp = gp;
        keyH = gp.keyH;
        mapH = gp.tileM.mapH;
        collisionH = gp.collisionH;

        setDefaultValues();
        getPlayerImage();
    }
    private void setDefaultValues() {
        screenX = gp.windowX / 2 - (gp.tileSize / 2);
        screenY = gp.windowY / 2 - (gp.tileSize / 2);

        defaultSpeed = 5;
        speed = defaultSpeed;
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
            up1 = Utility.scaleImage(ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/player/up1.png"))), gp.tileSize, gp.tileSize);
            up2 = Utility.scaleImage(ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/player/up2.png"))), gp.tileSize, gp.tileSize);
            down1 = Utility.scaleImage(ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/player/down1.png"))), gp.tileSize, gp.tileSize);
            down2 = Utility.scaleImage(ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/player/down2.png"))), gp.tileSize, gp.tileSize);
            left1 = Utility.scaleImage(ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/player/left1.png"))), gp.tileSize, gp.tileSize);
            left2 = Utility.scaleImage(ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/player/left2.png"))), gp.tileSize, gp.tileSize);
            right1 = Utility.scaleImage(ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/player/right1.png"))), gp.tileSize, gp.tileSize);
            right2 = Utility.scaleImage(ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/player/right2.png"))), gp.tileSize, gp.tileSize);

        } catch (IOException e) {e.printStackTrace();}
    }


    private void TouchObject(int index) {
        if (index != 999) {
            OBJ object = gp.objHandler.objects[index];
            int message = 0;

            switch (object.name) {
                case "GoldKey": goldKey = true; message = 1; gp.objHandler.objects[index] = null; break;
                case "BronzeKey": bronzeKey = true; message = 1; gp.objHandler.objects[index] = null; break;

                case "Stairs":
                    if (keyH.interact) {
                        gp.tileM.switchLevel("alpha"); message = 1;

                    } else {message = 2;}
                    break;

                case "GoldDoor":
                    if (goldKey) {
                        if (keyH.interact) {
                            goldKey = false;
                            gp.objHandler.objects[index] = null;
                            message = 1;

                        } else {message = 2;}

                    } else {message = 3;}
                    break;

                case "BronzeDoor":
                    if (bronzeKey) {
                        if (keyH.interact) {
                            bronzeKey = false;
                            gp.objHandler.objects[index] = null;
                            message = 1;
                        } else {message = 2;}

                    } else {message = 3;}
                    break;

                case "Sign":
                    if (keyH.interact && interactMsgTimer == 0) {
                        keyH.interact = false; timerOn = true; message = 1;
                    } else if (interactMsgTimer > object.secondaryTimerLength || interactMsgTimer == 0) {
                        timerOn = false;
                        interactMsgTimer = 0;
                        message = 2;
                    }
                    break;
            }

            gp.ui.showMessage(object, message);

            if (message == 1) {
                gp.soundEffects.setFile(object.soundIndex);
                gp.soundEffects.play();
            }

        }
    }


    public void update() {
        collisionOn = false;

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

            // Step 2: Check for tile collision
            collisionH.tileCollision(this);
        }


        // Step 3: Check for object collision (outside of if so that it's running all the time) and fixes bugs
        int objectIndex = collisionH.objectCollision(this);

        if (keyH.interact && objectIndex == 999 || interactMsgTimer > 0) { // Fixes "delayed interaction". Fixes "double-spacing objects"
            keyH.interact = false;
        }
        if (objectIndex == 999) { // Resets timer when not colliding with object
            timerOn = false;
            interactMsgTimer = 0;
        }
        TouchObject(objectIndex);



        if (keyH.up || keyH.down || keyH.left || keyH.right) {

            // Step 4: Move player position
            if (!collisionOn) {
                switch (direction) {
                    case "up": worldY -= speed; break;
                    case "down": worldY += speed; break;
                    case "left": worldX -= speed; break;
                    case "right": worldX += speed; break;
                }
            }

            // Step 5: Animation
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

        // Step 6: Object interact timer
        if (timerOn) {
            interactMsgTimer++;
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
        // Debug
        if (keyH.debug) {
            g2d.setColor(Color.red);
            g2d.drawRect(objectSolidArea.x + screenX, objectSolidArea.y + screenY, objectSolidArea.width, objectSolidArea.height);
        } else {
            speed = defaultSpeed;
        }
    }
}
