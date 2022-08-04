package entity.npc;

import main.GamePanel;

public class NPC_Roy extends NPC {
    public NPC_Roy(GamePanel gp) {
        super("roy");

        this.collisionH = gp.collisionH;
        name = "roy";
        message1 = "Roy: \"I luv fishing eh?\"";
        messageTimerLength = 80;
    }
}
