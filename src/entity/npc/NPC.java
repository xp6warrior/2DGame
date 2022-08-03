package entity.npc;

import entity.Entity;

import java.util.Random;

public class NPC extends Entity {
    private final Random AI = new Random();
    private int randomDirection = -1;
    private int AiTimer = 0;

    public String name;
    public String message;

    public void update() {
        // Step 1: Setting values
        collisionOn = false;
        solidArea.x = worldX;
        solidArea.y = worldY;

        // Step 2: Find AI direction
        if (AiTimer == 0) { // Start (waits 200 frames)
            AiTimer = 1;
        } else if (AiTimer == 170) { // Moves (50 frames)
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
        } else if (direction.equals("left") || direction.equals("right")) {
            spriteNumber = 2;
        }

    }
}
