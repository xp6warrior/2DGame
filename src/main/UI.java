package main;

import object.OBJ_BronzeKey;
import object.OBJ_GoldKey;

import java.awt.*;
import java.awt.image.BufferedImage;

public class UI {
    private final GamePanel gp;
    private final Font arial_45 = new Font("Arial", Font.PLAIN, 45);
    private final Font arial_80 = new Font("Arial", Font.PLAIN, 80);
    private final Font arial_20 = new Font("Arial", Font.PLAIN, 30);

    // Message
    private String message;
    private boolean messageOn = false;
    private int messageTimer = 0;

    // Display
    private BufferedImage goldKey;
    private BufferedImage bronzeKey;

    public UI(GamePanel gp) {
        this.gp = gp;
    }

    public void showMessage(String msg) {
        message = msg;
        messageTimer = 0;
        messageOn = true;

        goldKey = new OBJ_GoldKey(gp, true).image;
        bronzeKey = new OBJ_BronzeKey(gp, true).image;
    }

    private void displayItem(Graphics2D g2d) {
        if (gp.player.goldKey) {
            g2d.drawImage(goldKey, 10, gp.windowY - 100 - 10, null);
        }
        if (gp.player.bronzeKey) {
            g2d.drawImage(bronzeKey, 10, gp.windowY - 100 - 10, null);
        }

    }
    private void displayMessage(Graphics2D g2d) {
        g2d.setFont(arial_45);
        g2d.setColor(Color.white);

        if (messageOn) {
            g2d.drawString(message, 130, gp.windowY - 50);

            messageTimer++;

            if (messageTimer > 60) {
                messageTimer = 0;
                messageOn = false;
            }
        }

    }
    private void debugMenu(Graphics2D g2d) {
        g2d.setFont(arial_20);
        g2d.setColor(Color.white);

        if (gp.keyH.debug) {
            g2d.drawString("Draw Time: " + gp.drawTime, gp.windowX - 360, gp.windowY - 50);
        }

    }
    public void pauseMenu(Graphics2D g2d) {
        g2d.setFont(arial_80);
        g2d.setColor(Color.yellow);

        String msg = "GAME PAUSED";

        int stringLength = (int) g2d.getFontMetrics().getStringBounds(msg, g2d).getWidth();
        int stringHeight = (int) g2d.getFontMetrics().getStringBounds(msg, g2d).getHeight();
        int x = gp.windowX / 2 - stringLength / 2;
        int y = gp.windowX / 2 - stringHeight * 2;

        g2d.drawString(msg, x, y);
    }

    public void draw(Graphics2D g2d) {
        displayItem(g2d);
        debugMenu(g2d);
        displayMessage(g2d);

        if (gp.currentGameState == gp.pauseState) {
            pauseMenu(g2d);
        }

    }
}
