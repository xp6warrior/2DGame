package main;


import object.OBJ_BronzeKey;
import object.OBJ_GoldKey;

import java.awt.*;

public class UI {
    private final GamePanel gp;
    private final Font font = new Font("Arial", Font.PLAIN, 45);

    private String message;
    private boolean messageOn = false;
    private int messageTimer = 0;

    public UI(GamePanel gp) {
        this.gp = gp;
    }

    public void showMessage(String msg) {
        message = msg;
        messageOn = true;
    }

    public void draw(Graphics2D g2d) {
        g2d.setFont(font);
        g2d.setColor(Color.white);

        if (gp.player.goldKey) {
            g2d.drawImage(new OBJ_GoldKey().image, 10, gp.windowY - 100 - 10, 100, 100, null);
        }
        if (gp.player.bronzeKey) {
            g2d.drawImage(new OBJ_BronzeKey().image, 10, gp.windowY - 100 - 10, 100, 100, null);
        }

        if (messageOn) {
            g2d.drawString(message, 130, gp.windowY - 50);

            messageTimer++;

            if (messageTimer > 60) {
                messageTimer = 0;
                messageOn = false;
            }
        }
    }
}
