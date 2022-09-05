package object;

import handler.MapHandler;
import main.GamePanel;
import main.Util;

import java.awt.*;
import java.util.Arrays;

public class ObjectManager {
    public final OBJ[] objects = new OBJ[7];

    private final GamePanel gp;
    public ObjectManager(GamePanel gp) {
        this.gp = gp;
    }

    public void reloadAssets(MapHandler mapH) {
        Arrays.fill(objects, null);

        if (mapH == MapHandler.mapIsland) {
            OBJ bronzeKey = new OBJ_BronzeKey();
            bronzeKey.worldX = 13 * Util.tileSize;
            bronzeKey.worldY = 11 * Util.tileSize;
            bronzeKey.solidArea.x = bronzeKey.worldX;
            bronzeKey.solidArea.y = bronzeKey.worldY;
            objects[0] = bronzeKey;

            OBJ goldKey = new OBJ_GoldKey();
            goldKey.worldX = 16 * Util.tileSize;
            goldKey.worldY = 28 * Util.tileSize;
            goldKey.solidArea.x = goldKey.worldX;
            goldKey.solidArea.y = goldKey.worldY;
            objects[1] = goldKey;

            OBJ goldDoor = new OBJ_GoldDoor("");
            goldDoor.worldX = 16 * Util.tileSize;
            goldDoor.worldY = 11 * Util.tileSize;
            goldDoor.solidArea.x = goldDoor.worldX;
            goldDoor.solidArea.y = goldDoor.worldY;
            objects[2] = goldDoor;

            OBJ bronzeDoor = new OBJ_BronzeDoor("");
            bronzeDoor.worldX = 32 * Util.tileSize;
            bronzeDoor.worldY = 29 * Util.tileSize;
            bronzeDoor.solidArea.x = bronzeDoor.worldX;
            bronzeDoor.solidArea.y = bronzeDoor.worldY;
            objects[3] = bronzeDoor;

            OBJ sign = new OBJ_Sign("");
            sign.worldX = 20 * Util.tileSize;
            sign.worldY = 12 * Util.tileSize;
            sign.solidArea.x = sign.worldX;
            sign.solidArea.y = sign.worldY;
            sign.message1 = OBJ_Sign.writings[0];
            objects[4] = sign;

            OBJ_Stairs stairs = new OBJ_Stairs();
            stairs.worldX = 36 * Util.tileSize;
            stairs.worldY = 29 * Util.tileSize;
            stairs.solidArea.x = stairs.worldX;
            stairs.solidArea.y = stairs.worldY;
            objects[5] = stairs;

            OBJ sign2 = new OBJ_Sign("");
            sign2.worldX = 31 * Util.tileSize;
            sign2.worldY = 27 * Util.tileSize;
            sign2.solidArea.x = sign2.worldX;
            sign2.solidArea.y = sign2.worldY;
            sign2.message1 = OBJ_Sign.writings[1];
            objects[6] = sign2;

        }

        if (mapH == MapHandler.mapDungeon) {
            OBJ sign = new OBJ_Sign("D");
            sign.worldX = 23 * Util.tileSize;
            sign.worldY = 9 * Util.tileSize;
            sign.solidArea.x = sign.worldX;
            sign.solidArea.y = sign.worldY;
            sign.message1 = OBJ_Sign.writings[2];
            objects[0] = sign;

            OBJ sign2 = new OBJ_Sign("D");
            sign2.worldX = 27 * Util.tileSize;
            sign2.worldY = 9 * Util.tileSize;
            sign2.solidArea.x = sign2.worldX;
            sign2.solidArea.y = sign2.worldY;
            sign2.message1 = OBJ_Sign.writings[3];
            objects[1] = sign2;

            OBJ bronzeDoor = new OBJ_BronzeDoor("D");
            bronzeDoor.worldX = 33 * Util.tileSize;
            bronzeDoor.worldY = 10 * Util.tileSize;
            bronzeDoor.solidArea.x = bronzeDoor.worldX;
            bronzeDoor.solidArea.y = bronzeDoor.worldY;
            objects[2] = bronzeDoor;

            OBJ bookB = new OBJ_Sign("B");
            bookB.worldX = 39 * Util.tileSize;
            bookB.worldY = 27 * Util.tileSize;
            bookB.solidArea.x = bookB.worldX;
            bookB.solidArea.y = bookB.worldY;
            bookB.message1 = OBJ_Sign.writings[2];
            objects[3] = bookB;

            OBJ bookP = new OBJ_Sign("P");
            bookP.worldX = 22 * Util.tileSize;
            bookP.worldY = 26 * Util.tileSize;
            bookP.solidArea.x = bookP.worldX;
            bookP.solidArea.y = bookP.worldY;
            bookP.message1 = OBJ_Sign.writings[2];
            objects[4] = bookP;

            OBJ bookY = new OBJ_Sign("Y");
            bookY.worldX = 28 * Util.tileSize;
            bookY.worldY = 39 * Util.tileSize;
            bookY.solidArea.x = bookY.worldX;
            bookY.solidArea.y = bookY.worldY;
            bookY.message1 = OBJ_Sign.writings[2];
            objects[0] = bookY;
        }
    }

    public void draw(Graphics2D g2d) {
        for (OBJ object : objects) {

            if (object != null) {
                int screenX = object.worldX - gp.player.worldX + gp.player.screenX;
                int screenY = object.worldY - gp.player.worldY + gp.player.screenY;

                if (screenX > -Util.tileSize && screenX < Util.windowX
                        && screenY > -Util.tileSize && screenY < Util.windowY) {

                    g2d.drawImage(object.image, screenX, screenY,null);
                }
            }

        }
    }
}
