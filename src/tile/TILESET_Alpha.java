package tile;

import main.GamePanel;

public class TILESET_Alpha extends TILESET {

    public TILESET_Alpha(GamePanel gp) {
        path = "/tiles/tilesetAlpha/";
        initialiseTiles(10);
        loadBasicTiles(gp);
    }
}
