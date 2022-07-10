package object;

import handler.MapHandler;
import main.GamePanel;

import java.awt.*;
import java.util.Arrays;

public class ObjectManager {
    public final OBJ[] objects = new OBJ[6];

    private final GamePanel gp;

    public ObjectManager(GamePanel gp) {
        this.gp = gp;
    }

    public void reloadAssets(MapHandler mapH) {
        Arrays.fill(objects, null);

        if (mapH == MapHandler.mapIsland) {
            OBJ bronzeKey = new OBJ_BronzeKey(gp);
            bronzeKey.worldX = 13 * gp.tileSize;
            bronzeKey.worldY = 11 * gp.tileSize;
            bronzeKey.solidArea.x = bronzeKey.worldX;
            bronzeKey.solidArea.y = bronzeKey.worldY;
            objects[0] = bronzeKey;

            OBJ goldKey = new OBJ_GoldKey(gp);
            goldKey.worldX = 16 * gp.tileSize;
            goldKey.worldY = 28 * gp.tileSize;
            goldKey.solidArea.x = goldKey.worldX;
            goldKey.solidArea.y = goldKey.worldY;
            objects[1] = goldKey;

            OBJ goldDoor = new OBJ_GoldDoor(gp);
            goldDoor.worldX = 16 * gp.tileSize;
            goldDoor.worldY = 11 * gp.tileSize;
            goldDoor.solidArea.x = goldDoor.worldX;
            goldDoor.solidArea.y = goldDoor.worldY;
            objects[2] = goldDoor;

            OBJ bronzeDoor = new OBJ_BronzeDoor(gp);
            bronzeDoor.worldX = 32 * gp.tileSize;
            bronzeDoor.worldY = 29 * gp.tileSize;
            bronzeDoor.solidArea.x = bronzeDoor.worldX;
            bronzeDoor.solidArea.y = bronzeDoor.worldY;
            objects[3] = bronzeDoor;

            OBJ sign = new OBJ_Sign(gp);
            sign.worldX = 20 * gp.tileSize;
            sign.worldY = 12 * gp.tileSize;
            sign.solidArea.x = sign.worldX;
            sign.solidArea.y = sign.worldY;
            objects[4] = sign;

            OBJ_Stairs stairs = new OBJ_Stairs(gp);
            stairs.worldX = 36 * gp.tileSize;
            stairs.worldY = 29 * gp.tileSize;
            stairs.solidArea.x = stairs.worldX;
            stairs.solidArea.y = stairs.worldY;
            objects[5] = stairs;

        }
    }

    public void draw(Graphics2D g2d) {
        for (OBJ object : objects) {

            if (object != null) {
                int screenX = object.worldX - gp.player.worldX + gp.player.screenX;
                int screenY = object.worldY - gp.player.worldY + gp.player.screenY;

                if (screenX > -gp.tileSize && screenX < gp.windowX
                        && screenY > -gp.tileSize && screenY < gp.windowY) {

                    g2d.drawImage(object.image, screenX, screenY, gp.tileSize, gp.tileSize, null);
                }
            }

        }
    }
}
