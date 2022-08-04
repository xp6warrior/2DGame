package entity.npc;

import entity.Entity;
import main.Util;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.Objects;
import java.util.Random;

public abstract class NPC extends Entity {
    public String name;
    public String message2 = "[SPACE] to talk";
    public String message1;
    public int messageTimerLength;
    public boolean stationaryDebuff = false;

    private final Random AI = new Random();
    private int randomDirection = -1;
    private int AiTimer = 0;

    public NPC(String imageDirName) {
        super("npcs/"+ imageDirName);
    }

    public static BufferedImage getNPCIcon(String name, int width, int height) {
        try {
            return Util.scaleImage(ImageIO.read(Objects.requireNonNull(NPC.class.getClassLoader().getResourceAsStream("entities/npcs/"+name+"/down1.png"))), width, height);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    public void update() {
        collisionOn = false;
        solidArea.x = worldX;
        solidArea.y = worldY;

        // 2. Find AI direction
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

        // 3. Collision
        collisionH.tileCollision(this);
        collisionH.npcPlayerCollision(this);

        // 4. Move NPC position
        if (!collisionOn && randomDirection != -1) {
            switch (direction) {
                case "up": worldY -= speed; break;
                case "down": worldY += speed; break;
                case "left": worldX -= speed; break;
                case "right": worldX += speed; break;
            }

            // 5. Animation
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
