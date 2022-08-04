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
        // 1: Find columns/rows on each side of entity
        int tile1 = 0, tile2 = 0;
        int leftColumn = entity.solidArea.x / Util.tileSize;
        int rightColumn = (entity.solidArea.x + entity.solidArea.width) / Util.tileSize;
        int topRow = entity.solidArea.y / Util.tileSize;
        int bottomRow = (entity.solidArea.y + entity.solidArea.height) / Util.tileSize;

        // 2: Check for tiles nearest to player
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

        // 3: Disable movement if nearby tiles have collision
        entity.collisionOn = gp.tileM.tileSet[tile1].collision || gp.tileM.tileSet[tile2].collision || entity.collisionOn;
    }

    public int objectCollision(Entity entity) {
        int index = 999;
        // 1. Extends collider in entity direction
        nonTileCollision(entity);

        // 2. Searches for colliding objects, enables or disabled collision
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
        // 1. Extends collider in entity direction
        nonTileCollision(entity);

        // 2. Searches for colliding npcs, enables or disabled collision
        for (int i = 0; i < gp.npcHandler.NPCs.length; i++) {
            if (gp.npcHandler.NPCs[i] != null && newEntityCollider.intersects(gp.npcHandler.NPCs[i].solidArea)) {
                entity.collisionOn = true;
                index = i;
            }
        }
        return index;
    }

    public void npcPlayerCollision(Entity entity) {
        // 1. Extends collider in entity direction
        nonTileCollision(entity);

        // 2. Searches for colliding player, enables or disabled collision
        if (newEntityCollider.intersects(gp.player.solidArea)) {
            entity.collisionOn = true;
        }
    }


    private void nonTileCollision(Entity entity) {
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
