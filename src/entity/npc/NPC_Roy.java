package entity.npc;

import main.GamePanel;

public class NPC_Roy extends NPC {

    public NPC_Roy(GamePanel gp) {
        this.collisionH = gp.collisionH;

        name = "Roy";
        message = "I luv fishing eh?";

        setDefaultValues();
        getPlayerImage("npcs/roy");

    }
}
