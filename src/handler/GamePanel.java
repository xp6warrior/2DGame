package handler;

import entity.Player;

import javax.swing.*;
import java.awt.*;

public class GamePanel extends JPanel implements Runnable {

    // WINDOW SETTINGS
    final int originalTileSize = 16;

    // 64x64 TILE
    final int scale = 4;
    public final int tileSize = originalTileSize * scale;

    final int columns = 16;
    final int rows = 12;

    // 1152x768 WINDOW -> 4:3 ASPECT RATIO
    final int windowX = tileSize * columns;
    final int windowY = tileSize * rows;

    int FPS = 30;

    // INSTANTIATE
    Thread gameThread;
    KeyHandler keyH = new KeyHandler();
    Player player = new Player(keyH, this);


    public GamePanel() {
        this.setPreferredSize(new Dimension(windowX, windowY));
        this.setBackground(Color.black);
        this.addKeyListener(keyH);
        this.setFocusable(true);

        startGameThread();
    }
    private void startGameThread() {
        gameThread = new Thread(this);
        gameThread.start();
    }


    // GAME LOOP
    @Override
    public void run() {
        double timePerFrame = 1000000000d / FPS;
        double endFrameTime = System.nanoTime() + timePerFrame;

        while (gameThread != null) {

            // UPDATES INFORMATION
            update();

            // PAINTS ONTO SCREEN
            repaint();

            // MAINTAINS STABLE FPS
            try {
                double remainingTime = endFrameTime - System.nanoTime();
                remainingTime = remainingTime / 1000000;

                if (remainingTime < 0) {
                    remainingTime = 0;
                }

                Thread.sleep((long) remainingTime);

                endFrameTime += timePerFrame;

            } catch (InterruptedException e) {
                e.printStackTrace();
            }

        }
    }
    void update() {
        player.update();

    }
    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D g2d = (Graphics2D) g;

        player.paint(g2d);

        g2d.dispose();
    }
}
