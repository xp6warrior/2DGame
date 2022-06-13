package handler;

import entity.Player;
import tile.Tile;
import tile.TileManager;

import javax.swing.*;
import java.awt.*;

public class GamePanel extends JPanel implements Runnable {

    // WINDOW SETTINGS
    final int originalTileSize = 16;

    // 64x64 TILE
    final int scale = 4;
    public final int tileSize = originalTileSize * scale;

    public final int columns = 16;
    public final int rows = 12;

    // 1152x768 WINDOW -> 4:3 ASPECT RATIO
    final int windowX = tileSize * columns;
    final int windowY = tileSize * rows;

    final int FPS = 60;

    // INSTANTIATE
    Thread gameThread;
    TileManager tileM = new TileManager(this);
    KeyHandler keyH = new KeyHandler();
    Player player = new Player(this, keyH);

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
        double timePerFrame = 1000000000d / FPS; // In nanoseconds
        double endFrameTime = System.nanoTime() + timePerFrame;

        while (gameThread != null) {
            // UPDATES INFORMATION
            update();

            // PAINTS ONTO SCREEN
            repaint();

            // MAINTAINS STABLE FPS
            try {
                double remainingTime = endFrameTime - System.nanoTime();
                remainingTime = remainingTime / 1000000; // Change to milliseconds

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
        // ALL UPDATE METHODS
        player.update();
    }
    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D g2d = (Graphics2D) g;

        // ALL PAINT METHODS
        tileM.draw(g2d);
        player.paint(g2d);

        g2d.dispose();
    }
}
