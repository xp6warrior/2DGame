package handler;

import entity.Entity;
import main.GamePanel;
import main.Util;

import java.awt.*;

public class CollisionHandler {
    private final GamePanel gp;
    private final Rectangle objectCollider = new Rectangle();

    public CollisionHandler(GamePanel gp) {
        this.gp = gp;
    }

    public int tileCollision(Entity entity) {
        int remainingDistance = 0;

        // Step 1: Find coordinates of each edge of entity
        int leftX = entity.worldX + entity.solidArea.x;
        int rightX = entity.worldX + entity.solidArea.x + entity.solidArea.width;
        int topY = entity.worldY + entity.solidArea.y;
        int bottomY = entity.worldY + entity.solidArea.y + entity.solidArea.height;

        // Step 2: find the columns/rows on each side of entity
        int leftColumn = leftX / Util.tileSize;
        int rightColumn = rightX / Util.tileSize;
        int topRow = topY / Util.tileSize;
        int bottomRow = bottomY / Util.tileSize;

        int tile1 = 0, tile2 = 0;

        // Step 3: Check for tiles nearest to player given the players direction
        switch (entity.direction) {
            case "up":
                topRow = (topY - entity.speed) / Util.tileSize;
                remainingDistance = (topRow * Util.tileSize - topY);

                tile1 = gp.tileM.mapInfo[leftColumn][topRow];
                tile2 = gp.tileM.mapInfo[rightColumn][topRow];
                break;

            case "down":
                bottomRow = (bottomY + entity.speed) / Util.tileSize;
                remainingDistance = (bottomRow * Util.tileSize - bottomY);

                tile1 = gp.tileM.mapInfo[leftColumn][bottomRow];
                tile2 = gp.tileM.mapInfo[rightColumn][bottomRow];
                break;

            case "left":
                leftColumn = (leftX - entity.speed) / Util.tileSize;
                remainingDistance = (leftColumn * Util.tileSize - leftX);

                tile1 = gp.tileM.mapInfo[leftColumn][topRow];
                tile2 = gp.tileM.mapInfo[leftColumn][bottomRow];
                break;

            case "right":
                rightColumn = (rightX + entity.speed) / Util.tileSize;
                remainingDistance = (rightColumn * Util.tileSize - rightX);

                tile1 = gp.tileM.mapInfo[rightColumn][topRow];
                tile2 = gp.tileM.mapInfo[rightColumn][bottomRow];
                break;
        }

        // Step 4: Change the player speed a bit so that it reaches the tile border every time
        if (remainingDistance == entity.speed) {
            return 4;
        } else if (remainingDistance > 1) {
            return remainingDistance - 1;
        }

        // Step 5: Disable entity movement if nearby tiles have collision
        entity.collisionOn = gp.tileM.tileSet[tile1].collision || gp.tileM.tileSet[tile2].collision || entity.collisionOn;
        return 0;
    }

    public int objectCollision(Entity entity) {
        int index = 999;
        nonTileCollision(entity);

        // Step 2: Searches for which object player is colliding with, enables collision and returns object index
        for (int i = 0; i < gp.objHandler.objects.length; i++) {
            if (gp.objHandler.objects[i] != null && objectCollider.intersects(gp.objHandler.objects[i].solidArea)) {
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
            if (gp.npcHandler.NPCs[i] != null && objectCollider.intersects(gp.npcHandler.NPCs[i].solidArea)) {
                entity.collisionOn = true;
                index = i;
            }
        }

        return index;
    }


    private void nonTileCollision(Entity entity) {
        // Step 1: Create a new solidArea, whose area enlarged in the player direction
        objectCollider.x = entity.worldX + entity.solidArea.x;
        objectCollider.y = entity.worldY + entity.solidArea.y;
        objectCollider.width = entity.solidArea.width;
        objectCollider.height = entity.solidArea.height;
        switch (entity.direction) {
            case "up":
                objectCollider.y -= entity.speed;
                break;

            case "down":
                objectCollider.y += entity.speed;
                break;

            case "left":
                objectCollider.x -= entity.speed;
                break;

            case "right":
                objectCollider.x += entity.speed;
                break;
        }
    }
}
