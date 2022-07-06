package handler;

import entity.Entity;
import main.GamePanel;

public class CollisionHandler {
    private final GamePanel gp;

    public CollisionHandler(GamePanel gp) {
        this.gp = gp;
    }

    public void tileCollision(Entity entity) {
        // Step 1: Find coordinates of each edge of entity
        int topY = entity.worldY + entity.tileSolidAreaY;
        int bottomY = entity.worldY + entity.tileSolidAreaY + entity.objectSolidArea.height;
        int leftX = entity.worldX + entity.tileSolidAreaX;
        int rightX = entity.worldX + entity.tileSolidAreaX + entity.objectSolidArea.width;

        // Step 2: find the columns/rows on each side of entity
        int leftColumn = leftX / gp.tileSize;
        int rightColumn = rightX / gp.tileSize;
        int topRow = topY / gp.tileSize;
        int bottomRow = bottomY / gp.tileSize;

        int tile1 = 0, tile2 = 0;

        // Step 3: Check for tiles nearest to player given the players direction
        switch (entity.direction) {
            case "up":
                topRow = (topY - entity.speed) / gp.tileSize;

                tile1 = gp.tileM.mapInfo[leftColumn][topRow];
                tile2 = gp.tileM.mapInfo[rightColumn][topRow];
                break;

            case "down":
                bottomRow = (bottomY + entity.speed) / gp.tileSize;

                tile1 = gp.tileM.mapInfo[leftColumn][bottomRow];
                tile2 = gp.tileM.mapInfo[rightColumn][bottomRow];
                break;

            case "left":
                leftColumn = (leftX - entity.speed) / gp.tileSize;

                tile1 = gp.tileM.mapInfo[leftColumn][topRow];
                tile2 = gp.tileM.mapInfo[leftColumn][bottomRow];
                break;

            case "right":
                rightColumn = (rightX + entity.speed) / gp.tileSize;

                tile1 = gp.tileM.mapInfo[rightColumn][topRow];
                tile2 = gp.tileM.mapInfo[rightColumn][bottomRow];
                break;
        }

        // Step 4: Disable entity movement if nearby tiles have collision
        entity.collisionOn = gp.tileM.tileSet[tile1].collision || gp.tileM.tileSet[tile2].collision;
    }

    public int objectCollision(Entity entity) {
        int index = 999;

        entity.objectSolidArea.x = entity.objectSolidArea.x + entity.worldX;
        entity.objectSolidArea.y = entity.objectSolidArea.y + entity.worldY;

        switch (entity.direction) {
            case "up":
                entity.objectSolidArea.y -= entity.speed;
                break;

            case "down":
                entity.objectSolidArea.y += entity.speed;
                break;

            case "left":
                entity.objectSolidArea.x -= entity.speed;
                break;

            case "right":
                entity.objectSolidArea.x += entity.speed;
                break;
        }

        for (int i = 0; i < gp.objHandler.objects.length; i++) {
            if (gp.objHandler.objects[i] != null && entity.objectSolidArea.intersects(gp.objHandler.objects[i].solidArea)) {

                if (gp.objHandler.objects[i].collision) {
                    entity.collisionOn = true;
                }

                index = i;
            }
        }

        entity.objectSolidArea.x = entity.tileSolidAreaX;
        entity.objectSolidArea.y = entity.tileSolidAreaY;

        return index;
    }
}
