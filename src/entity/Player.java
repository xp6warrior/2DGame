package entity;

import handler.KeyHandler;
import main.GamePanel;
import main.Util;
import object.OBJ;

public class Player extends Entity {
    private final GamePanel gp;
    private final KeyHandler keyH;

    public boolean goldKey;
    public boolean bronzeKey;

    public int interactMessageTimer = 0;
    private boolean timerOn = false;

    public Player(GamePanel gp) {
        this.gp = gp;
        this.keyH = gp.keyH;
        this.collisionH = gp.collisionH;

        screenX = Util.windowX / 2 - (Util.tileSize / 2);
        screenY = Util.windowY / 2 - (Util.tileSize / 2);
        goldKey = false;
        bronzeKey = false;

        setDefaultValues(5);
        getPlayerImage("player");

        solidArea.x = 4 * Util.scale;
        solidArea.y = 6 * Util.scale;
        solidArea.width = Util.tileSize - (solidArea.x * 2);
        solidArea.height = Util.tileSize - solidArea.y;
    }


    @Override
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

            // Step 2: Check for tile collision and NPC collision
            int remainingDistance = collisionH.tileCollision(this);
            if (remainingDistance != 0) {
                speed = remainingDistance;
            }

            collisionH.npcCollision(this);
        }

        // Step 3: Check for object collision (outside of if so that it's running all the time) and fixes bugs
        int objectIndex = collisionH.objectCollision(this);
        TouchObject(objectIndex);
        if (keyH.interact && objectIndex == 999 || interactMessageTimer > 0) { // Fixes "delayed interaction". Fixes "double-spacing objects"
            keyH.interact = false;
        }
        if (objectIndex == 999) { // Resets timer when not colliding with object
            timerOn = false;
            interactMessageTimer = 0;
        }

        // Step 4: Object interact timer
        if (timerOn) {
            interactMessageTimer++;
        }

        if (keyH.up || keyH.down || keyH.left || keyH.right) {
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
        }

        speed = defaultSpeed;
    }


    private void TouchObject(int index) {
        if (index != 999) {
            OBJ object = gp.objHandler.objects[index];
            int message = 0; // Prevents sound effect playing many times stationary objects (sign)
            int messageLength = object.messageTimerLength;

            switch (object.name) {
                case "GoldKey": goldKey = true; message = 1; gp.objHandler.objects[index] = null; break;
                case "BronzeKey": bronzeKey = true; message = 1; gp.objHandler.objects[index] = null; break;

                case "Stairs":
                    if (keyH.interact) {
                        gp.tileM.switchLevel("alpha");
                        message = 1;
                        messageLength = object.secondaryTimerLength;
                    } else {message = 2;}
                    break;

                case "GoldDoor":
                    if (goldKey) {
                        if (keyH.interact) {
                            goldKey = false;
                            message = 1;
                            messageLength = object.secondaryTimerLength;
                            gp.objHandler.objects[index] = null;
                        } else {message = 2;}
                    } else {message = 3;}
                    break;

                case "BronzeDoor":
                    if (bronzeKey) {
                        if (keyH.interact) {
                            bronzeKey = false;
                            message = 1;
                            messageLength = object.secondaryTimerLength;
                            gp.objHandler.objects[index] = null;
                        } else {message = 2;}
                    } else {message = 3;}
                    break;

                case "Sign":
                    if (keyH.interact && interactMessageTimer == 0) {
                        keyH.interact = false;
                        timerOn = true;
                        message = 1;
                        messageLength = object.secondaryTimerLength;
                    } else if (interactMessageTimer > object.secondaryTimerLength || interactMessageTimer == 0) {
                        timerOn = false;
                        interactMessageTimer = 0;
                        message = 2;
                    }
                    break;
            }

            gp.ui.showMessage(object, message, messageLength);
            if (message == 1) {
                gp.soundEffects.setFile(object.soundIndex);
                gp.soundEffects.play();
            }

        }
    }
}
