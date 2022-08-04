package handler;

import main.GamePanel;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class KeyHandler implements KeyListener {
    private final GamePanel gp;

    public boolean up, down, left, right = false;
    public boolean interact = false;

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
            case KeyEvent.VK_F1: gp.ui.displayDebugMenu = !gp.ui.displayDebugMenu; break;

            case KeyEvent.VK_ESCAPE:
                int gameState = gp.getGameState() + 1;
                if (gameState == gp.pauseState + 1) {
                    gameState = gp.playState;
                }
                gp.setGameState(gameState);
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
