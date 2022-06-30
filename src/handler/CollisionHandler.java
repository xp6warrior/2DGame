package handler;

import entity.Entity;
import main.GamePanel;

public class CollisionHandler {
    GamePanel gp;

    public CollisionHandler(GamePanel gp) {
        this.gp = gp;
    }

    public void tileCollision(Entity entity) {

        // Step 1: Find coordinates of each edge of entity
        int topY = entity.worldY + entity.defaultSolidAreaY;
        int bottomY = entity.worldY + entity.defaultSolidAreaY + entity.solidArea.height;
        int leftX = entity.worldX + entity.defaultSolidAreaX;
        int rightX = entity.worldX + entity.defaultSolidAreaX + entity.solidArea.width;

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

                tile1 = gp.tileM.mapTileArray[leftColumn][topRow];
                tile2 = gp.tileM.mapTileArray[rightColumn][topRow];
                break;

            case "down":
                bottomRow = (bottomY + entity.speed) / gp.tileSize;

                tile1 = gp.tileM.mapTileArray[leftColumn][bottomRow];
                tile2 = gp.tileM.mapTileArray[rightColumn][bottomRow];
                break;

            case "left":
                leftColumn = (leftX - entity.speed) / gp.tileSize;

                tile1 = gp.tileM.mapTileArray[leftColumn][topRow];
                tile2 = gp.tileM.mapTileArray[leftColumn][bottomRow];
                break;

            case "right":
                rightColumn = (rightX + entity.speed) / gp.tileSize;

                tile1 = gp.tileM.mapTileArray[rightColumn][topRow];
                tile2 = gp.tileM.mapTileArray[rightColumn][bottomRow];
                break;
        }

        // Step 4: Disable entity movement if nearby tiles have collision
        entity.collisionOn = gp.tileM.tilesetAlpha[tile1].collision || gp.tileM.tilesetAlpha[tile2].collision;
    }

    public int objectCollision(Entity entity) {
        int index = 999;

        entity.solidArea.x = entity.solidArea.x + entity.worldX;
        entity.solidArea.y = entity.solidArea.y + entity.worldY;

        switch (entity.direction) {
            case "up":
                entity.solidArea.y -= entity.speed;
                break;

            case "down":
                entity.solidArea.y += entity.speed;
                break;

            case "left":
                entity.solidArea.x -= entity.speed;
                break;

            case "right":
                entity.solidArea.x += entity.speed;
                break;
        }

        for (int i = 0; i < gp.objHandler.objects.length; i++) {
            if (gp.objHandler.objects[i] != null && entity.solidArea.intersects(gp.objHandler.objects[i].solidArea)) {

                if (gp.objHandler.objects[i].collision) {
                    entity.collisionOn = true;
                }

                index = i;
            }
        }

        entity.solidArea.x = entity.defaultSolidAreaX;
        entity.solidArea.y = entity.defaultSolidAreaY;

        return index;
    }
}
