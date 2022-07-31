package entity;

import main.GamePanel;

public class NPC_Roy extends Entity {

    public NPC_Roy(GamePanel gp) {
        this.collisionH = gp.collisionH;

        setDefaultValues();
        direction = "up";
        getPlayerImage("roy");

    }
}
