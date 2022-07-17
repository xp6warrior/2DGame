package main;

import object.*;

import java.awt.*;
import java.awt.image.BufferedImage;

public class UIManager {
    private final GamePanel gp;
    private final Font arial_45 = new Font("Arial", Font.PLAIN, 45);
    private final Font arial_80 = new Font("Arial", Font.PLAIN, 80);
    private final Font arial_20 = new Font("Arial", Font.PLAIN, 30);

    // Message
    private String message;
    private boolean messageOn = false;
    private int messageTimer = 0;
    private BufferedImage img;
    private int timerLength;

    // Display
    private final BufferedImage goldKey;
    private final BufferedImage goldDoor;
    private final BufferedImage bronzeKey;
    private final BufferedImage bronzeDoor;
    private final BufferedImage sign;
    private final BufferedImage stairs;
    private final BufferedImage titleScreenKey;

    public UIManager(GamePanel gp) {
        this.gp = gp;

        goldKey = new OBJ_GoldKey(100, 100).image;
        bronzeKey = new OBJ_BronzeKey(100, 100).image;
        goldDoor = new OBJ_GoldDoor(90, 90).image;
        bronzeDoor = new OBJ_BronzeDoor(90, 90).image;
        sign = new OBJ_Sign(100, 100).image;
        stairs = new OBJ_Stairs(90, 90).image;

        titleScreenKey = new OBJ_GoldKey(150, 150).image;
    }

    public void showMessage(OBJ object, int msg, int length) {
        switch (object.name) {
            case "GoldDoor": img = goldDoor; break;
            case "GoldKey": img = goldKey; break;
            case "BronzeDoor": img = bronzeDoor; break;
            case "BronzeKey": img = bronzeKey; break;
            case "Sign": img = sign; break;
            case "Stairs": img = stairs; break;
        }

        switch (msg) {
            case 1: message = object.message1; break;
            case 2: message = object.message2; break;
            case 3: message = object.message3; break;
        }

        timerLength = length;
        messageTimer = 0;
        messageOn = true;
    }

    private void displayHeldItem(Graphics2D g2d) {
        if (gp.player.goldKey) {
            g2d.drawImage(goldKey, Util.windowX - 100 - 10, Util.windowY - 100 - 10, null);
        }
        if (gp.player.bronzeKey) {
            g2d.drawImage(bronzeKey, Util.windowX - 100 - 10, Util.windowY - 100 - 10, null);
        }
    }
    private void displayMessage(Graphics2D g2d) {
        g2d.setFont(arial_45);
        g2d.setColor(Color.white);

        if (messageOn) {
            g2d.drawString(message, 130, Util.windowY - 50);
            g2d.drawImage(img, 65 - img.getWidth() / 2, Util.windowY - 65 - img.getHeight() / 2, null);

            messageTimer++;

            if (messageTimer > timerLength) {
                messageOn = false;
                messageTimer = 0;
            }
        }
    }
    private void pauseMenu(Graphics2D g2d) {
        g2d.setFont(arial_80);
        g2d.setColor(Color.yellow);

        String msg = "GAME PAUSED";

        int[] stringPos = Util.getStringCenterPosition(g2d, msg);
        g2d.drawString(msg, stringPos[0], stringPos[1] - 150);
    }
    private void debugMenu(Graphics2D g2d) {
        g2d.setFont(arial_20);
        g2d.setColor(Color.white);

        g2d.drawString("Draw Time: " + gp.drawTime, Util.windowX - 500, Util.windowY - 90);
        g2d.drawString("Secondary Object Timer: " + gp.player.interactMessageTimer, Util.windowX - 500, Util.windowY - 50);
        g2d.drawString("Primary Object Timer: " + messageTimer, Util.windowX - 500, Util.windowY - 10);
    }
    public void titleMenu(Graphics2D g2d) {
        String msg = "Roy's Island";
        String msg2 = "Press [ESC] to continue";
        g2d.setColor(Color.white);

        g2d.setFont(arial_80);
        int[] msgPos = Util.getStringCenterPosition(g2d, msg);
        g2d.drawString(msg, msgPos[0], msgPos[1] / 2);

        g2d.setFont(arial_20);
        int[] msg2Pos = Util.getStringCenterPosition(g2d, msg2);
        g2d.drawString(msg2, msg2Pos[0], msg2Pos[1] + 150);

        int x3 = Util.windowX / 2 - titleScreenKey.getWidth() / 2;
        int y3 = Util.windowY / 2 - titleScreenKey.getHeight() / 2;
        g2d.drawImage(titleScreenKey, x3, y3, null);
    }

    public void draw(Graphics2D g2d) {
        displayHeldItem(g2d);
        displayMessage(g2d);

        if (gp.keyH.debug) {
            debugMenu(g2d);
        }
        if (gp.currentGameState == gp.titleState) {
            titleMenu(g2d);
        }
        if (gp.currentGameState == gp.pauseState) {
            pauseMenu(g2d);
        }
    }
}
