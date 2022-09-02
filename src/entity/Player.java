package entity;

import entity.npc.NPC;
import handler.KeyHandler;
import main.GamePanel;
import main.Util;
import object.OBJ;

public class Player extends Entity {
    private final GamePanel gp;
    private final KeyHandler keyH;

    // Inventory
    public boolean goldKey;
    public boolean bronzeKey;

    public Player(GamePanel gp) {
        super("player");

        this.gp = gp;
        this.keyH = gp.keyH;
        this.collisionH = gp.collisionH;

        // 1. Finds constant screen value (middle of screen)
        screenX = Util.windowX / 2 - (Util.tileSize / 2);
        screenY = Util.windowY / 2 - (Util.tileSize / 2);

        // 2. Overrides to default values
        speed = 5;
        solidAreaXOffset = 4 * Util.scale;
        solidAreaYOffset = 6 * Util.scale;
        solidArea.width = Util.tileSize - (solidAreaXOffset * 2);
        solidArea.height = Util.tileSize - solidAreaYOffset;

        goldKey = false;
        bronzeKey = false;
    }


    public void update() {
        collisionOn = false;
        solidArea.x = worldX + solidAreaXOffset;
        solidArea.y = worldY + solidAreaYOffset;

        if (keyH.up || keyH.down || keyH.left || keyH.right) {
            // 1. Find direction
            if (keyH.up) {
                direction = "up";
            } else if (keyH.down) {
                direction = "down";
            } else if (keyH.left) {
                direction = "left";
            } else {
                direction = "right";
            }

            // 2. Check for tile collision
            collisionH.tileCollision(this);
        }

        // 3. Object and NPC collision
        int objectIndex = collisionH.objectCollision(this);
        int npcIndex = collisionH.npcCollision(this);
        if (objectIndex == 999 && keyH.interact && npcIndex == 999) {
            keyH.interact = false;
        }

        TouchObject(objectIndex);
        TouchNPC(npcIndex);

        if (keyH.up || keyH.down || keyH.left || keyH.right) {
            // 5. Move player position
            if (!collisionOn) {
                switch (direction) {
                    case "up": worldY -= speed; break;
                    case "down": worldY += speed; break;
                    case "left": worldX -= speed; break;
                    case "right": worldX += speed; break;
                }
            }

            // 6. Animation
            spriteCounter++;
            if (spriteCounter > 20 - speed) {
                if (spriteNumber == 1) {
                    spriteNumber = 2;

                } else if (spriteNumber == 2) {
                    spriteNumber = 1;
                }
                spriteCounter = 0;
            }
        } else {
            spriteNumber = 2;
        }
    }


    private void TouchObject(int index) {
        if (index != 999) {
            OBJ object = gp.objHandler.objects[index];
            int messageIndex = 0;

            if (!gp.ui.displayTouchingObject) { object.stationaryDebuff = false; }

            switch (object.name) {
                case "goldKey": goldKey = true; messageIndex = 1; gp.objHandler.objects[index] = null; break;
                case "bronzeKey": bronzeKey = true; messageIndex = 1; gp.objHandler.objects[index] = null; break;

                case "goldDoor":
                    if (goldKey) {
                        if (keyH.interact) {
                            goldKey = false;
                            gp.objHandler.objects[index] = null;
                            messageIndex = 1;
                        } else { messageIndex = 2; }
                    } else { messageIndex = 3; }
                    break;

                case "bronzeDoor":
                    if (bronzeKey) {
                        if (keyH.interact) {
                            bronzeKey = false;
                            gp.objHandler.objects[index] = null;
                            messageIndex = 1;
                        } else { messageIndex = 2; }
                    } else { messageIndex = 3; }
                    break;

                case "stairs":
                    if (keyH.interact) {
                        gp.tileM.switchLevel("dungeon");
                        messageIndex = 1;
                    } else { messageIndex = 2; }
                    break;

                case "sign":
                    if (!object.stationaryDebuff) {
                        if (keyH.interact) {
                            keyH.interact = false;
                            object.stationaryDebuff = true;
                            messageIndex = 1;
                        } else { messageIndex = 2; }
                    }
                    break;
            }

            gp.ui.setTouching(object, messageIndex);
            gp.ui.displayTouchingObject = true;

            if (messageIndex == 1) {
                gp.soundEffects.setFile(object.soundIndex);
                gp.soundEffects.play();
            }

        }
    }

    private void TouchNPC(int index) {
        if (index != 999) {
            NPC npc = gp.npcHandler.NPCs[index];
            int messageIndex = 0;

            if (!gp.ui.displayTouchingObject) { npc.stationaryDebuff = false; }

            switch (npc.name) {
                case "roy":
                    if (!npc.stationaryDebuff) {
                        if (keyH.interact) {
                            keyH.interact = false;
                            npc.stationaryDebuff = true;
                            messageIndex = 1;
                        } else { messageIndex = 2; }
                    }
                    break;
            }

            gp.ui.setTouching(npc, messageIndex);
            gp.ui.displayTouchingObject = true;

        }
    }
}
