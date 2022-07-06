package object;

import handler.MapHandler;
import main.GamePanel;

import java.awt.*;

public class ObjectManager {
    public final OBJ[] objects = new OBJ[4];

    private final GamePanel gp;
    private final MapHandler mapH;

    public ObjectManager(GamePanel gp) {
        this.gp = gp;
        mapH = gp.mapH;

        loadAssets();
    }

    private void loadAssets() {
        if (mapH == MapHandler.mapIsland) {
            OBJ bronzeKey = new OBJ_BronzeKey();
            bronzeKey.worldX = 11 * gp.tileSize;
            bronzeKey.worldY = 11 * gp.tileSize;
            bronzeKey.solidArea = new Rectangle(bronzeKey.worldX, bronzeKey.worldY, gp.tileSize, gp.tileSize);
            objects[0] = bronzeKey;

            OBJ goldKey = new OBJ_GoldKey();
            goldKey.worldX = 14 * gp.tileSize;
            goldKey.worldY = 28 * gp.tileSize;
            goldKey.solidArea = new Rectangle(goldKey.worldX, goldKey.worldY, gp.tileSize, gp.tileSize);
            objects[1] = goldKey;

            OBJ goldDoor = new OBJ_GoldDoor();
            goldDoor.worldX = 14 * gp.tileSize;
            goldDoor.worldY = 11 * gp.tileSize;
            goldDoor.solidArea = new Rectangle(goldDoor.worldX, goldDoor.worldY, gp.tileSize, gp.tileSize);
            objects[2] = goldDoor;

            OBJ bronzeDoor = new OBJ_BronzeDoor();
            bronzeDoor.worldX = 30 * gp.tileSize;
            bronzeDoor.worldY = 29 * gp.tileSize;
            bronzeDoor.solidArea = new Rectangle(bronzeDoor.worldX, bronzeDoor.worldY, gp.tileSize, gp.tileSize);
            objects[3] = bronzeDoor;
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
