package tile;

import main.GamePanel;

public class TILESET_Frost extends TILESET {

    public TILESET_Frost(GamePanel gp) {
        path = "/tiles/tilesetFrost/";
        initialiseTiles(10);
        loadBasicTiles(gp);
    }
}
