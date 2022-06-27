package handler;

import entity.Entity;

public class CollisionHandler {
    GamePanel gp;

    public CollisionHandler(GamePanel gp) {
        this.gp = gp;
    }

    public void checkCollision(Entity entity) {
        int topY = entity.worldY + entity.solidArea.y;
        int bottomY = entity.worldY + entity.solidArea.y + entity.solidArea.height;
        int leftX = entity.worldX + entity.solidArea.x;
        int rightX = entity.worldX + entity.solidArea.x + entity.solidArea.width;

        int leftColumn = leftX / gp.tileSize;
        int rightColumn = rightX / gp.tileSize;
        int topRow = topY / gp.tileSize;
        int bottomRow = bottomY / gp.tileSize;

        int tile1 = 0, tile2 = 0;

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

        entity.collisionOn = gp.tileM.tilesetAlpha[tile1].collision || gp.tileM.tilesetAlpha[tile2].collision;
    }
}
