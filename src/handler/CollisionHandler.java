package handler;

import entity.Entity;
import main.GamePanel;
import main.Util;

import java.awt.*;

public class CollisionHandler {
    private final GamePanel gp;
    private final Rectangle newEntityCollider = new Rectangle();

    public CollisionHandler(GamePanel gp) {
        this.gp = gp;
    }

    public void tileCollision(Entity entity) {
        // Step 1: Find the columns/rows on each side of entity
        int leftColumn = entity.solidArea.x / Util.tileSize;
        int rightColumn = (entity.solidArea.x + entity.solidArea.width) / Util.tileSize;
        int topRow = entity.solidArea.y / Util.tileSize;
        int bottomRow = (entity.solidArea.y + entity.solidArea.height) / Util.tileSize;

        int tile1 = 0, tile2 = 0;

        // Step 2: Check for tiles nearest to player given the players direction
        switch (entity.direction) {
            case "up":
                topRow = (entity.solidArea.y - entity.speed) / Util.tileSize;

                tile1 = gp.tileM.mapInfo[leftColumn][topRow];
                tile2 = gp.tileM.mapInfo[rightColumn][topRow];
                break;

            case "down":
                bottomRow = (entity.solidArea.y + entity.solidArea.height + entity.speed) / Util.tileSize;

                tile1 = gp.tileM.mapInfo[leftColumn][bottomRow];
                tile2 = gp.tileM.mapInfo[rightColumn][bottomRow];
                break;

            case "left":
                leftColumn = (entity.solidArea.x - entity.speed) / Util.tileSize;

                tile1 = gp.tileM.mapInfo[leftColumn][topRow];
                tile2 = gp.tileM.mapInfo[leftColumn][bottomRow];
                break;

            case "right":
                rightColumn = (entity.solidArea.x + entity.solidArea.width + entity.speed) / Util.tileSize;

                tile1 = gp.tileM.mapInfo[rightColumn][topRow];
                tile2 = gp.tileM.mapInfo[rightColumn][bottomRow];
                break;
        }

        // Step 3: Disable entity movement if nearby tiles have collision
        entity.collisionOn = gp.tileM.tileSet[tile1].collision || gp.tileM.tileSet[tile2].collision || entity.collisionOn;
    }

    public int objectCollision(Entity entity) {
        int index = 999;
        nonTileCollision(entity);

        // Step 2: Searches for which object player is colliding with, enables collision and returns object index
        for (int i = 0; i < gp.objHandler.objects.length; i++) {
            if (gp.objHandler.objects[i] != null && newEntityCollider.intersects(gp.objHandler.objects[i].solidArea)) {
                if (gp.objHandler.objects[i].collision) {
                    entity.collisionOn = true;
                }

                index = i;
            }
        }

        return index;
    }

    public int npcCollision(Entity entity) {
        int index = 999;
        nonTileCollision(entity);

        // Step 2: Searches for which npc player is colliding with, enables collision and returns npc index
        for (int i = 0; i < gp.npcHandler.NPCs.length; i++) {
            if (gp.npcHandler.NPCs[i] != null && newEntityCollider.intersects(gp.npcHandler.NPCs[i].solidArea)) {
                entity.collisionOn = true;
                index = i;
            }
        }

        return index;
    }

    public void npcPlayerCollision(Entity entity) {
        nonTileCollision(entity);

        if (newEntityCollider.intersects(gp.player.solidArea)) {
            entity.collisionOn = true;
        }
    }


    private void nonTileCollision(Entity entity) {
        // Step 1: Create a new solidArea, whose area enlarged in the player direction
        newEntityCollider.x = entity.solidArea.x;
        newEntityCollider.y = entity.solidArea.y;
        newEntityCollider.width = entity.solidArea.width;
        newEntityCollider.height = entity.solidArea.height;
        switch (entity.direction) {
            case "up": newEntityCollider.y -= entity.speed; break;
            case "down": newEntityCollider.y += entity.speed; break;
            case "left": newEntityCollider.x -= entity.speed; break;
            case "right": newEntityCollider.x += entity.speed; break;
        }
    }
}
