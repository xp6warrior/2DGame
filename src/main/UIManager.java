package main;

import entity.npc.NPC;
import object.OBJ;

import java.awt.*;
import java.awt.image.BufferedImage;

public class UIManager {
    private final GamePanel gp;
    private final Font arial_45 = new Font("Arial", Font.PLAIN, 45);
    private final Font arial_80 = new Font("Arial", Font.PLAIN, 80);
    private final Font arial_20 = new Font("Arial", Font.PLAIN, 30);

    // Display
    private final BufferedImage goldKey;
    private final BufferedImage goldDoor;
    private final BufferedImage bronzeKey;
    private final BufferedImage bronzeDoor;
    private final BufferedImage sign;
    private final BufferedImage stairs;

    private final BufferedImage roy;

    private final BufferedImage titleScreenKey;

    public UIManager(GamePanel gp) {
        this.gp = gp;

        goldKey = OBJ.getObjectIcon("goldKey", 100, 100);
        bronzeKey = OBJ.getObjectIcon("bronzeKey", 100, 100);
        goldDoor = OBJ.getObjectIcon("goldDoor", 90, 90);
        bronzeDoor = OBJ.getObjectIcon("bronzeDoor", 90, 90);
        sign = OBJ.getObjectIcon("sign", 100, 100);
        stairs = OBJ.getObjectIcon("stairs", 90, 90);

        roy = NPC.getNPCIcon("roy", 90, 90);

        titleScreenKey = OBJ.getObjectIcon("goldKey", 150, 150);
    }


    private void displayHeldItem(Graphics2D g2d) {
        if (gp.player.goldKey) {
            g2d.drawImage(goldKey, Util.windowX - 100 - 10, Util.windowY - 100 - 10, null);
        }
        if (gp.player.bronzeKey) {
            g2d.drawImage(bronzeKey, Util.windowX - 100 - 10, Util.windowY - 100 - 10, null);
        }
    }

    private String message;
    private BufferedImage img;
    public int timer;
    private int timerLength = 0;
    public boolean displayTouchingObject = false;
    public void setTouching(OBJ object, int msgIndex) {
        switch (object.name) {
            case "goldDoor": img = goldDoor; break;
            case "goldKey": img = goldKey; break;
            case "bronzeDoor": img = bronzeDoor; break;
            case "bronzeKey": img = bronzeKey; break;
            case "sign": img = sign; break;
            case "stairs": img = stairs; break;
        }

        switch (msgIndex) {
            case 1: message = object.message1; timerLength = object.messageTimerLength; timer = 0; break;
            case 2: message = object.message2; timer = -10; break;
            case 3: message = object.message3; timer = -10; break;
        }

    }
    public void setTouching(NPC npc, int msgIndex) {
        switch (npc.name) {
            case "roy": img = roy; break;
        }

        switch (msgIndex) {
            case 1: message = npc.message1; timerLength = npc.messageTimerLength; timer = 0; break;
            case 2: message = npc.message2; timer = -10; break;
        }

    }
    private void touchingObject(Graphics2D g2d) {
        timer++;

        if (timer <= timerLength) {
            g2d.setFont(arial_45);
            g2d.setColor(Color.white);

            g2d.drawString(message, 130, Util.windowY - 50);
            g2d.drawImage(img, 65 - img.getWidth() / 2, Util.windowY - 65 - img.getHeight() / 2, null);
        } else {
            timer = 0;
            timerLength = 0;
            displayTouchingObject = false;
        }

    }

    public boolean displayPauseMenu = false;
    private void pauseMenu(Graphics2D g2d) {
        g2d.setFont(arial_80);
        g2d.setColor(Color.yellow);

        String msg = "GAME PAUSED";

        int[] stringPos = Util.getStringCenterPosition(g2d, msg);
        g2d.drawString(msg, stringPos[0], stringPos[1] - 150);
    }

    public boolean displayDebugMenu = false;
    private void debugMenu(Graphics2D g2d) {
        g2d.setFont(arial_20);
        g2d.setColor(Color.white);

        g2d.drawString("Draw Time: " + gp.drawTime, Util.windowX - 500, Util.windowY - 50);
        g2d.drawString("Object Timer: " + timer, Util.windowX - 500, Util.windowY - 10);
    }

    public boolean displayTitleMenu = true;
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

        if (displayTouchingObject) {
            touchingObject(g2d);
        }
        if (displayDebugMenu) {
            debugMenu(g2d);
        }
        if (displayTitleMenu) {
            titleMenu(g2d);
        }
        if (displayPauseMenu) {
            pauseMenu(g2d);
        }
    }
}
