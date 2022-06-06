package entity;

import handler.GamePanel;
import handler.KeyHandler;

import java.awt.*;

public class Player extends Entity {

    KeyHandler keyH;
    GamePanel gp;

    public Player(KeyHandler keyH, GamePanel gp) {
        this.keyH = keyH;
        this.gp = gp;

        setDefaultValues();
    }
    private void setDefaultValues() {
        x = 100;
        y = 100;
        speed = 5;
        direction = "down";
    }

    public void update() {
        if (keyH.up) {
            y -= speed;
            direction = "up";
        } else if (keyH.down) {
            y += speed;
            direction = "down";
        } else if (keyH.left) {
            x -= speed;
            direction = "left";
        } else if (keyH.right) {
            x += speed;
            direction = "right";
        }
    }

    public void paint(Graphics2D g2d) {
        switch (direction) {
            case "up": g2d.setColor(Color.red); break;
            case "down": g2d.setColor(Color.blue); break;
            case "left": g2d.setColor(Color.yellow); break;
            case "right": g2d.setColor(Color.green); break;
        }

        g2d.fillRect(x, y, gp.tileSize, gp.tileSize);
    }
}
