package handler;

import main.GamePanel;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class KeyHandler implements KeyListener {
    public boolean up, down, left, right = false;
    public boolean debug = false;
    public boolean interact = false;
    private final GamePanel gp;

    public KeyHandler(GamePanel gp) {
        this.gp = gp;
    }

    @Override
    public void keyTyped(KeyEvent e) {

    }

    @Override
    public void keyPressed(KeyEvent e) {
        switch (e.getKeyCode()) {
            case KeyEvent.VK_W: up = true; break;
            case KeyEvent.VK_A: left = true; break;
            case KeyEvent.VK_S: down = true; break;
            case KeyEvent.VK_D: right = true; break;
            case KeyEvent.VK_F1: debug = !debug; break;
            case KeyEvent.VK_ESCAPE:
                if (gp.getGameState() + 1 == 3) {
                    gp.setGameState(0);
                } else {
                    gp.setGameState(gp.getGameState()+1);
                }
                break;
            case KeyEvent.VK_SPACE: interact = true; break;
        }
    }

    @Override
    public void keyReleased(KeyEvent e) {
        switch (e.getKeyCode()) {
            case KeyEvent.VK_W: up = false; break;
            case KeyEvent.VK_A: left = false; break;
            case KeyEvent.VK_S: down = false; break;
            case KeyEvent.VK_D: right = false; break;
        }
    }
}
